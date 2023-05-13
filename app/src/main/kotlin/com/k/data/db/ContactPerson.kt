package com.k.data.db

import android.content.Context
import androidx.room.*
import com.k.data.converter.DateConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "ContactPerson")
data class ContactPerson(
    //主键 联系人id
    @PrimaryKey
        @ColumnInfo(name = "uid") val uid: Long,
    //联系人性别 0-男 1-女

    @ColumnInfo(name = "UserId") val UserId :Long,
    //标记一台设备上某个用户的唯一回话，用于更新避免插入多条数据

    @ColumnInfo(name = "sex") val sex: Int,
    //联系人生日日期
    @ColumnInfo(name = "birthday_date") val birthday_date: LocalDateTime,
    //联系人姓名
    @ColumnInfo(name = "user_name") val user_name: String,
    //关系
    @ColumnInfo(name = "relation") val relation: Int,
)

//    @ColumnInfo(name = "user_avatar") val user_avatar
@Dao
interface ContactPersonDao {
    @Query("SELECT * FROM ContactPerson")
    suspend fun getAll(): List<ContactPerson>?

    @Query("SELECT * FROM ContactPerson WHERE UserId=:UserId")
    suspend fun getOneByUserId(UserId: Long): List<ContactPerson>?

    @Query("SELECT * FROM ContactPerson WHERE uid=:uid")
    suspend fun getOne(uid: Long): ContactPerson?

    @Insert
    suspend fun insertOne(ContactPerson: ContactPerson)

    @Update
    suspend fun updateOne(ContactPerson: ContactPerson)
}

@TypeConverters(DateConverter::class)
@Database(entities = [ContactPerson::class], version = 1, exportSchema = false)
abstract class ContactPersonDatabase : RoomDatabase() {
    abstract fun contactPersonDao(): ContactPersonDao
}

object ContactPersonDbSingleton {
    private var db = Optional.empty<ContactPersonDatabase>()

    //    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    suspend operator fun invoke(ctx: Context) =
        withContext(Dispatchers.IO) {
            if (db.isEmpty)
                synchronized(this) {
                    db =
                        Optional.of(
                            Room.databaseBuilder(
                                ctx,
                                ContactPersonDatabase::class.java,
                                "contact_person_database"
                            )
                                .build()
                        )
                }

            val dao = db.get().contactPersonDao()
            /*if (dao.getAll() == null)
                dao.insertOne(
                    ContactPerson(
                        uid = 0,
                        user_name = null,
                        birthday_date = null,
                        relation = null,
                        sex = null
                    )
                )*/
            db.get()
        }
}

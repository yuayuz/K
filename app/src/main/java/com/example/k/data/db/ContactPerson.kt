package com.example.k.data.db

import android.content.Context
import androidx.room.*
import com.example.k.data.converter.DateConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

@Entity(tableName = "ContactPerson")
data class ContactPerson(
    @PrimaryKey
    @ColumnInfo(name = "uid") val uid: Long,
    @ColumnInfo(name = "sex") val sex: Int,
    @ColumnInfo(name = "birthday_date") val birthday_date: Date,
    @ColumnInfo(name = "user_name") val user_name: String,
    @ColumnInfo(name = "relation") val relation: Int,
)

//    @ColumnInfo(name = "user_avatar") val user_avatar
@Dao
interface ContactPersonDao {
    @Query("SELECT * FROM ContactPerson")
    suspend fun getAll(): ContactPerson?

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

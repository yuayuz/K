package com.example.k.data.db

import android.content.Context
import androidx.room.*
import com.example.k.data.converter.DateConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

@Entity(tableName = "Message")
data class Message(
    @PrimaryKey @ColumnInfo(name = "msg_id") val msg_id: Long,

    @ColumnInfo(name = "uid") val uid: Long,

    @ColumnInfo(name = "is_me") val is_me: Boolean,

    @ColumnInfo(name = "from") val from: Long,

    /*@ColumnInfo(name = "from_avatar") val from_avatar*/

    @ColumnInfo(name = "from_name") val from_name : String,

    @ColumnInfo(name = "to") val to: Long,

    /*@ColumnInfo(name = "to_avatar") val to_avatar*/

    @ColumnInfo(name = "to_name") val to_name : String,

    @ColumnInfo(name = "chat_type") val chat_type :Int,

    @ColumnInfo(name = "msg_type") val msg_type:Int,

    @ColumnInfo(name = "msg") val msg:String,

    @ColumnInfo(name="send_time") val send_time:Date,

    @ColumnInfo(name = "send_status") val send_status:Int

)


@Dao
interface MessageDao{

    @Query("SELECT * FROM Message")
    suspend fun getall(): Message?

    @Query("SELECT * FROM Message WHERE msg_id=:msg_id")
    suspend fun getone(msg_id: Long): Message?

    @Insert
    suspend fun insertone(account: Message)

    @Update
    suspend fun updateone(account: Message)
}

@TypeConverters(DateConverter::class)
@Database(entities = [Message::class], version = 1,exportSchema=false)
abstract class MessageDatabase : RoomDatabase() {
    abstract fun messagedao(): MessageDao
}

object MessageDbSingleton {
    private var db = Optional.empty<MessageDatabase>()

    //    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    suspend operator fun invoke(ctx: Context) =
        withContext(Dispatchers.IO) {
            if (db.isEmpty)
                synchronized(this) {
                    db =
                        Optional.of(
                            Room.databaseBuilder(
                                ctx,
                                MessageDatabase::class.java,
                                "message_database"
                            )
                                .build()
                        )
                }

            val dao = db.get().messagedao()
            db.get()
        }
}

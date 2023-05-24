package com.k.data.db

import android.content.Context
import androidx.room.*
import com.k.data.converter.DateConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "Message")
data class Message(
    //消息唯一id，服务器生成
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "msg_id") val msg_id: Long=0,
    //所属者id
    @ColumnInfo(name = "uid") val uid: Long,
    //区分是否是自己发送消息 1-是 0-否
    @ColumnInfo(name = "is_me") val is_me: Boolean,
    //消息发送者id
    @ColumnInfo(name = "from") val from: Long,
    //消息发送者头像
    /*@ColumnInfo(name = "from_avatar") val from_avatar*/
    //消息发送者名称
    @ColumnInfo(name = "from_name") val from_name : String,
    //消息接收者id
    @ColumnInfo(name = "to") val to: Long,
    //消息接收者头像
    /*@ColumnInfo(name = "to_avatar") val to_avatar*/
    //消息接收者名称
    @ColumnInfo(name = "to_name") val to_name : String,
    //会话类型 0-个人 1-群组 3-系统
    @ColumnInfo(name = "chat_type") val chat_type :Int,
    //消息类型 文字/图片/文件/音乐
    @ColumnInfo(name = "msg_type") val msg_type:Int,
    //消息内容
    @ColumnInfo(name = "msg") val msg:String,
    //消息发送时间
    @ColumnInfo(name="send_time") val send_time:LocalDateTime,
    //消息状态 发送中，发送完成，发送失败
    @ColumnInfo(name = "send_status") val send_status:Int

)


@Dao
interface MessageDao{

    @Query("SELECT * FROM Message WHERE uid = (:id)")
    suspend fun maybe(id: Long): Message?

    @Query("SELECT * FROM Message")
    suspend fun getAll(): List<Message>?

    @Query("SELECT * FROM Message WHERE uid = (:id)")
    suspend fun getAllByUid(id: Long): List<Message>?

    @Query("SELECT * FROM Message WHERE msg_id=:msg_id")
    suspend fun getOne(msg_id: Long): Message?

    @Insert
    suspend fun insert(message: Message)

    @Insert
    suspend fun insertOne(message: Message)

    @Update
    suspend fun updateOne(message: Message)
}

@TypeConverters(DateConverter::class)
@Database(entities = [Message::class], version = 1,exportSchema=false)
abstract class MessageDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
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

            val dao = db.get().messageDao()
            db.get()
        }
}

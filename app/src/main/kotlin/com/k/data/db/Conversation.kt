package com.k.data.db

import android.content.Context
import androidx.room.*
import com.k.data.converter.DateConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "Conversation")
data class Conversation(

    //信息所属
    @PrimaryKey @ColumnInfo(name = "uid") val uid : Long,
    //服务器所产生的会话id
    @ColumnInfo(name = "ChatId") val ChatId :Long,
    //标记一台设备上某个用户的唯一回话，用于更新避免插入多条数据
    //@ColumnInfo(name = "c_id") val c_id:Long
    //消息发送者id
    @ColumnInfo(name = "from") val from :Long,
    //消息接收者id
    @ColumnInfo(name = "to") val to :Long,
    //最后一条消息内容
    @ColumnInfo(name = "last_msg") val last_msg :String,
    //最后一条消息id
    @ColumnInfo(name = "last_msg_id") val last_msg_id :Long,
    //聊天对象名称
    @ColumnInfo(name = "chat_name") val chat_name :String,
    //最后发送信息人名称
    @ColumnInfo(name = "last_user_name") val last_user_name :String,
    //最后一条信息时间
    @ColumnInfo(name = "last_time") val last_time : LocalDateTime,
    //会话类型 0-个人 1-群组 2-系统
    @ColumnInfo(name = "chat_type") val chat_type : Int,
    //消息类型 文字/图片/文件/音乐
    @ColumnInfo(name = "msg_type") val msg_type : Int,
    //未读消息数
    @ColumnInfo(name = "unread_count") val unread_count : Int,
    )


@Dao
interface ConversationDao{
    @Query("SELECT * FROM Conversation")
    suspend fun getAll(): List<Conversation>?

    @Query("SELECT * FROM Conversation WHERE ChatId=:ChatId")
    suspend fun getOneByChatId(ChatId: Long): List<Conversation>?

    @Query("SELECT * FROM Conversation WHERE uid=:uid")
    suspend fun getOne(uid: Long): Conversation?

    @Insert
    suspend fun insertOne(account: Conversation)

    @Update
    suspend fun updateOne(account: Conversation)
}

@TypeConverters(DateConverter::class)
@Database(entities = [Conversation::class], version = 1,exportSchema=false)
abstract class ConversationDatabase : RoomDatabase() {
    abstract fun conversationDao(): ConversationDao
}

object ConversationDbSingleton {
    private var db = Optional.empty<ConversationDatabase>()

    //    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    suspend operator fun invoke(ctx: Context) =
        withContext(Dispatchers.IO) {
            if (db.isEmpty)
                synchronized(this) {
                    db =
                        Optional.of(
                            Room.databaseBuilder(
                                ctx,
                                ConversationDatabase::class.java,
                                "conversation_database"
                            )
                                .build()
                        )
                }

            val dao = db.get().conversationDao()
            db.get()
        }
}

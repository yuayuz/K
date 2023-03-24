package com.k.data.db

import android.content.Context
import androidx.room.*
import com.k.data.converter.DateConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

@Entity(tableName = "Conversation")
data class Conversation(
    @PrimaryKey @ColumnInfo(name = "uid") val uid : Long,

    @ColumnInfo(name = "ChatId") val ChatId :Long,

//    @ColumnInfo(name = "c_id") val c_id:Long

    @ColumnInfo(name = "from") val from :Long,

    @ColumnInfo(name = "to") val to :Long,

    @ColumnInfo(name = "last_msg") val last_msg :String,

    @ColumnInfo(name = "last_msg_id") val last_msg_id :Long,

    @ColumnInfo(name = "chat_name") val chat_name :String,

    @ColumnInfo(name = "last_user_name") val last_user_name :String,

    @ColumnInfo(name = "last_time") val last_time : Date,

    @ColumnInfo(name = "chat_type") val chat_type : Int,

    @ColumnInfo(name = "msg_type") val msg_type : Int,

    @ColumnInfo(name = "unread_count") val unread_count : Int,
    )


@Dao
interface ConversationDao{
    @Query("SELECT * FROM Conversation")
    suspend fun getAll(): Conversation?

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

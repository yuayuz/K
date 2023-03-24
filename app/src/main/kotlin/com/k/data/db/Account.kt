package com.k.data.db

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.*
import java.util.Optional

@Entity(tableName = "Account")
data class Account(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Long,
//    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "password") val password: String
)

@Dao
interface AccountDao {
    @Query("SELECT * FROM Account")
    suspend fun getAll(): com.k.data.db.Account?

    @Query("SELECT * FROM Account WHERE id=:id")
    suspend fun getOne(id: Long): com.k.data.db.Account?

    @Insert
    suspend fun insertOne(account: com.k.data.db.Account)

    @Update
    suspend fun updateOne(account: com.k.data.db.Account)
}

@Database(entities = [com.k.data.db.Account::class], version = 1, exportSchema = false)
abstract class AccountDatabase : RoomDatabase() {
    abstract fun accountDao(): com.k.data.db.AccountDao
}

object AccountDbSingleton {
    private var db = Optional.empty<com.k.data.db.AccountDatabase>()

    //    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    suspend operator fun invoke(ctx: Context) =
        withContext(Dispatchers.IO) {
            if (com.k.data.db.AccountDbSingleton.db.isEmpty)
                synchronized(this) {
                    com.k.data.db.AccountDbSingleton.db =
                        Optional.of(
                            Room.databaseBuilder(
                                ctx,
                                com.k.data.db.AccountDatabase::class.java,
                                "account_database"
                            )
                                .build()
                        )
                }

            val dao = com.k.data.db.AccountDbSingleton.db.get().accountDao()
            com.k.data.db.AccountDbSingleton.db.get()
        }
}

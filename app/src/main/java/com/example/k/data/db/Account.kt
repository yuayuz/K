package com.example.k.data.db

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.*
import java.util.Optional

@Entity(tableName = "Account")
data class Account(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "password") val password: String
)

@Dao
interface AccountDao {
    @Query("SELECT * FROM Account")
    suspend fun getall(): Account?

    @Query("SELECT * FROM Account WHERE id=:id")
    suspend fun getone(id: Long): Account?

    @Insert
    suspend fun insertone(account: Account)

    @Update
    suspend fun updateone(account: Account)
}

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class AccountDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}

object AccountDbSingleton {
    private var db = Optional.empty<AccountDatabase>()

    //    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    suspend operator fun invoke(ctx: Context) =
        withContext(Dispatchers.IO) {
            if (db.isEmpty)
                synchronized(this) {
                    db =
                        Optional.of(
                            Room.databaseBuilder(
                                ctx,
                                AccountDatabase::class.java,
                                "account_database"
                            )
                                .build()
                        )
                }

            val dao = db.get().accountDao()
            db.get()
        }
}

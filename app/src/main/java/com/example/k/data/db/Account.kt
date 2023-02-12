package com.example.k.data.db

import androidx.compose.runtime.Composable
import androidx.room.*

@Entity(tableName = "Account")
data class Account(
    @PrimaryKey
    @ColumnInfo(name="id") val id:Long,
    @ColumnInfo(name = "pwd") val pwd:String
)

@Dao
interface AccountDao{
    @Query("SELECT * FROM Account")
    suspend fun get_account():Account?

    @Insert
    suspend fun insert(account: Account)

    @Update
    suspend fun update(account: Account)
}

@Database(entities = [Account::class], version = 1)
abstract class accountDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}
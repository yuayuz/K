package com.k.data.repositories

import android.content.Context
import com.k.data.db.Account
import com.k.data.db.AccountDbSingleton
import kotlinx.coroutines.*


class AccountRepositories(val ctx: Context){

    fun accountInsertOne(account: Account) {
        CoroutineScope(Dispatchers.IO).launch {
            val accessDeniedException = AccountDbSingleton(ctx)
            val dao=accessDeniedException.accountDao()
            dao.insertOne(
                account = account
            )
            accessDeniedException.close()
        }
    }

    fun accountQueryOne(id:Long,password:String): Account? {
        val data:Account?
        runBlocking {
            withContext(Dispatchers.IO) {
                val accessDeniedException = AccountDbSingleton(ctx)
                val dao=accessDeniedException.accountDao()
                data = dao.getOne(id)
                accessDeniedException.close()

                /*rt = when {
                    //无帐号信息
                    data == null -> 0
                    //密码正确
                    password == data.password -> 1
                    //密码错误
                    else -> 2
                }*/
            }
        }
        return data
    }

    fun accountRevisePassword(account: Account) {
        CoroutineScope(Dispatchers.IO).launch {
            val accessDeniedException = AccountDbSingleton(ctx)
            val dao=accessDeniedException.accountDao()
            dao.updateOne(
                account =account
            )
            accessDeniedException.close()
        }
    }

}




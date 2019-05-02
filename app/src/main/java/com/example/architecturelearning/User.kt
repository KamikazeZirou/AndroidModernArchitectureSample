package com.example.architecturelearning

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Entity(indices = [Index(value = ["login"], unique = true)])
data class User(@PrimaryKey val id: Int,
                val login: String,
                val name: String,
                val email: String?,
                val avatarUrl: String?,
                val htmlUrl: String,
                var lastRefresh: Long) {
}

@Database(entities = [ User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: User)

    @Query("SELECT * FROM user WHERE login = :loginName")
    fun load(loginName: String): LiveData<User>

    @Query("SELECT * FROM user WHERE login = :loginName AND lastRefresh > :lastRefreshTimeout LIMIT 1")
    fun hasUser(loginName: String, lastRefreshTimeout: Long): Boolean
}
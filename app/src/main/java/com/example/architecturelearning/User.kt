package com.example.architecturelearning

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Entity(indices = [Index(value = ["name"], unique = true)])
data class User(@PrimaryKey val id: Int,
                val name: String,
                val email: String?,
                val avatarUrl: String?,
                val htmlUrl: String,
                var lastRefresh: Long) {

    companion object {
        val EMPTY_USER = User(0, "", "", "", "", 0)
    }
}

@Database(entities = [ User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: User)

    @Query("SELECT * FROM user WHERE name = :username")
    fun load(username: String): LiveData<User>

    @Query("SELECT * FROM user WHERE name = :username AND lastRefresh > :lastRefreshTimeout LIMIT 1")
    fun hasUser(username: String, lastRefreshTimeout: Long): Boolean
}
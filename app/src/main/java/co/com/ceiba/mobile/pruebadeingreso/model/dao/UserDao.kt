package co.com.ceiba.mobile.pruebadeingreso.model.dao

import androidx.room.*
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id = :userId")
    suspend fun loadAllById(userId: String): User

    @Insert
    suspend fun insertAll(users: List<User>)
}
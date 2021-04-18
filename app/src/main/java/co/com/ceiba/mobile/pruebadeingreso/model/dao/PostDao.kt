package co.com.ceiba.mobile.pruebadeingreso.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    suspend fun getAll(): List<Post>

    @Query("SELECT * FROM post WHERE user_id = :userId")
    suspend fun loadAllByIdUser(userId: Long): List<Post>

    @Insert
    suspend fun insertAll(post: List<Post>)
}
package co.com.ceiba.mobile.pruebadeingreso.model.dao

import androidx.room.*
import co.com.ceiba.mobile.pruebadeingreso.model.entities.*

@Dao
interface CompanyDao {
    @Query("SELECT * FROM company")
    suspend fun getAll(): List<Company>

    @Query("SELECT * FROM company WHERE user_id = :userId")
    suspend fun loadAllByIdUser(userId: Long): Company

    @Insert
    suspend fun insert(company: Company)
}
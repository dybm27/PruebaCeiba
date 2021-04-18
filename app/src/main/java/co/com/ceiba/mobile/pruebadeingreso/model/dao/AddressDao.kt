package co.com.ceiba.mobile.pruebadeingreso.model.dao

import androidx.room.*
import co.com.ceiba.mobile.pruebadeingreso.model.entities.*

@Dao
interface AddressDao {
    @Query("SELECT * FROM address")
    suspend fun getAll(): List<Address>

    @Query("SELECT * FROM company WHERE user_id = :userId")
    suspend fun loadAllByIdUser(userId: Long): Address

    @Insert
    suspend fun insert(address: Address): Long
}
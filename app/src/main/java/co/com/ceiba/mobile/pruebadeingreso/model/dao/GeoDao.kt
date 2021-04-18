package co.com.ceiba.mobile.pruebadeingreso.model.dao

import androidx.room.*
import co.com.ceiba.mobile.pruebadeingreso.model.entities.*

@Dao
interface GeoDao {
    @Query("SELECT * FROM geo")
    suspend fun getAll(): List<Geo>

    @Query("SELECT * FROM geo WHERE address_id = :addressId")
    suspend fun loadAllByIdAddress(addressId: Long): Geo

    @Insert
    suspend fun insert(geo: Geo)
}
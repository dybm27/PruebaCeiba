package co.com.ceiba.mobile.pruebadeingreso.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Geo(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name="address_id")
    val addressId: Long,
    @SerializedName("lat") val lat: Double?,
    @SerializedName("lng") val lng: Double?
): Serializable
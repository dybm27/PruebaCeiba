package co.com.ceiba.mobile.pruebadeingreso.model.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Address(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name="user_id")
    val userId: Long,
    @SerializedName("street") val street: String?,
    @SerializedName("suite") val suite: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("zipcode") val zipcode: String?,
    @Embedded(prefix = "geo_")
    @SerializedName("geo") val geo: Geo?
) : Serializable
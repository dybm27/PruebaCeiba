package co.com.ceiba.mobile.pruebadeingreso.model.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("email") val email: String?,
    @Embedded(prefix = "add_")
    @SerializedName("address") val address: Address?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("website") val website: String?,
    @Embedded(prefix = "com_")
    @SerializedName("company") val company: Company?
): Serializable
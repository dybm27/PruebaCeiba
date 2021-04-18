package co.com.ceiba.mobile.pruebadeingreso.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Company(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name="user_id")
    val userId: Long,
    @SerializedName("name") val name: String?,
    @ColumnInfo(name = "catch_phrase")
    @SerializedName("catchPhrase") val catchPhrase: String?,
    @SerializedName("bs") val bs: String?
): Serializable
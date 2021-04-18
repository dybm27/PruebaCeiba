package co.com.ceiba.mobile.pruebadeingreso.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Post(
    @ColumnInfo(name = "user_id")
    @SerializedName("userId") val userId: Long,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String?,
    @SerializedName("body") val body: String?
): Serializable
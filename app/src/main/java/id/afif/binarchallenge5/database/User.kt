package id.afif.binarchallenge5.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true) var id:Int?,
    @ColumnInfo(name = "username") val username:String,
    @ColumnInfo(name = "email") val email:String,
    @ColumnInfo(name = "password") val password:String,
    @ColumnInfo(name = "fullname") val fullname:String,
    @ColumnInfo(name = "tanggal") val tanggal:String,
    @ColumnInfo(name = "alamat") val alamat:String,
) : Parcelable

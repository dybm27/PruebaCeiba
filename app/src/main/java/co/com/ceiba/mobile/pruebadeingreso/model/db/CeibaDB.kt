package co.com.ceiba.mobile.pruebadeingreso.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.ceiba.mobile.pruebadeingreso.model.dao.*
import co.com.ceiba.mobile.pruebadeingreso.model.entities.*

@Database(
    entities = [User::class, Post::class, Geo::class, Address::class, Company::class],
    version = 5
)
abstract class CeibaDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
    abstract fun addressDao(): AddressDao
    abstract fun companyDao(): CompanyDao
    abstract fun geoDao(): GeoDao
}
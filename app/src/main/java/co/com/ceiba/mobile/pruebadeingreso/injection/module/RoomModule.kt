package co.com.ceiba.mobile.pruebadeingreso.injection.module

import android.app.Application
import androidx.room.Room
import co.com.ceiba.mobile.pruebadeingreso.model.db.CeibaDB
import dagger.Module
import dagger.Provides
import dagger.Reusable


@Module
@Suppress("unused")
object RoomModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideDatabase(app: Application) = Room.databaseBuilder(app, CeibaDB::class.java, "ceiba")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideUserDao(db: CeibaDB) = db.userDao()

    @Provides
    fun providePostDao(db: CeibaDB) = db.postDao()

    @Provides
    fun provideAddressDao(db: CeibaDB) = db.addressDao()

    @Provides
    fun provideCompanyDao(db: CeibaDB) = db.companyDao()

    @Provides
    fun provideGeoDao(db: CeibaDB) = db.geoDao()
}
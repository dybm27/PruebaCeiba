package co.com.ceiba.mobile.pruebadeingreso.injection.module

import co.com.ceiba.mobile.pruebadeingreso.rest.RetrofitApi
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@Suppress("unused")
object RetrofitModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Reusable
    @JvmStatic
    fun provideHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Endpoints.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit) = retrofit.create(RetrofitApi::class.java)

}
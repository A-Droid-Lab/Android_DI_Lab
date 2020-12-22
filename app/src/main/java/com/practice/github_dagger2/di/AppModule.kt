package com.practice.github_dagger2.di

import android.app.Application
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.practice.github_dagger2.data.local.GithubDatabase
import com.practice.github_dagger2.data.local.dao.GithubDao
import com.practice.github_dagger2.data.remote.ApiService
import com.practice.github_dagger2.utils.extension.Constants
import com.practice.github_dagger2.utils.extension.debug
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.apply {
            debug {
                addNetworkInterceptor(StethoInterceptor())
                addInterceptor(httpLoggingInterceptor)
            }
        }
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGithubDatabase(application: Application): GithubDatabase =
        Room.databaseBuilder(application, GithubDatabase::class.java, "github.db").build()

    @Provides
    @Singleton
    fun provideGithubDao(githubDatabase: GithubDatabase): GithubDao = githubDatabase.githubDao()

}
package com.themeetgroup.iavorskyitestapp.di

import android.content.Context
import androidx.room.Room
import com.themeetgroup.iavorskyitestapp.data.database.AppDatabase
import com.themeetgroup.iavorskyitestapp.data.database.PrePopulationData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
            .addCallback(PrePopulationData()).build()

    @Singleton
    @Provides
    fun provideAppContext(@ApplicationContext context: Context) = context

    @Singleton
    @Provides
    fun providePlayerDao(db: AppDatabase) = db.playerDao()

    @Singleton
    @Provides
    fun provideGameDao(db: AppDatabase) = db.gameDao()

}
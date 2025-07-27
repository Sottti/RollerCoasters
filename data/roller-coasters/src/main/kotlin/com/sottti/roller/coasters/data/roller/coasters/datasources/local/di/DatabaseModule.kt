package com.sottti.roller.coasters.data.roller.coasters.datasources.local.di

import android.content.Context
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): RollerCoastersDatabase = RollerCoastersDatabase.create(context)

    @Provides
    fun provideDao(
        database: RollerCoastersDatabase,
    ): RollerCoastersDao = database.dao()
}

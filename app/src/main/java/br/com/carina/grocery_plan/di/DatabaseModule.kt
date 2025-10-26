package br.com.carina.grocery_plan.di

import android.content.Context
import androidx.room.Room
import br.com.carina.grocery_plan.data.room.dao.PurchaseDao
import br.com.carina.grocery_plan.data.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "grocery-plan.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providePurchaseDao(db: AppDatabase): PurchaseDao = db.purchaseDao()
}
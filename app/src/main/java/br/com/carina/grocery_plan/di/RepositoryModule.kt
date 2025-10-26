package br.com.carina.grocery_plan.di

import br.com.carina.grocery_plan.data.room.repository.PurchaseRepository
import br.com.carina.grocery_plan.data.room.repository.PurchaseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPurchaseRepository(
        impl: PurchaseRepositoryImpl
    ): PurchaseRepository
}
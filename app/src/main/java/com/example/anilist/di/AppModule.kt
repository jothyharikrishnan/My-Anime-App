package com.example.anilist.di

import com.example.anilist.data.network.AnimeApi
import com.example.anilist.data.repository.AnimeRepositoryImpl
import com.example.anilist.domain.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAnimeAPi():AnimeApi{
        return Retrofit.Builder()
            .baseUrl(AnimeApi.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeRepository(animeApi: AnimeApi): AnimeRepository {
        return AnimeRepositoryImpl(animeApi)
    }

}
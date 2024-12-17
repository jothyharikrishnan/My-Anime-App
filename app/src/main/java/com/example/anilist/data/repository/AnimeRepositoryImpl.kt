package com.example.anilist.data.repository

import com.example.anilist.utils.NetworkResponse
import com.example.anilist.data.network.AnimeApi
import com.example.anilist.domain.model.AnimeData
import com.example.anilist.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeApi: AnimeApi
): AnimeRepository {
    override suspend fun getTrendingAnime(): NetworkResponse<List<AnimeData>> {
        return try {
            val response = animeApi.getTrendingAnime()
            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResponse.Success(it.toModel())
                } ?: NetworkResponse.Error("Unkown Error")
            } else {
                NetworkResponse.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e.message ?: "Unkown Error")
        }
    }

    override suspend fun getAnimeDetail(id: Int): NetworkResponse<AnimeData> {
       return try {
            val response = animeApi.getAnime(id = id)
            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResponse.Success(it.toModel())
                } ?: NetworkResponse.Error("Unkown Error")
            } else {
                NetworkResponse.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e.message ?: "Unkown Error")
        }

    }

}
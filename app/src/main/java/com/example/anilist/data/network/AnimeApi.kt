package com.example.anilist.data.network

import com.example.anilist.data.network.dto.AnimeResponseDto
import com.example.anilist.data.network.dto.TrendingAnimeListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApi {

    @GET("trending/anime")
    suspend fun getTrendingAnime():Response<TrendingAnimeListDto>

    @GET("anime/{id}")
    suspend fun getAnime(
        @Path("id") id : Int
    ):Response<AnimeResponseDto>

    companion object{
        const val baseUrl = "https://kitsu.io/api/edge/"
    }
}
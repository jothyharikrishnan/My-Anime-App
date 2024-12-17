package com.example.anilist.domain.repository

import com.example.anilist.utils.NetworkResponse
import com.example.anilist.domain.model.AnimeData

interface AnimeRepository {

    suspend fun getTrendingAnime(): NetworkResponse<List<AnimeData>>

    suspend fun getAnimeDetail(id: Int): NetworkResponse<AnimeData>
}
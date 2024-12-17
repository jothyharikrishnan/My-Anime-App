package com.example.anilist.presentation.screen.trendingAnime

import com.example.anilist.domain.model.AnimeData

sealed class TrendingAnimeState {
    data object Loading : TrendingAnimeState()
    data class Success(val data : List<AnimeData>) : TrendingAnimeState()
    data class Error(val message :String) : TrendingAnimeState()
}
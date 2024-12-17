package com.example.anilist.presentation.screen.animeDetailScreen

import com.example.anilist.domain.model.AnimeData

sealed class AnimeDetailScreenState{
    data object Loading : AnimeDetailScreenState()
    data class Success(val data : AnimeData): AnimeDetailScreenState()
    data class Error(val error : String) : AnimeDetailScreenState()
}
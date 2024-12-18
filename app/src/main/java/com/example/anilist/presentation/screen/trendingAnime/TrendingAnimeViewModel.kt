package com.example.anilist.presentation.screen.trendingAnime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anilist.utils.NetworkResponse
import com.example.anilist.domain.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingAnimeViewModel @Inject constructor(
    private val repository: AnimeRepository
):ViewModel() {

    private var _trendingAnimeData = MutableStateFlow<TrendingAnimeState>(TrendingAnimeState.Loading)
    val trendingAnimateData = _trendingAnimeData.asStateFlow()

    init {
        viewModelScope.launch {
            val responseResult =  repository.getTrendingAnime()

                when(responseResult){
                    is NetworkResponse.Error -> {
                        _trendingAnimeData.update {
                            TrendingAnimeState.Error(responseResult.message)
                        }
                    }
                    NetworkResponse.Loading -> {
                        _trendingAnimeData.update {
                            TrendingAnimeState.Loading
                        }
                    }
                    is NetworkResponse.Success -> {
                        _trendingAnimeData.update {
                            TrendingAnimeState.Success(responseResult.data)
                        }
                    }
                }

        }
    }

}
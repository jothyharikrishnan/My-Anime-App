package com.example.anilist.presentation.screen.animeDetailScreen

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
class AnimeDetailScreenViewModel @Inject constructor(
    private val animeRepository: AnimeRepository
) : ViewModel() {

    private var _animeDetailData = MutableStateFlow<AnimeDetailScreenState>(AnimeDetailScreenState.Loading)
    val animeDetailData = _animeDetailData.asStateFlow()


    fun fetchAnime(id : Int){
        viewModelScope.launch {
            val response = animeRepository.getAnimeDetail(id = id)

            when(response){
                is NetworkResponse.Error -> {
                    _animeDetailData.update {
                        AnimeDetailScreenState.Error(response.message)
                    }
                }
                NetworkResponse.Loading -> {
                    _animeDetailData.update {
                        AnimeDetailScreenState.Loading
                    }
                }
                is NetworkResponse.Success -> {
                    _animeDetailData.update {
                        AnimeDetailScreenState.Success(response.data)
                    }
                }
            }
        }
    }

}
@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.anilist.presentation.screen.trendingAnime

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.anilist.domain.model.AnimeData

@Composable
fun SharedTransitionScope.TrendingAnimeScreen(
    viewModel: TrendingAnimeViewModel = hiltViewModel(),
    animatedVisibilityScope: AnimatedVisibilityScope,
    onAnimeItemClick: (String, String) -> Unit
    ){
    val animeData by viewModel.trendingAnimateData.collectAsStateWithLifecycle()
    Scaffold {innerPadding->

        when(val trendingData = animeData){
            is TrendingAnimeState.Error -> {
                DisplayStatusOfResponse(isLoading = false,trendingData.message)
            }
            TrendingAnimeState.Loading -> {
                DisplayStatusOfResponse(isLoading = true)
            }
            is TrendingAnimeState.Success -> {
                DisplayAnimatedContent(
                    innerPaddingValues = innerPadding,
                    animeData = trendingData.data,
                    animatedVisibilityScope = animatedVisibilityScope,
                    onAnimeClick = onAnimeItemClick
                )
            }
        }


    }
}

@Composable
fun SharedTransitionScope.DisplayAnimatedContent(
    animeData : List<AnimeData> = emptyList(),
    innerPaddingValues: PaddingValues,
    animatedVisibilityScope : AnimatedVisibilityScope,
    onAnimeClick: (String, String) -> Unit,
){
    AnimatedContent(targetState = animeData.isEmpty(), label = "...") { isEmpty->
        if (isEmpty){
            CircularProgressIndicator()
        }else{
            LazyColumn(
                contentPadding = PaddingValues(
                    top = innerPaddingValues.calculateTopPadding() + 10.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = innerPaddingValues.calculateBottomPadding() + 10.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {
                    Text(text = "Trending Anime",
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold)
                }

                items(animeData){data->
                    AnimeCard(animeData = data,
                        onClick = {
                            onAnimeClick(data.attributes.posterImage.original, data.id)
                        },
                        animatedVisibilityScope = animatedVisibilityScope)
                }

            }
        }
    }
}

@Composable
fun DisplayStatusOfResponse(
    isLoading : Boolean,
    message : String = ""
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading){
            CircularProgressIndicator()
        }else{
            Text(text = message, fontStyle = FontStyle.Italic, fontSize = 16.sp)
        }
    }
}
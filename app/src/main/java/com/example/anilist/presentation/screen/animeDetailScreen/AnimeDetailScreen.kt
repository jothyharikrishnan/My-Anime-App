@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.anilist.presentation.screen.animeDetailScreen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

@Composable
fun SharedTransitionScope.AnimeDetailScreen(
    viewModel: AnimeDetailScreenViewModel = hiltViewModel(),
    coverImage : String?,
    id : Int,
    animatedVisibilityScope: AnimatedVisibilityScope,
){
    LaunchedEffect (key1 = true){
        viewModel.fetchAnime(id = id)
    }

    val animeDetailData by viewModel.animeDetailData.collectAsStateWithLifecycle()

    when(val anime = animeDetailData){
        is AnimeDetailScreenState.Error -> {
            DisplayStatusOfResponse(isLoading = false, message = anime.error)
        }
        AnimeDetailScreenState.Loading -> {
            DisplayStatusOfResponse(isLoading = true)
        }
        is AnimeDetailScreenState.Success -> {
            Scaffold { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = innerPadding.calculateBottomPadding()),
                    horizontalAlignment = Alignment.Start
                ) {
                    item {
                        AsyncImage(
                            model = coverImage,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .clip(
                                    RoundedCornerShape(
                                        bottomEnd = 20.dp,
                                        bottomStart = 20.dp
                                    )
                                )
                                .sharedElement(
                                    rememberSharedContentState(key = id.toString()),
                                    animatedVisibilityScope = animatedVisibilityScope
                                ),
                            contentScale = ContentScale.Crop
                        )
                    }

                    item {
                        if (anime != null) {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp, vertical = 16.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = anime.data.attributes.canonicalTitle ?: "",
                                    style = MaterialTheme.typography.displaySmall,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = anime.data.attributes.startDate?.split("-")?.first() ?: "",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Medium
                                    )

                                    Text(
                                        text = " - ",
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                    )

                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(1.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.Star,
                                            contentDescription = null,
                                            modifier = Modifier.padding(end = 4.dp)
                                        )

                                        Text(
                                            text = anime.data.attributes.averageRating ?: "", style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Column(horizontalAlignment = Alignment.Start) {
                                    Text(
                                        text = "Synopsis",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(text = anime.data.attributes.synopsis ?: "")
                                }
                            }
                        }
                    }
                }

                if (anime == null) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
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
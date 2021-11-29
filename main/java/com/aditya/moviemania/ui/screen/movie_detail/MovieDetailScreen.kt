package com.aditya.moviemania.ui.screen.movie_detail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MiscellaneousServices
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.aditya.moviemania.R
import com.aditya.moviemania.domain.model.Cast
import com.aditya.moviemania.domain.model.Torrent
import com.aditya.moviemania.ui.screen.common.*
import com.aditya.moviemania.ui.theme.*
import com.aditya.moviemania.ui.util.Screen
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MovieDetailScreen(
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val movieDetailState = viewModel.movieDetailState.value

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    val coroutineScope = rememberCoroutineScope()

    when (movieDetailState) {
        is MovieDetailState.Loading -> {
            ShimmerAnimation {
                MovieDetailLoadingScreen(it)
            }
        }
        is MovieDetailState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    imageVector = Icons.Default.MiscellaneousServices,
                    contentDescription = null,
                    modifier = Modifier.size(cardWidth),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onSurface)
                )
                Spacer(modifier = Modifier.height(spaceMedium))
                Text(
                    text = stringResource(id = R.string.error_message)
                )
            }
        }
        is MovieDetailState.Success -> {
            BottomSheetScaffold(
                scaffoldState = bottomSheetScaffoldState,
                sheetContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        DownloadSheet(torrents = movieDetailState.movieDetail.torrents) { hash ->
                            coroutineScope.launch {
                                bottomSheetScaffoldState.bottomSheetState.collapse()
                            }
                            viewModel.downloadMovie(
                                hash = hash,
                                name = movieDetailState.movieDetail.title,
                                context = context
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier.padding(spaceMedium)
                        )
                    }
                }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(spaceSmall)
                ) {
                    item {
                        TopBanner(
                            name = movieDetailState.movieDetail.title,
                            rating = movieDetailState.movieDetail.rating,
                            background = movieDetailState.movieDetail.mediumCoverImage
                        )
                    }
                    item {
                        LazyRow(
                            modifier = Modifier.padding(spaceSmall),
                            contentPadding = PaddingValues(spaceSmall)
                        ) {
                            items(movieDetailState.movieDetail.genres) { genre ->
                                Chip(text = genre)
                            }
                        }
                    }
                    item {
                        TextBox(
                            heading = stringResource(id = R.string.overview),
                            content = movieDetailState.movieDetail.descriptionFull
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(spaceMedium))
                        Button(
                            onClick = {
                                viewModel.watchMovieTrailer(
                                    youtubeId = movieDetailState.movieDetail.ytTrailerCode,
                                    context = context
                                )
                            },
                            modifier = Modifier
                                .padding(horizontal = spaceMedium, vertical = spaceSmall)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(id = R.string.trailer),
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(spaceMedium))
                        Cast(movieDetailState.movieDetail.cast)
                    }
                    item {
                        val urls = listOf(
                            movieDetailState.movieDetail.mediumScreenshotImage1,
                            movieDetailState.movieDetail.mediumScreenshotImage2,
                            movieDetailState.movieDetail.mediumScreenshotImage3
                        )
                        Screenshots(urls)
                    }
                    item {
                        MovieHorizontalRow(
                            movies = movieDetailState.movieSuggestion ?: emptyList(),
                            category = stringResource(id = R.string.similar),
                            onClick = { id ->
                                navController.navigate(
                                    Screen.DetailScreen.route.plus("/$id")
                                )
                            }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun Cast(casts: List<Cast>) {
    if (casts.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.cast),
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = spaceLarge)
            )
            LazyRow(
                modifier = Modifier.padding(spaceSmall),
                contentPadding = PaddingValues(spaceSmall)
            ) {
                items(casts) { cast ->
                    Column(
                        modifier = Modifier
                            .width(circularImageSize * 1.2f)
                            .padding(spaceSmall),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier.size(circularImageSize),
                            shape = CircleShape
                        ) {
                            Image(
                                painter = rememberImagePainter(
                                    data = cast.urlSmallImage,
                                    builder = { crossfade(true) }
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                            )
                        }
                        Text(
                            text = cast.name,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Screenshots(urls: List<String?>) {

    val totalScreenshots = remember { mutableStateOf(0) }

    urls.forEach {
        if (it != null) {
            totalScreenshots.value++
        }
    }

    if (totalScreenshots.value != 0) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spaceSmall)
        ) {
            Text(
                text = stringResource(id = R.string.screenshot),
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = spaceSmall, vertical = spaceLarge)
            )
            urls.forEach {
                it?.let { url ->
                    Image(
                        painter = rememberImagePainter(
                            data = url,
                            builder = { crossfade(true) }
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(cardHeightMedium)
                            .padding(spaceSmall)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun DownloadSheet(
    torrents: List<Torrent>,
    onDownloadClicked: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spaceMedium)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.download),
                style = MaterialTheme.typography.h5
            )
            Image(
                imageVector = Icons.Default.Menu,
                contentDescription = null
            )
        }
        torrents.forEach { torrent ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spaceSmall),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = torrent.quality,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = torrent.size,
                    style = MaterialTheme.typography.body1
                )
                IconButton(
                    onClick = {
                        onDownloadClicked(torrent.hash)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
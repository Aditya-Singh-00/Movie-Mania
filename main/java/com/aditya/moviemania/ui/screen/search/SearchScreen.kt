package com.aditya.moviemania.ui.screen.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MiscellaneousServices
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aditya.moviemania.R
import com.aditya.moviemania.ui.screen.common.PreviewCard
import com.aditya.moviemania.ui.screen.common.SearchBar
import com.aditya.moviemania.ui.screen.common.ShimmerAnimation
import com.aditya.moviemania.ui.theme.cardHeightMedium
import com.aditya.moviemania.ui.theme.cardWidth
import com.aditya.moviemania.ui.theme.spaceMedium
import com.aditya.moviemania.ui.theme.spaceSmall
import com.aditya.moviemania.ui.util.Screen

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current
    val searchScreenState = viewModel.searchScreenState.value

    LaunchedEffect(true) {
        localFocusManager.moveFocus(FocusDirection.Down)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(spaceMedium)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spaceMedium)
        ) {
            SearchBar(
                value = viewModel.searchQuery.value,
                onValueChange = { viewModel.onSearchQueryChange(it) },
                hint = stringResource(id = R.string.search),
                leadingIcon = Icons.Outlined.Search,
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.getSearchResults()
                        keyboardController?.hide()
                    }
                )
            )

            when (searchScreenState) {
                is SearchScreenState.Passive -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            imageVector = Icons.Default.Movie,
                            contentDescription = null,
                            modifier = Modifier.size(cardWidth)
                        )
                        Spacer(modifier = Modifier.height(spaceMedium))
                        Text(
                            text = stringResource(id = R.string.search_result)
                        )
                    }
                }
                is SearchScreenState.Loading -> {
                    ShimmerAnimation {
                        SearchLoadingScreen(it)
                    }
                }
                is SearchScreenState.Error -> {
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
                            text = stringResource(id = R.string.no_movies)
                        )
                    }
                }
                is SearchScreenState.Success -> {
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(3),
                        modifier = Modifier.padding(spaceSmall)
                    ) {
                        items(searchScreenState.movies) { movie ->
                            PreviewCard(
                                movie = movie,
                                modifier = Modifier
                                    .width(cardWidth)
                                    .height(cardHeightMedium),
                                onClick = { id ->
                                    navController.navigate(
                                        Screen.DetailScreen.route.plus("/$id")
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
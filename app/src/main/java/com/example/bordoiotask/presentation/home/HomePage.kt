package com.example.bordoiotask.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bordoiotask.data.response.BordoHomeItem
import com.example.bordoiotask.data.response.Technologies
import com.example.bordoiotask.presentation.home.viewmodel.HomeViewModel
import com.example.bordoiotask.shared_layout.ErrorMessage
import com.example.bordoiotask.shared_layout.LoadingCardView
import com.example.bordoiotask.shared_layout.SharedTitleDescription
import com.example.bordoiotask.shared_layout.ShowImageTitleCardView
import kotlinx.coroutines.launch

@Composable
fun HomePage(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state by homeViewModel.state.collectAsState()
    val bordoHomeItemList = remember { mutableStateOf<List<BordoHomeItem>>(emptyList()) }
    val technologiesItem = remember { mutableStateOf<List<Technologies>>(emptyList()) }

    Column(
        modifier = Modifier.background(Color(0xFFFCF2FD))
    ) {
        //TopAppBar(
        //            title = {
        //                Text(text = "Bordo Io")
        //            },
        //            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFFCF2FD))
        //        )
        if (state.loading == true) {
            Box(modifier = Modifier.fillMaxSize()) {
                LoadingCardView(modifier = Modifier.align(Alignment.Center))
            }
        }

        if (state.error?.isNotBlank() == true) {
            ErrorMessage(
                errorMessage = state.error!!,
                onRetry = {
                    homeViewModel.getHomeData()
                })
        }

        if (state.homeData?.isNotEmpty() == true) {
            state.homeData?.let {
                bordoHomeItemList.value = it

                it.map { item ->
                    item.technologies?.let { tech ->
                        technologiesItem.value = tech
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    count = bordoHomeItemList.value.size,
                    key = {
                        bordoHomeItemList.value[it].desc.toString()
                    },
                    itemContent = { index ->
                        bordoHomeItemList.value[index].title?.let {
                            bordoHomeItemList.value[index].desc?.let { it1 ->
                                HomePageTitle(
                                    title = it,
                                    content = it1
                                )
                            }
                        }
                    })
                item {
                    TechnologiesCardView(technologiesItem.value)
                }
            }
        }
    }
}

@Composable
fun HomePageTitle(title: String, content: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(Color(0xFFFBE9FD))
    ) {
        SharedTitleDescription(title = title, content = content)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TechnologiesCardView(technologiesItem: List<Technologies>) {

    val pagerState = rememberPagerState(pageCount = {
        technologiesItem.size
    })
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(15.dp),
    ) {
        HorizontalPager(
            beyondBoundsPageCount = technologiesItem.size,
            state = pagerState,
            key = { it }
        ) { index ->
            ShowImageTitleCardView(
                image = technologiesItem[index].image,
                title = technologiesItem[index].title,
                desc = technologiesItem[index].desc,
                width = 0.25f
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.25f)
                .fillMaxHeight(0.20f)
                .clip(RoundedCornerShape(20))
                .background(Color.LightGray)
                .zIndex(1f)
                .align(BottomEnd)

        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage - 1
                        )
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
            }
            IconButton(
                modifier = Modifier.align(CenterEnd),
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage + 1
                        )
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Forward"
                )
            }
        }
    }
}
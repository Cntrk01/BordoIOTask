package com.example.bordoiotask.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.bordoiotask.data.response.BordoHomeItem
import com.example.bordoiotask.data.response.Technologies
import com.example.bordoiotask.presentation.home.viewmodel.HomeViewModel
import com.example.bordoiotask.shared_layout.ErrorMessage
import com.example.bordoiotask.shared_layout.LoadingCardView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
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
        TopAppBar(
            title = {
                Text(text = "Bordo Io")
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFFCF2FD))
        )
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
                items(bordoHomeItemList.value.size) { index ->
                    bordoHomeItemList.value[index].title?.let {
                        bordoHomeItemList.value[index].desc?.let { it1 ->
                            HomePageTitle(
                                title = it,
                                content = it1
                            )
                        }
                    }
                }
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
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(Color(0xFFFAE0FD))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )

            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp),
                color = Color.Black
            )
        }
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
            .padding(10.dp)
    ){
        HorizontalPager(
            beyondBoundsPageCount = technologiesItem.size,
            state = pagerState,
            key = { it }
        ) { index ->
            Card(
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                colors = CardDefaults.cardColors(Color(0xFFFAE0FD))
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = technologiesItem[index].image),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .padding(start = 20.dp, top = 20.dp),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                    text = technologiesItem[index].title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                    text = technologiesItem[index].desc,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }

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
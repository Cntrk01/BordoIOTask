package com.example.bordoiotask.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bordoiotask.data.response.BordoItem
import com.example.bordoiotask.presentation.home.viewmodel.HomeViewModel
import com.example.bordoiotask.shared_layout.ErrorMessage
import com.example.bordoiotask.shared_layout.LoadingCardView

@Composable
fun HomePage(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state by homeViewModel.state.collectAsState()
    val bordoItemList = remember { mutableStateOf<List<BordoItem>>(emptyList()) }

    Column {

        if (state.loading == true) {
            LoadingCardView()
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
                bordoItemList.value = it
            }
            LazyColumn {
                items(bordoItemList.value.size) { index ->

                    CardView(
                        title = bordoItemList.value[index].title,
                        content = bordoItemList.value[index].desc
                    )
                }
            }
        }
    }
}

@Composable
fun CardView(title: String, content: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Title (h1 style)
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium
            )

            // Content
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
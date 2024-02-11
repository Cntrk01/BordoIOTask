package com.example.bordoiotask.presentation.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bordoiotask.data.response.BordoProjectsItem
import com.example.bordoiotask.presentation.project.viewmodel.ProjectViewModel
import com.example.bordoiotask.shared_layout.ErrorMessage
import com.example.bordoiotask.shared_layout.LoadingCardView
import com.example.bordoiotask.shared_layout.ShowImageTitleCardView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectPage(
    projectViewModel: ProjectViewModel= hiltViewModel()
){
    val state by projectViewModel.state.collectAsState()
    val bordoHomeItemList = remember { mutableStateOf<List<BordoProjectsItem>>(emptyList()) }


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
                    projectViewModel.getProjects()
                })
        }

        if (state.projectsList?.isNotEmpty() == true){
            state.projectsList?.let {
                bordoHomeItemList.value=it
            }
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(bordoHomeItemList.value.size){

                    bordoHomeItemList.value[it].image?.let { image ->

                        bordoHomeItemList.value[it].title?.let { title->

                            bordoHomeItemList.value[it].desc?.let{desc->
                                ShowImageTitleCardView(cardPadding = 10.dp, image = image, title = title, desc = desc)
                            }
                        }
                    }
                }
            }
        }
    }
}
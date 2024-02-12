package com.example.bordoiotask.presentation.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.bordoiotask.data.response.BordoProjectsItem
import com.example.bordoiotask.presentation.project.viewmodel.ProjectViewModel
import com.example.bordoiotask.shared_layout.ErrorMessage
import com.example.bordoiotask.shared_layout.LoadingCardView
import com.example.bordoiotask.shared_layout.SearchBar
import com.example.bordoiotask.shared_layout.SharedTitleDescription
import com.example.bordoiotask.shared_layout.ShowImageTitleCardView

@Composable
fun ProjectPage(
    projectViewModel: ProjectViewModel = hiltViewModel()
) {
    val state by projectViewModel.state.collectAsState()
    val bordoHomeItemList = remember { mutableStateOf<List<BordoProjectsItem>>(emptyList()) }
    var showDialog by remember {mutableStateOf(true)}
    val bordoItemForAlertDialog = remember { mutableStateOf(BordoProjectsItem(id = null, image = "Sample Title", title = "Sample Description", desc = "")) }
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.background(Color(0xFFFCF2FD))
    ) {
        //TopAppBar(
        //            title = {
        //                Text(text = "Bordo Io")
        //            },
        //            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFFCF2FD))
        //        )
        SearchBar(
            modifier = Modifier.padding(10.dp),
            hint = "Search Project",
            onSearch = {
                query = it
                projectViewModel.searchProject(query = it)
            },
        )

        if (state.loading == true) {
            Box(modifier = Modifier.fillMaxSize()) {
                LoadingCardView(modifier = Modifier.align(Alignment.Center))
            }
        }

        if (state.error?.isNotBlank() == true ) {
            if (state.error =="Query Not Found"){
                ErrorMessage(
                    errorMessage = state.error!!,
                    onRetry = {},
                    showRetryButton = false)
            }
            ErrorMessage(
                errorMessage = state.error!!,
                onRetry = {
                    projectViewModel.getProjects()
                })
        }

        if (state.projectsList?.isNotEmpty() == true) {
            state.projectsList?.let {
                bordoHomeItemList.value = it
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(bordoHomeItemList.value.size) { position ->

                    bordoHomeItemList.value[position].image?.let { image ->

                        bordoHomeItemList.value[position].title?.let { title ->

                            bordoHomeItemList.value[position].desc?.let { desc ->

                                ShowImageTitleCardView(
                                    cardPadding = 10.dp, image = image, title = title, desc = desc,
                                    width = 1F,
                                    height = 150.dp,
                                    clickItem = {
                                        showDialog=true
                                        bordoItemForAlertDialog.value = bordoHomeItemList.value[position]
                                    }
                                )
                            }
                        }
                    }
                }
            }

            if (bordoItemForAlertDialog.value.id != null) {
                if (showDialog){
                    ProjectDialog(
                        bordoProjectsItem = bordoItemForAlertDialog.value,
                        dismiss = {
                            showDialog=false
                        })
                }
            }
        }
    }
}

@Composable
private fun ProjectDialog(
    bordoProjectsItem: BordoProjectsItem,
    dismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                AsyncImage(
                    modifier= Modifier
                        .align(Alignment.Center)
                        .size(70.dp),
                    model = bordoProjectsItem.image,
                    contentDescription = null,
                )
                IconButton(
                    onClick = {dismiss.invoke()},
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
            }
        },
        text = {
            SharedTitleDescription(title = bordoProjectsItem.title.toString(), content = bordoProjectsItem.desc.toString())
        },
        confirmButton = {}
    )
}
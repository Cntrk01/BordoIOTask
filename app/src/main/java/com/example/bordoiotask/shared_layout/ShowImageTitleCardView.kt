package com.example.bordoiotask.shared_layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter


//Görsel olan durumlar için kullanılır
@Composable
fun ShowImageTitleCardView(
    cardPadding: Dp =0.dp,
    image:String,
    title: String,
    desc:String,
    width : Float,
    height : Dp = 100.dp,
    clickItem : (()->Unit) ?=null,
){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(cardPadding)
            .clickable {
                clickItem?.invoke()
            },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(Color(0xFFFBE9FD))
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = image),
            modifier = Modifier
                .fillMaxWidth(width)
                .height(height)
                .padding(20.dp),
            contentDescription = "Image",
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.padding(start = 20.dp, top = 10.dp),
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
            text = desc,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
}}
package com.example.lab3.ui.screens.placeDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab3.R
import com.example.lab3.data.ItemsData

@Composable
fun PlaceDetailsScreen(id: Int) {
    // Retrieve the item based on the provided id
    val item = ItemsData.itemList.first { it.id == id }
    Column(
        modifier = Modifier
            .background(Color(0xffe2ddd9))
    ) {
        // Use a LazyColumn for scrollable content
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "${item.id}. ${item.title}",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            item {
                val imageID = when (item.image) {
                    "zamok_palanok" -> R.drawable.zamok_palanok
                    "olesky_castle" -> R.drawable.olesky_castle
                    "kamianets_fortress" -> R.drawable.kamianets_fortress
                    "dovbush_rocks" -> R.drawable.dovbush_rocks
                    "hoverla" -> R.drawable.hoverla
                    "svirzh_castle" -> R.drawable.svirzh_castle
                    else -> R.drawable.hoverla
                }
                Image(
                    painter = painterResource(id = imageID),
                    contentDescription = item.title,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Text(
                    text = item.description,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

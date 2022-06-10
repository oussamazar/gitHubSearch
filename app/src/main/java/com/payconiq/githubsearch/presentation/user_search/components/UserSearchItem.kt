package com.payconiq.githubsearch.presentation.user_search.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.payconiq.githubsearch.domain.model.User
import com.payconiq.githubsearch.presentation.ui.theme.blue
import com.payconiq.githubsearch.presentation.ui.theme.darkBlue200
import com.payconiq.githubsearch.presentation.ui.theme.darkBlue500

@Composable
fun UserSearchItem(user: User, onItemClick: (User) -> Unit) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(user) },
        backgroundColor = darkBlue500,
        border = BorderStroke(
            0.5.dp,
            darkBlue200
        ),
        shape = RoundedCornerShape(10.dp)

    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth().padding(14.dp)

        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatar_url)
                    .crossfade(true)
                    .build(),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp),
            )

            Column() {
                Text(
                    text = "${user.login}",
                    style = MaterialTheme.typography.h2,
                    color = blue,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 14.dp)
                )
                Text(
                    text = "${user.html_url}",
                    style = MaterialTheme.typography.h3,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 14.dp)
                )
            }

        }
    }
}
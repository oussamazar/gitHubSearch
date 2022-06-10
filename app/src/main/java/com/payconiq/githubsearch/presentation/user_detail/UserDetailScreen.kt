package com.payconiq.githubsearch.presentation.user_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.payconiq.githubsearch.presentation.ui.theme.blue
import com.payconiq.githubsearch.presentation.ui.theme.darkBlue500

@Composable
fun UserDetailScreen(viewModel: UserDetailViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBlue500)
    ) {

        state.user?.let { user ->

            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

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
                        .size(100.dp),
                )

                Text(
                    text = "${user.name}",
                    style = MaterialTheme.typography.h1,
                    color = blue,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 14.dp, bottom = 30.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${user.following}",
                            style = MaterialTheme.typography.body1,
                            color = Color.White,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        Text(
                            text = "Following",
                            style = MaterialTheme.typography.body1,
                            color = Color.White,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    ) {
                        Text(
                            text = "${user.followers}",
                            style = MaterialTheme.typography.body1,
                            color = Color.White,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        Text(
                            text = "Followers",
                            style = MaterialTheme.typography.body1,
                            color = Color.White,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${user.public_repos}",
                            style = MaterialTheme.typography.body1,
                            color = Color.White,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        Text(
                            text = "Repository",
                            style = MaterialTheme.typography.body1,
                            color = Color.White,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                }

                Text(
                    text = "This is for the UI purpose ,It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                    style = MaterialTheme.typography.body1,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 50.dp),
                    textAlign = TextAlign.Center
                )


            }


        }


        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }



    }


}
package com.payconiq.githubsearch.presentation.user_search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.payconiq.githubsearch.R
import com.payconiq.githubsearch.presentation.Screen
import com.payconiq.githubsearch.presentation.ui.theme.darkBlue700
import com.payconiq.githubsearch.presentation.user_search.components.UserSearchItem
import kotlinx.coroutines.launch

@Composable
fun UserResearchScreen(
    navController: NavController,
    viewModel: UserSearchViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value
    val searchQuery = viewModel.searchQuery.value

    var emtyState by remember {
        mutableStateOf(true)
    }





    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBlue700)

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp)
        ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = viewModel::userSearch,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
                        textColor = Color.White,
                        cursorColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Search...", color = Color.White)

                    },
                    shape = RoundedCornerShape(8.dp),
                    maxLines = 1,
                    singleLine = true
                )

                if (emtyState) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Spacer(modifier = Modifier.height(80.dp))


                        Image(
                            painter = painterResource(R.drawable.github_mark),
                            contentDescription = "github mark",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        Text(
                            text = "Where the world builds software",
                            style = MaterialTheme.typography.h1,
                            color = Color.White,
                            textAlign= TextAlign.Center,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }
                }

            }





            state.search?.items?.let {
                emtyState = false
                LazyColumn(
                    contentPadding = PaddingValues(top = 10.dp, bottom = 30.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(it.size) { index ->
                        UserSearchItem(
                            user = it[index],
                            onItemClick = { user ->
                                navController.navigate(Screen.UserDetailScreen.route + "/${user.login}")
                            }
                        )
                    }
                }
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

/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.news.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.AppDrawer
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.Screen
import com.example.androiddevchallenge.ui.news.data.posts.impl.posts
import com.example.androiddevchallenge.ui.news.model.Post
import kotlinx.coroutines.launch

/**
 * @author jacky
 * @date 2021/3/19
 */

@Composable
fun HomeScreen(
    navigateTo: (Screen) -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    val coroutineScope = rememberCoroutineScope()
    //use the scaffold and add the topBar
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                navigationTo = navigateTo,
                currentScreen = Screen.Home,
                closeDrawer = { coroutineScope.launch { scaffoldState.drawerState.close() } }
            )
        },
        topBar = {
            val title = stringResource(id = R.string.home)
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_jetnews_logo),
                            contentDescription = stringResource(R.string.cd_open_navigation_drawer)
                        )
                    }
                }
            )
        },
        content = { PostList() }
    )

}


//the list of post
@Composable
private fun PostList() {


    val list = remember { posts }
    val postTop = list[3]
    val postsSimple = list.subList(0, 2)
    val postsPopular = list.subList(2, 7)
    val postsHistory = list.subList(7, 10)

    LazyColumn(content = {
        item { PostListTop(post = postTop) }
        item {
            PostSimple(
                post = postsSimple
            )
        }
        item { PostPopular(posts = postsPopular) }
        item { PostHistory(posts = postsHistory) }
    })
}


@Composable
private fun PostListTop(post: Post) {
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
        text = "Top stories for you",
        style = MaterialTheme.typography.subtitle1
    )
    PostCardTop(post = post, modifier = Modifier.clickable { /*TODO*/ })
    PostListDivider()
}


@Composable
private fun PostSimple(
    post: List<Post>
) {
    Column {
        post.forEach { post ->
            PostCardSimple(
                post = post,
                navigateTo = { /*TODO*/ },
                isFavorite = false,
                onToggleFavorite = { /*TODO*/ }
            )
            PostListDivider()
        }
    }
}


@Composable
private fun PostPopular(
    posts: List<Post>
) {
    Column {
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.popular_jetNews),
            style = MaterialTheme.typography.subtitle1
        )

        LazyRow(Modifier.padding(end = 16.dp)) {
            items(posts) { post ->
                PostCardPopular(
                    post = post,
                    navigationTo = {  },
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
        }
        PostListDivider()
    }
}


@Composable
private fun PostHistory(
    posts: List<Post>
) {
    Column {
        posts.forEach { post ->
            PostCardHistory(
                post = post,
                navigateTo = { /*TODO*/ }
            )
            PostListDivider()
        }
    }
}


@Composable
private fun PostListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}
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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.Screen
import com.example.androiddevchallenge.ui.news.data.posts.impl.post1
import com.example.androiddevchallenge.ui.news.data.posts.impl.post3
import com.example.androiddevchallenge.ui.news.model.Post
import com.example.androiddevchallenge.ui.news.model.PostAuthor
import com.example.androiddevchallenge.ui.theme.MyTheme

/**
 * @author jacky
 * @date 2021/3/19
 */

@Composable
fun PostCardPopular(
    post: Post,
    navigationTo: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.size(280.dp, 240.dp)
    ) {

        Column(modifier.clickable { navigationTo(Screen.Article(post.id)) }) {
            Image(
                painter = painterResource(post.imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = post.title,
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = post.metadata.author.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2
                )

                Text(
                    text = "${post.metadata.date} - " +
                            "${post.metadata.readTimeMinutes} min read",
                    style = MaterialTheme.typography.body2,
                )
            }


        }
    }
}


@Composable
@Preview
fun PreviewPopularCard() {
    PostCardPopular(post = post3, navigationTo = { })
}

@Preview("Regular colors, long text")
@Composable
fun PreviewPostCardPopularLongText() {
    val loremIpsum =
        """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras ullamcorper pharetra massa,
        sed suscipit nunc mollis in. Sed tincidunt orci lacus, vel ullamcorper nibh congue quis.
        Etiam imperdiet facilisis ligula id facilisis. Suspendisse potenti. Cras vehicula neque sed
        nulla auctor scelerisque. Vestibulum at congue risus, vel aliquet eros. In arcu mauris,
        facilisis eget magna quis, rhoncus volutpat mi. Phasellus vel sollicitudin quam, eu
        consectetur dolor. Proin lobortis venenatis sem, in vestibulum est. Duis ac nibh interdum,
        """.trimIndent()
    MyTheme {
        PostCardPopular(
            post1.copy(
                title = "Title$loremIpsum",
                metadata = post1.metadata.copy(
                    author = PostAuthor("Author: $loremIpsum"),
                    readTimeMinutes = Int.MAX_VALUE
                )
            ),
            {}
        )
    }
}

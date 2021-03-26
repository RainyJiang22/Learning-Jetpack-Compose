package com.example.androiddevchallenge.ui.news.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.news.data.posts.impl.posts
import com.example.androiddevchallenge.ui.news.model.Post
import com.example.androiddevchallenge.ui.theme.MyTheme

/**
 * @author jacky
 * @date 2021/3/18
 */
@Composable
fun PostCardTop(post: Post, modifier: Modifier = Modifier) {

    val typography = MaterialTheme.typography
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        val imageModifier = Modifier
            .heightIn(min = 180.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
        Image(
            painter = painterResource(post.imageId),
            contentDescription = null, // description is null
            modifier = imageModifier,
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = post.title, style = typography.h6)
        Text(
            text = post.metadata.author.name,
            style = typography.body2,

        )

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = "${post.metadata.date} - ${post.metadata.readTimeMinutes} min read",
                style = typography.body2
            )
        }
    }
}


@Composable
fun TutorialPreviewTemplate() {
    val previewPosts = posts.subList(1, 2)
    val post = previewPosts[0]

    MyTheme {
        PostCardTop(post = post)
    }
}

@Preview
@Composable
fun PreviewPostCard() {
    MyTheme {
        TutorialPreviewTemplate()
    }
}
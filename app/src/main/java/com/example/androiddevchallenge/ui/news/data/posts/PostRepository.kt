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
package com.example.androiddevchallenge.ui.news.data.posts

import com.example.androiddevchallenge.ui.news.data.Result
import com.example.androiddevchallenge.ui.news.model.Post
import kotlinx.coroutines.flow.Flow

/**
 * @author jacky
 * @date 2021/3/18
 */
interface PostRepository {

    /**
     * Get a specific JetNews post.
     */
    suspend fun getPost(postId: String): Result<Post>

    /**
     * Get JetNews posts.
     */
    suspend fun getPosts(): Result<List<Post>>

    /**
     * Observe the current favorites
     */
    fun observeFavorites(): Flow<Set<String>>

    /**
     * Toggle a postId to be a favorite or not.
     */
    suspend fun toggleFavorite(postId: String)

}
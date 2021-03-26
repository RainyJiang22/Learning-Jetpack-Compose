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
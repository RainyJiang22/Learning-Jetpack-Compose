package com.example.androiddevchallenge
import androidx.annotation.MainThread
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.ScreenName.ARTICLE
import com.example.androiddevchallenge.ScreenName.HOME
import com.example.androiddevchallenge.ScreenName.INTERESTS
/**
 * @author jacky
 * @date 2021/3/18
 */
enum class ScreenName { HOME, INTERESTS, ARTICLE }

sealed class Screen(val id: ScreenName) {
    object Home : Screen(HOME)
    object Interests : Screen(INTERESTS)
    data class Article(val postId: String) : Screen(ARTICLE)
}

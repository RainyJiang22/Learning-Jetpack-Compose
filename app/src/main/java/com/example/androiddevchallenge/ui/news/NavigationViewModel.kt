package com.example.androiddevchallenge.ui.news

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.Screen

/**
 * @author jacky
 * @date 2021/3/18
 */
class NavigationViewModel : ViewModel() {
    private val _screen = MutableLiveData<Screen>(Screen.Home)

    var curScreen: LiveData<Screen> = _screen

    @MainThread
    fun onBack(): Boolean {
        if (_screen.value != Screen.Home) {
            _screen.value = Screen.Home
            return true
        }
        return false
    }

    @MainThread
    fun navigateToInterests() {
        //go to navigateToInterests
        _screen.value = Screen.Interests
    }


    @MainThread
    fun navigateToArticle(postId: String) {
        // go to navigateTOArticle
        _screen.value = Screen.Article(postId = postId)
    }

    @MainThread
    fun navigateTo(screen: Screen) {
        _screen.value = screen
    }
}
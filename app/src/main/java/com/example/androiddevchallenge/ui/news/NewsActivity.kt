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
package com.example.androiddevchallenge.ui.news

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.androiddevchallenge.Screen
import com.example.androiddevchallenge.ui.news.home.HomeScreen
import com.example.androiddevchallenge.ui.news.interests.TabSections
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.news.article.ArticleScreen

/**
 * @author jacky
 * @date 2021/3/19
 */
class NewsActivity : AppCompatActivity() {

    private val navigationViewModel: NavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(navigationViewModel = navigationViewModel)
            }
        }
    }

    // built you app
    @Composable
    fun MyApp(navigationViewModel: NavigationViewModel) {
        val curScreen by navigationViewModel.curScreen.observeAsState(Screen.Home)

        Crossfade(curScreen) {
            Surface(color = MaterialTheme.colors.background) {

                when (curScreen) {
                    is Screen.Home -> HomeScreen(navigateTo = navigationViewModel::navigateTo)
                    is Screen.Interests -> TabSections(navigateTo = navigationViewModel::navigateTo)
                    is Screen.Article -> ArticleScreen(
                        post = (curScreen as Screen.Article).post,
                        onBack = { navigationViewModel.onBack() },
                        isFavorite = false,
                        onToggleFavorite = {})
                }
            }
        }
    }

}
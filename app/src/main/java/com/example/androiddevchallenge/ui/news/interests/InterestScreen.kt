package com.example.androiddevchallenge.ui.news.interests

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.AppDrawer
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.Screen
import com.example.androiddevchallenge.news.interests.Sections
import com.example.androiddevchallenge.news.interests.SelectTopicButton
import kotlinx.coroutines.launch

/**
 * @author jacky
 * @date 2021/3/19
 */

class TabContent(val section: Sections, val content: @Composable () -> Unit)

@Composable
fun InterestsScreen(
    tabContent: List<TabContent>,
    tab: Sections,
    onTabChange: (Sections) -> Unit,
    scaffoldState: ScaffoldState,
    navigateTo: (Screen) -> Unit
) {

    val coroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                navigationTo = navigateTo,
                currentScreen = Screen.Interests,
                closeDrawer = { coroutineScope.launch { scaffoldState.drawerState.close() } }
            )
        },
        topBar = {
            val title = stringResource(id = R.string.interests)
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
        content = { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            InterestsTabContent(tab, onTabChange, tabContent)
        })
}


@Composable
fun InterestsTabContent(
    currentSection: Sections,
    updateSection: (Sections) -> Unit,
    tabContent: List<TabContent>
) {
    val selectedTabIndex = tabContent.indexOfFirst { it.section == currentSection }
    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex
        ) {
            tabContent.forEachIndexed { index, tabContent ->
                Tab(
                    text = { Text(tabContent.section.title) },
                    selected = selectedTabIndex == index,
                    onClick = { updateSection(tabContent.section) }
                )
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            // display the current tab content which is a @Composable () -> Unit
            tabContent[selectedTabIndex].content()
        }
    }
}

@Composable
fun TabSections(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navigateTo: (Screen) -> Unit
) {

    val topicSections = TabContent(Sections.Topics) {
        //TODO
        Text(text = "this is topic", style = MaterialTheme.typography.subtitle1)
    }

    val peopleSections = TabContent(Sections.People) {
        //TODO
        Text(text = "this is people", style = MaterialTheme.typography.subtitle1)
    }

    val publicSections = TabContent(Sections.Publications) {
        Text(text = "this is publications", style = MaterialTheme.typography.subtitle1)
    }

    val tabContent = listOf(topicSections, peopleSections, publicSections)
    val (currentSection, updateSection) = rememberSaveable { mutableStateOf(tabContent.first().section) }

    InterestsScreen(
        tabContent = tabContent,
        tab = currentSection,
        onTabChange = updateSection,
        scaffoldState = scaffoldState,
        navigateTo = navigateTo
    )
}

@Composable
private fun TopicList() {

}

@Composable
private fun PeopleList() {

}


@Composable
private fun TopicItem(itemTitle: String, selected: Boolean, onToggle: () -> Unit) {
    val image = painterResource(id = R.drawable.placeholder_1_1)

    Row(
        modifier = Modifier
            .toggleable(value = selected, onValueChange = { onToggle })
            .padding(horizontal = 16.dp)
    ) {

        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(56.dp, 56.dp)
                .clip(RoundedCornerShape(4.dp))
        )

        Text(
            text = itemTitle,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(16.dp),
            style = MaterialTheme.typography.subtitle1
        )
        Spacer(Modifier.weight(1f))
        SelectTopicButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            selected = selected
        )
    }
}


@Composable
private fun TopicDivider() {
    Divider(
        modifier = Modifier.padding(start = 72.dp, top = 8.dp, bottom = 8.dp),
        color = MaterialTheme.colors.surface.copy(alpha = 0.08f)
    )
}

@Composable
@Preview
private fun previewImageList() {
    TopicItem(itemTitle = "item" , selected = false, onToggle = { /*TODO*/ })
}



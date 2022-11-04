@file:Suppress("DEPRECATION")

package com.siele.myresume

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.siele.myresume.ui.theme.MyResumeTheme
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = this
            val dataStore = PrefsStore(context = context)
            val isDarkModeActive =
                dataStore.getMode.collectAsState(initial = isSystemInDarkTheme()).value ?: false
            val language = dataStore.getLang.collectAsState(initial = "en").value
            context.resources.apply {
                val locale = Locale(language ?: "en")
                val config = Configuration(configuration)
                context.createConfigurationContext(config)
                Locale.setDefault(locale)
                config.setLocale(locale)
                updateConfiguration(config, displayMetrics)
            }

            MyResumeTheme(darkTheme = isDarkModeActive) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Log.d("MainActivity", "$isDarkModeActive")
                    MainContent()

                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Surface {
        Scaffold(topBar = { TopBar() }) { paddingValues ->
            BoxWithConstraints(
                modifier = modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                val aboutHeader = stringResource(R.string.about_me_label)
                val serviceHeader = stringResource(R.string.services_label)
                val aboutDesc = stringResource(R.string.about_desc)
                val serviceDesc = stringResource(R.string.service_desc)
                if (this.maxWidth < 600.dp) {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(top = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 18.dp),
                        ) {
                            ProfilePicture(modifier, 90.dp)
                            Spacer(modifier = modifier.width(20.dp))

                            Column {
                                Name()
                                Spacer(modifier = modifier.heightIn(5.dp))
                                TextCompose(stringResource(id = R.string.title_label))
                                Location(modifier)
                            }
                        }

                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 16.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column(
                                modifier = modifier.weight(.5f),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                SocialMedia(
                                    icon = R.drawable.ic_github,
                                    mediaName = stringResource(R.string.github_label),
                                    link = stringResource(R.string.github_link)
                                )
                                SocialMedia(
                                    icon = R.drawable.ic_linkedin,
                                    mediaName = stringResource(R.string.linkedin_label),
                                    link = stringResource(R.string.linkedin_link)
                                )

                            }
                            Column(
                                modifier = modifier.weight(.5f),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                SocialMedia(
                                    icon = R.drawable.ic_email,
                                    mediaName = stringResource(R.string.email_label),
                                    link = stringResource(R.string.email_link)
                                )
                                SocialMedia(
                                    icon = R.drawable.ic_twitter,
                                    mediaName = stringResource(R.string.twitter_label),
                                    link = stringResource(R.string.twitter_link)
                                )
                            }
                        }
                        Spacer(modifier = modifier.heightIn(20.dp))
                        Divider(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            thickness = 2.dp
                        )
                        Spacer(modifier = modifier.heightIn(20.dp))
                        Section(
                            modifier = modifier, sectionName = aboutHeader,
                            desc = aboutDesc
                        )

                        Section(
                            modifier = modifier,
                            sectionName = serviceHeader,
                            desc = serviceDesc
                        )
                        Spacer(modifier = modifier.heightIn(20.dp))
                        Section(
                            modifier = modifier,
                            sectionName = stringResource(R.string.skills_label),
                            desc = stringResource(R.string.ellipsis_todo)
                        )


                    }
                } else {

                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(top = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 18.dp)
                        ) {

                            Column(
                                modifier = modifier.weight(0.3f),
                            ) {
                                ProfilePicture(modifier = modifier, size = 70.dp)
                                Spacer(modifier = modifier.heightIn(5.dp))
                                Name()
                                Spacer(modifier = modifier.heightIn(5.dp))
                                TextCompose(stringResource(id = R.string.title_label))
                                Location(modifier)
                                Spacer(modifier = modifier.heightIn(10.dp))
                                SocialMedia(
                                    icon = R.drawable.ic_github,
                                    mediaName = stringResource(id = R.string.github_label),
                                    link = stringResource(id = R.string.github_link)
                                )
                                Spacer(modifier = modifier.heightIn(10.dp))
                                SocialMedia(
                                    icon = R.drawable.ic_linkedin,
                                    mediaName = stringResource(id = R.string.linkedin_label),
                                    link = stringResource(id = R.string.linkedin_link)
                                )
                                Spacer(modifier = modifier.heightIn(10.dp))
                                SocialMedia(
                                    icon = R.drawable.ic_email,
                                    mediaName = stringResource(id = R.string.email_label),
                                    link = stringResource(id = R.string.email_link)
                                )
                                Spacer(modifier = modifier.heightIn(10.dp))
                                SocialMedia(
                                    icon = R.drawable.ic_twitter,
                                    mediaName = stringResource(id = R.string.title_label),
                                    link = stringResource(id = R.string.twitter_link)
                                )
                            }
                            Column(modifier = modifier.weight(0.7f)) {
                                Section(
                                    modifier = modifier,
                                    sectionName = aboutHeader,
                                    desc = aboutDesc
                                )
                                Section(
                                    modifier = modifier,
                                    sectionName = "Services",
                                    desc = serviceDesc
                                )
                                Section(
                                    modifier = modifier,
                                    sectionName = "Skills",
                                    desc = "........"
                                )
                            }
                        }


                    }
                }
            }
        }

    }


}

@Composable
private fun ProfilePicture(modifier: Modifier, size: Dp) {
    Image(
        painter = painterResource(id = R.drawable._profile),
        contentDescription = null,
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(10.dp))
    )
}

@Composable
private fun Location(modifier: Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = null,
            modifier = modifier
                .size(24.dp)
                .padding(end = 5.dp, top = 5.dp, bottom = 5.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
        )
        Text(
            text = stringResource(id = R.string.location_label),
            fontSize = 12.sp
        )
    }
}

@Composable
private fun TextCompose(text:String) {
    Text(text = text)
}

@Composable
private fun Name() {
    Text(
        text = stringResource(id = R.string.name_label),
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = PrefsStore(context = context)
    val isDarkModeActive =
        dataStore.getMode.collectAsState(initial = isSystemInDarkTheme()).value ?: false

    val isDarkMode by remember { mutableStateOf(isDarkModeActive) }
    var showMenu by remember { mutableStateOf(false) }
    var langDialog by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = "") },
        actions = {
            IconToggleButton(
                modifier = modifier.clickable {
                },
                checked = isDarkMode,
                onCheckedChange = {
                    scope.launch {
                        if (isDarkModeActive) {
                            dataStore.toggleDarkMode(isDarkMode)
                        } else {
                            dataStore.toggleDarkMode(!isDarkMode)
                        }
                    }

                }) {
                Icon(
                    painter = if (isDarkModeActive) {
                        painterResource(id = R.drawable.ic_dark_mode)
                    } else {
                        painterResource(id = R.drawable.ic_light)
                    },
                    contentDescription = null
                )
            }
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(onClick = {
                    langDialog = true

                }) {
                    Text(text = stringResource(R.string.language_label))
                }
            }

        }
    )
    if (langDialog) {
        ShowLangDialog(setShowDialog = {
            langDialog=it
        }, setShowMenu = {showMenu=it})
    }

}

@Composable
fun ShowLangDialog(modifier: Modifier = Modifier, setShowDialog:(Boolean)-> Unit, setShowMenu:(Boolean)-> Unit) {
    val languages = mapOf("Default" to "en", "Spanish" to "es", "Swahili" to "sw")
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = PrefsStore(context = context)

    Dialog(
        onDismissRequest = { setShowDialog( false )}
    ) {
        Surface(shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(20.dp)
        ) {

            Column(modifier = modifier.padding(25.dp)) {
                Text(text = stringResource(R.string.select_lang_label),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
                )
                Spacer(modifier = modifier.heightIn(15.dp))
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(languages.size) { pos ->
                        val lang = languages.keys.elementAt(pos)
                        Text(text = lang,
                            fontSize = 16.sp,
                            modifier = modifier
                                .fillMaxWidth()
                                .clickable {
                                    scope.launch {
                                        dataStore.setLanguage(languages.getValue(lang))
                                        setShowDialog(false)
                                        setShowMenu(false)
                                        (context as MainActivity).recreate()
                                    }
                                }
                        )
                    }
                }
            }

        }

    }
}

@Composable
private fun Section(modifier: Modifier, sectionName: String, desc: String) {
    Text(
        text = sectionName,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )

    Text(
        text = desc,
        fontSize = 16.sp,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
private fun SocialMedia(modifier: Modifier = Modifier, icon: Int, mediaName: String, link: String) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = mediaName,
                modifier = modifier
                    .size(30.dp)
                    .padding(end = 10.dp),
                colorFilter = if (mediaName == stringResource(id = R.string.github_label) ||
                    mediaName == stringResource(id = R.string.email_label)
                ) {
                    ColorFilter.tint(MaterialTheme.colors.onSurface)
                } else {
                    null
                }


            )
            Text(
                text = mediaName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

        }
        Text(text = link)
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true,
    //uiMode = UI_MODE_NIGHT_YES,
    //device = Devices.AUTOMOTIVE_1024p
)
@Composable
fun DefaultPreview() {
    MyResumeTheme {
        MainContent()
    }
}
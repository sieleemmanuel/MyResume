package com.siele.myresume

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.siele.myresume.ui.theme.MyResumeTheme
import kotlinx.coroutines.launch


@Composable
fun MainContent(modifier: Modifier = Modifier, navController: NavController) {
    val scrollState = rememberScrollState()
    Surface {
        Scaffold(topBar = { TopBar(navController = navController) }) { paddingValues ->
            BoxWithConstraints(
                modifier = modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                val aboutHeader = stringResource(R.string.about_me_label)
                val serviceHeader = stringResource(R.string.services_label)
                val experienceHeader = stringResource(R.string.experience_label)
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
                            SocialMedia(
                                icon = R.drawable.ic_github,
                                mediaName = stringResource(R.string.github_label),
                                link = stringResource(R.string.github_link)
                            ){
                                navController.navigate(Screen.WebViewScreen.route +"/https://github.com/sieleemmanuel")
                            }
                            SocialMedia(
                                    icon = R.drawable.ic_linkedin,
                            mediaName = stringResource(R.string.linkedin_label),
                            link = stringResource(R.string.linkedin_link)
                            ){

                            }

                            SocialMedia(
                                    icon = R.drawable.ic_email,
                            mediaName = stringResource(R.string.email_label),
                            link = stringResource(R.string.email_link)
                            ){

                            }
                            SocialMedia(
                                icon = R.drawable.ic_twitter,
                                mediaName = stringResource(R.string.twitter_label),
                                link = stringResource(R.string.twitter_link)
                            ){

                            }
                        }

                        Section(
                            modifier = modifier, sectionName = null,
                            desc = aboutDesc
                        )

                        Spacer(modifier = modifier.heightIn(20.dp))
                        Divider(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            thickness = 2.dp
                        )

                        Spacer(modifier = modifier.heightIn(20.dp))

                       /* Section(
                            modifier = modifier,
                            sectionName = serviceHeader,
                            desc = serviceDesc,
                        )
                        Spacer(modifier = modifier.heightIn(20.dp))*/
                        Section(
                            modifier = modifier,
                            sectionName = stringResource(R.string.skills_label),
                            desc = null
                        )

                        Spacer(modifier = modifier.heightIn(20.dp))
                        SkillsSections()

                        Spacer(modifier = modifier.heightIn(20.dp))
                        Section(
                            modifier = modifier,
                            sectionName = "Education",
                            desc = null
                        )
                        Spacer(modifier = modifier.heightIn(20.dp))
                        Section(
                            modifier = modifier,
                            sectionName = stringResource(id = R.string.experience_label),
                            desc = null
                        )


                        Spacer(modifier = modifier.heightIn(20.dp))
                        ExperienceContent()
                    }

                } else {
/*
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
                                ){}
                                Spacer(modifier = modifier.heightIn(10.dp))
                                SocialMedia(
                                    icon = R.drawable.ic_linkedin,
                                    mediaName = stringResource(id = R.string.linkedin_label),
                                    link = stringResource(id = R.string.linkedin_link)
                                ){}
                                Spacer(modifier = modifier.heightIn(10.dp))
                                SocialMedia(
                                    icon = R.drawable.ic_email,
                                    mediaName = stringResource(id = R.string.email_label),
                                    link = stringResource(id = R.string.email_link)
                                ){}
                                Spacer(modifier = modifier.heightIn(10.dp))
                                SocialMedia(
                                    icon = R.drawable.ic_twitter,
                                    mediaName = stringResource(id = R.string.title_label),
                                    link = stringResource(id = R.string.twitter_link)
                                ){}
                            }
                            Column(modifier = modifier.weight(0.7f)) {
                                Section(
                                    modifier = modifier,
                                    sectionName = aboutHeader,
                                    desc = aboutDesc,
                                )
                                Section(
                                    modifier = modifier,
                                    sectionName = "Services",
                                    desc = serviceDesc,
                                )
                                Section(
                                    modifier = modifier,
                                    sectionName = "Skills",
                                    desc = null
                                )
                                SkillsSections()
                                Section(
                                    modifier = modifier,
                                    sectionName = stringResource(id = R.string.experience_label),
                                    desc = null
                                )
                            }
                        }


                    }*/
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
fun TopBar(modifier: Modifier = Modifier, navController: NavController) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = PrefsStore(context = context)
    val isDarkModeActive =
        dataStore.getMode.collectAsState(initial = isSystemInDarkTheme()).value ?: false

    val isDarkMode by remember { mutableStateOf(isDarkModeActive) }
    var showMenu by remember { mutableStateOf(false) }
    var langDialog by remember { mutableStateOf(false) }
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
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
                DropdownMenuItem(onClick = {
                    navController.navigate(route = Screen.SettingsScreen.route)

                }) {
                    Text(text = stringResource(R.string.settings_label))
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
private fun Section(
    modifier: Modifier,
    sectionName: String?,
    desc: String?,
) {
    if (sectionName != null) {
        Text(
            text = sectionName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }

    desc?.let {
        Text(
        text = it,
        fontSize = 16.sp,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    }
}

@Composable
private fun SocialMedia(
    modifier: Modifier = Modifier,
    icon: Int,
    mediaName: String,
    link: String,
    onClick:(String)->Unit
) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = mediaName,
                modifier = modifier
                    .border(
                        width = 0.1.dp,
                        color = MaterialTheme.colors.onSurface,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .size(40.dp)
                    .padding(10.dp)
                    .clickable(onClick = { onClick(link) })
                ,
                colorFilter = if (mediaName == stringResource(id = R.string.github_label) ||
                    mediaName == stringResource(id = R.string.email_label)
                ) {
                    ColorFilter.tint(MaterialTheme.colors.onSurface)
                } else {
                    null
                }


            )



}

@Composable
fun SkillsSections(modifier: Modifier = Modifier) {
    val state = rememberLazyGridState()
    val skills = listOf(
        Skill("Kotlin", R.drawable.ic_kotlin),
        Skill("Android SDK", R.drawable.ic_android),
        Skill("Jetpack Compose", R.drawable.ic_github),
        Skill("GitHub", R.drawable.ic_github),
        Skill( "Java", R.drawable.ic_java),
        Skill( "Dagger Hilt", R.drawable.ic_dagger)
                    )
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 112.dp),
        contentPadding = PaddingValues( 10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(256.dp)
    ){
        items(skills.size){ skillPosition ->
            Card (
                shape = RoundedCornerShape(5.dp),
                modifier = modifier.heightIn(112.dp)
                    ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = skills[skillPosition].icon),
                        contentDescription = null,
                        colorFilter = if (skills[skillPosition].skill == stringResource(id = R.string.github_label) ||
                            skills[skillPosition].skill == stringResource(id = R.string.email_label)
                        ) {
                            ColorFilter.tint(MaterialTheme.colors.onSurface)
                        } else {
                            null
                        }
                    )
                    Spacer(modifier = modifier.heightIn(16.dp))
                    Text(
                        text = skills[skillPosition].skill,
                        textAlign = TextAlign.Center,
                        modifier = modifier.fillMaxWidth()
                    )
                }
            }

        }
    }

}

@Composable
fun ExperienceContent(modifier: Modifier = Modifier) {
    val state = rememberLazyGridState()
    val experiences = listOf("Android Engineer", "Android Intern", "Mobile Intern", "Mobile Engineering Program Intern")

    LazyColumn(modifier = modifier
        .fillMaxWidth()
        .height(320.dp),
        contentPadding = PaddingValues(16.dp)
    ){
        items(experiences.size){ position ->
            Row(modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(start = 20.dp, top = 0.dp)) {
                Column(modifier = modifier
                    .fillMaxHeight()
                    .weight(.1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(
                        modifier = modifier
                            .height(40.dp)
                            .width(2.dp)
                            .padding(bottom = 5.dp)
                            .background(
                                if (position == 0) {
                                    Color.Transparent
                                } else {
                                    MaterialTheme.colors.onSurface
                                }
                            )
                    )

                    Box(modifier = modifier
                        .background(
                            color = if (position == 0) {
                                Color.Yellow
                            } else {
                                MaterialTheme.colors.onSurface
                            }, shape = CircleShape
                        )
                        .size(8.dp)
                        .padding(2.dp)



                    )
                    Spacer(
                        modifier = modifier
                            .fillMaxHeight()
                            .width(2.dp)
                            .padding(top = 5.dp)
                            .background(MaterialTheme.colors.onSurface)
                            .offset(y = (-24).dp)
                    )
                }
                Spacer(modifier = modifier.width(20.dp))
                Column(modifier = modifier
                    .fillMaxWidth()
                    .weight(.8f)
                    .padding(top = 16.dp)) {
                    Text(text = experiences[position])
                    Text(text = "Forage Company")
                    Text(text = "Oct 2022 - Dec 2022", fontWeight = FontWeight.Light)
                }

            }

        }
    }
}

@Composable
fun EducationSections(modifier: Modifier = Modifier) {

}
/*@Preview(
    showBackground = true,
    showSystemUi = true,
    //uiMode = UI_MODE_NIGHT_YES,
    //device = Devices.AUTOMOTIVE_1024p
)*/
@Composable
fun DefaultPreview() {
    MyResumeTheme {
        //MainContent(navController = rememberNavController())
        //SkillsSections()
        ExperienceContent()
    }
}
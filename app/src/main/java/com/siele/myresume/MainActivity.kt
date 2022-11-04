package com.siele.myresume

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.siele.myresume.ui.theme.MyResumeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = this
            val dataStore = PrefsStore(context = context)
            val isDarkModeActive =
                dataStore.getMode.collectAsState(initial = isSystemInDarkTheme()).value ?: false
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
        BoxWithConstraints(
            modifier = modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {

            val aboutHeader = "About Me"
            val serviceHeader = "Services"
            val aboutDesc = "Hello, I a Siele Emmanuel a Native android developer " +
                    "who is simple, innovative and tech enthusiastic. I like building " +
                    "robust and scalable android application that provides " +
                    "solutions requiring technical approaches. Currently, working as a Hngi9 Mobile Intern"
            val serviceDesc =
                "I help companies come up solution to real-world problems through " +
                        "research, analysis, building, and developing robust and scalable native android " +
                        "application"
            if (this.maxWidth < 600.dp) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    TopBar()
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp),
                        //horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable._profile),
                            contentDescription = "Profile picture",
                            modifier = modifier
                                .size(90.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )
                        Spacer(modifier = modifier.width(20.dp))

                        Column {
                            Text(
                                text = "Siele Emmanuel ",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = modifier.heightIn(5.dp))
                            Text(text = "Native Android Developer")
                            Row(verticalAlignment = Alignment.CenterVertically){
                                Image(
                                    painter = painterResource(id = R.drawable.ic_location),
                                    contentDescription = "Profile picture",
                                    modifier = modifier
                                        .size(24.dp)
                                        .padding(end = 5.dp, top = 5.dp, bottom = 5.dp)
                                )
                            Text(text = "Nairobi, Kenya",  fontSize = 12.sp)
                            }
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
                            SocialMedia(icon = R.drawable.ic_github, mediaName = "Github", link = "https://github.com/sieleemmanuel")
                            SocialMedia(icon = R.drawable.ic_linkedin, mediaName = "Linkedin",link = "https://linkedin.com/in/siele-emmanuel")

                        }
                        Column(
                            modifier = modifier.weight(.5f),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            SocialMedia(
                                icon = R.drawable.ic_email,
                                mediaName = "Email" ,link = "sielekim.emmanuel@gmail.com"
                            )
                            SocialMedia(icon = R.drawable.ic_twitter, mediaName = "Twitter", link = "https://twitter.com/sielekim")
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
                        sectionName = "Skills",
                        desc = "......."
                    )


                }
            } else {

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    TopBar()
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp)
                    ) {

                        Column(
                            modifier = modifier.weight(0.3f),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable._profile),
                                contentDescription = "Profile picture",
                                modifier = modifier
                                    .size(70.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                            Spacer(modifier = modifier.heightIn(5.dp))
                            Text(
                                text = "Siele Emmanuel ",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = modifier.heightIn(5.dp))
                            Text(text = "Native Android Developer")
                            Row(verticalAlignment = Alignment.CenterVertically){
                                Image(
                                    painter = painterResource(id = R.drawable.ic_location),
                                    contentDescription = "Profile picture",
                                    modifier = modifier
                                        .size(24.dp)
                                        .padding(end = 5.dp, top = 5.dp, bottom = 5.dp)
                                )
                                Text(text = "Nairobi, Kenya",
                                    fontSize = 12.sp
                                )
                            }
                            Spacer(modifier = modifier.heightIn(10.dp))
                            SocialMedia(icon = R.drawable.ic_github, mediaName = "Github", link = "https://github.com/sieleemmanuel")
                            Spacer(modifier = modifier.heightIn(10.dp))
                            SocialMedia(icon = R.drawable.ic_linkedin, mediaName = "Linkedin",link = "https://linkedin.com/in/siele-emmanuel")
                            Spacer(modifier = modifier.heightIn(10.dp))
                            SocialMedia( icon = R.drawable.ic_email,   mediaName = "Email", link = "sielekim.emmanuel@gmail.com" )
                            Spacer(modifier = modifier.heightIn(10.dp))
                            SocialMedia(icon = R.drawable.ic_twitter, mediaName = "Twitter", link = "https://twitter.com/sielekim")
                        }
                        Column(modifier = modifier.weight(0.7f)) {
                            Section(modifier = modifier, sectionName = aboutHeader, desc = aboutDesc)
                            Section(modifier = modifier, sectionName = "Services", desc = serviceDesc)
                            Section(modifier = modifier, sectionName = "Skills", desc = serviceDesc)
                        }
                    }



                }
            }
        }
    }


}

@Composable
fun TopBar(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = PrefsStore(context = context)
    val isDarkModeActive =
        dataStore.getMode.collectAsState(initial = isSystemInDarkTheme()).value ?: false

    var isDarkMode by remember { mutableStateOf(isDarkModeActive) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(end = 10.dp),
        horizontalArrangement = Arrangement.End
    ) {
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
private fun SocialMedia(modifier: Modifier = Modifier, icon: Int, mediaName: String, link:String) {
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
                colorFilter = if(mediaName=="Github" || mediaName=="Email") {
                    ColorFilter.tint(MaterialTheme.colors.onSurface)
                }else{
                    null
                }


            )
            Text(
                text = mediaName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

        }
        Text(text = link )
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES,
    //device = Devices.AUTOMOTIVE_1024p,
)
@Composable
fun DefaultPreview() {
    MyResumeTheme {
        MainContent()
    }
}
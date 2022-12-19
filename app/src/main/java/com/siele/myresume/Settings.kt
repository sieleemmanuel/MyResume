package com.siele.myresume

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jamal.composeprefs.ui.PrefsScreen
import com.jamal.composeprefs.ui.prefs.DropDownPref
import com.jamal.composeprefs.ui.prefs.ListPref
import com.jamal.composeprefs.ui.prefs.MultiSelectListPref
import com.jamal.composeprefs.ui.prefs.SwitchPref
import com.siele.myresume.ui.theme.MyResumeTheme

@Composable
fun SettingsScreen(navController: NavHostController) {
    val context = LocalContext.current
    Scaffold(topBar = {
        WebTopBar(
            title = "Settings",
            imageVector = Icons.Default.ArrowBack,
            iconClick = {
                navController.popBackStack()
                Toast.makeText(context, "PopBackstack", Toast.LENGTH_SHORT).show()
            }
        )
    }) { paddingValues ->
        SettingsContent(paddingValues = paddingValues)
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SettingsContent(paddingValues: PaddingValues, modifier: Modifier = Modifier) {
    var switchState by remember{ mutableStateOf(false) }
    val context = LocalContext.current
    /*Column(modifier = modifier.padding(paddingValues)) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_dark_mode),
                contentDescription = null,
                modifier = modifier
                    .size(30.dp)
                    .weight(0.2f),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
            )
            Spacer(modifier = modifier
                .width(20.dp)
                .weight(0.1f))
            Column(modifier = modifier.weight(0.55f)) {
                Text(
                    text = "Dark Mode",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Dark Mode on or off"
                )
            }
            Switch(
                checked = switchState,
                onCheckedChange ={switchState = it},
                modifier = modifier.weight(0.2f)
            )

        }
        Divider(modifier = modifier.fillMaxWidth(), thickness = 2.dp)
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_language),
                contentDescription = null,
                modifier = modifier
                    .size(30.dp)
                    .weight(0.15f),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
            )
            Spacer(modifier = modifier
                .width(20.dp)
                .weight(0.08f))
            Column(modifier = modifier.weight(0.77f)) {
                Text(
                    text = "Language",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Default(English)"
                )
            }

        }
        Divider(modifier = modifier.fillMaxWidth(), thickness = 2.dp)
    }*/

    PrefsScreen(dataStore = context.dataStore ){
        prefsItem {
            SwitchPref(
                key = "dark_mode",
                title = "Dark Mode",
                summary = "Dark mode on or off",
                leadingIcon = {
                    Icon(painter = painterResource(
                        id = R.drawable.ic_dark_mode),
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            )
        }

        prefsItem {
            ListPref(
                key = "lang",
                title = "Change Language",
                useSelectedAsSummary = true,
                summary = "English(Default)",
                defaultValue = "English(Default)",
                entries = mapOf(
                    "en" to "Default(en)",
                    "es" to "Spanish",
                    "sw" to "Swahili"
                )
            )
        }

        prefsItem {
        }
    }

}

@Preview()
@Composable
fun SettingPreview() {
    MyResumeTheme(darkTheme = false) {
        SettingsScreen(navController = rememberNavController())
    }
}

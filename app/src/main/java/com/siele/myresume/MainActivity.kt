@file:Suppress("DEPRECATION")

package com.siele.myresume

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.siele.myresume.ui.theme.MyResumeTheme
import java.util.*

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name= "settings")
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
                    NavigationResGraph()

                }
            }
        }
    }
}

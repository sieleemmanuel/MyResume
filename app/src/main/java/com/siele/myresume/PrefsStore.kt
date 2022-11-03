package com.siele.myresume

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PrefsStore(private val context:Context) {

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name= "settings")
        val darkModeKey = booleanPreferencesKey("dark_mode")

    }
    val getMode:Flow<Boolean?> = context.dataStore.data
        .map { prefs ->
            prefs[darkModeKey]?:false
        }

    suspend fun toggleDarkMode(isDarkMode:Boolean) {
        context.dataStore.edit { mutablePrefs ->
            mutablePrefs[darkModeKey]=isDarkMode
        }
    }
}
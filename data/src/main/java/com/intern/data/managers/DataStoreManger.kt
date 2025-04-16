package com.intern.data.managers

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManger(private val context: Context) {
    suspend fun saveTheme(themeMode: Boolean) {
        context.dataStore.edit { pref ->
            pref[booleanPreferencesKey("theme_mode")] = themeMode
        }
    }

    fun getSettings() = context.dataStore.data.map { pref ->
        pref[booleanPreferencesKey("theme_mode")] ?: false
    }
}
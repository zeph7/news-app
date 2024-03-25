package com.zeph7.newsapp.feature_auth.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.zeph7.newsapp.feature_auth.util.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first

class AuthPreferenceManager(
    private val context: Context
) {

    suspend fun setAppEntry() {
        context.authDataStore.edit { preferences ->
            preferences[PreferenceKeys.APP_ENTRY_KEY] = true
        }
    }

    suspend fun getAppEntry(): Boolean {
        val preferences = context.authDataStore.data.catch { emit(emptyPreferences()) }.first()
        return preferences[PreferenceKeys.APP_ENTRY_KEY] ?: false
    }

    private val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_settings")

    private object PreferenceKeys {
        val APP_ENTRY_KEY = booleanPreferencesKey(Constants.APP_ENTRY)
    }

}
package aej.dino.netflixcloneapps.core.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieDataStore constructor(
    private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "netflix_clone.pb")

    suspend fun <T> storeData(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preference ->
            preference[key] = value
        }
    }

    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.remove(USERNAME)
            preferences.remove(TOKEN)
            preferences.remove(EMAIL)
            preferences.remove(ID)
        }
    }

    val username: Flow<String>
        get() = context.dataStore.data.map { preferences ->
            preferences[USERNAME] ?: "tes"
        }

    val email: Flow<String>
        get() = context.dataStore.data.map { preferences ->
            preferences[EMAIL] ?: ""
        }

    val id: Flow<String>
        get() = context.dataStore.data.map { preferences ->
            preferences[ID] ?: ""
        }

    val token: Flow<String>
        get() = context.dataStore.data.map { preferences -> preferences[TOKEN] ?: "" }

    companion object {
        val USERNAME = stringPreferencesKey("USERNAME")
        val EMAIL = stringPreferencesKey("EMAIL")
        val ID = stringPreferencesKey("ID")
        val TOKEN = stringPreferencesKey("TOKEN")
    }

}
package org.sopt.at.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.sopt.at.local.model.SignInUserModel

private val Context.userdataStore: DataStore<Preferences> by preferencesDataStore("DataStoreModule")

class UserLocalDataStore(private val context: Context) {

    private val datastore = context.userdataStore

    val signInUserModel: Flow<SignInUserModel> =
        datastore.data.map { preferences ->
            SignInUserModel(
                preferences[USERID] ?: -1
            )
        }

    suspend fun saveUserId(userId: Long) {
        datastore.edit { preferences ->
            preferences[USERID] = userId
        }
    }

    companion object {
        private val USERID = longPreferencesKey("userId")
    }
}
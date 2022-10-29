package com.matikano.complimentapp.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.first
abstract class BaseDataStore(
    context: Context,
) {

    abstract val name: String

    private val dataStore by lazy {
        context.createDataStore(name)
    }

    suspend fun <T> set(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun <T> get(key: Preferences.Key<T>): T?  = dataStore.data.first()[key]
}


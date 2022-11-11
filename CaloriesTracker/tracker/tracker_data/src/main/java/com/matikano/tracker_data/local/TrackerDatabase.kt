package com.matikano.tracker_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matikano.tracker_data.local.TrackerDatabase.Companion.DATABASE_VERSION
import com.matikano.tracker_data.local.entity.TrackedFoodEntity

@Database(
    entities = [TrackedFoodEntity::class],
    version = DATABASE_VERSION
)
abstract class TrackerDatabase: RoomDatabase() {

    abstract val dao: TrackerDao

    companion object {
        const val DATABASE_NAME = "tracker_db"
        const val DATABASE_VERSION = 1
    }
}
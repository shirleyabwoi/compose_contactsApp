package com.mosaic.contactsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mosaic.contactsapp.model.Contacts


@Database(entities = arrayOf(Contacts::class), version = 1)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactsDao

    companion object {
        private var database: ContactsDatabase? = null
        fun getDatabase(context: Context): ContactsDatabase {
            if (database == null) {
                database = Room
                    .databaseBuilder(
                        context, ContactsDatabase::class.java,
                        "contactsDb"
                    )
                    .fallbackToDestructiveMigration(true)
                    .build()
            }
            return database as ContactsDatabase

        }
    }
}


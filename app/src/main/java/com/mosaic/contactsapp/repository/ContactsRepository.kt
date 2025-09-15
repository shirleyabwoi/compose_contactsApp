package com.mosaic.contactsapp.repository

import androidx.lifecycle.LiveData
import com.mosaic.contactsapp.ContactsApp
import com.mosaic.contactsapp.database.ContactsDatabase
import com.mosaic.contactsapp.model.Contacts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository {
    val database = ContactsDatabase.getDatabase(ContactsApp.appContext)

    suspend fun saveContact(contact: Contacts) {
        withContext(Dispatchers.IO) {
            database.contactDao().insertContact(contact)
        }
    }

    fun getALlContacts(): LiveData<List<Contacts>> {
        return database.contactDao().getAllContacts()
    }

    fun getContactById(contactId: Int): LiveData<Contacts> {
        return database.contactDao().getContactById(contactId)

    }
}
package com.mosaic.contactsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosaic.contactsapp.model.Contacts
import com.mosaic.contactsapp.repository.ContactsRepository
import kotlinx.coroutines.launch

class ContactsViewModel: ViewModel() {
    val contactsRepository = ContactsRepository()
    val contactsLiveData = MutableLiveData<List<Contacts>>()

    val contactLiveData = MutableLiveData<Contacts>()

    fun saveContact(contacts: Contacts) {
        viewModelScope.launch {
            contactsRepository.saveContact(contacts)
        }
    }

    fun getAllContacts() {
        viewModelScope.launch {
            contactsRepository.getALlContacts().observeForever {
                contactsLiveData.value = it
            }

        }


    }
    fun getContactById(contactId:Int){
        viewModelScope.launch {
            contactsRepository.getContactById(contactId).observeForever {
                contactLiveData.value=it
            }
        }
    }
}
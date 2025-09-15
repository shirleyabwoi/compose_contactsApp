package com.mosaic.contactsapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mosaic.contactsapp.model.Contacts
import com.mosaic.contactsapp.viewModel.ContactsViewModel


@Composable
fun AddContactScreen(onClickBack: ()-> Unit,
                     viewModel: ContactsViewModel = viewModel()
){
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    val mod = Modifier.fillMaxWidth()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Spacer(Modifier.height(24.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.clickable { onClickBack() })
            Spacer(Modifier.width(24.dp))
            Text(text = "Add Contact", style = MaterialTheme.typography.titleLarge)
        }
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            modifier = mod,
            value = name,
            label = {Text(text="Name")},
            onValueChange = {name=it}
        )
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(
            modifier = mod,
            value = phoneNumber,
            label = {Text(text="Phone Number")},
            onValueChange = {phoneNumber=it},

            )
        Spacer(Modifier.height(24.dp))



        OutlinedTextField(
            modifier = mod,
            value = email,
            label = {Text(text="Email")},
            onValueChange = {email=it}
        )
        Spacer(Modifier.height(24.dp))
        Button(onClick = {viewModel.saveContact(
            Contacts(
                contactId = 0,
                name = name,
                phoneNumber = phoneNumber,
                email = email,
                imageUrl = ""
            )
        )}, modifier = mod) {
            Text(text = "Save Contact")
        }
    }
}
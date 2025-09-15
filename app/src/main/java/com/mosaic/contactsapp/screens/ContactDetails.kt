package com.mosaic.contactsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mosaic.contactsapp.viewModel.ContactsViewModel

@Composable
fun ContactDetailScreen(contactId: Int, viewModel: ContactsViewModel= viewModel()){
    LaunchedEffect(Unit) {
        viewModel.getContactById(contactId)
    }
    val contact by viewModel.contactLiveData.observeAsState()


    Column (Modifier.fillMaxSize().padding(16.dp)){
        Image(imageVector = Icons.Default.AccountCircle,
            contentDescription = "",
            modifier = Modifier.weight(0.4f).size(256.dp))

        Column (Modifier.weight(0.6f).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally){

            contact?.let {
                Text(text=it.name)
                Spacer(Modifier.height(16.dp))
                Text(text=it.phoneNumber)
                Spacer(Modifier.height(16.dp))
                Text(text=it.email)
                Spacer(Modifier.height(16.dp))
                Row {
                    Icon(imageVector = Icons.Default.Call,
                        contentDescription = "")
                }
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Outlined.Send,
                    contentDescription = ""
                )
                Spacer(Modifier.height(16.dp))
            }

        }



    }
}
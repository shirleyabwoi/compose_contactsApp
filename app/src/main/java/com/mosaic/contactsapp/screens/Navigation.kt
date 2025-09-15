package com.mosaic.contactsapp.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String){
    object Contacts: Screen("contacts")
    object AddContact: Screen("addContact")

    object ContactDetails: Screen("contactDetails")
}
@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Contacts.route)
    {
        composable(Screen.Contacts.route) {
            ContactsScreen({navController.navigate(Screen.AddContact.route)},
                onClickContact = {contactId ->navController.navigate("${Screen.ContactDetails.route}/$contactId")}
            )

        }
        composable(Screen.AddContact.route) {
            AddContactScreen(onClickBack = {navController.popBackStack()})
        }
        composable("${Screen.ContactDetails.route}/{contactId}"){navBackStackEntry ->
            val contactId = navBackStackEntry.arguments?.getString("contactId")
            if (contactId != null) {
                ContactDetailScreen(contactId.toInt())
            }
        }
    }
}

package com.mosaic.contactsapp.screens

import android.Manifest
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.mosaic.contactsapp.viewModel.ContactsViewModel
import java.io.File
import java.util.Date
import java.util.Locale // Correct import for Locale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContactDetailScreen(contactId: Int, viewModel: ContactsViewModel= viewModel())
{
    val ctx = LocalContext.current
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var tempPhotoUri by remember {mutableStateOf<Uri?>(null)}

    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) {success ->
        if (success && tempPhotoUri !=null){
            photoUri=tempPhotoUri
        }
    }
    val permissionLauncher= rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {isGranted ->
        if(isGranted){
            tempPhotoUri = createImageUri(ctx)
            tempPhotoUri?.let { uri ->
                cameraLauncher.launch(uri)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getContactById(contactId)
    }
    val contact by viewModel.contactLiveData.observeAsState()


    Column (Modifier.fillMaxSize().padding(16.dp)){
        Image(imageVector = Icons.Default.AccountCircle,
            contentDescription = "",
            modifier = Modifier.weight(0.4f).size(200.dp))

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

fun createImageUri(context: Context): Uri?{
    return try {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss",
            Locale.getDefault()).format(Date()) // Correct usage of Locale.getDefault()
        val fileName ="JPEG_$timestamp"
        val storageDir = File(context.getExternalFilesDir(null), "Pictures")
        if(!storageDir.exists()){
            storageDir.mkdirs()
        }
        val imageFile = File.createTempFile(fileName, "jpg", storageDir)
        FileProvider.getUriForFile(context, "${context.packageName}.provider",
            imageFile)


    }
    catch (e: Exception){
        e.printStackTrace()
        null
    }

}

package com.nikhilproject.presentation.screens.accountsscreen

import android.Manifest
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nikhilproject.presentation.UiState
import com.nikhilproject.presentation.screens.components.ShowProgressDialog
import com.nikhilproject.presentation.screens.components.ShowToastMessage
import com.nikhilproject.presentation.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun EditProfileScreen(initialProfilePicUrl: String) {
    val userViewModel: UserViewModel = hiltViewModel()
    val accessToken = userViewModel.getAccessToken()
    val updateProfileState by userViewModel.updateProfileUiState.collectAsState()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var showLoading by remember { mutableStateOf(false) }
    var messageToShow by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val dobFormatter = remember { SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()) }
    val calendar = remember { Calendar.getInstance() }

    val cameraPermission = Manifest.permission.CAMERA

    val contentResolver = context.contentResolver

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var shouldShowDialogAfterPermission by remember { mutableStateOf(false) }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let {
            val uri = Uri.parse(MediaStore.Images.Media.insertImage(contentResolver, it, null, null))
            imageUri = uri
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            imageUri = it
        }
    }

    val storagePermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val hasCamera = permissions[cameraPermission] == true
        val hasStorage = permissions[storagePermission] == true

        if (hasCamera && hasStorage) {
            if (shouldShowDialogAfterPermission) {
                showDialog = true
                shouldShowDialogAfterPermission = false
            }
        } else {
            Toast.makeText(context, "Permissions denied", Toast.LENGTH_SHORT).show()
        }
    }

    val handleImageClick = {
        val hasCameraPermission = ContextCompat.checkSelfPermission(context, cameraPermission) == PackageManager.PERMISSION_GRANTED
        val hasStoragePermission = ContextCompat.checkSelfPermission(context, storagePermission) == PackageManager.PERMISSION_GRANTED

        if (hasCameraPermission && hasStoragePermission) {
            showDialog = true
        } else {
            shouldShowDialogAfterPermission = true
            permissionLauncher.launch(arrayOf(cameraPermission, storagePermission))
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Choose Image From") },
            confirmButton = {
                Text("Camera", modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showDialog = false
                        cameraLauncher.launch(null)
                    }
                    .padding(8.dp))
            },
            dismissButton = {
                Text("Gallery", modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showDialog = false
                        galleryLauncher.launch("image/*")
                    }
                    .padding(8.dp))
            }
        )
    }

    val showDatePicker = {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                dob = dobFormatter.format(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    LaunchedEffect(updateProfileState) {
        when (val state = updateProfileState) {
            is UiState.Loading -> showLoading = true
            is UiState.Success -> {
                showLoading = false
                messageToShow = state.data
            }

            is UiState.Error -> {
                showLoading = false
                messageToShow = state.message
            }

            else -> {}
        }
    }

    ShowProgressDialog(showLoading)

    ShowToastMessage(messageToShow) {
        messageToShow = it
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        AsyncImage(
            model = imageUri ?: initialProfilePicUrl,
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape)
                .clickable { handleImageClick() }
        )

        Spacer(modifier = Modifier.height(32.dp))

        ProfileTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = "First Name",
            icon = Icons.Default.Person
        )

        ProfileTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = "Last Name",
            icon = Icons.Default.Person
        )
        ProfileTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            icon = Icons.Default.Email,
            keyboardType = KeyboardType.Email
        )
        ProfileTextField(
            value = phone,
            onValueChange = { phone = it },
            label = "Phone Number",
            icon = Icons.Default.Phone,
            keyboardType = KeyboardType.Phone
        )

        ProfileTextField(
            value = dob,
            onValueChange = { },
            label = "DOB",
            icon = Icons.Default.DateRange,
            enabled = false,
            onClick = { showDatePicker() }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                userViewModel.updateProfile(
                    token = accessToken ?: "",
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    dob = dob,
                    phoneNo = phone,
                    profilePic = imageUri?.toString() ?: initialProfilePicUrl
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "SUBMIT", color = Color.Red)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White) },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = label, tint = Color.White)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            disabledBorderColor = Color.White,
            disabledTextColor = Color.White,
            disabledLabelColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .let {
                if (onClick != null) it.clickable { onClick() } else it
            },
        enabled = enabled,
        readOnly = onClick != null
    )
}

@Preview
@Composable
private fun EditProfileScreenPreview() {
    EditProfileScreen(initialProfilePicUrl = "")
}

fun createImageUri(context: Context): Uri? {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "profile_${System.currentTimeMillis()}.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/ProfileApp")
    }
    return context.contentResolver.insert(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValues
    )
}

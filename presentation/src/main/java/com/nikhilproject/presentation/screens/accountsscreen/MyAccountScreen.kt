package com.nikhilproject.presentation.screens.accountsscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nikhilproject.domain.model.DashboardResponse
import com.nikhilproject.presentation.UiState
import com.nikhilproject.presentation.viewmodel.UserViewModel
import com.nikhilproject.presentation.viewmodel.HomeScreenViewModel

@Composable
fun MyAccountScreen(
    onEditProfileClicked: (String?) -> Unit,
    onResetPasswordClicked: () -> Unit
) {
    val userViewModel = hiltViewModel<UserViewModel>()
    val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
    val token = userViewModel.getAccessToken()

    val uiState by homeScreenViewModel.dashboardUiState.collectAsState()

    LaunchedEffect(Unit) {
        homeScreenViewModel.fetchDashboard(accessToken = token ?: "")
    }

    when (uiState) {
        is UiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val dashboardResponse = (uiState as UiState.Success<DashboardResponse>).data
            val userData = dashboardResponse.productData.user_data

            MyAccountContent(
                firstName = userData.firstName,
                lastName = userData.lastName,
                email = userData.email,
                phone = "",
                dob = userData.dob ?: "",
                profilePic = userData.profilePic,
                onEditProfileClicked = {
                    onEditProfileClicked(userData.profilePic)
                },
                onResetPasswordClicked = {
                    onResetPasswordClicked.invoke()
                }
            )
        }

        is UiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${(uiState as UiState.Error).message}", color = Color.White)
            }
        }

        UiState.Idle -> {}
    }
}

@Composable
fun MyAccountContent(
    firstName: String,
    lastName: String,
    email: String,
    phone: String,
    dob: String,
    profilePic: String?,
    onEditProfileClicked: () -> Unit,
    onResetPasswordClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "My Account",
            style = MaterialTheme.typography.titleSmall.copy(color = Color.White)
        )

        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = profilePic,
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape)
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomOutlinedTextField(value = firstName, label = "First Name", onValueChange = {})
        CustomOutlinedTextField(value = lastName, label = "Last Name", onValueChange = {})
        CustomOutlinedTextField(value = email, label = "Email", onValueChange = {})
        CustomOutlinedTextField(value = phone, label = "Phone", onValueChange = {})
        CustomOutlinedTextField(value = dob, label = "DOB", onValueChange = {})

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onEditProfileClicked()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("EDIT PROFILE", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = {
                onResetPasswordClicked()
            },
            border = BorderStroke(1.dp, Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("RESET PASSWORD", color = Color.White)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledTextColor = Color.White,
            disabledBorderColor = Color.White,
            disabledLabelColor = Color.White,
            cursorColor = Color.White
        ),
        maxLines = 1,
        readOnly = true,
        enabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}


@Preview
@Composable
private fun EditAccountScreenPreview() {
    MyAccountScreen(onEditProfileClicked = {}, onResetPasswordClicked = {})
}
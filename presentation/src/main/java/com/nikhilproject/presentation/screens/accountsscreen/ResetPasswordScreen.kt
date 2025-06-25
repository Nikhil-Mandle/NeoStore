package com.nikhilproject.presentation.screens.accountsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikhilproject.presentation.viewmodel.UserViewModel

@Composable
fun ResetPasswordScreen() {
    val userViewModel = hiltViewModel<UserViewModel>()
    val token = userViewModel.getAccessToken()

    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var oldpasswordError by remember { mutableStateOf("") }
    var newpasswordError by remember { mutableStateOf("") }
    var confirmpasswordError by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "NeoSTORE",
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password fields
        PasswordTextField(
            value = currentPassword,
            onValueChange = { currentPassword = it },
            label = "Current Password",
            isError = oldpasswordError.isNotEmpty(),
            errorMessage = oldpasswordError
        )

        PasswordTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = "New Password",
            isError = newpasswordError.isNotEmpty(),
            errorMessage = newpasswordError
        )

        PasswordTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm Password",
            isError = confirmpasswordError.isNotEmpty(),
            errorMessage = confirmpasswordError
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Reset Password button
        Button(
            onClick = {
                var isValid = true

                if (currentPassword.isBlank()) {
                    isValid = false
                    oldpasswordError = "Old password is required"
                }

                if (newPassword.length < 6) {
                    isValid = false
                    newpasswordError = "Password must be at least 6 characters"
                }

                if (confirmPassword.length < 6) {
                    isValid = false
                    confirmpasswordError =
                        "Password must be at least 6 characters"
                }

                if (newPassword != confirmPassword) {
                    isValid = false
                    newpasswordError = "Passwords do not match"
                    confirmpasswordError = "Passwords do not match"
                }

                if (isValid && token != null) {
                    userViewModel.changePassword(
                        token = token,
                        oldPassword = currentPassword,
                        password = newPassword,
                        confirmPassword = confirmPassword
                    )
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "RESET PASSWORD", color = Color.Red)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorMessage: String = ""

) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = label, tint = Color.White)
        },
        isError = isError,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    )

    if (isError && errorMessage.isNotEmpty()) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}


@Preview
@Composable
private fun ResetPasswordScreenPreview() {
    ResetPasswordScreen()
}
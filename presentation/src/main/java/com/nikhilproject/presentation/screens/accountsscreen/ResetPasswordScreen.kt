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

@Composable
fun ResetPasswordScreen() {
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
            label = "Current Password"
        )

        PasswordTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = "New Password"
        )

        PasswordTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm Password"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Reset Password button
        Button(
            onClick = {
                // handle reset password action
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
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = label, tint = Color.White)
        },
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
}


@Preview
@Composable
private fun ResetPasswordScreenPreview() {
    ResetPasswordScreen()
}
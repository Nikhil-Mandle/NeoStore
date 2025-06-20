package com.nikhilproject.presentation.screens.accountsscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikhilproject.presentation.R

@Composable
fun EditAccountScreen() {
    var firstName by remember { mutableStateOf("Sagar") }
    var lastName by remember { mutableStateOf("Shinde") }
    var email by remember { mutableStateOf("sagarshinde@wwindia.com") }
    var phone by remember { mutableStateOf("9876543211") }
    var dob by remember { mutableStateOf("08-11-1857") }

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

        // Profile Image
        Image(
            painter = painterResource(id = R.drawable.empty_cart), // replace with real drawable
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Input fields
        CustomOutlinedTextField(
            value = firstName,
            label = "First Name",
            onValueChange = { firstName = it })
        CustomOutlinedTextField(
            value = lastName,
            label = "Last Name",
            onValueChange = { lastName = it })
        CustomOutlinedTextField(value = email, label = "Email", onValueChange = { email = it })
        CustomOutlinedTextField(
            value = phone,
            label = "Phone",
            onValueChange = { phone = it },
            keyboardType = KeyboardType.Phone
        )
        CustomOutlinedTextField(value = dob, label = "DOB", onValueChange = { dob = it })

        Spacer(modifier = Modifier.height(16.dp))

        // Edit Profile button
        Button(
            onClick = { /* handle edit profile */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("EDIT PROFILE", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Reset Password button
        OutlinedButton(
            onClick = { /* handle reset password */ },
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
            cursorColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}


@Preview
@Composable
private fun EditAccountScreenPreview() {
    EditAccountScreen()
}
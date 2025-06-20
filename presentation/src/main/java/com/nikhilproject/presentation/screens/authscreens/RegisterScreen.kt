package com.nikhilproject.presentation.screens.authscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikhilproject.domain.model.RegisterRequest
import com.nikhilproject.presentation.UiState
import com.nikhilproject.presentation.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onSuccessNavigate: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }
    var phoneNumber by remember { mutableStateOf("") }
    var isTermsAccepted by remember { mutableStateOf(false) }


    when (uiState) {
        is UiState.Idle -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFDA1E37)) // Red background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 24.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Register",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "NeoSTORE",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // First Name
                    CustomInputField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = "First Name",
                        icon = Icons.Default.Person
                    )

                    // Last Name
                    CustomInputField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = "Last Name",
                        icon = Icons.Default.Person
                    )

                    // Email
                    CustomInputField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email",
                        icon = Icons.Default.Email
                    )

                    // Password
                    CustomInputField(
                        value = password,
                        onValueChange = { password = it },
                        label = "Password",
                        icon = Icons.Default.Lock,
                        isPassword = true
                    )

                    // Confirm Password
                    CustomInputField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = "Confirm Password",
                        icon = Icons.Default.Lock,
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Gender
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Gender", color = Color.White, modifier = Modifier.weight(1f))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = gender == "Male",
                                onClick = { gender = "Male" },
                                colors = RadioButtonDefaults.colors(selectedColor = Color.White)
                            )
                            Text("Male", color = Color.White)
                            Spacer(modifier = Modifier.width(8.dp))
                            RadioButton(
                                selected = gender == "Female",
                                onClick = { gender = "Female" },
                                colors = RadioButtonDefaults.colors(selectedColor = Color.White)
                            )
                            Text("Female", color = Color.White)
                        }
                    }

                    // Phone Number
                    CustomInputField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = "Phone Number",
                        icon = Icons.Default.Phone
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Terms and Conditions Checkbox
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = isTermsAccepted,
                            onCheckedChange = { isTermsAccepted = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.White,
                                uncheckedColor = Color.White
                            )
                        )
                        Text(
                            buildAnnotatedString {
                                append("I agree the ")
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Yellow
                                    )
                                ) {
                                    append("Terms & Conditions")
                                }
                            },
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            val request = RegisterRequest(
                                firstName = "mayur",
                                lastName = "totre",
                                email = "myname12@test.com",
                                password = "qwerty12",
                                confirmPassword = "qwerty12",
                                gender = "M",
                                phoneNo = 1234567890
                            )
                            viewModel.register(request)
                        },
                        colors = ButtonDefaults.buttonColors(

                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(text = "REGISTER", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        is UiState.Loading -> {
            // Show loading indicator
        }

        is UiState.Success -> {
            LaunchedEffect(Unit) {
                onSuccessNavigate() // navigate to home
            }
        }

        is UiState.Error -> {
            Text(text = (uiState as UiState.Error).message, color = Color.Red)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null) },
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.LightGray,
            cursorColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.LightGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}

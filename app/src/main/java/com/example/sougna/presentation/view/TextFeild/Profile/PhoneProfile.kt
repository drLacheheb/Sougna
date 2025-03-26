package com.example.sougna.presentation.view.TextFeild.Profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneProfile(
    countryCode: String,
    onCountryCodeChange: (String) -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val countryCodes = listOf("+213", "+1", "+33", "+44", "+49", "+966")

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp), // Ensure both elements have the same height
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Country Code Selector
        Box(
            modifier = Modifier
                .weight(0.25f)
                .height(56.dp) // Align with input field height
        ) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxSize(), // Ensures full height
                contentPadding = PaddingValues(0.dp) // Remove extra padding
            ) {
                Text(text = countryCode, fontSize = 16.sp)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                countryCodes.forEach { code ->
                    DropdownMenuItem(
                        text = { Text(code) },
                        onClick = {
                            onCountryCodeChange(code)
                            expanded = false
                        }
                    )
                }
            }
        }

        // Phone Number Input Field
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { newValue ->
                onPhoneNumberChange(newValue.filter { it.isDigit() }.take(10)) // Allow only digits
            },
            label = { Text("Phone Number") },
            placeholder = { Text("Enter phone number") },
            modifier = Modifier
                .weight(0.75f)
                .height(56.dp), // Aligns with the button
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                focusedLabelColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

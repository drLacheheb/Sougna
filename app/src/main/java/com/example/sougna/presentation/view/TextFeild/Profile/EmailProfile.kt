package com.example.sougna.presentation.view.TextFeild.Profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailProfile(
    Email: String,
    onEmail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedDomain by remember { mutableStateOf("@outlook.com") }

    val domains = listOf("@gmail.com", "@outlook.com", "@hotmail.com")

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically, // Ensures elements are aligned
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Adds uniform spacing
    ) {
        OutlinedTextField(
            value = Email.substringBefore("@"),
            onValueChange = { onEmail(it + selectedDomain) },
            label = { Text("Email") },
            placeholder = { Text("Enter your email") },
            modifier = Modifier.weight(1.1f),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.Blue
            )
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(56.dp), // Matches OutlinedTextField height for better alignment
            contentAlignment = Alignment.Center // Centers the button text
        ) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(selectedDomain, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                domains.forEach { domain ->
                    DropdownMenuItem(
                        text = { Text(domain, fontSize = 14.sp) },
                        onClick = {
                            selectedDomain = domain
                            onEmail(Email.substringBefore("@") + domain)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

package com.example.sougna.ui.theme.SougnaTheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sougna.R.drawable.photo_5949390434042233594_y

@Composable
fun ProfileScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFF6F61), Color(0xFFE53935))
                )
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
                .background(Color.White, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /*Image(
                painter = painterResource(id = R.drawable.photo_5949390434042233594_y),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp))
            )*/


            Spacer(modifier = Modifier.height(8.dp))

            Text("My Profile", fontSize = 24.sp, color = Color.Black)

            Spacer(modifier = Modifier.height(16.dp))

            ProfileTextField("Name", name) { name = it }
            ProfileTextField("Email Address", email, KeyboardType.Email) { email = it }
            ProfileTextField("Password", password, KeyboardType.Password, true) { password = it }
            ProfileTextField("Phone Number", phone, KeyboardType.Phone) { phone = it }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Cancel Action */ },
                    colors = ButtonDefaults.buttonColors(Color.Gray),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel", color = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { /* Save Action */ },
                    colors = ButtonDefaults.buttonColors(Color(0xFFFF6F61)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Save", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun ProfileTextField(label: String, value: String, keyboardType: KeyboardType = KeyboardType.Text, isPassword: Boolean = false, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(text = label, fontSize = 14.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text("Enter your $label") },
            singleLine = true,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

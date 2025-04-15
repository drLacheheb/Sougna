package com.example.sougna.presentation.view.TextFeild.Product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceField(
    modifier: Modifier = Modifier,
    productPrice: String,
    onPriceChange: (String) -> Unit,
    selectedCurrency: String,
    onCurrencyChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Price Input
            OutlinedTextField(
                value = productPrice,
                onValueChange = {
                    if (it.all { char -> char.isDigit() || char == '.' }) {
                        onPriceChange(it)
                    }
                },
                label = { Text("Price of Product") }, // ✅ Fixed label issue
                placeholder = { Text("Enter Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )

            // Currency Dropdown
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedCurrency,
                    onValueChange = {},
                    label = { Text("Currency") }, // ✅ Added label
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .width(100.dp)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listOf("USD", "EUR", "GBP", "DZD").forEach { currency ->
                        DropdownMenuItem(
                            text = { Text(currency) },
                            onClick = {
                                onCurrencyChange(currency)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

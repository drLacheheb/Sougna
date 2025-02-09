package com.example.sougna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sougna.ui.theme.SougnaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SougnaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MarketplaceUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class Product(val name: String, val price: String)

@Composable
fun MarketplaceUI(modifier: Modifier = Modifier) {
    var nameValue by remember { mutableStateOf("") }
    var priceValue by remember { mutableStateOf("") }
    var showPriceField by remember { mutableStateOf(false) }
    val itemsList = remember { mutableStateListOf<Product>() }
    var displayedItems by remember { mutableStateOf(itemsList.toList()) }

    Column(
        modifier = modifier
            .padding(25.dp)
            .fillMaxSize()
    ) {
        SearchInputBar(
            nameValue = nameValue,
            onNameValueChange = { nameValue = it },
            priceValue = priceValue,
            onPriceValueChange = { priceValue = it },
            showPriceField = showPriceField,
            onShowPriceField = { showPriceField = true },
            onBack = {
                nameValue = ""
                priceValue = ""
                showPriceField = false
            },
            onAddItem = {
                if (nameValue.isNotBlank() && priceValue.isNotBlank()) {
                    itemsList.add(Product(nameValue, priceValue))
                    displayedItems = itemsList.toList()
                    nameValue = ""
                    priceValue = ""
                    showPriceField = false
                }
            },
            onSearch = {
                displayedItems = if (it.isNotBlank()) {
                    itemsList.filter { item -> item.name.contains(it, ignoreCase = true) }
                } else {
                    itemsList.toList()
                }
            },
            showSearch = !showPriceField
        )

        CardsList(displayedItems)
    }
}

@Composable
fun SearchInputBar(
    nameValue: String,
    onNameValueChange: (String) -> Unit,
    priceValue: String,
    onPriceValueChange: (String) -> Unit,
    showPriceField: Boolean,
    onShowPriceField: () -> Unit,
    onBack: () -> Unit,
    onAddItem: () -> Unit,
    onSearch: (String) -> Unit,
    showSearch: Boolean
) {
    Column {
        if (!showPriceField) {
            TextField(
                value = nameValue,
                onValueChange = onNameValueChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter product name...") },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFFFF9800), // لون الخط عند التركيز برتقالي
                    unfocusedIndicatorColor = Color(0xFFFF9800) // لون الخط عند عدم التركيز برتقالي
                )
            )
        } else {
            TextField(
                value = priceValue,
                onValueChange = onPriceValueChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter product price...") },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFFFF9800),
                    unfocusedIndicatorColor = Color(0xFFFF9800)
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (!showPriceField) {
                Button(
                    onClick = onShowPriceField,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
                ) {
                    Text("Add Product")
                }
                if (showSearch) {
                    Button(
                        onClick = { onSearch(nameValue) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
                    ) {
                        Text("Search")
                    }
                }
            } else {
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
                ) {
                    Text("Back")
                }
                Button(
                    onClick = onAddItem,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
                ) {
                    Text("Confirm")
                }
            }
        }
    }
}

@Composable
fun CardsList(displayedItems: List<Product>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(displayedItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = item.name, style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Price: ${'$'}${item.price}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMarketplaceUI() {
    SougnaTheme {
        MarketplaceUI()
    }
}

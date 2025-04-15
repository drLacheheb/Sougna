package com.example.sougna.presentation.view.TextFeild.Product

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ImagePicker(
    selectedImages: List<Uri?>,
    onImageClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val permission = Manifest.permission.READ_MEDIA_IMAGES
        val granted = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        Log.d("Permissions", "Permission granted: $granted")
    }

    // ✅ Choose correct permission for different Android versions
    val permission =
        Manifest.permission.READ_MEDIA_IMAGES

    // ✅ Permission request launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onImageClick(0) // ✅ Open gallery after permission is granted
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text("Picture of Product", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(14.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(27.dp),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(selectedImages.size) { index ->
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color.LightGray, RoundedCornerShape(8.dp))
                            .clickable {
                                // ✅ Check permission before opening image picker
                                if (ContextCompat.checkSelfPermission(context, permission)
                                    == PackageManager.PERMISSION_GRANTED
                                ) {
                                    onImageClick(index) // ✅ Open image picker directly
                                } else {
                                    permissionLauncher.launch(permission) // ✅ Show permission dialog
                                }
                            }
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (selectedImages[index] != null) {
                            Image(
                                painter = rememberAsyncImagePainter(selectedImages[index]),
                                contentDescription = "Selected Image",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Icon(
                                Icons.Default.Image,
                                contentDescription = "Upload Image",
                                tint = Color.DarkGray,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

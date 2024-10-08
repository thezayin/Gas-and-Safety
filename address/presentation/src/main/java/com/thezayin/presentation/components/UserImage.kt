package com.thezayin.presentation.components

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thezayin.assets.R

/**
 * A composable to display and allow the user to upload their profile image.
 *
 * This component displays a circular placeholder image that, when clicked, allows the user
 * to pick an image from their device's gallery using the provided [launcher].
 * If an image is already selected, it displays the selected image instead of the placeholder.
 *
 * @param imageUri The [Uri] of the selected image, or null if no image has been selected yet.
 * @param imageSelected A [MutableState] to track whether an image has been selected.
 * @param launcher A [ManagedActivityResultLauncher] used to launch the media picker for selecting an image.
 */
@Composable
fun UserImage(
    imageUri: Uri?, // URI of the selected image or null if no image is selected
    imageSelected: MutableState<Boolean>, // State to track if an image has been selected
    launcher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?> // Launcher to handle image selection
) {
    // A card container for the image with padding and a semi-transparent background
    Card(
        modifier = Modifier
            .fillMaxWidth() // Make the card take up the full width of the parent
            .height(200.dp) // Fixed height of 200dp
            .padding(horizontal = 25.dp), // Horizontal padding on both sides
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.semi_transparent) // Semi-transparent background color
        )
    ) {
        // Row for positioning the image in the center of the card
        Row(
            modifier = Modifier
                .fillMaxWidth() // Take up the full width
                .fillMaxHeight(), // Take up the full height of the card
            verticalAlignment = Alignment.CenterVertically, // Center the image vertically
            horizontalArrangement = Arrangement.Center // Center the image horizontally
        ) {
            // Circular image container that acts as a clickable image picker
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(180.dp)) // Circular clipping for the image container
                    .size(180.dp) // Fixed size for the image container
                    .background(colorResource(id = R.color.light_purple)) // Light purple background
                    .clickable {
                        // Launch the media picker when the container is clicked
                        launcher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                verticalAlignment = Alignment.CenterVertically, // Center content vertically within the container
                horizontalArrangement = Arrangement.Center // Center content horizontally within the container
            ) {
                // Display the selected image or a default upload icon if no image is selected
                AsyncImage(
                    model = if (imageSelected.value) imageUri else R.drawable.ic_upload, // Display the selected image or placeholder
                    contentDescription = null, // No content description as it's decorative
                    modifier = Modifier.size(120.dp), // Size of the image (120dp)
                    contentScale = ContentScale.Fit // Scale the image to fit within the container
                )
            }
        }
    }
}
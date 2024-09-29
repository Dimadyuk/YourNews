package org.yournews.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.imageResource
import org.yournews.utils.handCursor
import yournews.composeapp.generated.resources.Res
import yournews.composeapp.generated.resources.logo_image

@Composable
fun SidePanel(
    onMenuSelected: (header: String) -> Unit,
    onNewsSearched: (searchedText: String, header: String) -> Unit
) {
    var searchedText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth(0.25f)
            .fillMaxHeight()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image = imageResource(Res.drawable.logo_image)

        Image(
            image, contentDescription = "News Logo",
            modifier = Modifier.width(100.dp)
        )
        Spacer(modifier = Modifier.padding(18.dp))
        OutlinedTextField(
            value = searchedText,
            onValueChange = { searchedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(50.dp),
            singleLine = true,
            placeholder = {
                Text(
                    text = "Search news",
                    overflow = TextOverflow.Ellipsis,
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onNewsSearched(searchedText, "Results for $searchedText")
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .pointerHoverIcon(handCursor())
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search button",
                        tint = Color.Black
                    )
                }
            }
        )
        TextButton(
            onClick = {
                searchedText = ""
                onMenuSelected("Headlines")
            },
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text(
                "Headlines",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.pointerHoverIcon(handCursor())
            )
        }
    }
}

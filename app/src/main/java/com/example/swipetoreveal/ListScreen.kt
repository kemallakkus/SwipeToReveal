package com.example.swipetoreveal

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ListScreen(viewModel: ListViewModel = viewModel()) {
    val context = LocalContext.current
    val items by viewModel.items.collectAsState()


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(
            items = items,
            key = { _, item -> item.id }
        ) { _, item ->
            ItemWithSwipeReveal(
                isRevealed = item.isShow,
                onExpand = {
                    viewModel.updateItem(item.copy(isShow = true))
                },
                onCollapse = {
                    viewModel.updateItem(item.copy(isShow = false))
                },
                actions = {
                    Row(
                        modifier = Modifier.fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SwipeIcon(
                            onClick = {
                                viewModel.updateItem(item.copy(isShow = false))
                                viewModel.removeItem(item)
                                Toast.makeText(
                                    context,
                                    "Item ${item.name} was removed",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            backgroundColor = Color.Red,
                            icon = Icons.Default.Delete,
                            modifier = Modifier.fillMaxHeight(),
                        )
                        SwipeIcon(
                            onClick = {
                                viewModel.updateItem(item.copy(isShow = false))
                                Toast.makeText(
                                    context,
                                    "Item ${item.id} was edited",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            backgroundColor = Color.Green,
                            icon = Icons.Default.Edit,
                        )
                        SwipeIcon(
                            onClick = {
                                viewModel.updateItem(item.copy(isShow = false))
                                viewModel.addItem(item)
                                Toast.makeText(
                                    context,
                                    "Item ${item.name} was added",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            backgroundColor = Color.Blue,
                            icon = Icons.Default.Add,
                        )
                    }

                },
            ) {
                ListItem(
                    headlineContent = {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                )

            }

        }
    }
}
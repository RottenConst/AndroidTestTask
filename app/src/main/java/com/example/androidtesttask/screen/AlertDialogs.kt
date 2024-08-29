package com.example.androidtesttask.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidtesttask.R
import com.example.androidtesttask.data.Item

/**
 * Диалог для изменения количества товара
 * [isVisibleDialog] - виден ли диалог
 * [amount] - количество товара
 * [setVisible] - виден ли диалог
 * [saveItem] - метод изменение количества товара
 */
@Composable
fun SetAmountAlertDialog(isVisibleDialog: Boolean, amount: Int, setVisible: (Boolean) -> Unit, saveItem: (Int) -> Unit) {
    var itemAmount: Int by remember {
        mutableIntStateOf(amount)
    }
    if (isVisibleDialog) {
        AlertDialog(
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Setting Icon",
                )
            },
            title = {
                Text(text = stringResource(id = R.string.the_quantity_item))
            },
            text = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        if (itemAmount > 0) itemAmount -= 1
                    }) {
                        Icon(
                            modifier = Modifier.size(42.dp),
                            painter = painterResource(id = R.drawable.outline_do_not_disturb_on_24),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = "$itemAmount",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 24.dp),
                    )
                    IconButton(onClick = {
                        itemAmount += 1
                    }) {
                        Icon(
                            modifier = Modifier.size(42.dp),
                            painter = painterResource(id = R.drawable.outline_add_circle_outline_24),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                }

            },
            onDismissRequest = { setVisible(false) },
            dismissButton = {
                TextButton(onClick = {
                    setVisible(false)
                }) {
                    Text(
                        text = stringResource(id = R.string.cansel),
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        setVisible(false)
                        saveItem(itemAmount)
                    }) {
                    Text(
                        text = stringResource(id = R.string.apply),
                    )
                }
            },
        )
    }
}

/**
 * диалог удаления товара
 * [isVisibleDialog] виден ли диалог в данный момент
 * [item] - удаляемый товар
 * [setVisible] изменение видимости
 * [deleteItem] метод удаления товара
 */
@Composable
fun DeleteAlertDialog(isVisibleDialog: Boolean, item: Item, setVisible: (Boolean) -> Unit, deleteItem: (Item) -> Unit) {
    if (isVisibleDialog) {
        AlertDialog(
            icon = {
                Icon(imageVector = Icons.Rounded.Warning, contentDescription = "")
            },
            title = {
                Text(text = stringResource(id = R.string.delete_item))
            },
            text = {
                Text(
                    text = stringResource(id = R.string.do_you_really_want),
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
            onDismissRequest = { setVisible(false) },
            dismissButton = {
                TextButton(onClick = {
                    setVisible(false)
                }) {
                    Text(text = stringResource(id = R.string.no))
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        setVisible(false)
                        deleteItem(item)
                    }) {
                    Text(text = stringResource(id = R.string.yes))
                }
            },
        )
    }
}


@Preview
@Composable
fun SetAmountDialogPreview() {
    var isVisibleDialog by remember {
        mutableStateOf(true)
    }
    SetAmountAlertDialog(
        isVisibleDialog = isVisibleDialog,
        amount = 10,
        setVisible = {isVisibleDialog = !isVisibleDialog}) {

    }
}

@Preview
@Composable
fun DeleteItemDialogPreview() {
    val item = Item(
        id = 1,
        name = "iPhone 13",
        time = 1633046400000,
        tags = listOf("Телефон", "Новый", "Распродажа"),
        amount = 15,
    )

    var isVisibleDialog by remember {
        mutableStateOf(true)
    }
    DeleteAlertDialog(
        isVisibleDialog = isVisibleDialog,
        item = item,
        setVisible = {
            isVisibleDialog = !isVisibleDialog}
    ) {

    }
}
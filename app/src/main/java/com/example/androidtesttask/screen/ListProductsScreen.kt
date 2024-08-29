package com.example.androidtesttask.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidtesttask.R
import com.example.androidtesttask.data.Item
import com.example.androidtesttask.screen.vm.AppViewModelProvider
import com.example.androidtesttask.screen.vm.ListProductViewModel
import com.example.androidtesttask.ui.theme.AndroidTestTaskTheme
import com.example.androidtesttask.ui.theme.Blue
import com.example.androidtesttask.ui.theme.Purple
import com.example.androidtesttask.ui.theme.Red

@Composable
fun ListProductsScreen(viewModel: ListProductViewModel = viewModel(factory = AppViewModelProvider.Factory)) {

    viewModel.search("")
    val productList by viewModel.products.observeAsState()
    var search by rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.list_products))
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            if (productList!= null) {
                item {
                    SearchItemTextField(
                        search,
                        searchProduct = { search = viewModel.search(it) },
                        )
                }

                items(productList?.size ?: 0) { item ->
                    productList?.get(item)
                        ?.let { product->
                            CardProduct(
                                item = product,
                                date = viewModel.parseUnixTimestamp(product.time),
                                updateItem = {viewModel.updateItem(it)},
                                deleteItem = {viewModel.deleteItem(it)}
                            )
                        }
                }
            }
        }
    }

}

/**
 * Топ бар
 * [title] - заголовок экрана
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Blue,
        )
    )
}

/**
 * Карточка товара
 * [item] - товар
 * [date] - дата добавления в определённом формате
 * [updateItem] - обновление товара
 * [deleteItem] - удаление товара
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardProduct(
    modifier: Modifier = Modifier,
    item: Item,
    date: String,
    updateItem: (Item) -> Unit,
    deleteItem: (Item) -> Unit
) {
    // виден ли диалог для изменения количества товара
    var isVisibleSetAmount by remember {
        mutableStateOf(false)
    }

    // виден ли диалог для удаления товара
    var isVisibleDeleteDialog by remember {
        mutableStateOf(false)
    }

    // изменение количества товара
    SetAmountAlertDialog(
        isVisibleDialog = isVisibleSetAmount,
        amount = item.amount,
        setVisible = {isVisibleSetAmount = !isVisibleSetAmount},
        saveItem = {
            updateItem(item.copy(amount = it))
        }
    )

    // удаление товара
    DeleteAlertDialog(
        isVisibleDialog = isVisibleDeleteDialog,
        item = item,
        setVisible = {isVisibleDeleteDialog = !isVisibleDeleteDialog}
    ) {
        deleteItem(it)
    }

    ElevatedCard(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween)
            {
                //title
                Text(
                    text = item.name,
                    fontWeight = FontWeight.W500,
                    fontSize = 20.sp
                )
                //icon
                Row {
                    Icon(
                        modifier = modifier
                            .padding(horizontal = 16.dp)
                            .clickable { isVisibleSetAmount = true },
                        imageVector = Icons.Default.Create,
                        contentDescription = "",
                        tint = Purple
                    )
                    Icon(
                        modifier = modifier.clickable { isVisibleDeleteDialog = true },
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        tint = Red
                    )
                }
            }
            //tag
            FlowRow(
                modifier = modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                maxItemsInEachRow = 3
            ) {
                item.tags.forEach { tag ->
                    FilterChip(
                        modifier = Modifier.height(30.dp),
                        selected = false,
                        onClick = {},
                        label = {
                            Text(text = tag)
                        })
                }
            }
            Row(
                modifier = modifier.width(280.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //amount
                Column {
                    Text(
                        stringResource(id = R.string.in_stock),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = if (item.amount > 0 ) {
                            item.amount.toString()
                        } else {
                            stringResource(id = R.string.out_of_stock)
                        },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                //date
                Column {
                    Text(
                        text = stringResource(id = R.string.date_of_addition),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(text = date, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

/**
 * Поле поиска
 * [searchText] текст для поиска
 * [searchProduct] метод поиска
 */
@Composable
fun SearchItemTextField(searchText: String, searchProduct: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = {
            searchProduct(it) },
        label = { Text(stringResource(id = R.string.product_search)) },
        singleLine = true,
        modifier = Modifier
            .padding(top = 8.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth(),
        textStyle = TextStyle.Default.copy(fontSize = 14.sp),
        leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = "Search")},
        trailingIcon = { if(searchText.isNotBlank()) Icon(imageVector = Icons.Rounded.Clear, contentDescription = "Clear", modifier = Modifier.clickable { searchProduct("") })},
        colors = OutlinedTextFieldDefaults.colors(
            focusedLabelColor = Color.Gray,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
        )
    )
}

@Preview
@Composable
fun TopBarPreview() {
    AndroidTestTaskTheme {
        TopBar(title = "Список товаров")
    }
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    AndroidTestTaskTheme {
        SearchItemTextField(searchText = "", searchProduct = {})
    }
}

@Preview
@Composable
fun CardProductPreview() {
    val listItem: List<Item> = List(10) {
        Item(
            id = it,
            name = "iPhone 13",
            time = 1633046400000,
            tags = listOf("Телефон", "Новый", "Распродажа"),
            amount = 15,
        )
    }
    AndroidTestTaskTheme {
        LazyColumn {
            items(listItem.size) { item ->
                CardProduct(
                    modifier = Modifier,
                    item = listItem[item],
                    date = item.toString(),
                    updateItem = {}, deleteItem = {},
                )
            }
        }
    }
}
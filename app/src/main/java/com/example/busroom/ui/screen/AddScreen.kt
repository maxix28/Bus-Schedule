package com.example.busroom.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.busroom.AppViewModelProvider
import com.example.busroom.ui.theme.BusRoomTheme
import kotlinx.coroutines.launch
import java.util.Currency
import java.util.Locale

@Composable
fun StartScreen(viewModel : ScheduleViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
    factory = AppViewModelProvider.Factory
)){

    val UIstate = viewModel.itemUiState
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {

        }
    ) { innerPadding ->
        ItemEntryBody(
            itemUiState = viewModel.itemUiState,
            onItemValueChange ={ viewModel.setStation(it) },
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveItem()

                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}


@Composable
fun ItemEntryBody(
    itemUiState: ScheduleUiState,
    onItemValueChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.padding(16.dp)
    ) {
        ItemInputForm(
            itemUiState=itemUiState,

            onValueChange = onItemValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,

            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemInputForm(
    itemUiState: ScheduleUiState,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        OutlinedTextField(
            value = itemUiState.station,
            onValueChange = { onValueChange(it) },
            label = { "Station"},
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = "",
            onValueChange = {  },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = { "Time" },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),

            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled) {
            Text(
                text = "Save",
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}
@Preview
@Composable
fun pre(){
    BusRoomTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            allScreen()
        }
    }
}


@Composable
fun allScreen(modifier: Modifier= Modifier,
              viewModel : ScheduleViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
                  factory = AppViewModelProvider.Factory)){

    val ListUIState = viewModel.ListUIState.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        val coroutineScope= rememberCoroutineScope()
        OutlinedTextField(
            value = viewModel.itemUiState.station,
            onValueChange = {viewModel.setStation(it)},
            label = { "Station"},
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
        OutlinedTextField(
            value = viewModel.itemUiState.time,
            onValueChange = { viewModel.setTime(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = { "Time" },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),

            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        if (true) {
            Text(
                text = "Save",
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Button(
                onClick = {
                    coroutineScope.launch(){
                        viewModel.saveItem()
                    }

                   },

        shape = MaterialTheme.shapes.small,
        modifier = Modifier.fillMaxWidth()
        ) {
        Text("This button")
    }


        LazyColumn(){
        items(ListUIState.value.list){
            ItemScreen(it.StationName, it.StationTime)
            Spacer(modifier = modifier.height(8.dp))
        }
        }
    }


}

@Composable
fun ItemScreen(name:String, time : String ,modifier: Modifier=Modifier){
    Card(){
        Row(modifier.fillMaxWidth()){

            Text(text = name, modifier = modifier.padding(start = 5.dp))
            Spacer(modifier = modifier.weight(1f))
            Text(text = time, modifier = modifier.padding(end = 5.dp))


        }
    }

}
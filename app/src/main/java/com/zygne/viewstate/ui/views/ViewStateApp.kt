package com.zygne.viewstate.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import com.zygne.viewstate.MainViewModel

@Composable
fun ViewStateApp(mainViewModel: MainViewModel) {
    val viewState by mainViewModel.viewState.collectAsState()

    CounterScreen(
        viewState = viewState,
        onIncrement = {
            mainViewModel.performAction(MainViewModel.Action.IncrementBy(amount = it))

        },
        onDecrement = {
            mainViewModel.performAction(MainViewModel.Action.DecrementBy(amount = it))
        }
    )
}

@Composable
fun CounterScreen(
    viewState: MainViewModel.ViewState,
    onIncrement: (amount: Int) -> Unit,
    onDecrement: (amount: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Counter: ${viewState.counter}", modifier = Modifier.padding(16.dp))

        Row {
            Column(modifier = Modifier.fillMaxSize(0.5f)) {
                Button(
                    onClick = { onIncrement(1) },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "+1")
                }

                Button(
                    onClick = { onIncrement(10) },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "+10")
                }

                Button(
                    onClick = { onIncrement(25) },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "+25")
                }
            }

            Column(modifier = Modifier.fillMaxSize(0.5f)) {

                Button(
                    onClick = { onDecrement(1) },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "-1")
                }

                Button(
                    onClick = { onDecrement(10) },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "-10")
                }

                Button(
                    onClick = { onDecrement(25) },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "-25")
                }
            }
        }


        when (viewState.loadingState) {
            MainViewModel.LoadingState.Loading -> {
                LoadingDialog(isLoading = true)
            }

            MainViewModel.LoadingState.None -> {

            }
        }

    }
}

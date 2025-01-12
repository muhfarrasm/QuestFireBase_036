package com.example.mvvm_firebase.ui.pages

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvm_firebase.ui.PenyediaViewModel
import com.example.mvvm_firebase.ui.home.viewmodel.FormState
import com.example.mvvm_firebase.ui.home.viewmodel.InsertViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun InsertMhsView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState // State utama untuk loading, success, eror
    val uiEvent = viewModel.uiEvent // State untuk form dan validasi
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    //Observasi perubahan state untuk snackbar dan navigasi
    LaunchedEffect (uiState) {
        when (uiState) {
            is FormState.Success -> {
                println("InsertMhsView: uiState is FormState.Succes, navigate to home"+ uiState.message)
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(uiState.message) // Menampilkan snackbar
                }
                delay(1000)
                //Navigasi Langsung
                onNavigate()

                viewModel.resetSnackBarMessage()//Reset snackbar state
            }

            is FormState.Error -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(uiState.message)
                }
            }

            else -> Unit

        }
    }
}
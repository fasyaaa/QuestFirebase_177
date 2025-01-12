package com.example.pam14.ui.insert.page

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam14.ui.PenyediaViewModel
import com.example.pam14.ui.insert.viewmodel.InsertViewModel

@Composable
fun InsertMhsView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState //
    val uiEvent = viewModel.uiEvent
    val snackbarHostState = remember {SnackbarHostState()}
    val coroutineScope = rememberCoroutineScope()
}
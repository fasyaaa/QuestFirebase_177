package com.example.pam14.ui.detail.viewmodel

import android.net.http.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam14.model.Mahasiswa
import com.example.pam14.repository.RepositoryMhs
import com.example.pam14.ui.navigation.DestinasiDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailUiState {
    data class Success(val mahasiswa: RepositoryMhs) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel(
    private val mhs: RepositoryMhs
) : ViewModel() {

    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState.asStateFlow()
    fun getDetailMahasiswa(nim: String){
        viewModelScope.launch{
            _detailUiState.value = DetailUiState.Loading
            try {
                val mahasiswa = mhs.getMhs(nim)
                //_detailUiState.value = DetailUiState.Success(nim? mahasiswa? kurang paham mas)
            }
            catch (e: Exception){
                _detailUiState.value = DetailUiState.Error
            }
        }
    }
}
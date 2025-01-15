package com.example.pam14.ui.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam14.model.Mahasiswa
import com.example.pam14.repository.RepositoryMhs
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel (private val mhs: RepositoryMhs): ViewModel(){
    var mhsUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getMhs()
    }

    fun getMhs() {
        viewModelScope.launch {
           mhs.getAllMhs()
               .onStart {
               mhsUiState = HomeUiState.Loading
                 }
               .catch {
                   mhsUiState = HomeUiState.Error(e = it)
               }
               .collect {
                   mhsUiState = if (it.isEmpty()) {
                       HomeUiState.Error(Exception("Belum ada data Mahasiswa"))
                   } else {
                       HomeUiState.Success(it)
                   }
               }
        }
    }

    fun deleteMhs(mahasiswa: Mahasiswa){
        viewModelScope.launch {
            try {
                mhs.deleteMhs(mahasiswa)
            } catch (e: Exception){
                mhsUiState = HomeUiState.Error(e)
            }
        }
    }
}


sealed class HomeUiState {
    object  Loading: HomeUiState()
    data class Success(val data: List<Mahasiswa>) : HomeUiState()
    data class Error(val e: Throwable) : HomeUiState()
}
package com.example.pam14.ui.detail.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam14.model.Mahasiswa
import com.example.pam14.ui.PenyediaViewModel
import com.example.pam14.ui.detail.viewmodel.DetailViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(
    nim : String,
    navigateBack:() -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory )){

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val detailUiState = viewModel.detailUiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getDetailMahasiswa(nim)
    }

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {Text(text = "Home")}
            )
        }

    ){innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {


        }

    }
}


@Composable
fun DetailContent(
    mahasiswa: Mahasiswa,
    modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Nama: ${mahasiswa.nama}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "NIM: ${mahasiswa.nim}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Kelas: ${mahasiswa.kelas}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Angkatan: ${mahasiswa.angkatan}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Jenis Kelamin: ${mahasiswa.jenisKelamin}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Alamat: ${mahasiswa.alamat}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Judul: ${mahasiswa.judulSkripsi}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Dosen 1: ${mahasiswa.dosenPembimbing1}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Dosen 2: ${mahasiswa.dosenPembimbing2}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}


@Composable
fun OnLoadingDetail(modifier: Modifier = Modifier){

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        CircularProgressIndicator()
    }

}


@Composable
fun OnError(retryAction: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "An error occurred. Please try again.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = retryAction) {
                Text(text = "Retry")
            }
        }
    }
}

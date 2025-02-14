package com.example.pam14.repository

import com.example.pam14.model.Mahasiswa
import kotlinx.coroutines.flow.Flow

// berhubungan dengan view model agar bisa di akses di view model
interface RepositoryMhs {

   suspend fun getAllMhs(): Flow<List<Mahasiswa>>

    suspend fun getMhs(nim: String): Flow<Mahasiswa>

    suspend fun insertMhs(mahasiswa: Mahasiswa)

    suspend fun deleteMhs(mahasiswa: Mahasiswa)

    suspend fun updateMhs(mahasiswa: Mahasiswa)
}
package com.example.pam14.repository

import com.example.pam14.model.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkRepositoryMhs(private val firestore: FirebaseFirestore): RepositoryMhs{
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override fun getAllMhs(): Flow<List<Mahasiswa>> = callbackFlow {
//        ambil data dari firestore db mahasiswa
        val mhsCollection = firestore.collection("Mahasiswa")
            .orderBy("nim", Query.Direction.ASCENDING)
            .addSnapshotListener { value, error ->
                if(value != null){
                    val mhsList = value.documents.mapNotNull {
                        it.toObject(Mahasiswa::class.java)!!
                    }
                    trySend(mhsList)
                }
            }

//        agar mhsCollection tidak menjalankan lagi operasinya
        awaitClose {
            mhsCollection.remove()
        }
    }

    override fun getMhs(nim: String): Flow<Mahasiswa> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMhs(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMhs(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

}
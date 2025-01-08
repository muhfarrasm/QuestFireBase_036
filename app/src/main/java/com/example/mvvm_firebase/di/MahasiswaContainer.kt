package com.example.mvvm_firebase.di

import com.example.mvvm_firebase.repository.NetworkRepositoryMhs
import com.example.mvvm_firebase.repository.RepositoryMhs
import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer{
    val repositoryMhs: RepositoryMhs
}

class MahasiswaContainer: AppContainer{
    private val  firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override val repositoryMhs: RepositoryMhs by lazy {
        NetworkRepositoryMhs(firestore)
    }

}
package com.example.mvvm_firebase.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvm_firebase.repository.RepositoryMhs

class InsertViewModel(
    private val repositoryMhs: RepositoryMhs
) : ViewModel() {}



data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jenisKelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)
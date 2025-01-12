package com.example.mvvm_firebase.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvm_firebase.model.Mahasiswa
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

fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jenisKelamin = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)
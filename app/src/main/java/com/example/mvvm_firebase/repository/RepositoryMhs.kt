package com.example.mvvm_firebase.repository

import com.example.mvvm_firebase.model.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface RepositoryMhs {

    // method ini untuk memanggil fungsi geetAllMahasiswa dari mahasiswaDao untuk
    //mendapatkan semua data dlm bentuk aliran
    fun getAllMhs() : Flow<List<Mahasiswa>>
    //method ini untuk memanggil fungsi geetAllMahasiswa dari mahasiswaDao berdasarkan NIM
    fun getMhsbynim(nim: String): Flow<Mahasiswa?>

    //Method ini memanfaatkan fungsi insertMahasiswa dari MahasiswaDao
    suspend fun insertMhs(mahasiswa: Mahasiswa)

    //Method ini memanfaatkan fungsi deleteMahasiswa dari MahasiswaDao
    suspend fun deleteMhs(mahasiswa: Mahasiswa)

    //Method ini memanfaatkan fungsi updateMahasiswa dari MahasiswaDao
    suspend fun updateMhs(mahasiswa: Mahasiswa)




}
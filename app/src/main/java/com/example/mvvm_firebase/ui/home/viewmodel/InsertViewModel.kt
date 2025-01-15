package com.example.mvvm_firebase.ui.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_firebase.model.Mahasiswa
import com.example.mvvm_firebase.repository.RepositoryMhs
import kotlinx.coroutines.launch

class InsertViewModel(
    private val repositoryMhs: RepositoryMhs
) : ViewModel() {

    var uiEvent: InsertUiState by mutableStateOf(InsertUiState())
        private set

    var uiState: FormState by mutableStateOf(FormState.Idle)
        private set

    fun updateState(mahasiswaEvent: MahasiswaEvent) {
        uiEvent = uiEvent.copy(
            insertUiEvent = mahasiswaEvent,
        )
    }

    fun validateFields(): Boolean {
        val event = uiEvent.insertUiEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong",
            judulskripsi = if (event.judulskripsi.isNotEmpty()) null else "Judul Skripsi tidak boleh kosong",
            dosenpem1 = if (event.dosenpem1.isNotEmpty()) null else "Dospem tidak boleh kosong",
            dosenpem2 = if (event.dosenpem2.isNotEmpty()) null else "Dospem tidak boleh kosong"
        )
        uiEvent = uiEvent.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun insertMhs() {
        if (validateFields()) {
            viewModelScope.launch {
                uiState = FormState.Loading
                try {
                    repositoryMhs.insertMhs(uiEvent.insertUiEvent.toMhsModel())
                    uiState = FormState.Success("Data berhasil disimpan")
                } catch (e: Exception) {
                    uiState = FormState.Error("Data gagal disimpan")
                }
            }
        } else {
            uiState = FormState.Error("Data tidak valid")
        }
    }

    fun resetForm() {
        uiEvent = InsertUiState()
        uiState = FormState.Idle
    }
    fun resetSnackBarMessage() {
        uiState = FormState.Idle
    }
}


    sealed class FormState {
        object Idle : FormState()
        object Loading : FormState()
        data class Success(val message: String) : FormState()
        data class Error(val message: String) : FormState()
    }

    data class InsertUiState(
        val insertUiEvent: MahasiswaEvent = MahasiswaEvent(),
        val isEntryValid: FormErrorState = FormErrorState()
    )

    data class FormErrorState(
        val nim: String? = null,
        val nama: String? = null,
        val jenisKelamin: String? = null,
        val alamat: String? = null,
        val kelas: String? = null,
        val angkatan: String? = null,
        val judulskripsi: String? = null,
        val dosenpem1: String? = null,
        val dosenpem2: String? = null

    ) {
        fun isValid(): Boolean {
            return nim == null && nama == null && jenisKelamin == null &&
                    alamat == null && kelas == null && angkatan == null && judulskripsi == null
                    && dosenpem1 == null && dosenpem2 == null
        }
    }

    data class MahasiswaEvent(
        val nim: String = "",
        val nama: String = "",
        val jenisKelamin: String = "",
        val alamat: String = "",
        val kelas: String = "",
        val angkatan: String = "",
        val judulskripsi: String = "",
        val dosenpem1: String = "",
        val dosenpem2: String = ""
    )

    fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
        nim = nim,
        nama = nama,
        jenisKelamin = jenisKelamin,
        alamat = alamat,
        kelas = kelas,
        angkatan = angkatan,
        judulskripsi = judulskripsi,
        dosenpem1 = dosenpem1,
        dosenpem2 = dosenpem2
    )

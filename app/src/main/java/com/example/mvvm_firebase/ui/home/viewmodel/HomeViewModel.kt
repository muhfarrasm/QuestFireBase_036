package com.example.mvvm_firebase.ui.home.viewmodel

import android.net.http.HttpException
import android.os.Build
import android.provider.ContactsContract.RawContacts.Data
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_firebase.model.Mahasiswa
import com.example.mvvm_firebase.repository.NetworkRepositoryMhs
import com.example.mvvm_firebase.repository.RepositoryMhs
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.IOException

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class  HomeViewModel(
    private val repositoryMhs: RepositoryMhs
): ViewModel(){
    var mhsUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getMhs()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getMhs() {
        viewModelScope.launch {
           repositoryMhs.getAllMhs()
               .onStart {
                   mhsUiState = HomeUiState.Loading
               }
               .catch {
                   mhsUiState = HomeUiState.Error(e= it)
               }
               .collect{
                   mhsUiState = if (it.isEmpty()){
                       HomeUiState.Error(Exception("Belum Ada Data"))
                   }else{
                       HomeUiState.Succes(it)
                   }
               }
        }
    }

    fun deleteMhs(mahasiswa: Mahasiswa) {
        viewModelScope.launch {
            try {
                repositoryMhs.deleteMhs(mahasiswa)
                getMhs()

            } catch (e: Exception) {
                Log.e("DeleteMhs", "Gagal menghapus mahasiswa: ${e.message}")
                mhsUiState = HomeUiState.Error(e)
            }
        }
    }
}
sealed class HomeUiState {
    object Loading : HomeUiState()
    data class  Succes(val data: List<Mahasiswa>) : HomeUiState()
    data class  Error(val e: Throwable) : HomeUiState()

}
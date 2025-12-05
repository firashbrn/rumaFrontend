package com.example.rumafrontend.ui.theme.screen.profile


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rumafrontend.data.model.pengguna
import com.example.rumafrontend.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// DEFINISI UI STATE (Perbaikan)
data class ProfileUiState(
    val userModel: pengguna? = null, // Ditambahkan untuk menyimpan objek domain
    val username: String = "",
    val email: String = "", // Ditambahkan karena ditampilkan di Screen
    val isSaving: Boolean = false,
    val showLogoutDialog: Boolean = false,
    val isDarkMode: Boolean = false,
    val saveSuccess: Boolean = false,
    val error: String? = null
)

class ProfileViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        // Logika untuk mengisi data sesi dari Room
        viewModelScope.launch {
            repository.getUserProfile().collect { user ->
                _uiState.update { currentState ->
                    // Memperbarui state UI dengan data dari Room/Repository
                    currentState.copy(
                        userModel = user, // Menyimpan model lengkap
                        username = user.username?:"",
                        email = user.email?:"" // Menyalin email ke field input UI
                    )
                }
            }
        }
    }

    // FUNGSI PERBAIKAN 1: onDarkModeToggle (Mengatasi error di Switch)
    fun onDarkModeToggle(isChecked: Boolean) {
        _uiState.update { it.copy(isDarkMode = isChecked) }
        // TODO: Tambahkan logic untuk menyimpan preferensi Dark Mode ke DataStore/SharedPreferences
    }

    // FUNGSI PERBAIKAN 2: onLogoutClicked (Mengatasi error onClick di tombol Log Out)
    fun onLogoutClicked() {
        _uiState.update { it.copy(showLogoutDialog = true) }
    }

    // FUNGSI PERBAIKAN 3 & 6: dismissLogoutDialog (Mengatasi error onDismissRequest dan onClick 'Ga jadi')
    fun dismissLogoutDialog() {
        _uiState.update { it.copy(showLogoutDialog = false) }
    }

    // FUNGSI PERBAIKAN 4 & 5: confirmLogout (Mengatasi error onClick 'Yakin')
    fun confirmLogout() {
        viewModelScope.launch {
            repository.logout()
            // Setelah logout, Flow akan terpicu dan navigasi harus dilakukan di View/Activity
        }
    }

    // Fungsi untuk Halaman Edit Profile
    fun saveProfile(username: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true, error = null) }
            try {
                repository.updateProfile(username, email, password)

                // Perbarui juga data di state setelah sukses menyimpan
                _uiState.update { it.copy(
                    isSaving = false,
                    saveSuccess = true,
                    username = username,
                    email = email
                ) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isSaving = false, error = e.message) }
            }
        }
    }

    fun onSaveSuccessHandled() {
        _uiState.update { it.copy(saveSuccess = false) }
    }
}
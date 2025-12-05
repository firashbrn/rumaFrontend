package com.example.rumafrontend.ui.theme.screen.profile

import com.example.rumafrontend.data.model.pengguna

data class ProfileUiState(
    val pengguna: pengguna = username(),
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
        // Mengumpulkan (collect) data profile dari repository ke StateFlow
        viewModelScope.launch {
            repository.getUserProfile().collect { user ->
                _uiState.update { it.copy(user = user) }
            }
        }
        // Catatan: Data Dark Mode harus diambil dari SharedPreferences/DataStore
    }

    // Fungsi untuk Halaman Profile
    fun onLogoutClicked() {
        _uiState.update { it.copy(showLogoutDialog = true) }
    }

    fun dismissLogoutDialog() {
        _uiState.update { it.copy(showLogoutDialog = false) }
    }

    fun confirmLogout() {
        viewModelScope.launch {
            repository.logout()
            // Setelah logout, karena Room kosong, Flow di atas akan mengirim User() kosong
            // yang akan memicu navigasi ke Login di Activity/Fragment
        }
    }

    // Fungsi untuk Halaman Edit Profile
    fun saveProfile(username: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true, error = null) }
            try {
                // Panggil Repository untuk update
                repository.updateProfile(username, email, password)

                _uiState.update { it.copy(isSaving = false, saveSuccess = true) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isSaving = false, error = e.message) }
            }
        }
    }

    fun onSaveSuccessHandled() {
        _uiState.update { it.copy(saveSuccess = false) }
    }
}
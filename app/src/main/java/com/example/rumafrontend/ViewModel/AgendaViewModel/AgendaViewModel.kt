package com.example.rumafrontend.ViewModel.AgendaViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.rumafrontend.data.model.Agenda

class AgendaViewModel : ViewModel() {
    // gunakan nama yang sama dengan di screen: agendas
    private val _agendas = mutableStateListOf<Agenda>()
    val agendas: List<Agenda> get() = _agendas

    // fungsi menambahkan agenda (dipakai di AddAgendaScreen)
    fun addAgenda(judul: String, kategori: String, waktu: String) {
        val id = if (_agendas.isEmpty()) 1 else (_agendas.maxOf { it.id } + 1)
        val newAgenda = Agenda(
            id = id,
            judul = judul,
            kategori = kategori,
            waktu = waktu,
            tanggal = "",
            deskripsi = "",
            lokasi = "",
            pengingat = ""
        )
        _agendas.add(newAgenda)
    }

    // versi lain: tambah agenda langsung dengan objek
    fun addAgendaObject(agenda: Agenda) {
        _agendas.add(agenda)
    }

    fun removeAgendaById(id: Int) {
        _agendas.removeIf { it.id == id }
    }

    fun updateAgenda(updated: Agenda) {
        val index = _agendas.indexOfFirst { it.id == updated.id }
        if (index != -1) {
            _agendas[index] = updated
        }
    }
}

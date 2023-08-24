package co.id.mii.qrscanner.features.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.id.mii.qrscanner.features.home.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(val repo: HomeRepository) : ViewModel() {

    private val _saldoState = mutableDoubleStateOf(0.0)
    val saldoState: State<Double> = _saldoState

    init {
        getSaldo()
    }

    private fun getSaldo() {
        viewModelScope.launch {
            repo.getSaldo().collect() {
                _saldoState.doubleValue = it
            }
        }
    }

}
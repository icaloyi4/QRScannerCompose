package co.id.mii.qrscanner.features.promo.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.id.mii.qrscanner.core.database.entity.TransactionEntity
import co.id.mii.qrscanner.features.promo.model.BankPromoResponse
import co.id.mii.qrscanner.features.promo.repository.PromoRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PromoViewModel(val repository: PromoRepository) : ViewModel() {

    private val _listPromo = mutableStateOf<List<BankPromoResponse>>(arrayListOf())

    var listPromoState : State<List<BankPromoResponse>> = _listPromo

    private val _laodingState = mutableStateOf<Boolean>(false)
    val loadingState : State<Boolean>  = _laodingState

    init {
        getPromo()
    }

    private fun getPromo() {
        viewModelScope.launch {
            _laodingState.value = true
            repository.getPromo().collect() {
                _listPromo.value = it
            }
            _laodingState.value = false
        }
    }
}
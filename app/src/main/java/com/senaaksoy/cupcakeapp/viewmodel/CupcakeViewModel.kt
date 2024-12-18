package com.senaaksoy.cupcakeapp.viewmodel

import androidx.lifecycle.ViewModel
import com.senaaksoy.cupcakeapp.data.CupcakeUiState
import com.senaaksoy.cupcakeapp.data.priceForSameDay
import com.senaaksoy.cupcakeapp.data.unitPrice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CupcakeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CupcakeUiState())
    val uiState: StateFlow<CupcakeUiState> = _uiState.asStateFlow()


     fun upDateFlavor(newFlavor: String) {
        _uiState.update { currentState ->
            currentState.copy(flavor = newFlavor)
        }
    }

    fun upDatePickupDate(newDate: String) {
        _uiState.update { currentState ->
            currentState.copy(pickupDate = newDate)
        }
    }

    fun upDateSubTotal(quantity : Int) {
        _uiState.update { currentState->
            currentState.copy(
                subtotal = unitPrice * quantity,
                quantity = quantity
            )
        }
    }
    fun formatTotalPrice() : String {
        return if(_uiState.value.dateList.isNotEmpty() && _uiState.value.pickupDate==_uiState.value.dateList[0]){
            NumberFormat.getCurrencyInstance().format(_uiState.value.subtotal + priceForSameDay)
        }
        else{
            NumberFormat.getCurrencyInstance().format(_uiState.value.subtotal)
        }
    }


    fun currentDateList() : MutableList<String> {
        _uiState.value.dateList.clear()
        val calendar=Calendar.getInstance()
        val formatter = SimpleDateFormat("E MMM d",Locale.getDefault())
        repeat(4) {
            _uiState.value.dateList.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE,1)
        }
        return _uiState.value.dateList
    }
    fun resetOrder() {
        _uiState.update {
            CupcakeUiState()
        }
    }
    fun resetPickupDate() {
        _uiState.update { currentState ->
            currentState.copy(pickupDate = "")
        }
    }




}
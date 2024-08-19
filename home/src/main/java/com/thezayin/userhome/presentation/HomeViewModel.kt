package com.thezayin.userhome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetLoadingState
import com.thezayin.entities.HomeProductsModel
import com.thezayin.framework.utils.Response
import com.thezayin.userhome.domain.usecase.GetProWithImg
import com.thezayin.userhome.domain.usecase.GetProducts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val getProduct: GetProducts, private val getProWithImg: GetProWithImg) :
    ViewModel() {
    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    private val _getProducts = MutableStateFlow(GetProductState())
    val getProducts = _getProducts.asStateFlow()

    private val _isQueryError =
        MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    init {
        getProductsFromFirebase()
    }

    private fun getProducts(list: List<HomeProductsModel>) =
        viewModelScope.launch {
            getProWithImg(list).collect { response ->
                when (response) {
                    is Response.Success -> {
                        _isLoading.update { it.copy(isLoading = false) }
                        _getProducts.update { it.copy(list = response.data) }
                    }

                    is Response.Error -> {
                        _isQueryError.update { errorState ->
                            errorState.copy(isError = true, errorMessage = response.e)
                        }
                        _isLoading.update { it.copy(isLoading = false) }
                    }

                    is Response.Loading -> {
                        _isLoading.update { it.copy(isLoading = true) }
                    }
                }
            }
        }

    private fun getProductsFromFirebase() = viewModelScope.launch {
        getProduct().collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    getProducts(response.data)
                }

                is Response.Error -> {
                    _isQueryError.update { errorState ->
                        errorState.copy(isError = true, errorMessage = response.e)
                    }
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    data class GetProductState(val list: List<com.thezayin.entities.HomeProductsModel> = emptyList())
}
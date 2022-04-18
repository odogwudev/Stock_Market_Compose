package com.odogwudev.stockmarketcompose.presentation.company_infoscreen

import com.odogwudev.stockmarketcompose.domain.model.CompanyInfo
import com.odogwudev.stockmarketcompose.domain.model.IntraDayInfo
import com.odogwudev.stockmarketcompose.util.Resource

data class CompanyInfoState(
    val stockInfo: List<IntraDayInfo> = emptyList(),
    val companyInfo: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
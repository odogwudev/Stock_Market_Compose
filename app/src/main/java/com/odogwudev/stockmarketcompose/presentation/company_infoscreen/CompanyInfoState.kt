package com.odogwudev.stockmarketcompose.presentation.company_infoscreen

import com.odogwudev.stockmarketcompose.domain.model.CompanyInfo
import com.odogwudev.stockmarketcompose.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
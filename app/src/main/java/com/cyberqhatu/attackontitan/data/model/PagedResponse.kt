package com.cyberqhatu.attackontitan.data.model

import com.google.gson.annotations.SerializedName

data class PagedResponse<T>(
    val info: Info,
    val results: List<T>
)

data class Info(
    val count: Int,
    val pages: Int,
    @SerializedName("next_page") val next: String?,
    @SerializedName("prev_page") val prev: String?
)

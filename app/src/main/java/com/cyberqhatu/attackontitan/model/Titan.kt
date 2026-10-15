package com.cyberqhatu.attackontitan.model

import com.cyberqhatu.attackontitan.data.remote.StringOrListAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class Titan(
    val id: Int,
    val name: String,
    val img: String? = null,
    val height: String? = null,
    val abilities: List<String>? = emptyList(),
    @SerializedName("current_inheritor") val currentInheritor: String? = null,
    @SerializedName("former_inheritors") val formerInheritors: List<String>? = emptyList(),
    @JsonAdapter(StringOrListAdapter::class) val allegiance: String? = null
)

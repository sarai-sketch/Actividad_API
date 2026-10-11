package com.cyberqhatu.attackontitan.data.model

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class Titan(
    val id: Int,
    val name: String,
    val img: String,
    val height: String,
    val abilities: List<String>,
    @SerializedName("current_inheritor") val currentInheritor: String?,
    @SerializedName("former_inheritors") val formerInheritors: List<String>,
    @field:JsonAdapter(StringOrListAdapter::class) val allegiance: String?
)

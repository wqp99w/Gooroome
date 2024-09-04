package com.wqp99w.gooroome.naverSearchApi

import java.io.Serializable

data class SearchItem(
    val title: String,
    val link: String,
    val description: String
) : Serializable

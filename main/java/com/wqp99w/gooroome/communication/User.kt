package com.wqp99w.gooroome.communication

import java.io.Serializable

data class User(
    val id:Long? = null,
    val userid: String,
    val password: String,
    val one: String,
    val two: String,
    val three: String,
    val four: String) : Serializable

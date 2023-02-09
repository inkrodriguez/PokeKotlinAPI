package com.inkrodriguez.pokekotlinapi.api

data class myData(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)
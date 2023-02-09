package com.inkrodriguez.pokekotlinapi.api

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)
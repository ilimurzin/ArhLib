package ru.arhlib.app.actual

import kotlinx.serialization.Serializable

@Serializable
data class ActualLink(
        val emoji: String,
        val title: String,
        val description: String,
        val link: String,
) : ActualItem

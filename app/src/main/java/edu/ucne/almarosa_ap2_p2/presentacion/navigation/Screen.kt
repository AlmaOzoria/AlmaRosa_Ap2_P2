package edu.ucne.almarosa_ap2_p2.presentacion.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object ApiList : Screen()
    @Serializable
    data class Api (val name: String) : Screen()

}
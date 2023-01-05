package fr.mastersid.deliens.home.backend

interface HomeUtil {

    fun estimation(pieces: Int, surface: Float, region: String): Float
}
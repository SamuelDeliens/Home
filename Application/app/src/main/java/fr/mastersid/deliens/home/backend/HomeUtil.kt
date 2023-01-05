package fr.mastersid.deliens.home.backend

interface HomeUtil {

    fun estimation(propertyType: String ,pieces: Int, surface: Float, region: String): Float
}
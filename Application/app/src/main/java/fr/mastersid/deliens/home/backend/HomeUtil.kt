package fr.mastersid.deliens.home.backend

interface HomeUtil {

    fun estimation(propertyType: String ,pieces: Int, surfaceInside: Float ,surfaceOutside: Float, region: String): Float
}
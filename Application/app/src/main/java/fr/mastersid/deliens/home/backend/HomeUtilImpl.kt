package fr.mastersid.deliens.home.backend

import javax.inject.Inject

class HomeUtilImpl @Inject constructor(): HomeUtil {

    override fun estimation(pieces: Int, surface: Float, region: String): Float {
        return pieces * surface * 1000
    }
}
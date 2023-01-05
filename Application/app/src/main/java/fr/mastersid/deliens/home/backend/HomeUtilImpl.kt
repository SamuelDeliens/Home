package fr.mastersid.deliens.home.backend

import android.util.Log
import javax.inject.Inject

class HomeUtilImpl @Inject constructor(): HomeUtil {

    private val propertyMapping = mapOf(
        "Maison" to 1,
        "Appartement" to 2,
    )

    private val regionMapping = mapOf(
        "AB" to 0, "AC" to 1, "AD" to 2, "AE" to 3, "AH" to 4, "AI" to 5,
        "AK" to 6, "AO" to 7, "AP" to 8, "AR" to 9, "AS" to 10, "AT" to 11,
        "AV" to 12, "AW" to 13, "AX" to 14, "AY" to 15, "AZ" to 16,
        "BC" to 17, "BD" to 18, "BE" to 19, "BH" to 20, "BK" to 21,
        "BL" to 22, "BM" to 23, "BO" to 24, "BP" to 25, "BR" to 26,
        "BS" to 27, "BT" to 28, "BV" to 29, "BW" to 30
    )

    override fun estimation(propertyType: String, pieces: Int, surfaceInside: Float, surfaceOutside: Float , region: String): Float {
        val intPropertyType = propertyMapping[propertyType]!!
        val intRegion = regionMapping[region]!!

        return if (intPropertyType == 1) {
            estimationMaison(intPropertyType, pieces, surfaceInside, surfaceOutside, intRegion)
        } else {
            estimationAppartement(intPropertyType, pieces, surfaceInside, intRegion)
        }
    }


    private fun estimationAppartement(propertyType: Int, pieces: Int, surface: Float, region: Int): Float {
        return surface * pieces * 1000 + region
    }

    private fun estimationMaison(propertyType: Int, pieces: Int, surface: Float, terrain: Float, region: Int): Float {
        return surface * pieces * 1000 + terrain * 1000 + region
    }
}
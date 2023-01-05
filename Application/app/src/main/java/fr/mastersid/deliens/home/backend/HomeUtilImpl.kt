package fr.mastersid.deliens.home.backend

import javax.inject.Inject

class HomeUtilImpl @Inject constructor(): HomeUtil {

    private val propertyMapping = mapOf(
        "Maison" to 1,
        "Appartement" to 2,
    )

    private val regionMapping = mapOf(
        "000AB" to 0, "000AC" to 1, "000AD" to 2, "000AE" to 3, "000AH" to 4, "000AI" to 5,
        "000AK" to 6, "000AO" to 7, "000AP" to 8, "000AR" to 9, "000AS" to 10, "000AT" to 11,
        "000AV" to 12, "000AW" to 13, "000AX" to 14, "000AY" to 15, "000AZ" to 16,
        "000BC" to 17, "000BD" to 18, "000BE" to 19, "000BH" to 20, "000BK" to 21,
        "000BL" to 22, "000BM" to 23, "000BO" to 24, "000BP" to 25, "000BR" to 26,
        "000BS" to 27, "000BT" to 28, "000BV" to 29, "000BW" to 30
    )

    override fun estimation(propertyType: String, pieces: Int, surfaceInside: Float, surfaceOutside: Float , region: String): Float {
        val intPropertyType = propertyMapping[propertyType]!!
        val intRegion = regionMapping[region]!!

        return if (propertyType == "Maison") {
            estimationMaison(intPropertyType, pieces, surfaceInside, surfaceOutside, intRegion)
        } else {
            estimationAppartement(intPropertyType, pieces, surfaceInside, intRegion)
        }
    }


    private fun estimationAppartement(propertyType: Int, pieces: Int, surface: Float, region: Int): Float {
        return surface * pieces * 1000
    }

    private fun estimationMaison(propertyType: Int, pieces: Int, surface: Float, terrain: Float, region: Int): Float {
        return surface * pieces * 1000 + terrain * 1000
    }
}
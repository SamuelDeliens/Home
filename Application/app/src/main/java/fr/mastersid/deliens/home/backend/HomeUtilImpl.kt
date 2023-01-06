package fr.mastersid.deliens.home.backend

import javax.inject.Inject

/**
 * Created by Samuel Deliens and Wassim Briane on 2021-01-18.
 *
 * Implementation of the HomeUtil interface.
 * This class is used to estimate the price of a property.
 *
 * Arguments:
 * ----------
 * propertyType: String => The type of the property. Can be "Maison" or "Appartement".
 * pieces: Int => The number of pieces in the property.
 * surfaceInside: Float => The surface of the property inside.
 * surfaceOutside: Float? => The surface of the property outside. Only used for "Maison".
 * region: String => The region of the property. Can be "AB", "AC", "AD", "AE", "AH", "AI", "AK", "AO", "AP", "AR", "AS", "AT", "AV", "AW", "AX", "AY", "AZ", "BC", "BD", "BE", "BH", "BK", "BL", "BM", "BO", "BP", "BR", "BS", "BT", "BV", "BW".
 *
 * Returns:
 * --------
 * Float => The estimated price of the property.
 */
class HomeUtilImpl @Inject constructor(): HomeUtil {

    /**
     * Parameters for the estimation:
     *
     * propertyMapping: Map<String, Float> => The mapping between the property type and the coefficient.
     * regionMapping: Map<String, Float> => The mapping between the region and the coefficient.
     */
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


    /**
     * Parameters for the estimation:
     *
     * Maison:
     * coefMaison: FloatArray => The coefficients for the estimation of a "Maison".
     * constantMaison: Float => The constant for the estimation of a "Maison".
     *
     * Appartement:
     * coefAppartement: FloatArray => The coefficients for the estimation of an "Appartement".
     * constantAppartement: Float => The constant for the estimation of an "Appartement".
     */
    private val coefMaison = listOf(
        0.00000000e+00,  1.56556876e+03,  1.95966722e+04,  8.98006888e+01,
        7.65267056e+01, -1.36436233e+02,  6.31053297e-01,  6.08622895e+00,
        -2.27808753e+01, -1.47887928e+02,  2.44993526e-01
    )
    val constantMaison = -19587.326479585405


    private val coefAppartement = listOf(
        0.00000000e+00,  3.32656511e+03, -2.47891337e+04, -2.49033260e+04,
        -1.10055465e+03, -6.34467833e+00,  3.66451410e+03
    )
    val constantAppartement = 596167.5011129292


    /**
     * Estimate the price of a property.
     * @param propertyType: String => The type of the property. Can be "Maison" or "Appartement".
     * @param pieces: Int => The number of pieces in the property.
     * @param surfaceInside: Float => The surface of the property inside.
     * @param surfaceOutside: Float? => The surface of the property outside. Only used for "Maison".
     *
     * @return Float => The estimated price of the property.
     */
    override fun estimation(propertyType: String, pieces: Int, surfaceInside: Float, surfaceOutside: Float?, region: String): Float {

        val intPropertyType = propertyMapping[propertyType]!!
        val intRegion = regionMapping[region]!!


        return if (intPropertyType == 1) {
            estimationMaison(pieces, surfaceInside, surfaceOutside!!, intRegion)
        } else {
            estimationAppartement(pieces, surfaceInside, intRegion)
        }
    }


    /**
     * Estimate the price of a "Appartement".
     * @param pieces: Int => The number of pieces in the property.
     * @param surfaceInside: Float => The surface of the property inside.
     * @param surfaceOutside: Float => The surface of the property outside.
     * @param region: Int => The region of the property.
     *
     * @return Float => The estimated price of the property.
     */
    private fun estimationAppartement(pieces: Int, surfaceInside: Float, region: Int): Float {
        return (
                constantAppartement +
                coefAppartement[0] +
                coefAppartement[1] * surfaceInside +
                coefAppartement[2] * pieces +
                coefAppartement[3] * region +
                coefAppartement[4] * surfaceInside * pieces +
                coefAppartement[5] * surfaceInside * region +
                coefAppartement[6] * pieces * region
                ).toFloat()
    }

    /**
     * Estimate the price of a "Maison".
     * @param pieces: Int => The number of pieces in the property.
     * @param surfaceInside: Float => The surface of the property inside.
     * @param surfaceOutside: Float => The surface of the property outside.
     * @param region: Int => The region of the property.
     *
     * @return Float => The estimated price of the property.
     */
    private fun estimationMaison(pieces: Int, surfaceInside: Float, surfaceOutside: Float, region: Int): Float {
        return (
                constantMaison
                + coefMaison[0]
                + coefMaison[1] * surfaceInside
                + coefMaison[2] * pieces
                + coefMaison[3] * surfaceOutside
                + coefMaison[4] * region
                + coefMaison[5] * surfaceInside * pieces
                + coefMaison[6] * surfaceInside * surfaceOutside
                + coefMaison[7] * surfaceInside * region
                + coefMaison[8] * pieces * surfaceOutside
                + coefMaison[9] * pieces * region
                + coefMaison[10] * surfaceOutside * region
                ).toFloat()
    }
}
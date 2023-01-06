package fr.mastersid.deliens.home.backend

/**
 * Created by Samuel Deliens on 2021-01-18.
 *
 * Interface for the HomeUtil class.
 * This class is used to estimate the price of a property.
 *
 * Arguments:
 * ----------
 * @param propertyType: String => The type of the property. ("Maison" or "Appartement)
 * @param pieces: Int => The number of pieces in the property.
 * @param surfaceInside: Float => The surface of the property inside.
 * @param surfaceOutside: Float? => The surface of the property outside. Only used for "Maison".
 * @param region: String => The region of the property. (AB, BC, MB, NB, NL, NS, NT, NU, ON, PE, QC, SK, YT)
 *
 * Returns:
 * --------
 * @return Float => The estimated price of the property.
 */
interface HomeUtil {

    fun estimation(propertyType: String, pieces: Int, surfaceInside: Float, surfaceOutside: Float?, region: String): Float
}
package fr.mastersid.deliens.home.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Sealed Interface that captures the estimation result
 */
sealed interface EstimationResult: Parcelable {

    @Parcelize
    /**
     * Data class that captures the estimation result
     * @param propertyType the type of the property
     * @param pieces the number of pieces
     * @param surface the surface of the property
     * @param terrain the terrain of the property
     * @param region the region of the property
     * @param result the price of the property
     */

    data class Estimated(val propertyType: String, val pieces: Int, val surface: Float, val terrain: Float?, val region: String, val result: Float): EstimationResult

    @Parcelize
    /**
     * Data class that captures the error message
     * @param message the error message
     */
    data class Failed(val error_message: String): EstimationResult

    @Parcelize
    /**
     * Data class when the estimation is empty
     */
    object Empty: EstimationResult
}
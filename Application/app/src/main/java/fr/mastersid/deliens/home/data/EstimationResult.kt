package fr.mastersid.deliens.home.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface EstimationResult: Parcelable {

    @Parcelize
    data class Estimated(val propertyType: String, val pieces: Int, val surface: Float, val region: String, val result: Float): EstimationResult

    @Parcelize
    data class Failed(val error_message: String): EstimationResult

    @Parcelize
    object Empty: EstimationResult
}
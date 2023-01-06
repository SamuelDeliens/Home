package fr.mastersid.deliens.home.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
/**
 * Data class that captures List of the estimation results
 *
 * @param id the id of the estimation result
 * @param estimation the estimation result
 */
data class EstimationList(val id: Int, val estimation: EstimationResult.Estimated): Parcelable

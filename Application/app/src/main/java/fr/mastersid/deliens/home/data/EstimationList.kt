package fr.mastersid.deliens.home.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EstimationList(val id: Int, val estimation: EstimationResult.Estimation): Parcelable
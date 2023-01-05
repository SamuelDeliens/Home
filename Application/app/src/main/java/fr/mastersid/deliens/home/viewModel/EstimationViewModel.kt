package fr.mastersid.deliens.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.deliens.home.backend.HomeUtil
import fr.mastersid.deliens.home.data.EstimationResult
import javax.inject.Inject

private const val STATE_ESTIMATION_RESULT = "state_estimation_result"
private const val STATE_ESTIMATION_LIST = "state_estimation_list"

@HiltViewModel
class EstimationViewModel @Inject constructor(
    state: SavedStateHandle,
    private val homeUtil: HomeUtil
) : ViewModel() {

    private val _resultEstimation : MutableLiveData<EstimationResult> = state.getLiveData(STATE_ESTIMATION_RESULT, EstimationResult.Empty)
    val resultEstimation: LiveData<EstimationResult> = _resultEstimation

    private val _estimationList : MutableLiveData<List<EstimationResult.Estimated>> = state.getLiveData(STATE_ESTIMATION_LIST, emptyList())
    val estimationList: LiveData<List<EstimationResult.Estimated>> = _estimationList

    fun estimation(propertyType: String, pieces: Int?, surface: Float?, terrain: Float?, region: String) {
        try {
            if (pieces == null || surface == null || terrain == null) {
                throw IllegalArgumentException("Missing arguments")
            } else {
                val estimation = homeUtil.estimation(propertyType, pieces, surface, terrain, region)

                _resultEstimation.value = EstimationResult.Estimated(propertyType, pieces, surface, region, estimation)
                _estimationList.value = _estimationList.value!! + listOf(EstimationResult.Estimated(propertyType, pieces, surface, region, estimation))
            }
        } catch (e: IllegalArgumentException) {
            _resultEstimation.value = EstimationResult.Failed(e.message!!)
        }
    }
}
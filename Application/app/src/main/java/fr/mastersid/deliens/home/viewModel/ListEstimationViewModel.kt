package fr.mastersid.deliens.home.viewModel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.deliens.home.backend.HomeUtil
import fr.mastersid.deliens.home.data.EstimationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val STATE_ESTIMATION_RESULT = "state_estimation_result"
private const val STATE_ESTIMATION_LIST = "state_estimation_list"

@HiltViewModel
/**
 * ViewModel for the list of estimations
 * @param homeUtil the home util
 */
class ListEstimationViewModel @Inject constructor(
    state : SavedStateHandle,
    private val homeUtil: HomeUtil
) : ViewModel() {

    // The list of estimations
    private val _resultEstimation : MutableLiveData<EstimationResult> = state.getLiveData(STATE_ESTIMATION_RESULT, EstimationResult.Empty)
    val resultEstimation: LiveData<EstimationResult> = _resultEstimation

    // The list of estimations
    private val _estimationList : MutableLiveData<List<EstimationResult.Estimated>> = state.getLiveData(STATE_ESTIMATION_LIST, emptyList())
    val estimationList: LiveData<List<EstimationResult.Estimated>> = _estimationList


    /**
     * Initialize the list of estimations
     */
     fun initEstimationList() {
            if (_estimationList.value!!.isEmpty()) {
                viewModelScope.launch(Dispatchers.Default) {
                    val initValue = arrayOf(
                        arrayOf("Maison", 5, 100f, 180f, "AB"),
                        arrayOf("Appartement", 3, 50f, 0f, "AB"),
                        arrayOf("Maison", 4, 80f, 150f, "AB"),
                        arrayOf("Appartement", 2, 40f, 0f, "AB"),
                        arrayOf("Maison", 6, 120f, 200f, "AB"),
                    )
                    var list:List<EstimationResult.Estimated> = emptyList()

                    for (i in initValue) {
                        val estimation = homeUtil.estimation(i[0] as String, i[1] as Int, i[2] as Float, i[3] as Float, i[4] as String)
                        list = list + EstimationResult.Estimated(i[0] as String, i[1] as Int, i[2] as Float, i[3] as Float, i[4] as String, estimation)
                    }
                    _estimationList.postValue(list)
                }
            }
        }

        /**
         * Estimate the price of a house
         *
         * @param propertyType the type of the property
         * @param pieces the number of pieces
         * @param surfaceInside the inside surface
         * @param surfaceOutside the outside surface
         * @param region the region
         *
         * @return the estimation result
         * @throws Exception if the estimation failed
         */
        fun estimation(propertyType: String, pieces: Int?, surfaceInside: Float?, surfaceOutside: Float?, region: String) {
            viewModelScope.launch(Dispatchers.Default) {
                try {
                    if (pieces == null || surfaceInside == null || (surfaceOutside == null && propertyType == "Maison")) {
                        throw IllegalArgumentException("Missing arguments")
                    } else {
                        val estimation = homeUtil.estimation(propertyType, pieces, surfaceInside, surfaceOutside, region)
                        _resultEstimation.postValue(
                            EstimationResult.Estimated(propertyType, pieces, surfaceInside, surfaceOutside, region, estimation)
                        )
                        _estimationList.postValue(
                            listOf(EstimationResult.Estimated(propertyType, pieces, surfaceInside, surfaceOutside, region, estimation)) + _estimationList.value!!
                        )
                    }
                } catch (e: IllegalArgumentException) {
                    _resultEstimation.postValue(
                        EstimationResult.Failed(e.message!!)
                    )
                }
            }
        }

}
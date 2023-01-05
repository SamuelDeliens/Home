package fr.mastersid.deliens.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.deliens.home.data.EstimationResult
import javax.inject.Inject

private const val STATE_ESTIMATION_LIST = "state_estimation_list"

@HiltViewModel
class ListEstimationViewModel @Inject constructor(
    state : SavedStateHandle,
) : ViewModel() {

    private val _estimationList : MutableLiveData<List<EstimationResult.Estimation>> = state.getLiveData(STATE_ESTIMATION_LIST, emptyList())
    val estimationList: LiveData<List<EstimationResult.Estimation>> = _estimationList

}
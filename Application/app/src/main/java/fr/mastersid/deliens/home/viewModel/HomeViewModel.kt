package fr.mastersid.deliens.home.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.deliens.home.backend.HomeUtil
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    state: SavedStateHandle,
) : ViewModel() {

}
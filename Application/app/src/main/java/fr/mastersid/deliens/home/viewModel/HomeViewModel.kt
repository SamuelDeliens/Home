package fr.mastersid.deliens.home.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
/**
 * ViewModel for the home page
 */
class HomeViewModel @Inject constructor(
    state: SavedStateHandle,
) : ViewModel() {

}
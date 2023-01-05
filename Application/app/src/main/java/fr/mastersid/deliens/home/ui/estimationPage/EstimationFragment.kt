package fr.mastersid.deliens.home.ui.estimationPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.deliens.home.data.EstimationResult
import fr.mastersid.deliens.home.databinding.FragmentEstimationBinding
import fr.mastersid.deliens.home.viewModel.EstimationViewModel

@AndroidEntryPoint
class EstimationFragment : Fragment() {

    private lateinit var binding: FragmentEstimationBinding
    private val estimationViewModel: EstimationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEstimationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val estimationResult = binding.estimationResult


        binding.estimationButton.setOnClickListener {
            val radioResult: RadioButton = binding.radioButton.checkedRadioButtonId.let { id ->
                binding.root.findViewById(id)
            }
            val propertyType = binding.spinner.selectedItem.toString()

            estimationViewModel.estimation(
                binding.spinner.selectedItem.toString(),
                binding.editTextRooms.text.toString().toInt(),
                binding.editTextSurface.text.toString().toFloat(),
                binding.editTextOther.text.toString()
            )
        }

        estimationViewModel.resultEstimation.observe(viewLifecycleOwner) { value ->
            when (value) {
                is EstimationResult.Empty -> {
                    estimationResult.visibility = View.GONE
                }
                is EstimationResult.Estimated -> {
                    estimationResult.visibility = View.VISIBLE
                    estimationResult.text = value.result.toString()
                }
                is EstimationResult.Failed -> {
                    estimationResult.visibility = View.VISIBLE
                    estimationResult.text = value.error_message
                }
            }

        }
    }
}
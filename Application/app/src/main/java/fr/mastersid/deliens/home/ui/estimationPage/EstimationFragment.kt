package fr.mastersid.deliens.home.ui.estimationPage

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.deliens.home.R
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
            val propertyType : RadioButton = binding.radioButton.checkedRadioButtonId.let { id ->
                binding.root.findViewById(id)
            }
            val region = binding.spinner.selectedItem.toString()

            estimationViewModel.estimation(
                propertyType.text.toString(),
                binding.editTextRooms.text.toString().toIntOrNull(),
                binding.editTextSurface.text.toString().toFloatOrNull(),
                binding.editTextTerrain.text.toString().toFloatOrNull(),
                binding.spinner.selectedItem.toString(),
            )
        }

        binding.radioMaison.setOnClickListener {
            binding.editTextTerrain.visibility = View.VISIBLE
        }

        binding.radioAppartement.setOnClickListener {
            binding.editTextTerrain.visibility = View.GONE
        }


        estimationViewModel.resultEstimation.observe(viewLifecycleOwner) { value ->
            when (value) {
                is EstimationResult.Empty -> {
                    estimationResult.visibility = View.GONE
                }
                is EstimationResult.Estimated -> {
                    estimationResult.visibility = View.VISIBLE
                    estimationResult.text = value.result.toString()
                    estimationResult.setTextColor(resources.getColor(R.color.black))
                }
                is EstimationResult.Failed -> {
                    estimationResult.visibility = View.VISIBLE
                    estimationResult.text = value.error_message
                    estimationResult.setTextColor(resources.getColor(R.color.error))
                }
            }
        }
    }
}
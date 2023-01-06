package fr.mastersid.deliens.home.ui.estimationPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.deliens.home.R
import fr.mastersid.deliens.home.data.EstimationResult
import fr.mastersid.deliens.home.databinding.FragmentEstimationBinding
import fr.mastersid.deliens.home.viewModel.ListEstimationViewModel

@AndroidEntryPoint
/**
 * A simple [Fragment] subclass.
 */
class EstimationFragment : Fragment() {

    private lateinit var binding: FragmentEstimationBinding
    private val listEstimationViewModel: ListEstimationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentEstimation = FragmentEstimationBinding.inflate(layoutInflater)
        binding = fragmentEstimation
        return fragmentEstimation.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = listEstimationViewModel
            estimationFragment = this@EstimationFragment
        }


        val estimationResult = binding.estimationResult


        /**
         * 1. On récupère les valeurs des boutons radio
         * 2. On récupère les valeurs des EditText
         * 3. On fait les calculs d'estimation
         */
        binding.estimationButton.setOnClickListener {
            val propertyType : RadioButton = binding.radioButton.checkedRadioButtonId.let { id ->
                binding.root.findViewById(id)
            }
            val region = binding.spinner.selectedItem.toString()
            listEstimationViewModel.estimation(
                propertyType.text.toString(),
                binding.editTextRooms.text.toString().toIntOrNull(),
                binding.editTextSurface.text.toString().toFloatOrNull(),
                binding.editTextTerrain.text.toString().toFloatOrNull(),
                binding.spinner.selectedItem.toString(),
            )
        }

        /**
         * 1. On récupère les valeurs des boutons radio
         * 2. On cache les EditText inutiles
         */
        binding.radioMaison.setOnClickListener {
            binding.editTextTerrain.visibility = View.VISIBLE
        }

        /**
         * 1. On récupère les valeurs des boutons radio
         * 2. On affiche les EditText inutiles
         */
        binding.radioAppartement.setOnClickListener {
            binding.editTextTerrain.visibility = View.GONE
        }


        /**
         * 1. On observe les changements de la variable estimationResult
         * 2. On affiche les résultats
         * 3. On affiche la vue des résultats
         */
        listEstimationViewModel.resultEstimation.observe(viewLifecycleOwner) { value ->
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
package fr.mastersid.deliens.home.ui.estimationPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import fr.mastersid.deliens.home.R
import fr.mastersid.deliens.home.databinding.FragmentEstimationBinding
import java.util.*

class EstimationFragment : Fragment() {

    private lateinit var binding: FragmentEstimationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEstimationBinding.inflate(layoutInflater)

        val list_bien = arrayOf<String?>("Maison", "Appartement", "Local", "Terrain", "Autre")
        val spinner = binding.spinner
        val adapter: ArrayAdapter<Any?> = ArrayAdapter(requireContext(), R.layout.spinner_list, list_bien)
        adapter.setDropDownViewResource(R.layout.spinner_list)
        spinner.adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
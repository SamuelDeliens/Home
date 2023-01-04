package fr.mastersid.deliens.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.mastersid.deliens.home.R
import fr.mastersid.deliens.home.databinding.FragmentEstimationBinding
import fr.mastersid.deliens.home.databinding.FragmentListEstimationBinding

class EstimationFragment : Fragment() {

    private lateinit var binding: FragmentEstimationBinding

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
    }
}
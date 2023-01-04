package fr.mastersid.deliens.home.ui.listEstimationPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.mastersid.deliens.home.databinding.FragmentListEstimationBinding

class ListEstimationFragment : Fragment() {

    private lateinit var binding: FragmentListEstimationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListEstimationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val intent = Intent(context, ListEstimation::class.java)
        //startActivity(intent)
    }
}
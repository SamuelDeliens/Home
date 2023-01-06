package fr.mastersid.deliens.home.ui.listEstimationPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.deliens.home.databinding.FragmentListEstimationBinding
import fr.mastersid.deliens.home.viewModel.ListEstimationViewModel

@AndroidEntryPoint
class ListEstimationFragment : Fragment() {

    private var binding: FragmentListEstimationBinding? = null
    private val listEstimationViewModel: ListEstimationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentList = FragmentListEstimationBinding.inflate(inflater, container, false)
        binding = fragmentList
        return fragmentList.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = listEstimationViewModel
            listEstimationFragment = this@ListEstimationFragment
        }

        binding!!.goToNewEstimation.setOnClickListener() {
            val action = ListEstimationFragmentDirections.actionListEstimationToEstimationFragment()
            findNavController().navigate(action)
        }


        listEstimationViewModel.estimationList.observe(viewLifecycleOwner) { value ->
            if (value.isEmpty()) {
                binding!!.backImage.visibility = View.VISIBLE
                binding!!.fragmentCarousel2.visibility = View.GONE
                return@observe
            } else {
                binding!!.backImage.visibility = View.GONE
                binding!!.fragmentCarousel2.visibility = View.VISIBLE
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
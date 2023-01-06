package fr.mastersid.deliens.home.ui.listEstimationPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.deliens.home.R
import fr.mastersid.deliens.home.databinding.FragmentListEstimationBinding
import fr.mastersid.deliens.home.viewModel.ListEstimationViewModel
import android.view.animation.AnimationUtils

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

        binding!!.initEstimation.setOnClickListener {
            listEstimationViewModel.initEstimationList()
            val animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
            binding!!.initEstimation.startAnimation(animation)
            binding!!.initEstimation.visibility = View.INVISIBLE
        }

        binding!!.goToNewEstimation.setOnClickListener {
            val action = ListEstimationFragmentDirections.actionListEstimationToEstimationFragment()
            findNavController().navigate(action)
        }


        listEstimationViewModel.estimationList.observe(viewLifecycleOwner) { value ->
            if (value.isEmpty()) {
                val animation1 = AnimationUtils.loadAnimation(context, R.anim.fade_out)
                binding!!.backImage.startAnimation(animation1)
                val animation2 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
                binding!!.backImage.startAnimation(animation2)

                binding!!.backImage.visibility = View.VISIBLE
                binding!!.fragmentCarousel2.visibility = View.GONE
                return@observe
            } else {
                val animation1 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
                binding!!.backImage.startAnimation(animation1)
                val animation2 = AnimationUtils.loadAnimation(context, R.anim.fade_out)
                binding!!.backImage.startAnimation(animation2)

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
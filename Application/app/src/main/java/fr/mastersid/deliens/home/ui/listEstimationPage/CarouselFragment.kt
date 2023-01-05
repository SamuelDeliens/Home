package fr.mastersid.deliens.home.ui.listEstimationPage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Carousel
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.deliens.home.R
import fr.mastersid.deliens.home.databinding.FragmentCarousselBinding

@AndroidEntryPoint
class CarouselFragment: Fragment() {

    private lateinit var binding: FragmentCarousselBinding

    private var selectedIndex: Int = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCarousselBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val motionLayout = binding.motionContainer

        val v1 = motionLayout.get(0) //BLUE
        val v2 = motionLayout.get(1) //ORANGE
        val v3 = motionLayout.get(2) //RED
        val v4 = motionLayout.get(3) //GREEN
        val v5 = motionLayout.get(4) //PURPLE


        v1.setOnClickListener {
            if (selectedIndex == 0) return@setOnClickListener

            if (selectedIndex == 1) {
                motionLayout.setTransition(R.id.s2, R.id.s1) //orange to blue transition
            } else if (selectedIndex == 4) {
                motionLayout.setTransition(R.id.s5, R.id.s1) //purple to blue transition
            }

            motionLayout.transitionToEnd()
            selectedIndex = 0
        }

        v2.setOnClickListener {
            if (selectedIndex == 1) return@setOnClickListener

            if (selectedIndex == 2) {
                motionLayout.setTransition(R.id.s3, R.id.s2)  //red to orange transition
            } else if (selectedIndex == 0){
                motionLayout.setTransition(R.id.s1, R.id.s2) //blue to orange transition
            }
            motionLayout.transitionToEnd()
            selectedIndex = 1
        }

        v3.setOnClickListener {
            if (selectedIndex == 2) return@setOnClickListener

            if (selectedIndex == 1) {
                motionLayout.setTransition(R.id.s2, R.id.s3) //orange to red transition
            } else if (selectedIndex == 3){
                motionLayout.setTransition(R.id.s4, R.id.s3) //green to red transition
            }

            motionLayout.transitionToEnd()
            selectedIndex = 2
        }

        v4.setOnClickListener {
            if (selectedIndex == 3) return@setOnClickListener

            if (selectedIndex == 2) {
                motionLayout.setTransition(R.id.s3, R.id.s4) //red to green transition
            } else if (selectedIndex == 4){
                motionLayout.setTransition(R.id.s5, R.id.s4) //purple to green transition
            }

            motionLayout.transitionToEnd()
            selectedIndex = 3
        }

        v5.setOnClickListener {
            if (selectedIndex == 4) return@setOnClickListener

            if (selectedIndex == 3) {
                motionLayout.setTransition(R.id.s4, R.id.s5) //green to purple transition
            } else if (selectedIndex == 0){
                motionLayout.setTransition(R.id.s1, R.id.s5) //blue to purple transition
            }

            motionLayout.transitionToEnd()
            selectedIndex = 4
        }
    }
}
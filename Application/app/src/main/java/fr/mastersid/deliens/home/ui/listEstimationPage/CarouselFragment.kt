package fr.mastersid.deliens.home.ui.listEstimationPage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.deliens.home.R
import fr.mastersid.deliens.home.data.EstimationResult
import fr.mastersid.deliens.home.databinding.FragmentCarousselBinding
import fr.mastersid.deliens.home.viewModel.ListEstimationViewModel

@AndroidEntryPoint
class CarouselFragment: Fragment() {

    private var binding: FragmentCarousselBinding? = null
    private val listEstimationViewModel: ListEstimationViewModel by activityViewModels()

    private var selectedIndex: Int = 0
    private var currentsId: MutableList<Int> = mutableListOf(3, 4, 0, 1, 2)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val carouselFragment = FragmentCarousselBinding.inflate(inflater, container, false)
        binding = carouselFragment
        return carouselFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = listEstimationViewModel
            carouselFragment = this@CarouselFragment
        }

        
        val motionLayout = binding!!.motionContainer

        val v1 = motionLayout.get(0) //BLUE
        val v2 = motionLayout.get(1) //ORANGE
        val v3 = motionLayout.get(2) //RED
        val v4 = motionLayout.get(3) //GREEN
        val v5 = motionLayout.get(4) //PURPLE


        v1.setOnClickListener {
            if (selectedIndex == 0) return@setOnClickListener
            setTo1(motionLayout)
        }
        v2.setOnClickListener {
            if (selectedIndex == 1) return@setOnClickListener
            setTo2(motionLayout)
        }
        v3.setOnClickListener {
            if (selectedIndex == 2) return@setOnClickListener
            setTo3(motionLayout)
        }
        v4.setOnClickListener {
            if (selectedIndex == 3) return@setOnClickListener
            setTo4(motionLayout)
        }
        v5.setOnClickListener {
            if (selectedIndex == 4) return@setOnClickListener
            setTo5(motionLayout)
        }


        listEstimationViewModel.estimationList.observe(viewLifecycleOwner) { value ->
            if (value.isEmpty()) {
                return@observe
            } else {
                Log.d("debug", "updateList: ${value.size}")
                initView(value)
            }
        }
    }


    fun setTo1(motionLayout: MotionLayout) {
        if (selectedIndex == 4) {
            nextEstimation()
            motionLayout.setTransition(R.id.s5, R.id.s1) //purple to blue transition
        } else if (selectedIndex == 1) {
            previousEstimation()
            motionLayout.setTransition(R.id.s2, R.id.s1) //orange to blue transition
        }

        motionLayout.transitionToEnd()
        updateEstimation(currentsId.first(), binding!!.imageV4, binding!!.typeV4, binding!!.priceV4, binding!!.surfaceV4, binding!!.terrainV4, binding!!.roomsV4, binding!!.regionV4)
        updateEstimation(currentsId.last(), binding!!.imageV3, binding!!.typeV3, binding!!.priceV3, binding!!.surfaceV3, binding!!.terrainV3, binding!!.roomsV3, binding!!.regionV3)

        selectedIndex = 0
    }

    fun setTo2(motionLayout: MotionLayout) {
        if (selectedIndex == 0){
            nextEstimation()
            motionLayout.setTransition(R.id.s1, R.id.s2) //blue to orange transition
        } else if (selectedIndex == 2) {
            previousEstimation()
            motionLayout.setTransition(R.id.s3, R.id.s2) //red to orange transition
        }

        motionLayout.transitionToEnd()
        updateEstimation(currentsId.first(), binding!!.imageV5, binding!!.typeV5, binding!!.priceV5, binding!!.surfaceV5, binding!!.terrainV5, binding!!.roomsV5, binding!!.regionV5)
        updateEstimation(currentsId.last(), binding!!.imageV4, binding!!.typeV4, binding!!.priceV4, binding!!.surfaceV4, binding!!.terrainV4, binding!!.roomsV4, binding!!.regionV4)

        selectedIndex = 1
    }

    fun setTo3(motionLayout: MotionLayout) {
        if (selectedIndex == 1) {
            nextEstimation()
            motionLayout.setTransition(R.id.s2, R.id.s3) //orange to red transition
        } else if (selectedIndex == 3){
            previousEstimation()
            motionLayout.setTransition(R.id.s4, R.id.s3) //green to red transition
        }

        motionLayout.transitionToEnd()
        updateEstimation(currentsId.first(), binding!!.imageV1, binding!!.typeV1, binding!!.priceV1, binding!!.surfaceV1, binding!!.terrainV1, binding!!.roomsV1, binding!!.regionV1)
        updateEstimation(currentsId.last(), binding!!.imageV5, binding!!.typeV5, binding!!.priceV5, binding!!.surfaceV5, binding!!.terrainV5, binding!!.roomsV5, binding!!.regionV5)

        selectedIndex = 2
    }

    fun setTo4(motionLayout: MotionLayout) {
        if (selectedIndex == 2) {
            nextEstimation()
            motionLayout.setTransition(R.id.s3, R.id.s4) //red to green transition
        } else if (selectedIndex == 4){
            previousEstimation()
            motionLayout.setTransition(R.id.s5, R.id.s4) //purple to green transition
        }

        motionLayout.transitionToEnd()
        updateEstimation(currentsId.first(), binding!!.imageV2, binding!!.typeV2, binding!!.priceV2, binding!!.surfaceV2, binding!!.terrainV2, binding!!.roomsV2, binding!!.regionV2)
        updateEstimation(currentsId.last(),binding!!.imageV1, binding!!.typeV1, binding!!.priceV1, binding!!.surfaceV1, binding!!.terrainV1, binding!!.roomsV1, binding!!.regionV1)

        selectedIndex = 3
    }

    fun setTo5(motionLayout: MotionLayout) {
        if (selectedIndex == 3) {
            nextEstimation()
            motionLayout.setTransition(R.id.s4, R.id.s5) //green to purple transition
        } else if (selectedIndex == 0){
            previousEstimation()
            motionLayout.setTransition(R.id.s1, R.id.s5) //blue to purple transition
        }

        motionLayout.transitionToEnd()
        updateEstimation(currentsId.first(), binding!!.imageV3, binding!!.typeV3, binding!!.priceV3, binding!!.surfaceV3, binding!!.terrainV3, binding!!.roomsV3, binding!!.regionV3)
        updateEstimation(currentsId.last(), binding!!.imageV2, binding!!.typeV2, binding!!.priceV2, binding!!.surfaceV2, binding!!.terrainV2, binding!!.roomsV2, binding!!.regionV2)
        selectedIndex = 4
    }


    fun initView(value: List<EstimationResult>) {
        val size = value.size
        //SIZE >= 1
        updateEstimation(0, binding!!.imageV1, binding!!.typeV1, binding!!.priceV1, binding!!.surfaceV1, binding!!.terrainV1, binding!!.roomsV1, binding!!.regionV1)
        if (size >= 2) updateEstimation(1, binding!!.imageV2, binding!!.typeV2, binding!!.priceV2, binding!!.surfaceV2, binding!!.terrainV2, binding!!.roomsV2, binding!!.regionV2)
        if (size >= 3) updateEstimation(2, binding!!.imageV3, binding!!.typeV3, binding!!.priceV3, binding!!.surfaceV3, binding!!.terrainV3, binding!!.roomsV3, binding!!.regionV3)
        if (size >= 4) updateEstimation(3, binding!!.imageV4, binding!!.typeV4, binding!!.priceV4, binding!!.surfaceV4, binding!!.terrainV4, binding!!.roomsV4, binding!!.regionV4)
        if (size >= 5) updateEstimation(4, binding!!.imageV5, binding!!.typeV5, binding!!.priceV5, binding!!.surfaceV5, binding!!.terrainV5, binding!!.roomsV5, binding!!.regionV5)
    }


    fun nextEstimation() {
        currentsId.removeAt(0)
        if (currentsId[3] < listEstimationViewModel.estimationList.value!!.size-1) {
            currentsId.add(4, currentsId[3]+1)
        } else {
            currentsId.add(4, 0)
        }
        val first = currentsId.first()
        val last = currentsId.last()
    }

    fun previousEstimation() {
        currentsId.removeAt(4)
        if (currentsId[0] > 0) {
            currentsId.add(0,currentsId[0]-1)
        } else {
            currentsId.add(0,listEstimationViewModel.estimationList.value!!.size-1)
        }
        val first = currentsId.first()
        val last = currentsId.last()
    }

    fun updateEstimation(
        id: Int,
        image: ImageView,
        property: TextView,
        price: TextView,
        surface: TextView,
        terrain: TextView,
        pieces: TextView,
        region: TextView
    ) {
        val estimation = listEstimationViewModel.estimationList.value?.get(id)
        if (estimation != null) {
            if (estimation.propertyType == "Maison") {
                image.setImageResource(R.drawable.maison)
            } else {
                image.setImageResource(R.drawable.appart)
            }
            property.text = estimation.propertyType
            price.text = estimation.result.toString()
            surface.text = estimation.surface.toString()
            terrain.text = estimation.terrain.toString()
            pieces.text = estimation.pieces.toString()
            region.text = estimation.region
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
package com.hubbox.ui.home.createShipment.pickupLocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hubbox.R
import com.hubbox.model.Address
import com.hubbox.ui.home.createShipment.ShipmentMainVM
import kotlinx.android.synthetic.main.fragment_pickup_address.*


class PickupLocationFragment : Fragment(), MotionLayout.TransitionListener {
    private var pickupAddress: Address? = null
    private lateinit var doneLottie: View
    private lateinit var selectedCard: CardView
    private var rollBack: Boolean = false
    private lateinit var vm: ShipmentMainVM
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_pickup_address, null)

        vm =
            ViewModelProvider(requireParentFragment().requireParentFragment()).get(ShipmentMainVM::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupUI()

    }

    private fun setupUI() {

        confirm.setOnClickListener {
            pickupAddress?.details = buildingDetails.editText?.text.toString()
            vm.pickupAddress.postValue(pickupAddress)
            Navigation.findNavController(it)
                .navigate(R.id.action_pickupLocationFragment_to_pickupContactFragment)
        }

        motion.addTransitionListener(this)
        office.setOnClickListener {
            it.bringToFront()
            if (rollBack) {
                workContent.visibility = VISIBLE
                removeDoneLottie()
                motion.transitionToState(R.id.start)
            }
            else
                motion.transitionToState(R.id.OfficeEnd)
        }
        home.setOnClickListener {
            it.bringToFront()
            if (rollBack){
                homeContent.visibility = VISIBLE
                removeDoneLottie()
                motion.transitionToState(R.id.start)
            }else
                motion.transitionToState(R.id.homeEnd)
        }
        more.setOnClickListener {
            it.bringToFront()
            if (rollBack){
                moreContent.visibility = VISIBLE
                removeDoneLottie()
                motion.transitionToState(R.id.start)
            }else
                motion.transitionToState(R.id.otherEnd)
        }
    }

    private fun setupObservers() {
        vm.pickupAddress.observe(viewLifecycleOwner, {
            address.text = it.address
            this.pickupAddress = it
        })
    }

    override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {

    }

    override fun onTransitionChange(
        motionLayout: MotionLayout?,
        startId: Int,
        endId: Int,
        progress: Float
    ) {
    }

    override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
        if (currentId == R.id.OfficeEnd) {
            workContent.visibility = GONE
            addDoneLottie(office)
        }
        if (currentId == R.id.homeEnd) {
            homeContent.visibility = GONE
            addDoneLottie(home)
        }
        if (currentId == R.id.otherEnd) {
            moreContent.visibility = GONE
            addDoneLottie(more)
        }
        rollBack = currentId != R.id.start
    }

    override fun onTransitionTrigger(
        motionLayout: MotionLayout?,
        triggerId: Int,
        positive: Boolean,
        progress: Float
    ) {
    }

    fun addDoneLottie(card: CardView) {
        this.selectedCard = card
        val lottieView: View = LayoutInflater.from(context).inflate(R.layout.item_done_lottie, null)
        this.doneLottie = lottieView
        card.addView(lottieView)
    }

    fun removeDoneLottie() {
        this.selectedCard.removeView(doneLottie)
    }


}
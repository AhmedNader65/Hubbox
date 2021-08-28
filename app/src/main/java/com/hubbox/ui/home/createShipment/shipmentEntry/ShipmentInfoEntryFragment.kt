package com.hubbox.ui.home.createShipment.shipmentEntry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.card.MaterialCardView
import com.hubbox.R
import com.hubbox.adapters.DayAdapter
import com.hubbox.adapters.SpinnerAdapter
import com.hubbox.adapters.VehicleAdapter
import com.hubbox.databinding.*
import com.hubbox.model.Day
import com.hubbox.model.Spot
import com.hubbox.model.Vehicle
import com.hubbox.ui.home.createShipment.CreateShipmentFragment


class ShipmentInfoEntryFragment : Fragment() {
    lateinit var binding: FragmentShipmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shipment_info, container, false)
        val mRootView = binding.getRoot()
        binding.serviceTypeList.adapter = VehicleAdapter(createVehicles())
        binding.pickupDaysList.adapter = DayAdapter(createDays())
        binding.dropOffDaysList.adapter = DayAdapter(createDays())
        binding.increase.setOnClickListener {
            binding.counter.text = (binding.counter.text.toString().toInt() + 1).toString()
            binding.countTop.text = "${binding.counter.text} Piece"
        }
        binding.decrease.setOnClickListener {
            if (binding.counter.text.toString().toInt() > 1)
                binding.counter.text = (binding.counter.text.toString().toInt() - 1).toString()
            binding.countTop.text = "${binding.counter.text} Piece"
        }

        binding.papers.setOnClickListener {
            checkPaperLayout()
        }
        binding.parcel.setOnClickListener {
            checkParcelLayout()
        }
        binding.confirm.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.shipmentConfirmationFragment)
        }
        binding.asap.setOnClickListener { checkASAP() }
        binding.nineToTwelve.setOnClickListener { checkNineAm() }
        binding.oneToThree.setOnClickListener { checkOnePM() }
        binding.fourToSix.setOnClickListener { checkFour() }
        binding.express.setOnClickListener {
            hideCustomizingLayout()
            (it as MaterialCardView).setCardBackgroundColor(resources.getColor(R.color.purple))
            binding.expressText1.setTextColor(resources.getColor(R.color.white))
            binding.expressText2.setTextColor(resources.getColor(R.color.white))
            binding.standard.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.standardText1.setTextColor(resources.getColor(R.color.black))
            binding.standardText2.setTextColor(resources.getColor(R.color.black))
            binding.customize.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.customizeText1.setTextColor(resources.getColor(R.color.black))
            binding.customizeText2.setTextColor(resources.getColor(R.color.black))
        }
        binding.standard.setOnClickListener {
            hideCustomizingLayout()
            (it as MaterialCardView).setCardBackgroundColor(resources.getColor(R.color.purple))
            binding.expressText1.setTextColor(resources.getColor(R.color.black))
            binding.expressText2.setTextColor(resources.getColor(R.color.black))
            binding.express.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.standardText1.setTextColor(resources.getColor(R.color.white))
            binding.standardText2.setTextColor(resources.getColor(R.color.white))
            binding.customize.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.customizeText1.setTextColor(resources.getColor(R.color.black))
            binding.customizeText2.setTextColor(resources.getColor(R.color.black))
        }
        binding.customize.setOnClickListener {
            showCustomizingLayout()
            (it as MaterialCardView).setCardBackgroundColor(resources.getColor(R.color.purple))
            binding.expressText1.setTextColor(resources.getColor(R.color.black))
            binding.expressText2.setTextColor(resources.getColor(R.color.black))
            binding.standard.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.standardText1.setTextColor(resources.getColor(R.color.black))
            binding.standardText2.setTextColor(resources.getColor(R.color.black))
            binding.express.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.customizeText1.setTextColor(resources.getColor(R.color.white))
            binding.customizeText2.setTextColor(resources.getColor(R.color.white))
        }
        binding.schedulerGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked){
                if (checkedId == R.id.senderScheduler){
                    showSenderSchudeling()
                    binding.senderScheduler.setBackgroundColor(resources.getColor(R.color.purple))
                    binding.senderScheduler.setTextColor(resources.getColor(R.color.white))
                    binding.receiverScheduler.setTextColor(resources.getColor(R.color.black))
                    binding.receiverScheduler.setBackgroundColor(resources.getColor(R.color.white))
                }else{
                    binding.receiverScheduler.setBackgroundColor(resources.getColor(R.color.purple))
                    binding.receiverScheduler.setTextColor(resources.getColor(R.color.white))
                    binding.senderScheduler.setTextColor(resources.getColor(R.color.black))
                    binding.senderScheduler.setBackgroundColor(resources.getColor(R.color.white))
                    hideSenderSchudeling()
                }
            }
        }
        return mRootView
    }

    private fun showCustomizingLayout() {

        binding.dropOffDaysList.visibility = View.VISIBLE
        binding.dropOffTimeLB.visibility = View.VISIBLE
        binding.optional1.visibility = View.VISIBLE
        binding.fromTime.visibility = View.VISIBLE
        binding.toTime.visibility = View.VISIBLE
    }
    private fun hideCustomizingLayout() {

        binding.dropOffDaysList.visibility = View.GONE
        binding.dropOffTimeLB.visibility = View.GONE
        binding.optional1.visibility = View.GONE
        binding.fromTime.visibility = View.GONE
        binding.toTime.visibility = View.GONE
    }

    private fun hideSenderSchudeling() {
        binding.senderSchedulerLayout.visibility = View.GONE
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.root)
        constraintSet.connect(R.id.sendingWhatLB,ConstraintSet.TOP,R.id.schedulerGroup,ConstraintSet.BOTTOM,16)
        constraintSet.applyTo(binding.root)
    }

    private fun showSenderSchudeling() {
        binding.senderSchedulerLayout.visibility = View.VISIBLE
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.root)
        constraintSet.connect(R.id.sendingWhatLB,ConstraintSet.TOP,R.id.senderSchedulerLayout,ConstraintSet.BOTTOM,16)
        constraintSet.applyTo(binding.root)
    }

    private fun createDays(): List<Day> {

        val days = ArrayList<Day>()
        days.add(
            Day(
                name = "Sun",
                day = 31
            )
        )
        days.add(
            Day(
                name = "Mon",
                day = 1
            )
        )
        days.add(
            Day(
                name = "Tue",
                day = 2
            )
        )
        days.add(
            Day(
                name = "Wed",
                day = 3
            )
        )
        days.add(
            Day(
                name = "Thu",
                day = 4
            )
        )
        days.add(
            Day(
                name = "Fri",
                day = 5
            )
        )
        days.add(
            Day(
                name = "Sat",
                day = 6
            )
        )
        return days
    }

    private fun checkASAP() {

        binding.asap.setCardBackgroundColor(resources.getColor(R.color.purple))
        binding.asap.setStrokeColor(resources.getColor(R.color.purple))
        (binding.asap.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.white))
        binding.oneToThree.setCardBackgroundColor(resources.getColor(R.color.white))
        (binding.oneToThree.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.black))
        binding.oneToThree.setStrokeColor(resources.getColor(R.color.stroke_grey))
        binding.nineToTwelve.setCardBackgroundColor(resources.getColor(R.color.white))
        (binding.nineToTwelve.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.black))
        binding.nineToTwelve.setStrokeColor(resources.getColor(R.color.stroke_grey))
        binding.fourToSix.setCardBackgroundColor(resources.getColor(R.color.white))
        (binding.fourToSix.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.black))
        binding.fourToSix.setStrokeColor(resources.getColor(R.color.stroke_grey))
    }

    private fun checkFour() {

        binding.fourToSix.setCardBackgroundColor(resources.getColor(R.color.purple))
        binding.fourToSix.setStrokeColor(resources.getColor(R.color.purple))
        (binding.fourToSix.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.white))
        binding.oneToThree.setCardBackgroundColor(resources.getColor(R.color.white))
        (binding.oneToThree.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.black))
        binding.oneToThree.setStrokeColor(resources.getColor(R.color.stroke_grey))
        binding.nineToTwelve.setCardBackgroundColor(resources.getColor(R.color.white))
        (binding.nineToTwelve.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.black))
        binding.nineToTwelve.setStrokeColor(resources.getColor(R.color.stroke_grey))
        binding.asap.setCardBackgroundColor(resources.getColor(R.color.white))
        (binding.asap.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.black))
        binding.asap.setStrokeColor(resources.getColor(R.color.stroke_grey))
    }

    private fun checkNineAm() {

        binding.nineToTwelve.setCardBackgroundColor(resources.getColor(R.color.purple))
        binding.nineToTwelve.setStrokeColor(resources.getColor(R.color.purple))
        (binding.nineToTwelve.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.white))
        binding.oneToThree.setCardBackgroundColor(resources.getColor(R.color.white))
        (binding.oneToThree.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.black))
        binding.oneToThree.setStrokeColor(resources.getColor(R.color.stroke_grey))
        binding.asap.setCardBackgroundColor(resources.getColor(R.color.white))
        (binding.asap.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.black))
        binding.asap.setStrokeColor(resources.getColor(R.color.stroke_grey))
        binding.fourToSix.setCardBackgroundColor(resources.getColor(R.color.white))
        (binding.fourToSix.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.black))
        binding.fourToSix.setStrokeColor(resources.getColor(R.color.stroke_grey))
    }

    private fun checkOnePM() {

        binding.oneToThree.setCardBackgroundColor(resources.getColor(R.color.purple))
        binding.oneToThree.setStrokeColor(resources.getColor(R.color.purple))
        (binding.oneToThree.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.white))
        binding.asap.setCardBackgroundColor(resources.getColor(R.color.white))
        (binding.asap.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.black))
        binding.asap.setStrokeColor(resources.getColor(R.color.stroke_grey))
        binding.nineToTwelve.setCardBackgroundColor(resources.getColor(R.color.white))
        (binding.nineToTwelve.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.black))
        binding.nineToTwelve.setStrokeColor(resources.getColor(R.color.stroke_grey))
        binding.fourToSix.setCardBackgroundColor(resources.getColor(R.color.white))
        (binding.fourToSix.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.black))
        binding.fourToSix.setStrokeColor(resources.getColor(R.color.stroke_grey))
    }

    private fun checkPaperLayout() {
        binding.papers.setCardBackgroundColor(resources.getColor(R.color.selected_green))
        binding.parcel.setCardBackgroundColor(resources.getColor(R.color.lavandar_200))
    }

    private fun checkParcelLayout() {
        binding.parcel.setCardBackgroundColor(resources.getColor(R.color.selected_green))
        binding.papers.setCardBackgroundColor(resources.getColor(R.color.lavandar_200))
    }

    private fun createVehicles(): List<Vehicle> {

        val vehicles = ArrayList<Vehicle>()
        vehicles.add(
            Vehicle(
                name = "Bike",
                size = "Small",
                upto = 5,
                image = R.drawable.ic_bike
            )
        )
        vehicles.add(
            Vehicle(
                name = "Car",
                size = "Medium",
                upto = 15,
                image = R.drawable.ic_car
            )
        )
        vehicles.add(
            Vehicle(
                name = "Van",
                size = "Large",
                upto = 25,
                image = R.drawable.ic_van
            )
        )
        vehicles.add(
            Vehicle(
                name = "Truck",
                size = "Bulk",
                upto = 200,
                image = R.drawable.ic_truck
            )
        )
        return vehicles
    }


}
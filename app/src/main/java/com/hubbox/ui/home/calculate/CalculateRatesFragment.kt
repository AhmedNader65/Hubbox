package com.hubbox.ui.home.calculate

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.card.MaterialCardView
import com.hubbox.R
import com.hubbox.adapters.DayAdapter
import com.hubbox.model.City
import com.hubbox.model.Country
import com.hubbox.model.Day
import kotlinx.android.synthetic.main.fragment_calculate_rates.*
import kotlinx.android.synthetic.main.fragment_calculate_rates.Customizelin
import kotlinx.android.synthetic.main.fragment_calculate_rates.Expresslin
import kotlinx.android.synthetic.main.fragment_calculate_rates.Standardlin
import kotlinx.android.synthetic.main.fragment_calculate_rates.countryfrom
import kotlinx.android.synthetic.main.fragment_calculate_rates.dropOffDaysList
import kotlinx.android.synthetic.main.fragment_calculate_rates.dropOffTimeLB
import kotlinx.android.synthetic.main.fragment_calculate_rates.fromTime
import kotlinx.android.synthetic.main.fragment_calculate_rates.optional1
import kotlinx.android.synthetic.main.fragment_calculate_rates.recovery
import kotlinx.android.synthetic.main.fragment_calculate_rates.toTime
import kotlinx.android.synthetic.main.fragment_rate.*

class CalculateRatesFragment : Fragment(), CalculateBottomSheet.OnCalculateClicked,
    CountriesBS.OnCountrySelected, CitiesBS.OnCitySelected {

    lateinit var calculateBottomSheet: CalculateBottomSheet
    lateinit var countriesBS: CountriesBS
    lateinit var citiesBS: CitiesBS
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculate_rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        document.setOnClickListener({
            clearCons()
            document.background = resources.getDrawable(R.drawable.bg_round_green_light_stroked)
        })
        parcell.setOnClickListener({
            clearCons()
            parcell.background = resources.getDrawable(R.drawable.bg_round_purpl_light_stroked)
        })
        recovery.setOnClickListener({
            clearCons()
            recovery.background = resources.getDrawable(R.drawable.bg_round_pink_light_stroked)
        })
        Moving.setOnClickListener({
            clearCons()
            Moving.background = resources.getDrawable(R.drawable.bg_round_orange_light_stroked)

        })
        dropOffDaysList.adapter = DayAdapter(createDays())

        Expresslin.setOnClickListener({
            deliveryType(Expresslin, exptitle, expdesc)
            hideCustomizingLayout()
        })

        Standardlin.setOnClickListener({
            deliveryType(Standardlin, standardtxt, standarddesc)
            hideCustomizingLayout()
        })

        Customizelin.setOnClickListener({
            deliveryType(Customizelin, customizetxt, customizedesc)
            showCustomizingLayout()
        })

        CalculateRates.setOnClickListener({
            calculateBottomSheet = CalculateBottomSheet(this)
            calculateBottomSheet.show(parentFragmentManager, "TAG")

        })
        countryfromLin.setOnClickListener({
            countriesBS = CountriesBS(createCountries(), this, 1)
            countriesBS.show(childFragmentManager, "TAGGG")
        })
        countrytolin.setOnClickListener({
            countriesBS = CountriesBS(createCountries(), this, 2)
            countriesBS.show(childFragmentManager, "TAGGG")
        })

        cityfromlin.setOnClickListener({
            citiesBS= CitiesBS(createCities(),this,1)
            citiesBS.show(childFragmentManager,"TAG")
        })
        citytolin.setOnClickListener({
            citiesBS= CitiesBS(createCities(),this,2)
            citiesBS.show(childFragmentManager,"TAG")
        })

        toTime.setOnClickListener({
            dropOffButtons(toTime)
        })
        fromTime.setOnClickListener({
            dropOffButtons(fromTime)
        })

    }


    fun deliveryType(linearLayout: LinearLayout, header: TextView, desc: TextView) {
        Expresslin.background = resources.getDrawable(R.drawable.bg_white_with_stroke)
        Standardlin.background = resources.getDrawable(R.drawable.bg_white_with_stroke)
        Customizelin.background = resources.getDrawable(R.drawable.bg_white_with_stroke)
        expdesc.setTextColor(resources.getColor(R.color.delivery_black))
        exptitle.setTextColor(resources.getColor(R.color.delivery_black))
        standarddesc.setTextColor(resources.getColor(R.color.delivery_black))
        standardtxt.setTextColor(resources.getColor(R.color.delivery_black))
        customizetxt.setTextColor(resources.getColor(R.color.delivery_black))
        customizedesc.setTextColor(resources.getColor(R.color.delivery_black))


        linearLayout.background = resources.getDrawable(R.drawable.bg_round_purple_small_rad)
        header.setTextColor(resources.getColor(R.color.white))
        desc.setTextColor(resources.getColor(R.color.white))
    }


    private fun showCustomizingLayout() {

        dropOffDaysList.visibility = View.VISIBLE
        dropOffTimeLB.visibility = View.VISIBLE
        optional1.visibility = View.VISIBLE
        fromTime.visibility = View.VISIBLE
        toTime.visibility = View.VISIBLE
    }

    private fun clearCons() {
        document.background = resources.getDrawable(R.drawable.bg_round_green_light)
        parcell.background = resources.getDrawable(R.drawable.bg_round_purpl_light)
        recovery.background = resources.getDrawable(R.drawable.bg_round_pink_light)
        Moving.background = resources.getDrawable(R.drawable.bg_round_orange_light)
    }

    private fun hideCustomizingLayout() {

        dropOffDaysList.visibility = View.GONE
        dropOffTimeLB.visibility = View.GONE
        optional1.visibility = View.GONE
        fromTime.visibility = View.GONE
        toTime.visibility = View.GONE
    }

    private fun dropOffButtons(button: Button) {

        fromTime.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.white)))
        toTime.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.white)))
        button.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.purplelight)))
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

    private fun createCountries(): List<Country> {

        val days = ArrayList<Country>()
        days.add(
            Country(
                name = "Italy",
                flag = ""
            )
        )

        days.add(
            Country(
                name = "UAE",
                flag = ""
            )
        )

        days.add(
            Country(
                name = "Egypt",
                flag = ""
            )
        )

        days.add(
            Country(
                name = "Saudi Arabia",
                flag = ""
            )
        )

        return days
    }
    private fun createCities(): List<City> {

        val days = ArrayList<City>()
        days.add(
            City(
                name = "Dubai",
            )
        )


        days.add(
            City(
                name = "Cairo",
            )
        )

        days.add(
            City(
                name = "Paris",
            )
        )

        days.add(
            City(
                name = "Rome",
            )
        )



        return days
    }

    override fun onCalculateClickde() {
        calculateBottomSheet.dismiss()
        Navigation.findNavController(Expresslin).navigate(R.id.nav_calculate_rate)

    }

    override fun onCountrySelected(country: Country, type: Int) {
        if (type == 1) {
            countryfrom.setText(country.name)
            countryfrom.setTextColor(resources.getColor(R.color.delivery_black))
        } else if (type == 2) {
            couuntryto.setText(country.name)
            couuntryto.setTextColor(resources.getColor(R.color.delivery_black))
        }
    }



    override fun onCitySelected(city: City, type: Int) {

        if (type == 1) {
            cityfromtv.setText(city.name)
            cityfromtv.setTextColor(resources.getColor(R.color.delivery_black))
        } else if (type == 2) {
            citytotv.setText(city.name)
            citytotv.setTextColor(resources.getColor(R.color.delivery_black))
        }
    }


}
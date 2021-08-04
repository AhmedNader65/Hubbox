package com.hubbox.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hubbox.databinding.FragmentOnBoardingBinding
import com.hubbox.utils.OnSwipeTouchListener

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ViewSwitcher
import androidx.navigation.Navigation
import com.hubbox.R


class OnBoardingFragment : Fragment() {

    lateinit var binding: FragmentOnBoardingBinding
    var index: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_on_boarding, container, false)
        val mRootView = binding.getRoot()
        binding.onBoardingImg.setFactory {
            val imgView = ImageView(requireContext())
            imgView.scaleType = ImageView.ScaleType.FIT_CENTER
            imgView.setPadding(8, 8, 8, 8)
            imgView
        }

        binding.onBoardingImg.setImageResource(R.drawable.img_onboarding1)
        mRootView.setOnTouchListener(object : OnSwipeTouchListener(requireActivity()) {
            override fun onSwipeTop() {
            }

            override fun onSwipeRight() {
                showPrevious()
            }

            override fun onSwipeLeft() {
                showNext()
            }

            override fun onSwipeBottom() {
            }
        })
        binding.nextBtn.setOnClickListener {
            showNext()
        }
        return mRootView
    }

    private fun showNext() {

        val inn: Animation =
            AnimationUtils.loadAnimation(requireContext(), android.R.anim.slide_in_left)
        val out: Animation =
            AnimationUtils.loadAnimation(requireContext(), android.R.anim.slide_out_right)
        binding.onBoardingImg.inAnimation = inn
        binding.onBoardingImg.outAnimation = out

        when (index) {
            0 -> {
                index++
                binding.onBoardingImg.setImageResource(R.drawable.img_onboarding2)
                binding.nextBtn.setImageResource(R.drawable.boarding_btn_2)
                binding.indicator.setImageResource(R.drawable.onboarding_indicator_2)
                binding.onBoardingTitle.text = resources.getString(R.string.on_boarding2)
            }
            1 -> {
                index++
                binding.onBoardingImg.setImageResource(R.drawable.img_onboarding3)
                binding.nextBtn.setImageResource(R.drawable.boarding_btn_3)
                binding.onBoardingTitle.text = resources.getString(R.string.on_boarding3)
                binding.indicator.setImageResource(R.drawable.onboarding_indicator_3)

            }
            2 -> {
                index=0
                Navigation.findNavController(binding.onBoardingImg)
                    .navigate(R.id.action_onBoardingFragment_to_loginFragment)

            }
        }
    }

    private fun showPrevious() {

        val inn: Animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out_left)
        val out: Animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_right)
        binding.onBoardingImg.inAnimation = out
        binding.onBoardingImg.outAnimation = inn

        when (index) {
            1 -> {
                index--
                binding.onBoardingImg.setImageResource(R.drawable.img_onboarding1)
                binding.nextBtn.setImageResource(R.drawable.boarding_btn_1)
                binding.indicator.setImageResource(R.drawable.onboarding_indicator_1)
                binding.onBoardingTitle.text = resources.getString(R.string.on_boarding1)
            }
            2 -> {
                index--
                binding.onBoardingImg.setImageResource(R.drawable.img_onboarding2)
                binding.nextBtn.setImageResource(R.drawable.boarding_btn_2)
                binding.onBoardingTitle.text = resources.getString(R.string.on_boarding2)
                binding.indicator.setImageResource(R.drawable.onboarding_indicator_2)

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OnBoardingFragment()
    }
}
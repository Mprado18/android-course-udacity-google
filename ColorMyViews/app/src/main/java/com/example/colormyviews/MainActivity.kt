package com.example.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.colormyviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setListeners()
    }

    private fun setListeners() {
        val clickableViews: List<View> = listOf(
            binding.boxOneText,
            binding.boxTwoText,
            binding.boxThreeText,
            binding.boxFourText,
            binding.boxFiveText,
            binding.redButton,
            binding.yellowButton,
            binding.greenButton
        )

        for (item in clickableViews) {
            item.setOnClickListener { makeColored(it) }
        }
    }

    private fun makeColored(view: View) {
        when (view.id) {
            R.id.box_one_text -> view.setBackgroundResource(R.color.dark_gray)
            R.id.box_two_text -> view.setBackgroundResource(R.color.gray)

            R.id.box_three_text -> view.setBackgroundResource(R.color.green_light)
            R.id.box_four_text -> view.setBackgroundResource(R.color.green_dark)
            R.id.box_five_text -> view.setBackgroundResource(R.color.green_light)

            R.id.red_button -> binding.boxTwoText.setBackgroundResource(R.color.red)
            R.id.yellow_button -> binding.boxFiveText.setBackgroundResource(R.color.yellow)
            R.id.green_button -> binding.boxThreeText.setBackgroundResource(R.color.green)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}
package com.example.stylesthemes.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.stylesthemes.R
import com.example.stylesthemes.StyleThemesApplication
import com.example.stylesthemes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.onActivityCreateSetTheme(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpSpinner()
        setUpSwitch()
        binding.btnClear.setOnClickListener { cleanFields() }

    }

    private fun setUpSpinner(){

        ArrayAdapter.createFromResource(
            this,
            R.array.theme_array,
            R.layout.custom_spinner
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.custom_spinner_item)
            binding.spTheme.adapter = adapter
        }

        binding.spTheme.setSelection(StyleThemesApplication.currentPosition)
        StyleThemesApplication.currentPosition = binding.spTheme.selectedItemPosition
        binding.spTheme.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (StyleThemesApplication.currentPosition != position) {
                    ThemeManager.changeToTheme(this@MainActivity, position)
                }
                StyleThemesApplication.currentPosition = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }

    private fun setUpSwitch() {
        binding.swDarkMode.setOnCheckedChangeListener { _, isSelected ->
            if (isSelected) {
                enableDarkMode()
            }else {
                disableDarkMode()
            }
        }
    }

    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }

    private fun cleanFields(){
        binding.tvPassword.text?.clear()
        binding.tvUserName.text?.clear()
    }

}
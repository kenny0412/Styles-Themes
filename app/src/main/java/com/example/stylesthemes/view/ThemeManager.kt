package com.example.stylesthemes.view

import android.app.Activity
import android.content.Intent
import com.example.stylesthemes.R

object ThemeManager {
    private var sTheme = 0
    internal fun changeToTheme(activity: Activity, theme: Int) {
        sTheme = theme
        activity.finish()
        activity.startActivity(Intent(activity, activity.javaClass))
        activity.overridePendingTransition(
            androidx.appcompat.R.anim.abc_fade_in,
            androidx.appcompat.R.anim.abc_fade_out
        )
    }

    internal fun onActivityCreateSetTheme(activity: Activity) {
        when (sTheme) {
            Themes.THEME_LIGTH.ordinal -> activity.setTheme(R.style.Theme_LightTheme)
            Themes.THEME_DARK.ordinal -> activity.setTheme(R.style.Theme_DarkTheme)
            else -> activity.setTheme(R.style.Theme_LightTheme)
        }
    }
}
package com.siele.myresume

sealed class Screen(val route:String){
    object MainScreen:Screen("main_screen")
    object SettingsScreen:Screen("settings_screen")
    object WebViewScreen:Screen("web_view_screen")
}

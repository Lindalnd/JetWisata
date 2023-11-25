package com.lindahasanah.submissionjetpack.ui.navigation

sealed class Screen(val route:String){
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Detail : Screen("home/{wisataId}"){
        fun createRoute(wisataId: String) = "home/$wisataId"
    }
}

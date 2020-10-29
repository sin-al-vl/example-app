package com.example.app.navigation

import androidx.navigation.NavController
import com.example.app.R
import com.example.feature_counter.navigation.CounterNavigator

class CounterNavigatorImpl(
    private val navController: NavController
) : CounterNavigator {

    override fun openRandomizerScreen() {
        navController.navigate(R.id.action_counterFragment_to_randomizerFragment)
    }
}
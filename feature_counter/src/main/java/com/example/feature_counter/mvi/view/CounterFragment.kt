package com.example.feature_counter.mvi.view

import android.os.Bundle
import android.view.View
import com.google.android.gms.instantapps.InstantApps
import com.example.core.mvi.view.BaseFragment
import com.example.feature_counter.R
import com.example.feature_counter.data.AppType
import com.example.feature_counter.mvi.CounterIntent
import com.example.feature_counter.mvi.domain.CounterViewModel
import kotlinx.android.synthetic.main.fragment_counter.*

class CounterFragment : BaseFragment<CounterState, CounterViewModel>() {

    override val layoutResId = R.layout.fragment_counter

    override fun vmClass() = CounterViewModel::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        counter_button.setOnClickListener {
            viewModel.postIntent(CounterIntent.CounterButtonIntent)
        }
        open_randomizer_button.setOnClickListener {
            viewModel.postIntent(CounterIntent.OpenRandomizerIntent)
        }
        viewModel.postIntent(
            CounterIntent.AppTypeIntent(
                InstantApps.getPackageManagerCompat(requireContext()).isInstantApp
            )
        )
    }

    override fun render(state: CounterState) {
        some_data_text_view.text = state.counter.toString()
        app_type_text_view.text = getAppTypeLabel(state.appType)
    }

    private fun getAppTypeLabel(appType: AppType) =
        when (appType) {
            is AppType.Instant -> getString(R.string.instant_app_label)
            is AppType.Common -> getString(R.string.common_app_label)
        }
}
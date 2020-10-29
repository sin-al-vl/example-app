package com.example.feature_randomizer.mvi.view

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import com.example.core.mvi.view.BaseFragment
import com.example.feature_randomizer.R
import com.example.feature_randomizer.mvi.RandomizerIntent
import com.example.feature_randomizer.mvi.domain.RandomizerViewModel
import kotlinx.android.synthetic.main.fragment_randomizer.*

class RandomizerFragment : BaseFragment<RandomizerState, RandomizerViewModel>() {

    override val layoutResId = R.layout.fragment_randomizer

    override fun vmClass() = RandomizerViewModel::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        randomize_button.setOnClickListener {
            val interactorType =
                getSelectedInteractorType(interactor_radio_group.checkedRadioButtonId)
            viewModel.postIntent(RandomizerIntent.RandomizerButtonIntent(interactorType))
        }
    }

    private fun getSelectedInteractorType(
        @IdRes checkedRadioButtonId: Int
    ) = when (checkedRadioButtonId) {
        R.id.blocking_interactor_radio_button -> RandomizerIntent.BLOCKING_INTERACTOR
        R.id.single_interactor_radio_button -> RandomizerIntent.SINGLE_INTERACTOR
        R.id.publisher_interactor_radio_button -> RandomizerIntent.PUBLISHER_INTERACTOR
        else -> throw IllegalStateException("Unexpected interactor radio button: $checkedRadioButtonId")
    }

    override fun render(state: RandomizerState) {
        some_data_text_view.text = state.randomValue?.toString() ?: ""
        progress.visibility = if (state.progress) View.VISIBLE else View.GONE
        randomize_button.isEnabled = !state.progress
    }
}

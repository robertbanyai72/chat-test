package hu.robert.banyai.lmitest.presentation.model

import hu.robert.banyai.lmitest.presentation.screen.AdapterViewState

data class AdapterItemModel(
        val id: String = "",
        val message: String = "",
        val imageUrl: String = "",
        val adapterViewState: AdapterViewState
)
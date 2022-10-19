package com.fractaldev.composeexample

import com.fractaldev.composeexample.lambdatest.LambdaTestViewModel
import com.fractaldev.composeexample.lazylisttest.LazyListTestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LambdaTestViewModel()
    }
    viewModel {
        LazyListTestViewModel()
    }
}
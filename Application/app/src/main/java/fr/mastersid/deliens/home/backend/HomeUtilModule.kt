package fr.mastersid.deliens.home.backend

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by Samuel Deliens and Wassim Briane on 2021-01-18.
 *
 * Module for the HomeUtil class.
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeUtilModule {
    @Binds
    abstract fun bindHomeUtil(homeUtilImpl: HomeUtilImpl): HomeUtil
}
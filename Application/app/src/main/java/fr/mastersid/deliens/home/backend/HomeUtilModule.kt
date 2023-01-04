package fr.mastersid.deliens.home.backend

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeUtilModule {
    @Binds
    abstract fun bindHomeUtil(homeUtilImpl: HomeUtilImpl): HomeUtil
}
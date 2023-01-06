package fr.mastersid.deliens.home.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The [Application] class for the Home module.
 * This is used by Dagger Hilt to generate the required classes.
 */
@HiltAndroidApp
class HomeApp: Application()

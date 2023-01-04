package fr.mastersid.deliens.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.mastersid.deliens.home.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
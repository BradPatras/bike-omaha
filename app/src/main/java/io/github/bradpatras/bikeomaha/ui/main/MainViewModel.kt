package io.github.bradpatras.bikeomaha.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.github.bradpatras.bikeomaha.data.TrailsRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    private val repository = TrailsRepository()

    val trails = liveData(Dispatchers.IO) {
        val trailsResponse = repository.getTrails()
        emit(trailsResponse.trails)
    }
}

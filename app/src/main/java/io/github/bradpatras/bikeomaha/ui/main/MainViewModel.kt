package io.github.bradpatras.bikeomaha.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.github.bradpatras.bikeomaha.data.Trail
import io.github.bradpatras.bikeomaha.data.TrailsRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    private val repository = TrailsRepository()

    val trails: LiveData<List<Trail>> = liveData(Dispatchers.IO) {
        emit(repository.getTrails())
    }
}

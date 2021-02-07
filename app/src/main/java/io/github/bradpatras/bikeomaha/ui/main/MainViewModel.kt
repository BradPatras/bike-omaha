package io.github.bradpatras.bikeomaha.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.github.bradpatras.bikeomaha.data.Trail
import io.github.bradpatras.bikeomaha.data.TrailsRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    val trailsRepository = TrailsRepository()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    val trails: LiveData<List<Trail>> = liveData(Dispatchers.IO) {
        isLoading.postValue(true)
        emit(trailsRepository.getTrails())
        isLoading.postValue(false)
    }
}

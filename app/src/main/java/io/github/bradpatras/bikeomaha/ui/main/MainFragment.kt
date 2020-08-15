package io.github.bradpatras.bikeomaha.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.awaitMap
import io.github.bradpatras.bikeomaha.R
import io.github.bradpatras.bikeomaha.data.GeoJsonLayerFactory
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        map_view?.onCreate(savedInstanceState)

        lifecycle.coroutineScope.launchWhenCreated {
            setupMap()
        }
    }

    private suspend fun setupMap() {
        val googleMap = map_view?.awaitMap() ?: return

        val omaha = LatLng(41.259698, -96.022224)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(omaha, 9.5f))

        viewModel.trails.observe(viewLifecycleOwner, Observer { trails ->
            trails.map { it.geoJSON }.forEach {
                GeoJsonLayerFactory(googleMap).create(it).addLayerToMap()
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        map_view?.onResume()
    }

    override fun onDestroyView() {
        map_view?.onDestroy()
        super.onDestroyView()
    }

    override fun onPause() {
        super.onPause()
        map_view?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map_view?.onLowMemory()
    }

    override fun onStart() {
        super.onStart()
        map_view?.onStart()
    }
}

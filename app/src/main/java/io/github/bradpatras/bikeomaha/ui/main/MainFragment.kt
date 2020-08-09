package io.github.bradpatras.bikeomaha.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import io.github.bradpatras.bikeomaha.R
import io.github.bradpatras.bikeomaha.data.GeoJsonLayerFactory
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment), OnMapReadyCallback {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var map: GoogleMap? = null

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        map_view?.let {
            it.onCreate(savedInstanceState)
            it.getMapAsync(this)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.trails.observe(viewLifecycleOwner, Observer { trails ->
            map?.let { googleMap ->
                trails.map { it.geoJSON }.forEach {
                    GeoJsonLayerFactory(googleMap).create(it).addLayerToMap()
                }
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val omaha = LatLng(41.259698, -96.022224)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(omaha, 9.5f))

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

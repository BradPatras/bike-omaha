package io.github.bradpatras.bikeomaha.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.awaitMap
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import io.github.bradpatras.bikeomaha.R
import io.github.bradpatras.bikeomaha.adapters.TrailAdapter
import io.github.bradpatras.bikeomaha.data.GeoJsonLayerFactory
import io.github.bradpatras.bikeomaha.databinding.MainFragmentBinding

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()
    private var googleMap: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)

        binding.mapView.onCreate(savedInstanceState)

        lifecycle.coroutineScope.launchWhenCreated {
            setupMap()
            setupBottomSheet()
            checkLocationPermission()
            setupLoadingIndicator()
        }
    }

    private fun checkLocationPermission() {
        Dexter.withContext(requireContext())
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(locationPermissionListener())
            .check()
    }

    private fun locationPermissionListener(): PermissionListener {
        return object: PermissionListener {
            @SuppressLint("MissingPermission")
            override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                googleMap?.isMyLocationEnabled = true
                googleMap?.uiSettings?.isMyLocationButtonEnabled = true
            }

            override fun onPermissionRationaleShouldBeShown(request: PermissionRequest?, response: PermissionToken?) {
                response?.cancelPermissionRequest()
            }

            override fun onPermissionDenied(response: PermissionDeniedResponse?) { }
        }
    }

    private fun setupLoadingIndicator() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            //
        }
    }

    private fun setupBottomSheet() {
        viewModel.trails.observe(viewLifecycleOwner) { trails ->
            binding.bottomSheet.listAdapter?.submitList(trails)
        }

        binding.bottomSheet.listAdapter?.itemCheckedLiveData?.observe(viewLifecycleOwner) { itemId ->
            viewModel.trailsRepository.toggleTrailSelected(itemId)
            binding.bottomSheet.listAdapter?.notifyDataSetChanged()
        }
    }

    private suspend fun setupMap() {
        val map = binding.mapView.awaitMap()

        googleMap = map
        map.uiSettings.isCompassEnabled = true
        map.uiSettings.isMyLocationButtonEnabled = false
        map.mapType = GoogleMap.MAP_TYPE_HYBRID
        val omaha = LatLng(41.259698, -96.022224)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(omaha, 9.5f))

        viewModel.trails.observe(viewLifecycleOwner, Observer { trails ->
            trails.map { it.geoJSON }.forEach {
                GeoJsonLayerFactory(map).create(it).addLayerToMap()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onDestroyView() {
        binding.mapView.onDestroy()
        super.onDestroyView()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }
}

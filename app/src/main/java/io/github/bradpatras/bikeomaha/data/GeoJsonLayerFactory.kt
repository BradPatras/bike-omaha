package io.github.bradpatras.bikeomaha.data

import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.collections.GroundOverlayManager
import com.google.maps.android.collections.MarkerManager
import com.google.maps.android.collections.PolygonManager
import com.google.maps.android.collections.PolylineManager
import com.google.maps.android.data.geojson.GeoJsonFeature
import com.google.maps.android.data.geojson.GeoJsonLayer
import io.github.bradpatras.bikeomaha.util.applyPropertyStyles
import org.json.JSONObject

/**
 * Factory is needed for GeoJsonLayers because the styling values contained in the
 * GeoJsonFeature's 'properties' object are not automatically applied when the google map
 * adds the feature geometry to the map.
 *
 * The responsibility of this factory is to take a geojson JSONObject and create a geojsonlayer
 * using the built in constructor of the GeoJsonLayer class.  Then for each feature of the layer,
 * it will grab the styling properties and apply them to a GeoJsonLineStringStyle object that
 * will then be added to that feature.
 */
class GeoJsonLayerFactory(
    private val map: GoogleMap
) {
    private val polylineManager = PolylineManager(map)
    private val groundOverlayManager = GroundOverlayManager(map)
    private val polygonManager = PolygonManager(map)
    private val markerManager = MarkerManager(map)

    fun create(geoJsonRootObject: JSONObject): GeoJsonLayer {
        return GeoJsonLayer(
            map,
            geoJsonRootObject,
            markerManager,
            polygonManager,
            polylineManager,
            groundOverlayManager
        ).apply {
            features.forEach(GeoJsonFeature::applyPropertyStyles)
        }
    }
}
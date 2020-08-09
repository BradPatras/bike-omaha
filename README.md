# bike-omaha

Android app for viewing bike paths/routes in omaha.  Hoping to make travel by bicycle less painful for Omaha residents.

## Rough Outline
### Design
Full screen map as main app screen
Minimal or nonexistent top bar
Rounded bottom sheet peeking out at bottom with drag handle/expand button (drag handle possibly not viable with phone swipe navigation)
Bottom sheet contains toggles for turning individual routes on/off

### Data
Data hosted on my github pages site in a raw json file
JSON file will contain GPS data in [GeoJSON format](https://geojson.org/). 

### Other Resources
There are a few GeoJSON kotlin libraries available, here's a one that has semi-active development https://github.com/chris-hatton/geojson-kotlin 

Google Maps SDK for Android [supports](https://developers.google.com/maps/documentation/android-sdk/utility/geojson) loading GeoJSON directly

## Service Documentation

### bradpatras.github.io/bike-omaha/trails.json
 **This is the file that the app consumes**.

This raw json file will contain all the trails, each in a json object. A trail object will has three values, a title, an identifier, and a GeoJSON feature collection json object.

```jsx
{
    "trails": [
        {
            "title": "West Papio Trail", // String
            "identifier": 1, // Int
            "geoJSON": {}, // GeoJSON 'FeatureCollection' object (massive)
        },
    ]
}
```

### 'Back End'
I played with different ideas for generating the trails.json file but settled with a script that will take in FeatureCollection json files, and combine them with a more eye-friendly metadata json file.  One thing i'm trying to avoid is ever having to manually edit the FeatureCollection files.  The GeoJSON data will all be generated by either a converter from KML, or from a graphical mapping tool that generates GeoJSON directly, like [geojson.io](https://geojson.io/).  The only manual data entry that I plan on doing is adding a new metadata entry when I add a new trail and then pasting in a geojson file into the directory.  

Here's what the metadata file looks like
```jsx
{
    "trails": [
        {
            "title": "West Papio Trail",
            "identifier": 1,
            "filename": "west-papio.json"
        },
        {
            "title": "Keystore Trail",
            "identifier": 2,
            "filename": "keystone.json"
        }
    ]
}
```

The combination is done in a swift script in the servicebuilder.swift file.  To generate the output file on a mac you need to have swift installed and located in `/usr/bin/` and then in terminal, run `./servicebuilder.swift` from the `sources/` directory

## Dev Notes
- GeoJsonFeature 'properties' json object contain styling to be used when laying out the feature on the map (stroke width, stroke color). Currently these properties are required by the app to be present and they are not documented anywhere and were originally created by the kml exporter on the google MyMaps GUI.  I don't want to be locked in to using the MyMaps GUI in the future for creating new trails so I should figure out a way of adding the properties to the features some other way. Possibly as part of the `servicebuilder.swift` script.  At the very least I need to add some validation to the servicebuilder script to ensure that the features all contain a valid properties object.

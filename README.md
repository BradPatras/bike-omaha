# bike-omaha

Android app for viewing bike paths/routes in omaha.  Hoping to make travel by bicycle less painful for Omaha residents.

## Rough Outline
### Design
Full screen map as main app screen
Minimal or nonexistant top bar
Rounded bottom sheet peeking out at bottom with drag handle/expand button (drag handle possibly not viable with phone swipe navigation)
Bottom sheet contains toggles for turning individual routes on/off

### Data
Data hosted on my github pages site in raw json files
JSON files will contain GPS data in [GeoJSON format](https://geojson.org/). 

### Other Resources
There are a few GeoJSON kotlin libraries available, here's a one that has semi-active development https://github.com/chris-hatton/geojson-kotlin 

Google Maps SDK for Android [supports](https://developers.google.com/maps/documentation/android-sdk/utility/geojson) loading GeoJSON directly


package com.me.hillfortsfinal.views.map

import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.me.hillfortsfinal.R
import com.me.hillfortsfinal.helpers.readImageFromPath
import com.me.hillfortsfinal.models.PlacemarkModel
import com.me.hillfortsfinal.views.BaseView
import com.me.hillfortsfinal.views.map.PlacemarkMapPresenter
import kotlinx.android.synthetic.main.activity_placemark_map.*
import kotlinx.android.synthetic.main.content_placemark_maps.*


class PlacemarkMapView : BaseView(), GoogleMap.OnMarkerClickListener {

  lateinit var presenter: PlacemarkMapPresenter
  lateinit var map : GoogleMap

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placemark_map)
    super.init(toolbar, true)

    presenter = initPresenter (PlacemarkMapPresenter(this)) as PlacemarkMapPresenter

    mapView.onCreate(savedInstanceState);
    mapView.getMapAsync {
      map = it
      map.setOnMarkerClickListener(this)
      presenter.loadPlacemarks()
    }
  }

  override fun showPlacemark(placemark: PlacemarkModel) {
    currentTitle.text = placemark.title
    currentDescription.text = placemark.description
    Glide.with(this).load(placemark.image).into(currentImage);
  }

  override fun showPlacemarks(placemarks: List<PlacemarkModel>) {
    presenter.doPopulateMap(map, placemarks)
  }

  override fun onMarkerClick(marker: Marker): Boolean {
    presenter.doMarkerSelected(marker)
    return true
  }

  override fun onDestroy() {
    super.onDestroy()
    mapView.onDestroy()
  }

  override fun onLowMemory() {
    super.onLowMemory()
    mapView.onLowMemory()
  }

  override fun onPause() {
    super.onPause()
    mapView.onPause()
  }

  override fun onResume() {
    super.onResume()
    mapView.onResume()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    mapView.onSaveInstanceState(outState)
  }
}

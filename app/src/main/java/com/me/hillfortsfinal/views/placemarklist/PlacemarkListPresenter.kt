package com.me.hillfortsfinal.views.placemarklist

import com.google.firebase.auth.FirebaseAuth
import com.me.hillfortsfinal.models.PlacemarkModel
import com.me.hillfortsfinal.views.VIEW
import com.me.hillfortsfinal.views.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class PlacemarkListPresenter(view: BaseView) : BasePresenter(view) {

  fun doAddPlacemark() {
    view?.navigateTo(VIEW.PLACEMARK)
  }

  fun doEditPlacemark(placemark: PlacemarkModel) {
    view?.navigateTo(VIEW.PLACEMARK, 0, "placemark_edit", placemark)
  }

  fun doShowPlacemarksMap() {
    view?.navigateTo(VIEW.MAPS)
  }

  fun loadPlacemarks() {
    doAsync {
      val placemarks = app.placemarks.findAll()
      uiThread {
        view?.showPlacemarks(placemarks)
      }
    }
  }

  fun doLogout() {
    app.placemarks.clear()
    FirebaseAuth.getInstance().signOut()
    view?.navigateTo(VIEW.LOGIN)
  }
}
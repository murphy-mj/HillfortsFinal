package com.me.hillfortsfinal.views

import android.content.Intent

import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.me.hillfortsfinal.models.PlacemarkModel
import com.me.hillfortsfinal.views.BasePresenter
import com.me.hillfortsfinal.views.editlocation.EditLocationView
import com.me.hillfortsfinal.views.login.LoginView
import org.jetbrains.anko.AnkoLogger
import com.me.hillfortsfinal.models.Location
import com.me.hillfortsfinal.views.map.PlacemarkMapView
import com.me.hillfortsfinal.views.placemark.PlacemarkView
import com.me.hillfortsfinal.views.placemarklist.PlacemarkListView



val IMAGE_REQUEST = 1
val LOCATION_REQUEST = 2

enum class VIEW {
  LOCATION, PLACEMARK, MAPS, LIST, LOGIN
}

open abstract class BaseView() : AppCompatActivity(), AnkoLogger {

  var basePresenter: BasePresenter? = null

  fun navigateTo(view: VIEW, code: Int = 0, key: String = "", value: Parcelable? = null) {
    var intent = Intent(this, PlacemarkListView::class.java)
    when (view) {
      VIEW.LOCATION -> intent = Intent(this, EditLocationView::class.java)
      VIEW.PLACEMARK -> intent = Intent(this, PlacemarkView::class.java)
      VIEW.MAPS -> intent = Intent(this, PlacemarkMapView::class.java)
      VIEW.LIST -> intent = Intent(this, PlacemarkListView::class.java)
      VIEW.LOGIN -> intent = Intent(this, LoginView::class.java)
    }
    if (key != "") {
      intent.putExtra(key, value)
    }
    startActivityForResult(intent, code)
  }

  fun initPresenter(presenter: BasePresenter): BasePresenter {
    basePresenter = presenter
    return presenter
  }

  fun init(toolbar: Toolbar, upEnabled: Boolean) {
    toolbar.title = title
    setSupportActionBar(toolbar)
    val user = FirebaseAuth.getInstance().currentUser
    if (user != null) {
      toolbar.title = "${title}: ${user.email}"
    }
    supportActionBar?.setDisplayHomeAsUpEnabled(upEnabled)
  }

  override fun onDestroy() {
    basePresenter?.onDestroy()
    super.onDestroy()
  }


  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (data != null) {
      basePresenter?.doActivityResult(requestCode, resultCode, data)
    }
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    basePresenter?.doRequestPermissionsResult(requestCode, permissions, grantResults)
  }

  open fun showPlacemark(placemark: PlacemarkModel) {}
  open fun showPlacemarks(placemarks: List<PlacemarkModel>) {}
  open fun showLocation(location : Location) {}
  open fun showProgress() {}
  open fun hideProgress() {}
}
package com.me.hillfortsfinal.views

import android.content.Intent
import com.me.hillfortsfinal.main.MainApp
import com.me.hillfortsfinal.views.BaseView

open class BasePresenter(var view: BaseView?) {

  var app: MainApp =  view?.application as MainApp

  open fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
  }

  open fun doRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
  }

  open fun onDestroy() {
    view = null
  }
}
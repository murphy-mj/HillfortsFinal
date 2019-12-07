package com.me.hillfortsfinal.views.placemarklist

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.me.hillfortsfinal.R
import com.me.hillfortsfinal.models.PlacemarkModel
import com.me.hillfortsfinal.views.BaseView
import kotlinx.android.synthetic.main.activity_placemark_list.*
import com.me.hillfortsfinal.views.placemarklist.PlacemarkAdapter
import com.me.hillfortsfinal.views.placemarklist.PlacemarkListPresenter
import com.me.hillfortsfinal.views.placemarklist.PlacemarkListener

class PlacemarkListView :  BaseView(), PlacemarkListener {

  lateinit var presenter: PlacemarkListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placemark_list)
    super.init(toolbar, false);

    presenter = initPresenter(PlacemarkListPresenter(this)) as PlacemarkListPresenter

    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    presenter.loadPlacemarks()
  }

  override fun showPlacemarks(placemarks: List<PlacemarkModel>) {
    recyclerView.adapter = PlacemarkAdapter(placemarks, this)
    recyclerView.adapter?.notifyDataSetChanged()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_add -> presenter.doAddPlacemark()
      R.id.item_map -> presenter.doShowPlacemarksMap()
      R.id.item_logout ->presenter.doLogout()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onPlacemarkClick(placemark: PlacemarkModel) {
    presenter.doEditPlacemark(placemark)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    presenter.loadPlacemarks()
    super.onActivityResult(requestCode, resultCode, data)
  }
}
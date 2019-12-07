package com.me.hillfortsfinal.views.login

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.me.hillfortsfinal.R
import com.bumptech.glide.Glide.init
import com.me.hillfortsfinal.views.BaseView
import com.me.hillfortsfinal.views.login.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast



class LoginView : BaseView() {

  lateinit var presenter: LoginPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_login)
    init(toolbar, false)
    progressBar.visibility = ProgressBar.INVISIBLE

    presenter = initPresenter(LoginPresenter(this)) as LoginPresenter

    signUp.setOnClickListener {
      val email = email.text.toString()
      val password = password.text.toString()
      if (email == "" || password == "") {
        toast("Please provide email + password")
      } else {
        presenter.doSignUp(email, password)
      }
    }

    logIn.setOnClickListener {
      val email = email.text.toString()
      val password = password.text.toString()
      if (email == "" || password == "") {
        toast("Please provide email + password")
      } else {
        presenter.doLogin(email, password)
      }
    }
  }

  override fun showProgress() {
    progressBar.visibility = ProgressBar.VISIBLE
  }

  override fun hideProgress() {
    progressBar.visibility = ProgressBar.INVISIBLE
  }
}
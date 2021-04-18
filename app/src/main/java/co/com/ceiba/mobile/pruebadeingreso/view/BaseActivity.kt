package co.com.ceiba.mobile.pruebadeingreso.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.presenter.BasePresenter

abstract class BaseActivity<P : BasePresenter<BaseView>> : BaseView, AppCompatActivity() {

    protected lateinit var presenter: P
    private lateinit var alertDialogBuilder: AlertDialog.Builder
    protected lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
        alertDialogBuilder = instantiateAlertDialogBuilder()
        dialog = alertDialogBuilder.setView(R.layout.progress_view)
            .setCancelable(false)
            .create()
    }

    protected abstract fun instantiatePresenter(): P

    protected abstract fun instantiateAlertDialogBuilder(): AlertDialog.Builder

    override fun getContext(): Context {
        return this
    }
}
package co.com.ceiba.mobile.pruebadeingreso.presenter

import co.com.ceiba.mobile.pruebadeingreso.injection.component.DaggerPresenterInjector
import co.com.ceiba.mobile.pruebadeingreso.injection.component.PresenterInjector
import co.com.ceiba.mobile.pruebadeingreso.injection.module.ContextModule
import co.com.ceiba.mobile.pruebadeingreso.injection.module.RetrofitModule
import co.com.ceiba.mobile.pruebadeingreso.injection.module.RoomModule
import co.com.ceiba.mobile.pruebadeingreso.presenter.post.PostPresenter
import co.com.ceiba.mobile.pruebadeingreso.presenter.user.UserPresenter
import co.com.ceiba.mobile.pruebadeingreso.view.BaseView

abstract class BasePresenter<out V : BaseView>(protected val view: V) {

    private val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .retrofitModule(RetrofitModule)
        .roomModule(RoomModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is PostPresenter -> injector.inject(this)
            is UserPresenter -> injector.inject(this)
        }
    }
}
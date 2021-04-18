package co.com.ceiba.mobile.pruebadeingreso.view.user

import co.com.ceiba.mobile.pruebadeingreso.model.entities.User
import co.com.ceiba.mobile.pruebadeingreso.view.BaseView

interface UserView : BaseView {

    fun updateUsers(users: List<User>)

    fun showError(error: String)

    fun showLoading()

    fun hideLoading()
}
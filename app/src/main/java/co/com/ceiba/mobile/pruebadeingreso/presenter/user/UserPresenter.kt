package co.com.ceiba.mobile.pruebadeingreso.presenter.user

import co.com.ceiba.mobile.pruebadeingreso.interfaces.RetrofitApi
import co.com.ceiba.mobile.pruebadeingreso.model.dao.AddressDao
import co.com.ceiba.mobile.pruebadeingreso.model.dao.CompanyDao
import co.com.ceiba.mobile.pruebadeingreso.model.dao.GeoDao
import co.com.ceiba.mobile.pruebadeingreso.model.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.model.entities.Address
import co.com.ceiba.mobile.pruebadeingreso.model.entities.Company
import co.com.ceiba.mobile.pruebadeingreso.model.entities.Geo
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User
import co.com.ceiba.mobile.pruebadeingreso.presenter.BasePresenter
import co.com.ceiba.mobile.pruebadeingreso.view.user.UserView
import kotlinx.coroutines.*
import javax.inject.Inject

class UserPresenter(userView: UserView) : BasePresenter<UserView>(userView) {

    @Inject
    lateinit var retrofitApi: RetrofitApi

    @Inject
    lateinit var userDao: UserDao

    @Inject
    lateinit var addressDao: AddressDao

    @Inject
    lateinit var companyDao: CompanyDao

    @Inject
    lateinit var geoDao: GeoDao

    fun getUsers() {
        checkUsers()
    }

    private fun checkUsers() {
        CoroutineScope(Dispatchers.Main).launch {
            val list = withContext(Dispatchers.IO) {
                userDao.getAll()
            }
            if (list.isNotEmpty()) {
                view.updateUsers(list)
            } else {
                loadUsers()
            }
        }
    }

    private fun loadUsers() {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val call = withContext(Dispatchers.IO) {
                retrofitApi.getUsers()
            }
            view.hideLoading()
            if (call.isSuccessful) {
                val users = call.body()
                if (users != null) {
                    view.updateUsers(users)
                    addUsers(users)
                } else {
                    view.showError("Error")
                }
            } else {
                view.showError("Error")
            }
        }
    }

    private fun addUsers(users: List<User>) {
        CoroutineScope(Dispatchers.IO).launch {
            users.forEach {
                val address = Address(
                    0,
                    it.id,
                    it.address!!.street,
                    it.address.suite,
                    it.address.city,
                    it.address.zipcode,
                    it.address.geo
                )
                val id = addressDao.insert(address)
                val geo = Geo(0, id, address.geo!!.lat, address.geo.lng)
                geoDao.insert(geo)
                val company =
                    Company(0, it.id, it.company!!.name, it.company.catchPhrase, it.company.bs)
                companyDao.insert(company)
            }
            userDao.insertAll(users)
        }
    }

}
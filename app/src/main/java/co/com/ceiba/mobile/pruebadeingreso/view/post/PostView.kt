package co.com.ceiba.mobile.pruebadeingreso.view.post

import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post
import co.com.ceiba.mobile.pruebadeingreso.view.BaseView

interface PostView : BaseView {

    fun updatePosts(post: List<Post>)

    fun showError(error: String)

    fun showLoading()

    fun hideLoading()
}
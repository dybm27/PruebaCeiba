package co.com.ceiba.mobile.pruebadeingreso.presenter.post

import co.com.ceiba.mobile.pruebadeingreso.interfaces.RetrofitApi
import co.com.ceiba.mobile.pruebadeingreso.model.dao.PostDao
import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post
import co.com.ceiba.mobile.pruebadeingreso.presenter.BasePresenter
import co.com.ceiba.mobile.pruebadeingreso.view.post.PostView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostPresenter(postView: PostView) : BasePresenter<PostView>(postView) {
    @Inject
    lateinit var retrofitApi: RetrofitApi

    @Inject
    lateinit var postDao: PostDao

    fun getPosts(idUser: Long) {
        checkUserPosts(idUser)
    }

    private fun checkUserPosts(idUser: Long) {
        CoroutineScope(Dispatchers.Main).launch {
            val list = withContext(Dispatchers.IO) {
                postDao.loadAllByIdUser(idUser)
            }
            if (list.isNotEmpty()) {
                view.updatePosts(list)
            } else {
                loadPost(idUser)
            }
        }
    }

    private fun loadPost(idUser: Long) {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val call = withContext(Dispatchers.IO) {
                retrofitApi.getPost(idUser.toString())
            }
            view.hideLoading()
            if (call.isSuccessful) {
                val posts = call.body()
                if (posts != null) {
                    view.updatePosts(posts)
                    addPost(posts)
                } else {
                    view.showError("Error")
                }
            } else {
                view.showError("Error")
            }
        }
    }

    private fun addPost(post: List<Post>) {
        CoroutineScope(Dispatchers.IO).launch {
            postDao.insertAll(post)
        }
    }
}
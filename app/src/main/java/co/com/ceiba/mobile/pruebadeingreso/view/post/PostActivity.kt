package co.com.ceiba.mobile.pruebadeingreso.view.post

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding
import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User
import co.com.ceiba.mobile.pruebadeingreso.presenter.post.PostPresenter
import co.com.ceiba.mobile.pruebadeingreso.view.BaseActivity

class PostActivity : BaseActivity<PostPresenter>(), PostView {

    private lateinit var binding: ActivityPostBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        initExtra()
    }

    private fun initExtra() {
        val extras = intent.extras!!
        if (extras.isEmpty) {
            showError("No se encontraron extras")
        } else {
            val user: User = extras.get("user") as User
            initDataUser(user)
            presenter.getPosts(user.id)
        }
    }

    private fun initRecycler() {
        postAdapter = PostAdapter(listOf())
        binding.recyclerViewPostsResults.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPostsResults.adapter = postAdapter
    }

    private fun initDataUser(user: User) {
        binding.email.text = user.email
        binding.phone.text = user.phone
        binding.name.text = user.name
    }

    override fun instantiatePresenter(): PostPresenter {
        return PostPresenter(this)
    }

    override fun updatePosts(post: List<Post>) {
       postAdapter.updatePost(post)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    override fun hideLoading() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    override fun instantiateAlertDialogBuilder(): AlertDialog.Builder {
        return AlertDialog.Builder(this)
    }
}
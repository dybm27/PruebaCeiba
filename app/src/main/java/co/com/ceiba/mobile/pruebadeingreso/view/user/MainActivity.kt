package co.com.ceiba.mobile.pruebadeingreso.view.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding
import co.com.ceiba.mobile.pruebadeingreso.interfaces.OnClick
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User
import co.com.ceiba.mobile.pruebadeingreso.presenter.user.UserPresenter
import co.com.ceiba.mobile.pruebadeingreso.view.BaseActivity
import co.com.ceiba.mobile.pruebadeingreso.view.post.PostActivity
import java.util.*


class MainActivity : BaseActivity<UserPresenter>(), UserView, OnClick {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var users: List<User>
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        initTextChange()
        presenter.getUsers()
        inflateViewEmpty()
    }

    private fun inflateViewEmpty() {
        view = layoutInflater.inflate(R.layout.empty_view, binding.root, false)
        binding.content.addView(view)
        view.visibility = View.GONE
    }

    private fun initTextChange() {
        binding.editTextSearch.addTextChangedListener { et ->
            if (et.toString().isNotEmpty()) {
                val aux = users.filter {
                    it.name!!.toUpperCase(Locale.ROOT).contains(
                        et.toString().toUpperCase(
                            Locale.ROOT
                        )
                    )
                }
                if (aux.isEmpty()) {
                    view.visibility = View.VISIBLE
                    binding.recyclerViewSearchResults.visibility = View.GONE
                } else {
                    view.visibility = View.GONE
                    binding.recyclerViewSearchResults.visibility = View.VISIBLE
                    userAdapter.updateUsers(aux)
                }
            } else {
                userAdapter.updateUsers(users)
            }
        }
    }

    private fun initRecycler() {
        userAdapter = UserAdapter(listOf(), this)
        binding.recyclerViewSearchResults.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewSearchResults.adapter = userAdapter
    }

    override fun instantiatePresenter(): UserPresenter {
        return UserPresenter(this)
    }

    override fun updateUsers(users: List<User>) {
        this.users = users
        userAdapter.updateUsers(users)
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

    override fun click(user: User) {
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    override fun instantiateAlertDialogBuilder(): AlertDialog.Builder {
        return AlertDialog.Builder(this)
    }
}
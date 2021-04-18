package co.com.ceiba.mobile.pruebadeingreso.view.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.interfaces.OnClick
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User

class UserAdapter(private var users: List<User>, private val onClick: OnClick) : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    inner class UserHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = UserListItemBinding.bind(view)
        fun bind(user: User) {
            binding.email.text = user.email
            binding.name.text = user.name
            binding.phone.text = user.phone
            binding.btnViewPost.setOnClickListener {
                onClick.click(user)
            }
        }
    }

    fun updateUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserHolder(layoutInflater.inflate(R.layout.user_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
}
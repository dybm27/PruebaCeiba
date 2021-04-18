package co.com.ceiba.mobile.pruebadeingreso.view.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.databinding.PostListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User

class PostAdapter(private var posts: List<Post>) : RecyclerView.Adapter<PostAdapter.PostHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PostHolder(layoutInflater.inflate(R.layout.post_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class PostHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = PostListItemBinding.bind(view)
        fun bind(post: Post) {
            binding.title.text = post.title
            binding.body.text = post.body
        }
    }

    fun updatePost(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }
}
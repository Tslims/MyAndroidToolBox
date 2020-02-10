package fr.isen.berton.myandroidtoolbox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_view_user.view.*
import androidx.recyclerview.widget.RecyclerView


class UserWebServicesAdapter(
    val users: ArrayList<UserWebServicesModel>,
    val clickListener:(UserWebServicesModel) -> Unit
): RecyclerView.Adapter<UserWebServicesAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_user, parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.count()
    }

    override fun onBindViewHolder(holder: UserWebServicesAdapter.UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user,clickListener)
    }

    class UserViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(user: UserWebServicesModel, clickListener: (UserWebServicesModel) -> Unit){
            view.NametextView.text= "${user.name?.first} ${user.name?.last}"
            view.locationtextview.text = user.location.toString()
            view.emailtextView.text = user.email
            Picasso.get().load("${user?.picture?.large}").into(view.imageView);
            view.setOnClickListener { clickListener(user) }

        }
    }


}
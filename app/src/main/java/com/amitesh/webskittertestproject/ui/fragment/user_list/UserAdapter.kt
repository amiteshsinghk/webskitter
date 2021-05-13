package com.amitesh.webskittertestproject.ui.fragment.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.amitesh.webskittertestproject.R
import com.amitesh.webskittertestproject.data.entities.User
import com.amitesh.webskittertestproject.databinding.RowUserBinding
import com.amitesh.webskittertestproject.ui.activities.dashboard.DashboardViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UserAdapter (private val vm: DashboardViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<User>()

    fun setData(newList: MutableList<User>) {
        val diffCallback = UserDiffCallback(list, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding:RowUserBinding  = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_user,
            parent,
            false
        )
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as MyViewHolder).bind(list[position])
    }


    inner class MyViewHolder(private val binding: RowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
            binding.viewmodel = vm
            binding.executePendingBindings()
            binding.root.setOnLongClickListener {
                MaterialAlertDialogBuilder(it.context)
                    .setTitle("Delete")
                    .setMessage("Are you sure, you want to delete this user?")
                    .setPositiveButton("Delete") { _, _ ->
                        vm.delete(user)
                    }
                    .setNegativeButton("Cancel", null)
                    .show();
                true
            }
        }

    }
}
class UserDiffCallback(private val oldList: List<User>, private val newList: List<User>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name === newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val oldData = oldList[oldPosition]
        val newData = newList[newPosition]

        return oldData.name == newData.name
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}
package com.amitesh.webskittertestproject.ui.fragment.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amitesh.webskittertestproject.R
import com.amitesh.webskittertestproject.data.models.AlbumItem
import com.amitesh.webskittertestproject.databinding.RowAlbumBinding
import com.amitesh.webskittertestproject.ui.activities.dashboard.DashboardViewModel
import com.amitesh.webskittertestproject.utility.trycatch

class AlbumAdapter(private val vm: DashboardViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<AlbumItem>()
    private var isSearching = false

    fun setData(newList: MutableList<AlbumItem>, searching: Boolean = false) {
        isSearching = searching

        val diffCallback = AlbumDiffCallback(list, newList)
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
        val binding: RowAlbumBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_album, parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as MyViewHolder).bind(list[position])
    }


    inner class MyViewHolder(private val binding: RowAlbumBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(album: AlbumItem) {
            binding.albumitem = album
            binding.executePendingBindings()

            binding.cl.setOnClickListener {
                if (isSearching) {
                    trycatch {
                        album.selected = !album.selected
                        vm.photoList.firstOrNull { it.id == album.id }?.run {
                            this.selected = album.selected
                        }

                        binding.albumitem = album
                        binding.executePendingBindings()

                        vm.totalSelectedLD.value = vm.photoList.filter { it.selected }.size
                    }
                }
            }

        }

    }
    class AlbumDiffCallback(private val oldList: List<AlbumItem>, private val newList: List<AlbumItem>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            val oldData = oldList[oldPosition]
            val newData = newList[newPosition]

            return oldData.id == newData.id
        }

        @Nullable
        override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
            return super.getChangePayload(oldPosition, newPosition)
        }
    }


}
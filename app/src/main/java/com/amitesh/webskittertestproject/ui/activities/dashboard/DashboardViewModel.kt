package com.amitesh.webskittertestproject.ui.activities.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.amitesh.webskittertestproject.R
import com.amitesh.webskittertestproject.data.db.DatabaseHelper
import com.amitesh.webskittertestproject.data.entities.User
import com.amitesh.webskittertestproject.data.models.AlbumItem
import com.amitesh.webskittertestproject.ui.fragment.album.AlbumAdapter
import com.amitesh.webskittertestproject.ui.fragment.user_list.UserAdapter
import com.amitesh.webskittertestproject.utility.trycatch
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    var hostController: NavController? = null
    var dbHelper: DatabaseHelper? = null
    val photoList: MutableList<AlbumItem> = mutableListOf()
    var albumAdapter: AlbumAdapter = AlbumAdapter(this)
    val searchList: MutableList<AlbumItem> = mutableListOf()
    val userList: MutableList<User> = mutableListOf()
    val totalSelectedLD = MutableLiveData<Int>()
    var userAdapter: UserAdapter = UserAdapter(this)
    var clickedUser: User? = null

    init {
        totalSelectedLD.value = 0
    }
    fun fetchUsers() {
        viewModelScope.launch {
            trycatch {
                dbHelper?.run {
                    userList.clear()
                    userList.addAll(getUsers())
                    userAdapter.setData(userList)
                }
            }
        }
    }

    fun add(user: User) {
        viewModelScope.launch {
            dbHelper?.run {
                insert(user)
            }
        }
    }

    fun onUserClick(user: User) {
        clickedUser = user
        hostController?.navigate(R.id.userFragment)
    }

    fun update(user: User) {
        viewModelScope.launch {
            dbHelper?.run {
                update(user)
            }
        }
    }

    fun delete(user: User) {
        viewModelScope.launch {
            dbHelper?.run {
                delete(user)
                fetchUsers()
            }
        }
    }


}
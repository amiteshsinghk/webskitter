package com.amitesh.webskittertestproject.ui.fragment.album

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.observe
import com.amitesh.webskittertestproject.R
import com.amitesh.webskittertestproject.data.models.Album
import com.amitesh.webskittertestproject.databinding.FragmentAlbumBinding
import com.amitesh.webskittertestproject.networking.ApiService
import com.amitesh.webskittertestproject.networking.RetrofitInstance
import com.amitesh.webskittertestproject.ui.activities.dashboard.DashboardViewModel
import com.amitesh.webskittertestproject.utility.getViewModel
import com.amitesh.webskittertestproject.utility.hideKeyboard
import retrofit2.Response

class AlbumFragment : Fragment() {
    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: FragmentAlbumBinding
    private lateinit var apiService: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_album, container, false)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_album, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = requireActivity().getViewModel { DashboardViewModel() }
        binding.viewmodel = viewModel

        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)

        initUI()
        reqData()
    }

    private fun initUI() {
        binding.rvPhotos.adapter = viewModel.albumAdapter

        binding.etSearch.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                searchTitle(it.toString())
            } else {
                viewModel.albumAdapter.setData(viewModel.photoList)
            }
        }

        binding.ivClear.setOnClickListener {
            binding.etSearch.text.clear()
            if (binding.etSearch.isFocusable)binding.etSearch.clearFocus()
            requireContext().hideKeyboard(it)
        }
    }

    private fun reqData() {
        binding.progressBar.visibility= View.VISIBLE
        viewModel.photoList.clear()
        viewModel.albumAdapter.setData(viewModel.photoList)

        val responseLiveData: LiveData<Response<Album>> = liveData {
            val response = apiService.getProducts()
            emit(response)
        }

        responseLiveData.observe(viewLifecycleOwner, {
            loadData(it.body())
        })
    }

    private fun loadData(album: Album?) {
        album?.run {
            viewModel.photoList.addAll(album.take(100))
            viewModel.albumAdapter.setData(viewModel.photoList)
            binding.progressBar.visibility = View.GONE
        }

    }

    private fun searchTitle(searchText: String) {
        viewModel.searchList.clear()
        viewModel.searchList.addAll(viewModel.photoList.filter { it.title.contains(searchText, true) })
        viewModel.searchList.sortByDescending { it.selected }
        viewModel.albumAdapter.setData(viewModel.searchList, true)
    }

}
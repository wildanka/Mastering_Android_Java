package com.wildanka.pagingstackexchange

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.wildanka.pagingstackexchange.model.entity.Items
import com.wildanka.pagingstackexchange.model.network.ItemDataSource
import com.wildanka.pagingstackexchange.model.network.ItemDataSourceFactory
import com.wildanka.pagingstackexchange.model.network.PAGE_SIZE
import com.wildanka.pagingstackexchange.repo.NetworkRepo

class MainViewModel() : ViewModel() {
    var itemPagedList: LiveData<PagedList<Items>>
    var liveDataSource: LiveData<PageKeyedDataSource<Int, Items>>
    private lateinit var stackResponses: LiveData<List<Items?>>

    init {
        val itemDataSourceFactory = ItemDataSourceFactory()
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource()
        val config = (PagedList.Config.Builder()).setEnablePlaceholders(false).setPageSize(PAGE_SIZE).build()
        itemPagedList = (LivePagedListBuilder(itemDataSourceFactory, config)).build()
    }


    fun fetchStackAnswer(): LiveData<List<Items?>> {
        if (!::stackResponses.isInitialized) {
            loadStackAnswer()
        }
        return stackResponses
    }

    private fun loadStackAnswer(){
        stackResponses = NetworkRepo().fetchStackAnswer(1, 50)

    }


}
package com.wildanka.pagingstackexchange.model.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.wildanka.pagingstackexchange.model.entity.Items

class ItemDataSourceFactory() : DataSource.Factory<Int, Items>() {
    private val itemLiveDataSource : MutableLiveData<PageKeyedDataSource<Int, Items>> = MutableLiveData()

    override fun create(): DataSource<Int, Items> {
        val itemDataSource = ItemDataSource()
        itemLiveDataSource.postValue(itemDataSource)

        return itemDataSource
    }

    fun getItemLiveDataSource() : MutableLiveData<PageKeyedDataSource<Int, Items>>{
        return itemLiveDataSource
    }
}
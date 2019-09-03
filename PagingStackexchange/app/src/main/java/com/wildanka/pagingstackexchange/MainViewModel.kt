package com.wildanka.pagingstackexchange

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wildanka.pagingstackexchange.model.entity.Items
import com.wildanka.pagingstackexchange.repo.NetworkRepo

class MainViewModel() : ViewModel() {
    private lateinit var stackResponses: MutableLiveData<List<Items?>>
    private val repo = NetworkRepo()

    fun fetchStackAnswer(): LiveData<List<Items?>> {
        if (!::stackResponses.isInitialized) {
            loadStackAnswer()
        }
        return stackResponses
    }

    private fun loadStackAnswer(){
        stackResponses = repo.fetchStackAnswer(1, 50)

    }
}
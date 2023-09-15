package com.kotlin.newsapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.newsapp.data.model.News
import com.kotlin.newsapp.repositories.NewsRepository
import com.kotlin.newsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val repository : NewsRepository
) : ViewModel() {

    val breakingNews : MutableLiveData<Resource<News>> = MutableLiveData()

    init {
        getBreakingNews()
    }
    fun getBreakingNews() = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = repository.getBreakingNews()
        breakingNews.postValue(handleResponse(response))
    }
    private fun handleResponse(response : Response<News>) : Resource<News>{
        if(response.isSuccessful){
            response.body()?.let {result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}
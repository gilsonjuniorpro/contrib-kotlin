package com.example.contribmontano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contribmontano.service.GithubFactory
import com.example.contribmontano.service.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MontanoViewModel: ViewModel() {
    private val repositories: MutableLiveData<List<Repository>> by lazy {
        MutableLiveData<List<Repository>>()
    }

    fun getRepositories(): LiveData<List<Repository>> {
        return repositories
    }

    fun requestRepositories(name: String) {
        viewModelScope.launch {
            loadRepositories(name)
        }
    }

    private suspend fun loadRepositories(name: String) {
        val service = withContext(Dispatchers.IO) {
            GithubFactory.makeGithubService()
        }
        val response = service.getRepos(name)

        repositories.value = response.body()
    }

}
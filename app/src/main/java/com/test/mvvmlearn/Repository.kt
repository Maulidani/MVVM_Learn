package com.test.mvvmlearn

import com.test.mvvmlearn.network.ApiService

class Repository(private val apiService: ApiService) {

    fun getCharacter(page: String) = apiService.fetchCharacter(page)
}
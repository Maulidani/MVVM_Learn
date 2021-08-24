package com.test.mvvmlearn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.mvvmlearn.network.ApiClient
import com.test.mvvmlearn.network.Character
import com.test.mvvmlearn.network.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel(private val repository: Repository = Repository(ApiClient.apiService)) :
    ViewModel() {

    private var _characterLivedata = MutableLiveData<ScreenState<List<Character>?>>()

    val characterLiveData: LiveData<ScreenState<List<Character>?>>
        get() = _characterLivedata

    init {
        fetchCharacter()
    }

    private fun fetchCharacter() {

        _characterLivedata.postValue(ScreenState.Loading(null))

        val client = repository.getCharacter("1")
        client.enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful) {
                    Log.e("character", "success: " + response.body())
                    _characterLivedata.postValue(ScreenState.Success(response.body()?.results))

                } else {
                    Log.e("character", "not success: " + response.code().toString())
                    _characterLivedata.postValue(ScreenState.Error(response.code().toString(),null))
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("character", "failure: " + t.message.toString())
                _characterLivedata.postValue(ScreenState.Error(t.message.toString(),null))
            }

        })
    }
}
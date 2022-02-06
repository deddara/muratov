package com.example.aaaa.ui.top

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aaaa.enumiration.GifApiStatus
import com.example.giphloaderapp.network.GifApi
import com.example.giphloaderapp.network.GifProperty
import kotlinx.coroutines.launch
import java.lang.Exception


class TopViewModel : ViewModel() {


    private val _status = MutableLiveData<GifApiStatus>()

    val status: LiveData<GifApiStatus> = _status

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text

    private val _currentGif = MutableLiveData<GifProperty>()

    private var _gifs = mutableListOf<GifProperty>()

    val currentGif: LiveData<GifProperty> = _currentGif

    private var _currentPage = 0

    private var _currentItem = 0

    val currentItem get() = _currentItem

    private var _currentGifsSize = 0

    init {
        getHot()
    }

    private fun getHot() {
        viewModelScope.launch {
            _status.value = GifApiStatus.LOADING
            try {
                val gifs = GifApi.retrofitService.getTopPhotos(_currentPage, "true")
                _gifs = (_gifs + gifs.result) as MutableList<GifProperty>
                _currentGif.value = _gifs[_currentItem]
                _text.value = _currentGif.value?.description ?: ""
                _currentGifsSize = _gifs.size
                _status.value = GifApiStatus.DONE
            } catch (e: Exception) {
                Log.e("ERROR", "${e.message}")
                _status.value = GifApiStatus.ERROR
                wipeData()
            }
        }
    }

    fun getNewGif() {
        _currentItem++
        if (_currentItem == _currentGifsSize || checkForOutboundIndex()) {
            _currentPage++
            getHot()
        }
        else {
            _currentGif.value = _gifs[_currentItem]
            _text.value = _currentGif.value?.description
        }
    }

    fun getPrevGif() {
        _currentItem--
        _currentGif.value = _gifs[_currentItem]
        _text.value = _currentGif.value?.description
    }

    private fun checkForOutboundIndex(): Boolean =
        if (_currentItem > _currentGifsSize)
             true
        else false

    private fun wipeData() {
        _gifs.clear()
        _currentItem = 0
        _currentGifsSize = 0
        _currentGif.value = GifProperty("", "")
    }
}
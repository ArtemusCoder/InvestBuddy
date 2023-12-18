package com.example.investbuddy.ui.base

import androidx.lifecycle.ViewModel
import com.example.investbuddy.data.network.UserAPI
import com.example.investbuddy.data.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel (
    private val repository: BaseRepository
) : ViewModel() {

    suspend fun logout(api: UserAPI) = withContext(Dispatchers.IO) { repository.logout(api)}
}
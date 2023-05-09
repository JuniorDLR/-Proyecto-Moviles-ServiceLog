package com.example.servivelog.ui.usuario.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.servivelog.data.database.entities.UserEntity
import com.example.servivelog.domain.model.user.InsertUser
import com.example.servivelog.domain.model.user.UserItem
import com.example.servivelog.domain.model.user.toDomain
import com.example.servivelog.domain.userusecase.CrudUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val crudUser: CrudUser
) : ViewModel() {

    fun getUser(idU: Int): UserItem {
        return crudUser.getUser(idU)
    }


    fun insertUser(user: InsertUser) {
        viewModelScope.launch {
            crudUser.insertUser(user)
        }
    }

    fun updateUser(user: UserItem) {
        viewModelScope.launch {
            crudUser.updateUser(user)
        }
    }

    fun deelteUser(user: UserItem) {
        viewModelScope.launch {
            crudUser.deelteUser(user)
        }

    }

     suspend fun getUserByUsername():List<UserItem> {

        return crudUser.getUserByUsername()

    }
}
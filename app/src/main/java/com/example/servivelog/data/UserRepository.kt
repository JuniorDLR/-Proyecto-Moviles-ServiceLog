package com.example.servivelog.data

import com.example.servivelog.data.database.dao.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

}
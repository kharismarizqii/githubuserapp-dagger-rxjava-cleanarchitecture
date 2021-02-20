package com.kharismarizqii.githubuserapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.kharismarizqii.githubuserapp.core.data.Resource
import com.kharismarizqii.githubuserapp.core.domain.model.User
import com.kharismarizqii.githubuserapp.core.domain.repository.IUserRepository
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: IUserRepository): UserUseCase {
    override fun getSearchUser(q: String): LiveData<Resource<List<User>>> {
        return userRepository.getSearchUser(q)
    }

}
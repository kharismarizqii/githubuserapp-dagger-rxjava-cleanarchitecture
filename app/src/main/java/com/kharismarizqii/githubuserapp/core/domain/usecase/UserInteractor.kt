package com.kharismarizqii.githubuserapp.core.domain.usecase

import com.kharismarizqii.githubuserapp.core.domain.repository.IUserRepository
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: IUserRepository) :
    UserUseCase {
    override fun getSearchUser(q: String) = userRepository.getSearchUser(q)

}
package com.kharismarizqii.githubuserapp.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kharismarizqii.githubuserapp.core.data.source.local.LocalDataSource
import com.kharismarizqii.githubuserapp.core.data.source.remote.RemoteDataSource
import com.kharismarizqii.githubuserapp.core.data.source.remote.network.ApiResponse
import com.kharismarizqii.githubuserapp.core.data.source.remote.response.UserResponse
import com.kharismarizqii.githubuserapp.core.domain.model.User
import com.kharismarizqii.githubuserapp.core.domain.repository.IUserRepository
import com.kharismarizqii.githubuserapp.core.utils.AppExecutors
import com.kharismarizqii.githubuserapp.core.utils.DataMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IUserRepository {
    override fun getSearchUser(q: String): LiveData<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>,List<UserResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<User>> {
                return Transformations.map(localDataSource.getSearchUser(q)){
                    DataMapper.mapUserEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<User>?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getSearchUser(q)

            override fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapUserResponsesToEntities(data)
                localDataSource.insertSearchUser(userList)
            }

        }.asLiveData()
}
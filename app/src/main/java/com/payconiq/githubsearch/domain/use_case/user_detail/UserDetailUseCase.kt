package com.payconiq.githubsearch.domain.use_case.user_detail

import com.payconiq.githubsearch.common.Resource
import com.payconiq.githubsearch.data.remote.dto.UserDto
import com.payconiq.githubsearch.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserDetailUseCase @Inject constructor(private val mainRepository: MainRepository) {


    operator fun invoke(name: String): Flow<Resource<UserDto>> = flow {

        try {
            emit(Resource.Loading<UserDto>())
            val detail = mainRepository.getUserDetail(name)
            emit(Resource.Success<UserDto>(detail))

        } catch (e: HttpException) {
            emit(Resource.Error<UserDto>(e.localizedMessage ?: " unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error<UserDto>("Check your internet connection."))
        }

    }
}
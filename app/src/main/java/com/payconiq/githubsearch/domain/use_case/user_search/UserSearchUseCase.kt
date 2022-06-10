package com.payconiq.githubsearch.domain.use_case.user_search

import com.payconiq.githubsearch.common.Resource
import com.payconiq.githubsearch.data.remote.dto.SearchUserDto
import com.payconiq.githubsearch.data.remote.dto.toSearchUser
import com.payconiq.githubsearch.domain.model.SearchUser
import com.payconiq.githubsearch.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserSearchUseCase @Inject constructor(private val mainRepository: MainRepository) {

    operator fun invoke(name: String): Flow<Resource<SearchUser>> = flow {


        // in case we wanna check if empty input  and make an emit
        /*  if (name.isBlank()) {
              emit(Resource.Error<SearchUser>("Oops empty input"))
              return@flow
          }*/


        try {
            emit(Resource.Loading<SearchUser>())
            val search = mainRepository.searchUser(name).toSearchUser()
            emit(Resource.Success<SearchUser>(search))

        } catch (e: HttpException) {
            emit(Resource.Error<SearchUser>(e.localizedMessage ?: " unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error<SearchUser>("Check your internet connection."))
        }

    }
}
package com.payconiq.githubsearch.di

import com.payconiq.githubsearch.common.Constants
import com.payconiq.githubsearch.data.remote.GithubSearchApi
import com.payconiq.githubsearch.data.remote.GithubSearchHelper
import com.payconiq.githubsearch.data.remote.GithubSearchImpl
import com.payconiq.githubsearch.data.repository.MainRepositoryImpl
import com.payconiq.githubsearch.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGithubApi(): GithubSearchApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubSearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGithubHelper(apiHelper: GithubSearchImpl): GithubSearchHelper = apiHelper

    @Provides
    @Singleton
    fun provideMainRepository(api: GithubSearchImpl): MainRepository {
        return MainRepositoryImpl(api)
    }

}
package com.nelsonalmendra.movies.api

import com.nelsonalmendra.movies.model.Movie
import com.nelsonalmendra.movies_app.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("/")
    suspend fun getMovies(
        @Query("apikey") apikey: String = BuildConfig.OMDB_API_KEY,
        @Query("i") i: String = "tt0139654"
    ): Movie

    companion object {
        private const val BASE_URL = "https://www.omdbapi.com/"

        fun create(): MoviesService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesService::class.java)
        }
    }
}
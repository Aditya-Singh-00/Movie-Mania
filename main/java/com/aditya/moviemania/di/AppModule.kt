package com.aditya.moviemania.di

import com.aditya.moviemania.data.data_source.service.MovieService
import com.aditya.moviemania.data.data_source.service.MovieServiceImpl
import com.aditya.moviemania.data.repository.MovieRepositoryImpl
import com.aditya.moviemania.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieService(): MovieService {
        return MovieServiceImpl(
            client = HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    val json = Json { ignoreUnknownKeys = true }
                    serializer = KotlinxSerializer(json)
                }
            }
        )
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieService: MovieService): MovieRepository {
        return MovieRepositoryImpl(movieService)
    }

}
package com.codeenemy.assignmentapp.di

import com.codeenemy.assignmentapp.data.repositories.CharactersRepository
import com.codeenemy.assignmentapp.network.ApiService
import com.codeenemy.assignmentapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
/**
 * [AppModule] provides Dagger Hilt modules for dependency injection in the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides the base URL for the Star Wars API.
     *
     * @return The base URL as a [String].
     */
    @Singleton
    @Provides
    fun providesBaseUrl(): String {
        return BASE_URL
    }

    /**
     * Provides an instance of [HttpLoggingInterceptor] for logging HTTP requests and responses.
     *
     * @return The logging interceptor.
     */
    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    /**
     * Provides a Gson converter factory for parsing JSON responses.
     *
     * @return The Gson converter factory.
     */
    @Singleton
    @Provides
    fun providesConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    /**
     * Provides an OkHttpClient with specified configurations.
     *
     * @param httpLoggingInterceptor The logging interceptor.
     * @return The configured OkHttpClient.
     */
    @Singleton
    @Provides
    fun providesOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    /**
     * Provides a Retrofit instance with the specified base URL, converter factory, and OkHttpClient.
     *
     * @param baseUrl The base URL of the Star Wars API.
     * @param converterFactory The Gson converter factory.
     * @param okHttpClient The OkHttpClient.
     * @return The configured Retrofit instance.
     */
    @Singleton
    @Provides
    fun providesRetrofit(
        baseUrl: String,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)

        return retrofit.build()
    }

    /**
     * Provides an instance of the Star Wars API service.
     *
     * @param retrofit The Retrofit instance.
     * @return The API service.
     */
    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    /**
     * Provides an instance of the [CharactersRepository] for managing character data.
     *
     * @param apiService The Star Wars API service.
     * @return The characters repository.
     */
    @Singleton
    @Provides
    fun providesCharactersRepository(apiService: ApiService): CharactersRepository {
        return CharactersRepository(apiService)
    }
}

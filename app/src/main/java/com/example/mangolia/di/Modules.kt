package com.example.mangolia.di

import com.example.mangolia.network.ApiInterface
import com.example.mangolia.repository.MainRepo
import com.example.mangolia.utils.retrofitBuilder
import com.example.mangolia.utils.retrofitHttpClient
import com.example.mangolia.vewmodel.DashBoardViewModel
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule: Module = module {
    viewModel { DashBoardViewModel(repository = get()) }

}
val apiModule: Module = module {
    single(createdAtStart = false) { get<Retrofit>().create(ApiInterface::class.java)}
}

val repositoryModule = module {
    single { MainRepo(get()) }
}

val retrofitModule = module {
    single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }
    single { GsonBuilder().create() }
    single { retrofitHttpClient() }
    single { retrofitBuilder() }
    single {
        Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().apply {
                header("Accept", "application/json")
            }.build())
        }
    }
}


package com.tw4gamers.mvrxk.koin.koin

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.tw4gamers.mvrxk.BuildConfig
import com.tw4gamers.mvrxk.koin.service.MarketService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit


val serviceModule = module {

  single { createOkHttpClient() }
  single { provideObjectMapper() }

  single<MarketService> {
    createWebService(get(), get())
  }

}

object Properties {
  const val SERVER_URL = "https://api.cobinhood.com/"
}

fun createOkHttpClient(): OkHttpClient {
  val httpClient = OkHttpClient.Builder()
  val logLevelForDebug = HttpLoggingInterceptor.Level.BASIC
  return httpClient.connectTimeout(5L, TimeUnit.SECONDS)
      .readTimeout(5L, TimeUnit.SECONDS)
      .addInterceptor(
          HttpLoggingInterceptor().setLevel(
              if (BuildConfig.DEBUG)
                logLevelForDebug
              else
                HttpLoggingInterceptor.Level.NONE
          )
      ).build()
}

fun provideObjectMapper(): ObjectMapper {
  return ObjectMapper()
      .registerModule(KotlinModule())
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true)
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, objectMapper: ObjectMapper): T {
  val retrofit = Retrofit.Builder()
      .baseUrl(Properties.SERVER_URL)
      .client(okHttpClient)
      .addConverterFactory(JacksonConverterFactory.create(objectMapper))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
  return retrofit.create(T::class.java)
}

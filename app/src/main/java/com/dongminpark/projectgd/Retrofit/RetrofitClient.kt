package com.dongminpark.projectgd.Retrofit

import android.util.Log
import com.dongminpark.projectgd.Utils.Constants.TAG
import com.dongminpark.projectgd.Utils.USER
import com.dongminpark.projectgd.Utils.isJsonArray
import com.dongminpark.projectgd.Utils.isJsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// 싱글턴
object RetrofitClient {
    // 레트로핏 클라이언트 선언

    private var retrofitClient: Retrofit? = null
    //private lateinit var retrofitClient: Retrofit



    fun getClient(baseUrl: String): Retrofit?{
        Log.d(TAG, "RetrofitClient - getClient() called")

        // okhttp 인스턴스 생성
        val client = OkHttpClient.Builder()

        // 로그를 찍기 위해 로깅 인터셉터 설정
        val loggingIntercepter = HttpLoggingInterceptor { message ->
            Log.d(TAG, "RetrofitClient - log() called / message: $message")

            when {
                message.isJsonObject() ->
                    Log.d(TAG, JSONObject(message).toString(4))
                message.isJsonArray() ->
                    Log.d(TAG, JSONObject(message).toString(4))
                else -> {
                    try {
                        Log.d(TAG, JSONObject(message).toString(4))
                    } catch (e: Exception) {
                        Log.d(TAG, message)
                    }
                }
            }
        }

        loggingIntercepter.setLevel(HttpLoggingInterceptor.Level.BODY)

        // 위에서 설정한 로깅 인터셉터를 okhttp 클라이언트에 추가
        client.addInterceptor(loggingIntercepter)

        
        // 기본 파라미터 인터셉터 설정 -> 이걸 손봐서 헤더에 넣는걸로 수정.
//        val baseParameterInterceptor: Interceptor = (object : Interceptor{
//            override fun intercept(chain: Interceptor.Chain): Response {
//                Log.d(TAG, "REtrofitClient - intercept() called")
//                // 오리지날 리퀘스트
//                val originalREquest = chain.request()
//
//                // 쿼리 파라메터 추가하기
//                val addedUrl = originalREquest.url.newBuilder().addQueryParameter("client_id", API.CLIENT_ID).build()
//                val finalRequest = originalREquest.newBuilder().url(addedUrl).method(originalREquest.method, originalREquest.body).build()
//
//                val response = chain.proceed(finalRequest)
//
//                if (response.code != 200){
//                    //Toast.makeText()
//                    //에러 메세지
//                    Log.d(TAG, "error code ${response.code}")
//                }else
//                    Log.d(TAG, "success code ${response.code}")
//                return response
//            }
//        })


        // 엑세스 토큰을 헤더에 넣는 방법
        val baseParameterInterceptor: Interceptor = (object : Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d(TAG, "RetrofitClient - intercept() called")

                val newRequest = chain.request().newBuilder().addHeader("Authorization", "Bearer ${USER.ACCESS_TOKEN}").build()
                val newResponse = chain.proceed(newRequest)

                return newResponse
            }
        })

        // 위에서 설정한 기본파라메터 인터셉터를 okhttp 클라이언트에 추가
        client.addInterceptor(baseParameterInterceptor)



        // 커넥션 타임아웃
        client.connectTimeout(1000, TimeUnit.SECONDS)
        client.readTimeout(1000, TimeUnit.SECONDS)
        client.writeTimeout(1000, TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)



        if (retrofitClient == null){

            // 레트로핏 빌더를 통해 인스턴스 생성
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build()) // 위에서 설정한 클라이언트로 레트로핏 클라이언트를 설정
                .build()
        }

        return retrofitClient
    }

}
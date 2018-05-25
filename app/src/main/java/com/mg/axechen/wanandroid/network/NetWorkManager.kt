package com.mg.axechen.wanandroid.network

import com.mg.axechen.wanandroid.network.converter.GsonConverterFactory
import com.mg.axechen.wanandroid.network.interceptor.AddCookieInterceptor
import com.mg.axechen.wanandroid.network.interceptor.GetCookieInterceptor
import network.request.Request
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Created by AxeChen on 2018/3/19.
 *
 * 网络请求管理类
 */
class NetWorkManager private constructor() {

    companion object {
        //声明一些静态的属性，方法
        var mManger: NetWorkManager? = null
        var mRetrofit: retrofit2.Retrofit? = null
        var mRequest: Request? = null

        // 初始化NetWorkManager单例
        fun getInstance(): NetWorkManager {
            if (mManger == null) {
                //NetWorkManager::class是NetWorkManager这个kt类型，反射
                synchronized(NetWorkManager::class) {
                    if (mManger == null) {
                        mManger = NetWorkManager()
                    }
                }
            }
            return mManger!!//用“!!”表示不能为空
        }
    }

    fun getRequest(): Request? {//返回Request，这个Request可以为Null
        if (mRequest == null) {
            synchronized(Request::class) {
                mRequest = mRetrofit?.create(Request::class.java)
            }
        }
        return mRequest
    }

    fun init() {

        // 初始化OKHTTP
        val client: okhttp3.OkHttpClient.Builder = okhttp3.OkHttpClient.Builder().apply {
            addInterceptor(okhttp3.logging.HttpLoggingInterceptor())
            addInterceptor(AddCookieInterceptor())
            addInterceptor(GetCookieInterceptor())
        }

        // 初始化Retrofit
        mRetrofit = retrofit2.Retrofit.Builder().apply {
            client(client.build())
            baseUrl(Request.HOST)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())


        }.build()
    }


}
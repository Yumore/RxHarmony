package com.nathaniel.rxharmony.reflex;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class ApiRetrofit {
    private static final int DEFAULT_TIMEOUT = 15;
    private static ApiRetrofit apiRetrofit;
    public String baseUrl = BaseContent.BASE_URL;
    private Retrofit retrofit;
    private ApiService apiService;
    private Gson gson;

    public ApiRetrofit() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder
//                .cookieJar(new CookieManger(MyApplication.getContext()))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static ApiRetrofit getInstance() {
        if (apiRetrofit == null) {
            synchronized (Object.class) {
                if (apiRetrofit == null) {
                    apiRetrofit = new ApiRetrofit();
                }
            }
        }
        return apiRetrofit;
    }

    /**
     * 增加后台返回""和"null"的处理,如果后台返回格式正常
     * 1.int=>0
     * 2.double=>0.00
     * 3.long=>0L
     * 4.String=>""
     *
     * @return
     */
    public Gson buildGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(Integer.class, new IntegerDefaultAdapter())
                    .registerTypeAdapter(int.class, new IntegerDefaultAdapter())
                    .registerTypeAdapter(Double.class, new DoubleDefaultAdapter())
                    .registerTypeAdapter(double.class, new DoubleDefaultAdapter())
                    .registerTypeAdapter(Long.class, new LongDefaultAdapter())
                    .registerTypeAdapter(long.class, new LongDefaultAdapter())
                    .registerTypeAdapter(String.class, new StringNullAdapter())
                    .create();
        }
        return gson;
    }

    public ApiService getApiService() {
        return apiService;
    }
}

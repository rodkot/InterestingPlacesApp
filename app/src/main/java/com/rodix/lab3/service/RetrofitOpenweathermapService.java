package com.rodix.lab3.service;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rodix.lab3.Constant;
import com.rodix.lab3.api.GraphhopperServiceApi;
import com.rodix.lab3.api.OpenweathermapServiceApi;
import com.rodix.lab3.dto.ModelWeatherPlaceDto;
import com.rodix.lab3.mapper.ModelWeatherPlaceMapperImpl;
import com.rodix.lab3.model.ModelGeoPlace;
import com.rodix.lab3.model.ModelWeatherPlace;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RetrofitOpenweathermapService {
    private static final String TAG = RetrofitOpenweathermapService.class.getSimpleName();

    private final OpenweathermapServiceApi apiService;
    private static Subscription subscription;
    private static RetrofitOpenweathermapService instance;
    public RetrofitOpenweathermapService() {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Gson gson = new GsonBuilder().create();
        Interceptor interceptor = new Interceptor() {
            @NonNull
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("appid", Constant.API_KEY_OPENWEATHERMAP).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL_OPENWEATHERMAP)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .client(client)
                .build();

        apiService = retrofit.create(OpenweathermapServiceApi.class);

    }

    public static RetrofitOpenweathermapService getInstance() {
        if (instance == null) {
            instance = new RetrofitOpenweathermapService();
        }
        return instance;
    }

    public Observable<ModelWeatherPlace> getWeatherPlace(Double lat, Double lon){
        return apiService.getWeatherPlace(lat,lon,"metric","ru")
                .map(modelWeatherPlaceDto -> {
            ModelWeatherPlaceMapperImpl mapper = new ModelWeatherPlaceMapperImpl();
           return mapper.mapToModel(modelWeatherPlaceDto);
        });
    }
}

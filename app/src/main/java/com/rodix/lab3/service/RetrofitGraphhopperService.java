package com.rodix.lab3.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rodix.lab3.Constant;
import com.rodix.lab3.mapper.ModelGeoPlaceMapperImpl;
import com.rodix.lab3.api.GraphhopperServiceApi;
import com.rodix.lab3.dto.ModelGeoDto;
import com.rodix.lab3.model.ModelGeoPlace;

import java.util.List;
import java.util.stream.Collectors;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

public class RetrofitGraphhopperService {

    private final GraphhopperServiceApi apiService;
    private static RetrofitGraphhopperService instance;

    public RetrofitGraphhopperService() {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Gson gson = new GsonBuilder().create();
        Interceptor interceptor = chain -> {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder().addQueryParameter("key", Constant.API_KEY_GRAPHHOOPPER).build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL_GRAPHHOOPPER)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .client(client)
                .build();
        apiService = retrofit.create(GraphhopperServiceApi.class);
    }

    public static RetrofitGraphhopperService getInstance() {
        if (instance == null) {
            instance = new RetrofitGraphhopperService();
        }
        return instance;
    }

    public Observable<List<ModelGeoPlace>> getListPlace(String query, String lang) {
        ModelGeoPlaceMapperImpl modelGeoPlaceMapper = new ModelGeoPlaceMapperImpl();
        return apiService.getGeoPlace(query, lang, 20)
                .map(ModelGeoDto::getPlaceArray)
                .map(modelGeoPlaceDtos -> modelGeoPlaceDtos
                        .stream().
                        map(modelGeoPlaceMapper::mapToModel)
                        .collect(Collectors.toList()));
    }
}

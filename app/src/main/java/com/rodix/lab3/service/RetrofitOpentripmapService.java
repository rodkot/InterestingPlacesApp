package com.rodix.lab3.service;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rodix.lab3.Constant;
import com.rodix.lab3.api.GraphhopperServiceApi;
import com.rodix.lab3.api.OpentripmapServiceApi;
import com.rodix.lab3.dto.ModelInterestingPlaceDto;
import com.rodix.lab3.mapper.ModelInterestingPlaceMapper;
import com.rodix.lab3.model.ModelGeoPlace;
import com.rodix.lab3.model.ModelInterestingPlace;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

public class RetrofitOpentripmapService {
    private static final String TAG = RetrofitGraphhopperService.class.getSimpleName();
    private final OpentripmapServiceApi apiService;
    private static RetrofitOpentripmapService instance;

    public RetrofitOpentripmapService() {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Gson gson = new GsonBuilder().create();
        Interceptor interceptor = new Interceptor() {
            @NonNull
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("apikey", Constant.API_KEY_OPENTRIPMAP).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL_OPENTRIPMAP)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .client(client)
                .build();
        apiService = retrofit.create(OpentripmapServiceApi.class);

    }

    public static RetrofitOpentripmapService getInstance() {
        if (instance == null) {
            instance = new RetrofitOpentripmapService();
        }
        return instance;
    }

    public Observable<List<ModelInterestingPlace>> getListInterestingPlacesAroundPlace(ModelGeoPlace place) {
        return apiService
                .getInterestingPlace("ru", 100000.2, place.getLng(), place.getLat(), "json", 100,"wikidata","interesting_places")
                .map(mainInfoDtos -> mainInfoDtos.stream().map(mainInfoDto -> {
                    ModelInterestingPlaceDto modelInterestingPlaceDto = new ModelInterestingPlaceDto(mainInfoDto, null);
                    ModelInterestingPlaceMapper mapper = new ModelInterestingPlaceMapper();
                    return mapper.mapToModel(modelInterestingPlaceDto);
                }).collect(Collectors.toList()));
    }

    public Observable<ModelInterestingPlace> getDescriptionInterestingPlace(ModelInterestingPlace place) {
        return apiService.getDescriptionPlace("ru", place.getXid()).map(descriptionDto -> {
            ModelInterestingPlaceDto modelInterestingPlaceDto = new ModelInterestingPlaceDto(new ModelInterestingPlaceDto.MainInfoDto(place.getXid(), place.getName()), descriptionDto);
            ModelInterestingPlaceMapper mapper = new ModelInterestingPlaceMapper();
            return mapper.mapToModel(modelInterestingPlaceDto);
        });
    }

}

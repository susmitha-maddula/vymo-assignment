package com.vymodemo.example.application;

import android.app.Application;
import com.vymodemo.example.constants.VymoConstants;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class VymoDemoApplication extends Application {

  private Retrofit retrofit;
  private static VymoDemoApplication mInstance;

  @Override
  public void onCreate() {
    super.onCreate();
    mInstance = this;
  }

  public static VymoDemoApplication getInstance() {
    return mInstance;
  }

  public Retrofit getRetrofit() {
    if (retrofit == null) {
      initRetrofit();
    }
    return retrofit;
  }

  private void initRetrofit() {
    retrofit = new Retrofit.Builder()
        .baseUrl(VymoConstants.BASE_URL)
        .addConverterFactory(JacksonConverterFactory.create())
        .build();
  }
}

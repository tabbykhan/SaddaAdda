package com.imuons.saddaadda.retrofit;


import com.imuons.saddaadda.EntityClass.LoginEntity;
import com.imuons.saddaadda.EntityClass.RegitrationEntity;
import com.imuons.saddaadda.responseModel.CommonResponse;
import com.imuons.saddaadda.responseModel.LoginResponseModel;
import com.imuons.saddaadda.responseModel.RandomUserIdResponse;
import com.imuons.saddaadda.responseModel.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
/*
 * Created by Tabish on 19-05-2020.
 */
public interface AppService {

    @POST("login")
    Call<LoginResponseModel> LoginApi(
            @Body LoginEntity loginEntity
    );

    @POST("register")
    Call<RegisterResponse> RegisterApi(
            @Body RegitrationEntity registerEntity
    );

    @GET("generate-userid")
    Call<RandomUserIdResponse> GetRendomNumber();
}

package com.imuons.saddaadda.retrofit;


import com.imuons.saddaadda.EntityClass.LoginEntity;
import com.imuons.saddaadda.responseModel.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
/*
 * Created by Tabish on 19-05-2020.
 */
public interface AppService {
//    @POST("register")
//    Call<RegisterResponse> RegisterApi(
//            @Body RegitrationEntity registerEntity
//    );
//

    @POST("login")
    Call<LoginResponseModel> LoginApi(
            @Body LoginEntity loginEntity
    );
}

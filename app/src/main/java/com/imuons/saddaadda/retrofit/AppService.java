package com.imuons.saddaadda.retrofit;


import com.imuons.saddaadda.EntityClass.ChangePinEntity;
import com.imuons.saddaadda.EntityClass.LoginEntity;
import com.imuons.saddaadda.EntityClass.OtpEnitity;
import com.imuons.saddaadda.EntityClass.PinEntitiy;
import com.imuons.saddaadda.EntityClass.RegitrationEntity;
import com.imuons.saddaadda.EntityClass.ResetPasswordEntity;
import com.imuons.saddaadda.EntityClass.ResetPinEntity;
import com.imuons.saddaadda.EntityClass.SathKaDamEntity;
import com.imuons.saddaadda.EntityClass.UpdateProfileEntity;
import com.imuons.saddaadda.View.ForgetPassword;
import com.imuons.saddaadda.responseModel.CommonResponse;
import com.imuons.saddaadda.responseModel.DashboardResponse;
import com.imuons.saddaadda.responseModel.ForgetPasswordResponse;
import com.imuons.saddaadda.responseModel.LoginResponseModel;
import com.imuons.saddaadda.responseModel.OptResponse;
import com.imuons.saddaadda.responseModel.PinResponse;
import com.imuons.saddaadda.responseModel.ProfileGetResponse;
import com.imuons.saddaadda.responseModel.RandomUserIdResponse;
import com.imuons.saddaadda.responseModel.RegisterResponse;
import com.imuons.saddaadda.responseModel.SathKaDamResponse;
import com.imuons.saddaadda.responseModel.UpdateProfileResponse;
import com.imuons.saddaadda.responseModel.VerifyUserResponse;

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

    @GET("user/get-profile-info")
    Call<ProfileGetResponse> Get_ProfileInfo();

    @GET("user/user-details")
    Call<DashboardResponse> Get_DashboardInfo();

    @POST("user/user-update")
    Call<UpdateProfileResponse> UPDATE_PROFILE_RESPONSE_CALL(
            @Body UpdateProfileEntity updateProfileEntity
    );

    @POST("user/seven_up_down")
    Call<SathKaDamResponse> SATH_KA_DAM_RESPONSE_Call(
            @Body SathKaDamEntity sathKaDamEntity
    );

    @POST("newreset-password")
    Call<ForgetPasswordResponse> FORGET_PASSWORD_CALL(
            @Body ResetPasswordEntity resetPasswordEntity
    );

    @POST("user/verify-pin")
    Call<PinResponse> pinApi(
            @Body PinEntitiy resetPasswordEntity
    );

    @POST("send-otp")
    Call<OptResponse> SendOTP_FOR_PIN(
            @Body OtpEnitity resetPasswordEntity
    );

    @POST("reset-pin")
    Call<PinResponse> ResetPinApi(
            @Body ResetPinEntity resetPasswordEntity
    );
    @POST("user/change-pin")
    Call<PinResponse> ChangePinApi(
            @Body ChangePinEntity resetPasswordEntity
    );
    @POST("checkuserexist")
    Call<VerifyUserResponse> checkUserApi (
            @Body OtpEnitity resetPasswordEntity
    );
    @POST("user/all-transactions-report")
    Call<VerifyUserResponse> REPORT_CALL (
            @Body OtpEnitity resetPasswordEntity
    );
    @POST("user/buy-balance")
    Call<BuyCoinResponse> buyCoin(
            @Body BuyCoinEntity buyCoinEntity
    );


}

package com.imuons.saddaadda.retrofit;


import com.imuons.saddaadda.DataModel.DetailsTicketResponseModel;
import com.imuons.saddaadda.DataModel.ShatakProductResponseModel;
import com.imuons.saddaadda.DataModel.TransactionSlipResponseModel;
import com.imuons.saddaadda.EntityClass.BuyCoinEntity;
import com.imuons.saddaadda.EntityClass.ChangePasswordEntity;
import com.imuons.saddaadda.EntityClass.ChangePinEntity;
import com.imuons.saddaadda.EntityClass.ChatEntity;
import com.imuons.saddaadda.EntityClass.CompleteSlotEntity;
import com.imuons.saddaadda.EntityClass.FundTransEntity;
import com.imuons.saddaadda.EntityClass.LoginEntity;
import com.imuons.saddaadda.EntityClass.OtpEnitity;
import com.imuons.saddaadda.EntityClass.PinEntitiy;
import com.imuons.saddaadda.EntityClass.RegitrationEntity;
import com.imuons.saddaadda.EntityClass.ResetPasswordEntity;
import com.imuons.saddaadda.EntityClass.ResetPinEntity;
import com.imuons.saddaadda.EntityClass.SaddaXEntity;
import com.imuons.saddaadda.EntityClass.SaddaXTopUp;
import com.imuons.saddaadda.EntityClass.SathKaDamEntity;
import com.imuons.saddaadda.EntityClass.SellCoinEntity;
import com.imuons.saddaadda.EntityClass.SendMessage;
import com.imuons.saddaadda.EntityClass.TicketEntity;
import com.imuons.saddaadda.EntityClass.UpdateProfileEntity;
import com.imuons.saddaadda.EntityClass.WinningNumberEntity;
import com.imuons.saddaadda.responseModel.BuyCoinResponse;
import com.imuons.saddaadda.responseModel.BuyHistoryResponse;
import com.imuons.saddaadda.responseModel.ChangePasswordResponse;
import com.imuons.saddaadda.responseModel.CoinsResponseModel;
import com.imuons.saddaadda.responseModel.CommonResponseModel;
import com.imuons.saddaadda.responseModel.CompleteSlotResponse;
import com.imuons.saddaadda.responseModel.DashboardResponse;
import com.imuons.saddaadda.responseModel.FetchChatResponse;
import com.imuons.saddaadda.responseModel.ForgetPasswordResponse;
import com.imuons.saddaadda.responseModel.FundTransferResponse;
import com.imuons.saddaadda.responseModel.LoginResponseModel;
import com.imuons.saddaadda.responseModel.OptResponse;
import com.imuons.saddaadda.responseModel.PinResponse;
import com.imuons.saddaadda.responseModel.ProfileGetResponse;
import com.imuons.saddaadda.responseModel.RandomUserIdResponse;
import com.imuons.saddaadda.responseModel.RegisterResponse;
import com.imuons.saddaadda.responseModel.ReportResponse;
import com.imuons.saddaadda.responseModel.SaddaXResponse;
import com.imuons.saddaadda.responseModel.SaddaxReportResponse;
import com.imuons.saddaadda.responseModel.SathKaDamResponse;
import com.imuons.saddaadda.responseModel.SellHistoryReport;
import com.imuons.saddaadda.responseModel.SellResponseModel;
import com.imuons.saddaadda.responseModel.TicketResponse;
import com.imuons.saddaadda.responseModel.TransReportResponse;
import com.imuons.saddaadda.responseModel.UpcomingSlotResponse;
import com.imuons.saddaadda.responseModel.UpdateProfileResponse;
import com.imuons.saddaadda.responseModel.VerifyUserResponse;
import com.imuons.saddaadda.responseModel.WinningDateResponse;
import com.imuons.saddaadda.responseModel.WinningNumberResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
    Call<VerifyUserResponse> checkUserApi(
            @Body OtpEnitity resetPasswordEntity
    );

    @POST("user/all-transactions-report")
    Call<ReportResponse> REPORT_CALL(
    );

    @FormUrlEncoded
    @POST("user/sell-report")
    Call<SellHistoryReport> SellREPORT_CALL(
            @FieldMap Map<String, String> loginMap
    );

    @FormUrlEncoded
    @POST("user/buy-report")
    Call<BuyHistoryResponse> BuyREPORT_CALL(
            @FieldMap Map<String, String> loginMap
    );

    @POST("user/buy-balance")
    Call<BuyCoinResponse> buyCoin(
            @Body BuyCoinEntity buyCoinEntity
    );


    //tabish
    @POST("user/sell-wallet-balance")
    Call<SellResponseModel> sellCoin(
            @Body SellCoinEntity sellCoinEntity
    );

    //tabish
    @GET("user/get-wallet-balance")
    Call<CoinsResponseModel> get_Coins_Details();

    //tabish
    @POST("user/change-newpassword")
    Call<ChangePasswordResponse> change_password(
            @Body ChangePasswordEntity changePasswordEntity
    );

    //tabish
    @POST("user/zero_to_nine")
    Call<SaddaXResponse> SADDA_X_RESPONSE_CALL(
            @Body SaddaXEntity saddaXEntity
    );

    //tabish
    @POST("user/get-zerotonine-topup")
    Call<SaddaxReportResponse> SADDAX_REPORT_RESPONSE_CALL(
            @Body SaddaXTopUp saddaXTopUp);


    //tabish
    @POST("user/link-report")
    Call<TicketResponse> TICKET_RESPONSE_CALL(
            @Body TicketEntity ticketEntity
    );

    @POST("user/fetch-message")
    Call<FetchChatResponse> getChatList(
            @Body ChatEntity chatEntity
    );
    //azhar

    @FormUrlEncoded
    @POST("user/all-transactions-report")
    Call<TransReportResponse> BuyTransREPORT_CALL(
            @FieldMap Map<String, String> loginMap
    );

    //tabish
    @GET("user/upcoming_zero_to_nine_slots")
    Call<UpcomingSlotResponse> UPCOMING_SLOT_RESPONSE_CALL();

    //Rahul
    @POST("user/pay-on-link")
    Call<DetailsTicketResponseModel> GetPayPnLink(@Body Map<String, Object> map);

    @POST("user/confirmlink")
    Call<CommonResponseModel> GetConfirmLink(@Body Map<String, Object> map);

    @POST("user/rejectlink")
    Call<CommonResponseModel> GetRejectLink(@Body Map<String, Object> map);

    @POST("user/send-sms")
    Call<CommonResponseModel> GetSendSms(@Body Map<String, Object> map);

    @POST("user/transaction-slip")
    Call<TransactionSlipResponseModel> GetSlip(@Body Map<String, Object> map);

    @POST("user/send-message")
    Call<PinResponse> sendMessage(
            @Body SendMessage sendMessage
    );

    //tabish
    @POST("user/zero_to_nine_completed_slots")
    Call<CompleteSlotResponse> COMPLETE_SLOT_RESPONSE_CALL(
            @Body CompleteSlotEntity completeSlotEntity);

    //tabish
    @POST("user/zero_to_nine_leadership")
    Call<WinningNumberResponse> WINNING_NUMBER_RESPONSE_CALL(
            @Body WinningNumberEntity winningNumberEntity);

    //tabish
    @GET("user/zero_to_nine_last_winning_date")
    Call<WinningDateResponse> WINNING_DATE_RESPONSE_CALL();


    //tabish
    @POST("user/transfer-fund")
    Call<FundTransferResponse> FUND_TRANSFER_RESPONSE_CALL(
            @Body FundTransEntity fundTransEntity);


    @GET("user/get-products-damdarshatak")
    Call<ShatakProductResponseModel> GetShatakProduct();

    @POST("user/damdar_shatak")
    Call<CommonResponseModel> DumdarShatak(@Body Map<String, Object> param);

    @POST("user/get-damdatshatak-topup")
    Call<SaddaxReportResponse> GetShtakReport(@Body  SaddaXTopUp saddaXTopUp);

    @POST("user/upcoming_damdar_shatak_slots")
    Call <UpcomingSlotResponse>GetShatakUpcomingSolt(@Body Map<String, Object> paparam);

    @GET("user/damdar_shatak_last_winning_date")
    Call<WinningDateResponse> ShatakWinningDate();

    @POST("user/damdar_shatak_completed_slots")
    Call<CompleteSlotResponse> ShatakCompleteSolts(
            @Body CompleteSlotEntity completeSlotEntity);

    @POST("user/damdar_shatak_leadership")
    Call<WinningNumberResponse> ShatakLeadership(
            @Body WinningNumberEntity winningNumberEntity);

}

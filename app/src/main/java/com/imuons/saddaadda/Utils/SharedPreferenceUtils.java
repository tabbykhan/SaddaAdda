package com.imuons.saddaadda.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.imuons.saddaadda.responseModel.DashboardResponse;
import com.imuons.saddaadda.responseModel.LoginResponseModel;

import static android.content.Context.MODE_PRIVATE;

/*
 * Created by Tabish on 22-05-2020.
 */

public class SharedPreferenceUtils {
    static String PREFERENCE_NAME = "ProductPref";
    static String LOGIN_OBJECT = "login_object";
    static String DASHBOARD_OBJECT = "dashboard_object";
    static String USER_NAME = "user_name";
    static String Pin = "pin";
    static String USER_PPASSWORD = "user_password";
    static String USER_ID = "userid";
    static String CartNumber = "cartnumber";
    static String ProuctId = "productid";
    static String ACCESSTOKEN = "accesstoken";
    static String AVA_BAL = "ava_bal";
    static String HISTORY_ID = "history_id";
    static String To_USER_ID = "touserid";
    static String REQ_ID = "requestid";
    static String ID = "id";
    static String Splash = "splash";

    public static void storeLoginObject(LoginResponseModel model, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(model);
        editor.putString(LOGIN_OBJECT, json);
        editor.apply();
    }

    public static LoginResponseModel getLoginObject(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        Gson gson = new Gson();
        String json = prefs.getString(LOGIN_OBJECT, "");
        return gson.fromJson(json, LoginResponseModel.class);
    }

    public static void storeDashboardObject(DashboardResponse model, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(model);
        editor.putString(DASHBOARD_OBJECT, json);
        editor.apply();
    }

    public static DashboardResponse getDashboardObject(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        Gson gson = new Gson();
        String json = prefs.getString(DASHBOARD_OBJECT, "");
        return gson.fromJson(json, DashboardResponse.class);
    }
    public static void storeId(Context context, String id) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(ID, id);
        editor.commit();
    }
    public static String getId(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(ID, null);
    }

    public static void storeReqid(Context context, String requestid) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(REQ_ID, requestid);
        editor.commit();
    }
    public static String getReqid(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(REQ_ID, null);
    }

    public static void storeToUserId(Context context, String touserid) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(To_USER_ID, touserid);
        editor.commit();
    }
    public static String getToUserId(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(To_USER_ID, null);
    }

    public static void storeHistoryId(Context context, String historyid) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(HISTORY_ID, historyid);
        editor.commit();
    }
    public static String getHistoryId(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(HISTORY_ID, null);
    }

    public static void storePin(Context context, String pin) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(Pin, pin);
        editor.commit();
    }
    public static String getPin(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(Pin, null);
    }

    public static void storeCartnumber(Context context, String cartnumber) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(CartNumber, cartnumber);
        editor.commit();
    }
    public static String getCartnumber(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(CartNumber, null);
    }
    public static void storeProductId(Context context, String prouctId) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(ProuctId, prouctId);
        editor.commit();
    }
    public static String getProductId(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(ProuctId, null);
    }

    public static void storeUserName(Context context, String userId) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(USER_NAME, userId);
        editor.commit();
    }

    public static String getUserName(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(USER_NAME, null);
    }

    public static void storePassword(Context context, String password) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(USER_PPASSWORD, password);
        editor.commit();
    }

    public static String getPassword(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(USER_PPASSWORD, null);
    }

    public static void clearPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        preferences.edit().remove(LOGIN_OBJECT).apply();
    }
    public static void clearID(Context context) {
        SharedPreferences preferencess = context.getSharedPreferences(PREFERENCE_NAME, 0);
        preferencess.edit().remove(USER_ID).apply();

    }
    public static void clearAva_bal(Context context) {
        SharedPreferences preferencess = context.getSharedPreferences(PREFERENCE_NAME, 0);
        preferencess.edit().remove(AVA_BAL).apply();

    }
    public static void clearAccess_Token(Context context) {
        SharedPreferences preferencess = context.getSharedPreferences(PREFERENCE_NAME, 0);
        preferencess.edit().remove(ACCESSTOKEN).apply();

    }
    public static void storeAccessToken(Context context, String accesstoken) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(ACCESSTOKEN, accesstoken);
        editor.commit();
    }

    public static String getAccesstoken(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(ACCESSTOKEN, null);
    }

    public static void storeAuthantication(Context context, String advanceWalletBalance) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(AVA_BAL, advanceWalletBalance);
        editor.commit();
    }

    public static String getAuthantication(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(AVA_BAL, null);
    }

    public static void storeSplash(Context context, String splashScreen) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(Splash, splashScreen);
        editor.commit();
    }

    public static String getSplash(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(Splash, "");
    }
}

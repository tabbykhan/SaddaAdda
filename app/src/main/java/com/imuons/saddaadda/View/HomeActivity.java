package com.imuons.saddaadda.View;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.AppUpdateRespponse;
import com.imuons.saddaadda.DataModel.DashboardData;
import com.imuons.saddaadda.EntityClass.LoginEntity;
import com.imuons.saddaadda.EntityClass.OtpEnitity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Service.HomeWatcher;
import com.imuons.saddaadda.Service.MusicService;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.AppUpdateUtils;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.responseModel.DashboardResponse;
import com.imuons.saddaadda.responseModel.LoginResponseModel;
import com.imuons.saddaadda.responseModel.OptResponse;
import com.imuons.saddaadda.responseModel.ProfileGetResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.gotoProfile)
    RelativeLayout gotoProfile;
    @BindView(R.id.txUserId)
    TextView txUserId;
    @BindView(R.id.coin)
    TextView coin;

    @BindView(R.id.coinBuy)
    ImageView coinBuy;

    @BindView(R.id.img_menu)
    ImageView img_menu;

    @BindView(R.id.imDemo)
    Button imDemo;
    private PopupWindow mypopupWindow;
    HomeWatcher mHomeWatcher;
    int selectedType = 0;
    @BindView(R.id.sound)
    ImageView sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);
        setContentView(R.layout.home_new);
        ButterKnife.bind(this);
        setPopUpWindow();
        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mypopupWindow.showAsDropDown(v, -153, 0);

            }
        });
        if (AppCommon.getInstance(this).isDemo().equalsIgnoreCase("Live")) {
            imDemo.setActivated(true);
        } else {
            imDemo.setActivated(false);
        }
        if(AppCommon.getInstance(this).isSound()){
            sound.setActivated(true);
            callSound();
        }
        else {
            sound.setActivated(false);
        }
        if (AppCommon.getInstance(this).getUserObject() == null)
            getUserProfileInfo();

    }

    private void callSound() {
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

        //Start HomeWatcher
        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }

            @Override
            public void onHomeLongPressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        });
        mHomeWatcher.startWatch();
    }

    @OnClick(R.id.sound)
    void soundonOff() {
        if (AppCommon.getInstance(this).isSound()) {
            sound.setActivated(false);
            AppCommon.getInstance(this).setSound(false);
            doUnbindService();
            Intent music = new Intent();
            music.setClass(this, MusicService.class);
            stopService(music);


        }else {
            sound.setActivated(true);
            AppCommon.getInstance(this).setSound(true);
            callSound();
        }
    }

    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder) binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService() {
        bindService(new Intent(this, MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }




    @Override
    protected void onPause() {
        super.onPause();

        //Detect idle screen
        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            //UNBIND music service
            doUnbindService();
            Intent music = new Intent();
            music.setClass(this, MusicService.class);
            stopService(music);
        }catch (Exception e){

        }

    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        GetAppVersion();
        getDashboardInfo();
        if (mServ != null) {
            mServ.resumeMusic();
        }
    }

    private void ShowUpdateDialog(String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_app_update,
                viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.show();

        TextView txtxt_update = (TextView) dialogView.findViewById(R.id.txt_update);
        TextView not_now = (TextView) dialogView.findViewById(R.id.txt_not_now);
        txtxt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser(url);
            }
        });
        not_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        alertDialog.setCancelable(false);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                Log.d("back", "---" + keyCode);
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    finishAffinity();
                }
                return false;
            }
        });
    }

    private void openBrowser(String url) {
        try {
            final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            startActivity(intent);
        } catch (Exception e) {

        }
    }

    private void getDashboardInfo() {
        Log.d("token", AppCommon.getInstance(getApplicationContext()).getToken());
        if (AppCommon.getInstance(getApplicationContext()).isConnectingToInternet(getApplicationContext())) {
           // AppCommon.getInstance(getApplicationContext()).setNonTouchableFlags(HomeActivity.this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getApplicationContext()).getToken());
            //Dialog dialog = ViewUtils.getProgressBar(HomeActivity.this);
            Map<String, String> roiMap = new HashMap<>();
            roiMap.put("mode", AppCommon.getInstance(this).isDemo());
            //Call call = apiService.Get_DashboardInfo(AppCommon.getInstance(this).isDemo());
            Call call = apiService.Get_DashboardInfo(roiMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getApplicationContext()).clearNonTouchableFlags(HomeActivity.this);
                    // dialog.dismiss();
                    DashboardResponse authResponse = (DashboardResponse) response.body();
                    if (authResponse != null) {
                        Log.i("CategoryResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setDashboardData(authResponse.getData());
                            // Toast.makeText(LoginActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(getApplicationContext()).showDialog(HomeActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                      //dialog.dismiss();
                    AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(getApplicationContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }


    private void setDashboardData(DashboardData data) {
        if (AppCommon.getInstance(this).getSesstionId() == data.getSession_id()) {
            txUserId.setText(String.valueOf(data.getUserId()));
            coin.setText(String.valueOf(data.getWalletBalance()));
            AppCommon.getInstance(this).setAccount(Integer.parseInt(data.getWalletBalance()));
            AppCommon.getInstance(this).setID(data.getId());

        } else {
            AppCommon.getInstance(HomeActivity.this).clearPreference();
            startActivity(new Intent(HomeActivity.this, SelectionPage.class));
            finishAffinity();
            Toast.makeText(HomeActivity.this, "Session Expire", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.gotoProfile)
    void goToProfile() {

        startActivity(new Intent(this, ProfileActivity.class));
    }

    @OnClick(R.id.goProfile)
    void goProfile() {

        startActivity(new Intent(this, ProfileActivity.class));
    }

    @OnClick(R.id.more)
    void more() {
        startActivity(new Intent(getApplicationContext(), ActivityMore.class));
    }

    @OnClick(R.id.coinBuy)
    void goToBuyCoin() {

        if (AppCommon.getInstance(this).isDemo().equalsIgnoreCase("Live"))
            startActivity(new Intent(this, BuyCoinActivity.class));
        else
            Toast.makeText(this, getString(R.string.useLive), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.sevenClick)
    public void sevenClick() {
        startActivity(new Intent(this, SevenUpDown.class));
    }

    public void duskaDum(View view) {
        // Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, UpcomingSlotActivity.class));
    }

    public void saddaShatak(View view) {
        // Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), SaddaShatakUpcomingSlotActivity.class));
    }

    public void saddaQuiz(View view) {
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }

    public void chat(View view) {
        startActivity(new Intent(this, ChatActivity.class));
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

   /* @Override
    protected void onResume() {
        super.onResume();

    }*/

    public void ludo_game(View view) {
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }

    public void rummy_game(View view) {
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }

    public void changeMoneyType(boolean isDemo) {
        LoginEntity loginEntity = new LoginEntity(AppCommon.getInstance(this).getUserId(), AppCommon.getInstance(this).getPassword(), "");
        if (isDemo) {
            ServiceGenerator.changeApiBaseUrl("https://www.saddaadda.games/saddaddapanel/api/");

        } else {
            ServiceGenerator.changeApiBaseUrl("https://www.saddaadda.games/saddaddapanel/api/");
        }

        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            final Dialog dialog = ViewUtils.getProgressBar(HomeActivity.this);
            Call call = apiService.LoginApi(loginEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
                    dialog.dismiss();
                    LoginResponseModel authResponse = (LoginResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("LoginResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            AppCommon.getInstance(HomeActivity.this).setToken(authResponse.getData().getAccessToken());
                            AppCommon.getInstance(HomeActivity.this).setSesstionId(authResponse.getData().getSession_id());
                            startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                            finish();
                            Toast.makeText(HomeActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(HomeActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(HomeActivity.this).showDialog(HomeActivity.this, "Server Error");
                        //Toast.makeText(HomeActivity.this, "The user credentials were incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(HomeActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }


    private void setPopUpWindow() {
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.menu_item_view, null);

        LinearLayout profile = view.findViewById(R.id.menu_profile);
        LinearLayout all_Transaction = view.findViewById(R.id.menu_allTransaction);
        LinearLayout withdrawal = view.findViewById(R.id.menu_withdraw);
        LinearLayout sellHistory = view.findViewById(R.id.menu_sellHistory);
        LinearLayout changePassword = view.findViewById(R.id.menu_changePassword);
        LinearLayout changePin = view.findViewById(R.id.menu_changePin);
        LinearLayout logout = view.findViewById(R.id.menu_logout);
        LinearLayout buyHistory = view.findViewById(R.id.menu_buyHistory);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });
        all_Transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ReportActivity.class));
            }
        });
        withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SellCoinActivity.class));
            }
        });
        sellHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SellHistoryReportActivity.class));
            }
        });
        buyHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BuyActivityHistory.class));
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callsendOtp();
                //startActivity(new Intent(getApplicationContext(), ChangePassword.class));
            }
        });
        changePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChangePin.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
        mypopupWindow = new PopupWindow(view, 500, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
    }

    private void showAlertDialog() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(getResources().getString(R.string.app_name));
        adb.setIcon(R.mipmap.ic_launcher_round);
        adb.setMessage("Are you sure you want to Logout?");
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppCommon.getInstance(HomeActivity.this).clearPreference();
                startActivity(new Intent(HomeActivity.this, SelectionPage.class));
                finishAffinity();
                Toast.makeText(HomeActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent());
                // finishAffinity();
            }

        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        adb.show();
    }

    private void callsendOtp() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(HomeActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());
            Call call = apiService.SendOTP_FOR_PIN(new OtpEnitity(AppCommon.getInstance(this).getUserId()));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
                    dialog.dismiss();
                    OptResponse authResponse = (OptResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            Toast.makeText(HomeActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(HomeActivity.this, ChangePassword.class));
                        } else {
                            Toast.makeText(HomeActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(HomeActivity.this).showDialog(HomeActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(HomeActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void GetAppVersion() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            // Dialog dialog = ViewUtils.getProgressBar(HomeActivity.this);
            //AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());
            Map<String, Object> param = new HashMap<>();
            param.put("device_type", "A");
            param.put("version_code", AppUpdateUtils.getAppInstalledVersionCode(getApplicationContext()));
            Call call = apiService.GetAppUpdate(param);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
                    //   dialog.dismiss();
                    AppUpdateRespponse authResponse = (AppUpdateRespponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            if (authResponse.getData() != null) {
                                ShowUpdateDialog(authResponse.getData().getAppLink());
                            }
                        } else {
                            // Toast.makeText(HomeActivity.this, authResponse.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(HomeActivity.this).showDialog(HomeActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    // dialog.dismiss();
                    AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(HomeActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.imDemo)
    void demo() {
        if (AppCommon.getInstance(this).isDemo().equalsIgnoreCase("Live")) {
            AppCommon.getInstance(this).setDemo("Demo");
            imDemo.setActivated(false);
        } else {
            AppCommon.getInstance(this).setDemo("Live");
            imDemo.setActivated(true);
        }
            getDashboardInfo();
    }


    // profile api
    private void getUserProfileInfo() {
        if (AppCommon.getInstance(getApplicationContext()).isConnectingToInternet(getApplicationContext())) {
           // AppCommon.getInstance(getApplicationContext()).setNonTouchableFlags(HomeActivity.this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getApplicationContext()).getToken());
            // Dialog dialog = ViewUtils.getProgressBar(HomeActivity.this);
            Call call = apiService.Get_ProfileInfo();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getApplicationContext()).clearNonTouchableFlags(HomeActivity.this);
                    //dialog.dismiss();
                    ProfileGetResponse authResponse = (ProfileGetResponse) response.body();
                    if (authResponse != null) {
                        Log.i("ResponseProfile::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            AppCommon.getInstance(HomeActivity.this).setUserObject(new Gson().toJson(authResponse.getData()));
                            Long tsLong = System.currentTimeMillis();
                            String ts = tsLong.toString();
                            AppCommon.getInstance(HomeActivity.this).setCurrentTime(ts);
                            // setProfileInfo(authResponse.getData());
                            // Toast.makeText(LoginActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(getApplicationContext()).showDialog(HomeActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    //dialog.dismiss();
                    AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(getApplicationContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }
}


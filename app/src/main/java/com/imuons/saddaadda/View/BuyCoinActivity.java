package com.imuons.saddaadda.View;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.TicketRecordModel;
import com.imuons.saddaadda.DataModel.TransactionDataModel;
import com.imuons.saddaadda.DataModel.TransactionSlipResponseModel;
import com.imuons.saddaadda.EntityClass.BuyCoinEntity;
import com.imuons.saddaadda.EntityClass.TicketEntity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.adapters.TicketAdapter;
import com.imuons.saddaadda.responseModel.BuyCoinResponse;
import com.imuons.saddaadda.responseModel.CommonResponseModel;
import com.imuons.saddaadda.responseModel.TicketResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.MultipartRequest;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyCoinActivity extends AppCompatActivity implements TicketAdapter.ClickEvent {

    public static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int GALLERY_PICTURE_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final int INITIAL_REQUEST_CAMERA = 1338;
    private static final int REQUEST_WRITE_PERMISSION = 786;
    private static final String[] CAMERA_PERM = {Manifest.permission.CAMERA};
    private static final String IMAGE_DIRECTORY_NAME = "PMC";
    private static final int PICK_FILE_REQUEST = 1;
    private final String twoHyphens = "------";
    private final String lineEnd = "\r\n";
    private final String boundary = "WebKitFormBoundary" + System.currentTimeMillis();
    private final String mimeType = "multipart/form-data;boundary=" + boundary;
    @BindView(R.id.submitBtn)
    TextView tv_signup;
    @BindView(R.id.ticket)
    TextView ticket;
    @BindView(R.id.etAmount)
    EditText etAmount;
    @BindView(R.id.recycler_view_ticket)
    RecyclerView recyclerView;
    ArrayList<TicketRecordModel> records;
    TicketAdapter ticketAdapter;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.bottomProgressBar)
    ProgressBar bottomProgressBar;
    @BindView(R.id.ll_confirm_layer)
    LinearLayout ll_confirm_layer;
    @BindView(R.id.ll_reject)
    LinearLayout ll_reject;
    @BindView(R.id.ll_upload)
    LinearLayout ll_upload;
    @BindView(R.id.txt_file_name)
    TextView txt_file_name;
    @BindView(R.id.iv_image_preview)
    ImageView iv_image_preview;
    int offsetLevel = 0;
    private boolean is_layerOpen;
    private TicketRecordModel tickt_model;
    private int REQUEST_WRITE_PERMISSION_FILE_CHOSER = 1000;
    private String selectedFilePath;
    private Uri fileUri;
    private String filePath = "";
    private String cameraFlag;
    private String tag = "BuyCoinActivity";
    private Bitmap bitmapImge;
    private File destination;

    private static File getOutputMediaFile(int type) {
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), IMAGE_DIRECTORY_NAME);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {
        // BEST QUALITY MATCH First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        // Calculate inSampleSize
        // Raw height and width of image
        final int height = 600;
        final int width = 800;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = true;
        int inSampleSize = 1;

        if (height > reqHeight) {
            inSampleSize = Math.round((float) height / (float) reqHeight);
        }

        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth) {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float) width / (float) reqWidth);
        }


        Bitmap bm;
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);

        return bm;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_coin);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(BuyCoinActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(ticketAdapter);

        getTickets(new TicketEntity("10", "0", "0"));
    }

    @OnClick(R.id.txt_confirm_yes)
    void confirmyes() {
        ll_confirm_layer.setVisibility(View.GONE);
        is_layerOpen = false;
        confirmTickt();
    }

    private void confirmTickt() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(BuyCoinActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Map<String, Object> param = new HashMap<>();
            param.put("transaction_id", tickt_model.getTranid());
            param.put("amount", tickt_model.getBuyAmount());
            param.put("commit_id", tickt_model.getCommitId());
            Call call = apiService.GetConfirmLink(param);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    dialog.dismiss();
                    CommonResponseModel buyCoinResponse = (CommonResponseModel) response.body();
                    if (buyCoinResponse != null) {
                        Log.i("ResponseUpdate::", new Gson().toJson(buyCoinResponse));
                        if (buyCoinResponse.getCode() == 200) {
                            Toast.makeText(BuyCoinActivity.this, buyCoinResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            getTickets(new TicketEntity("10", "0", "0"));
                        } else {
                            Toast.makeText(BuyCoinActivity.this, buyCoinResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(BuyCoinActivity.this).showDialog(BuyCoinActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    Log.d("fail rq", t.getMessage());
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(BuyCoinActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.txt_confirm_no)
    void confirmNo() {
        is_layerOpen = false;
        ll_confirm_layer.setVisibility(View.GONE);
    }

    private void setAdapter(List<TicketRecordModel> data) {
        Log.d("ticket arrya", "--data of ticket-" + data);
        TicketAdapter ticketAdapter = new TicketAdapter(this, data, BuyCoinActivity.this);
        recyclerView.setAdapter(ticketAdapter);
    }

    @OnClick(R.id.txt_reject_yes)
    void reject_yes() {
        ll_reject.setVisibility(View.GONE);
        is_layerOpen = false;
        rejectLink();
    }

    private void rejectLink() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(BuyCoinActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Map<String, Object> param = new HashMap<>();
            param.put("transaction_id", tickt_model.getTranid());
            param.put("commit_id", tickt_model.getCommitId());
            Call call = apiService.GetRejectLink(param);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    dialog.dismiss();
                    CommonResponseModel buyCoinResponse = (CommonResponseModel) response.body();
                    if (buyCoinResponse != null) {
                        Log.i("ResponseUpdate::", new Gson().toJson(buyCoinResponse));
                        if (buyCoinResponse.getCode() == 200) {
                            getTickets(new TicketEntity("10", "0", "0"));
                            Toast.makeText(BuyCoinActivity.this, buyCoinResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BuyCoinActivity.this, buyCoinResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(BuyCoinActivity.this).showDialog(BuyCoinActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    Log.d("fail rq", t.getMessage());
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(BuyCoinActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.txt_reject_no)
    void reject_no() {
        is_layerOpen = false;
        ll_reject.setVisibility(View.GONE);
    }

    public void callapi(int position) {
        offsetLevel = offsetLevel + 1;
        //callgetLevelView(position - 1, records.size(), 0);
        bottomProgressBar.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.submitBtn)
    void submitCall() {
        String amount = etAmount.getText().toString().trim();

        if (amount.isEmpty()) {
            etAmount.setError("Please enter Amount");
        } else
            callLBuyCoinApi(new BuyCoinEntity(amount));

    }

    private void callLBuyCoinApi(BuyCoinEntity buyCoinEntity) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(BuyCoinActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.buyCoin(buyCoinEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    dialog.dismiss();
                    BuyCoinResponse buyCoinResponse = (BuyCoinResponse) response.body();
                    if (buyCoinResponse != null) {
                        Log.i("ResponseUpdate::", new Gson().toJson(buyCoinResponse));
                        if (buyCoinResponse.getCode() == 200) {
                            Toast.makeText(BuyCoinActivity.this, buyCoinResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            etAmount.setText("");
                        } else {
                            Toast.makeText(BuyCoinActivity.this, buyCoinResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(BuyCoinActivity.this).showDialog(BuyCoinActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(BuyCoinActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    private void getTickets(TicketEntity ticketEntity) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(BuyCoinActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());
            Call call = apiService.TICKET_RESPONSE_CALL(ticketEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    dialog.dismiss();
                    TicketResponse authResponse = (TicketResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setAdapter(authResponse.getData().getRecords());
                            Toast.makeText(BuyCoinActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            ticket.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(BuyCoinActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(BuyCoinActivity.this).showDialog(BuyCoinActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(BuyCoinActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }


    }

    private void callgetLevelView(TicketEntity ticketEntity) {
        boolean isupdate = false;
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());
            isupdate = bottomProgressBar.getVisibility() != View.VISIBLE;

            Call call = apiService.TICKET_RESPONSE_CALL(ticketEntity);
            boolean finalIsupdate = isupdate;
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    if (bottomProgressBar.getVisibility() != View.VISIBLE)
                        progressBar.setVisibility(View.GONE);
                    else
                        bottomProgressBar.setVisibility(View.GONE);
                    TicketResponse authResponse = (TicketResponse) response.body();
                    if (authResponse != null) {
                        Log.i("MyTeamResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setAdapter(authResponse.getData().getRecords());
                        } else {
                            if (records.size() == 0)
                                ticketAdapter.upDateList(records, 0);
                        }
                    } else {
                        if (finalIsupdate)
                            AppCommon.getInstance(BuyCoinActivity.this).showDialog(BuyCoinActivity.this, "Data Not Found");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    if (bottomProgressBar.getVisibility() != View.VISIBLE)
                        progressBar.setVisibility(View.GONE);
                    else
                        bottomProgressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(BuyCoinActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            if (bottomProgressBar.getVisibility() == View.VISIBLE)
                progressBar.setVisibility(View.GONE);
            else
                bottomProgressBar.setVisibility(View.GONE);
            Toast.makeText(BuyCoinActivity.this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void clickButton(boolean is_confirm_pop, TicketRecordModel recordModel) {
        tickt_model = recordModel;
        is_layerOpen = true;
        if (is_confirm_pop) {
            ll_confirm_layer.setVisibility(View.VISIBLE);
        } else {
            ll_reject.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void clickUpload(TicketRecordModel recordModel) {
        tickt_model = recordModel;
        is_layerOpen = true;
        ll_upload.setVisibility(View.VISIBLE);
        filePath = "";
        txt_file_name.setText("No File Chosen");
        iv_image_preview.setImageBitmap(null);
    }


    @Override
    public void viewSlip(TicketRecordModel recordModel) {
        tickt_model = recordModel;
        viewImage();
    }

    private void viewImage() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(BuyCoinActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Map<String, Object> param = new HashMap<>();
            param.put("transaction_id", tickt_model.getTranid());
            Call call = apiService.GetSlip(param);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    dialog.dismiss();
                    TransactionSlipResponseModel buyCoinResponse = (TransactionSlipResponseModel) response.body();
                    if (buyCoinResponse != null) {
                        Log.i("ResponseUpdate::", new Gson().toJson(buyCoinResponse));
                        if (buyCoinResponse.getCode() == 200) {
                            callIntent(buyCoinResponse.getData());
                        } else {
                            Toast.makeText(BuyCoinActivity.this, buyCoinResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(BuyCoinActivity.this).showDialog(BuyCoinActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    Log.d("fail rq", t.getMessage());
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(BuyCoinActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void callIntent(TransactionDataModel data) {
        Intent intent = new Intent(getApplicationContext(), ViewSlipActivity.class);
        intent.putExtra("url", data.getAttachment());
        startActivity(intent);
    }

    @Override
    public void sendSms(TicketRecordModel recordModel) {

        sendSmscall(recordModel);
    }

    private void sendSmscall(TicketRecordModel recordModel) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(BuyCoinActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());
            Map<String, Object> param = new HashMap<>();
            param.put("transaction_id", recordModel.getTranid());
            Call call = apiService.GetSendSms(param);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    dialog.dismiss();
                    CommonResponseModel authResponse = (CommonResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {

                            Toast.makeText(BuyCoinActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BuyCoinActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(BuyCoinActivity.this).showDialog(BuyCoinActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    Log.d("fail rq", t.getMessage());
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(BuyCoinActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        if (is_layerOpen) {
            ll_reject.setVisibility(View.GONE);
            ll_confirm_layer.setVisibility(View.GONE);
            is_layerOpen = false;
            ll_upload.setVisibility(View.GONE);
            txt_file_name.setText("");
            filePath = "";
        } else {
            finish();
        }
    }

    @OnClick(R.id.txt_choose_file)
    public void showPictureDialog() {

        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(BuyCoinActivity.this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {getResources().getString(R.string.Select_photo_from_gallery), getResources().getString(R.string.Capture_photo_from_camera)};
        pictureDialog.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        choosePhotoFromGallary();
                        break;
                    case 1:
                        takePhotoFromCamera();
                        break;
                }
            }
        });
        pictureDialog.show();
    }

    @OnClick(R.id.cross)
    void close() {
        ll_upload.setVisibility(View.GONE);
        txt_file_name.setText("");
        filePath = "";
        is_layerOpen = false;
    }

    public void choosePhotoFromGallary() {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                requestpermission(1);
            } else {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                try {
                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                    galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(galleryIntent, GALLERY_PICTURE_REQUEST_CODE);
                } catch (Exception e) {
                    e.printStackTrace();
                    //                    Toast.makeText(getContext(),"Something went Wrong", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //            Toast.makeText(getContext(),"Storage area Something went Wrong "+e.getMessage(),
            //                    Toast.LENGTH_LONG).show();
        }
    }

    private void takePhotoFromCamera() {
        try {
            cameraFlag = "click";
            if (Build.VERSION.SDK_INT >= 23) {
                requestpermission(2);


            } else {

                try {
                    Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent1, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestpermission(int i) {
        if (i == 1) {
            int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (result == PackageManager.PERMISSION_GRANTED) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                Log.d(tag, "----req permission-GALLERY_PICTURE_REQUEST_CODE--" + fileUri);
                galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(galleryIntent, GALLERY_PICTURE_REQUEST_CODE);

            } else {
                requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
            }

        } else if (i == 2) {
            int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
            if (result == PackageManager.PERMISSION_GRANTED) {

                if (cameraFlag != null) {
                    if (cameraFlag.equals("click")) {
                        Log.d(tag, "-----------------camera---------------" + cameraFlag);
                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent1, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);

                    }
                    cameraFlag = null;
                } else {

                }
            } else {
                requestPermissions(CAMERA_PERM, INITIAL_REQUEST_CAMERA);
            }
        }

    }

    public void setImage(int requestCode, int resultCode, Intent data) {
        Log.d(tag, "---resulte coddde-request code-" + resultCode + "--" + requestCode);
        if (resultCode == RESULT_OK) {

            if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
                if (data == null) {
                    //no data present
                    return;
                }
                if (resultCode == RESULT_OK) {

                    try {

                        onCaptureImageResult(data);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (resultCode == RESULT_CANCELED) {
                    // user cancelled Image capture
                    //   Toast.makeText(getApplicationContext(), getString(R.string.canclecaptcheimage), Toast.LENGTH_SHORT).show();
                } else {
                    // user cancelled Image capture

                }
            } else if (requestCode == GALLERY_PICTURE_REQUEST_CODE) {
                if (resultCode == RESULT_OK) {
                    // video successfully recorded
                    // preview the recorded video
                    filePath = getPath(data.getData());
                    txt_file_name.setText(new File(filePath).getName());
                    bitmapImge = decodeSampledBitmapFromFile(filePath, 600, 800);
                    iv_image_preview.setImageBitmap(bitmapImge);
                } else if (resultCode == RESULT_CANCELED) {
                    // user cancelled recording


                } else {

                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_WRITE_PERMISSION_FILE_CHOSER) {
            int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (result == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "No File  Choose option", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == REQUEST_WRITE_PERMISSION) {
            int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (result == PackageManager.PERMISSION_GRANTED) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                Log.d(tag, "----req permission-GALLERY_PICTURE_REQUEST_CODE--" + fileUri);
                galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(galleryIntent, GALLERY_PICTURE_REQUEST_CODE);
                //Toast.makeText(getContext(),"Gallery Permission Allow", Toast.LENGTH_LONG).show();
            } else {
                // Toast.makeText(getContext(),"Gallery Permission Denied", Toast.LENGTH_LONG)
                // .show();
            }
        } else if (requestCode == INITIAL_REQUEST_CAMERA) {
            int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
            if (result == PackageManager.PERMISSION_GRANTED) {

                if (cameraFlag != null) {
                    if (cameraFlag.equals("click")) {
                        Log.d(tag, "-----------------camera---------------" + cameraFlag);
                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent1, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);

                    }
                    cameraFlag = null;
                } else {

                }
            }
        }

    }

    private String getPath(Uri uri) {

        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = 0;
        try {
            // cursor = CaptureVehicleDetailActivity.this.getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                return cursor.getString(column_index);
            }
        } finally {
            //if (cursor != null)
            //  cursor.close();
        }
        assert cursor != null;
        return cursor.getString(column_index);
    }

    private void onCaptureImageResult(Intent data) {
        bitmapImge = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmapImge.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        iv_image_preview.setImageBitmap(bitmapImge);
        destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");

        Log.d("destination", "" + destination.getName());
        // filePath = Environment.getExternalStorageDirectory().toString() + System.currentTimeMillis() + ".jpg";
        filePath = destination.getAbsolutePath();
        Log.d("filePath", "" + filePath);
        if (destination != null) {
            txt_file_name.setText(destination.getName());
        } else {
            Log.e("erroe", " onCaptureImageResult send null file");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(tag, "------requestCode code" + requestCode);
        Log.d(tag, "------resultCode code" + resultCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == 200 || requestCode == 100) {
                setImage(requestCode, resultCode, data);
            } else if (requestCode == 100) {

            } else if (requestCode == 1010) {

            }

        } else {

        }
    }

    @OnClick(R.id.iv_send)
    void uploadFile() {
        if (filePath.equals("")) {
            Toast.makeText(getBaseContext(), "Choose File ", Toast.LENGTH_LONG).show();
            txt_file_name.setError("Please Choose File");
            txt_file_name.requestFocus();
            return;
        }
        try {
            callService(getparameter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] getparameter() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        if (!filePath.equals("")) {
            byte[] fileData1 = getFileDataFromDrawable(getApplicationContext(), filePath);
            File uu = new File(filePath);
            buildPart(dos, fileData1, uu.getName() + ".png");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            buildTextPart(dos, "transaction_id", String.valueOf(tickt_model.getTranid()));
        }

        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);


        return bos.toByteArray();
    }

    private byte[] getFileDataFromDrawable(Context context, String filePath) {

        Log.d("bitmap image", "--------------" + filePath);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Log.d("bitmap 1", "--------------");

        Log.d("bitmap 2", "--------------");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (byteArrayOutputStream == null) {
            Log.e("byte null", "byteArrayOutputStream nill ************************");
        }

        bitmapImge.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);

        byte[] b = byteArrayOutputStream.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("encode image ", "--------------" + imageEncoded);
        return byteArrayOutputStream.toByteArray();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void buildTextPart(DataOutputStream dataOutputStream, String parameterName, String parameterValue) throws IOException {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + parameterName + "\"" + lineEnd);
        dataOutputStream.writeBytes(lineEnd);
        dataOutputStream.writeBytes(parameterValue + lineEnd);

    }

    private void buildPart(DataOutputStream dataOutputStream, byte[] fileData, String fileName) throws IOException {
        Log.d("build part 1", "--------------");
        if (dataOutputStream == null) {
            Log.e("Registration", "dataOutputStream is null");
        }
        if (fileData == null) {
            Log.e("Registration", "fileData is null");
        }
        if (fileName == null) {
            Log.e("Registration", "fileName is null");
        }
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"attachment\"; filename=\"" + fileName + "\"" + lineEnd);
        dataOutputStream.writeBytes(lineEnd);
        Log.d("build part 2", "--------------");
        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(fileData);
        int bytesAvailable = fileInputStream.available();
        Log.d("build part 3", "--------------");
        int maxBufferSize = 1024 * 10;
        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
        byte[] buffer = new byte[bufferSize];

        // read file and write it into form...
        int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        Log.d("build part 4", "--------------");
        while (bytesRead > 0) {
            dataOutputStream.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }
        Log.d("build part 5", "--------------");

        dataOutputStream.writeBytes(lineEnd);
    }

    private void callService(byte[] multipartBody) {
        String url = ServiceGenerator.API_BASE_URL + "user/upload-transaction-slip";
        Map<String, String> params = new HashMap<String, String>();
        params.put("Authorization", "Bearer " + AppCommon.getInstance(getApplicationContext()).getToken());
        params.put("Content-Type", "multipart/form-data; boundary=----" + boundary);
        Log.d(tag, "--auth--" + params.toString());
        MultipartRequest multipartRequest = new MultipartRequest(url, params, mimeType, multipartBody, response -> {
            String resultResponse = new String(response.data);
            try {

                AppCommon.getInstance(getApplicationContext()).clearNonTouchableFlags(BuyCoinActivity.this);
                if (isFinishing()) {
                    return;
                }

                JSONObject jsonObject = new JSONObject(resultResponse);
                handleResponse(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getBaseContext(), "Upload fail", Toast.LENGTH_LONG).show();
                AppCommon.getInstance(getApplicationContext()).clearNonTouchableFlags(BuyCoinActivity.this);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppCommon.getInstance(getApplicationContext()).clearNonTouchableFlags(BuyCoinActivity.this);
            }

        });

        Volley.newRequestQueue(getBaseContext()).add(multipartRequest);
    }

    private void handleResponse(JSONObject jsonObject) throws JSONException {
        if (jsonObject.getInt("code") == 409) {
            Toast.makeText(getBaseContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

        } else if (jsonObject.getInt("code") == 200) {
            Toast.makeText(getBaseContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
            ll_upload.setVisibility(View.GONE);
            is_layerOpen = false;
            filePath = "";
            getTickets(new TicketEntity("10", "0", "0"));
            if (jsonObject.has("data")) {
                JSONObject dataobj = jsonObject.getJSONObject("data");
            }
        } else {
            //  {"code":404,"status":"Not Found","message":"You can make only one investment!",
            //  "data":{}}
            Toast.makeText(getBaseContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
        }
    }
}

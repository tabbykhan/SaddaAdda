package com.imuons.saddaadda;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.FirebaseApp;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.lang.reflect.Field;

public class SaddaAdda extends Application {
    public static ImageLoader imageLoader;
  public static Context context;
    //HomeWatcher mHomeWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        FirebaseApp.initializeApp(this);
        if (imageLoader == null) {
            intImageLoader();
            // getTranslateService();
        }
        getAppInstance();
       /* doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);
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
    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon,Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }*/
    }

    public Context getAppInstance() {
        if (context == null) {
            context = getApplicationContext();
            return context;
        } else {
            return context;
        }
    }

    private void intImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(defaultOptions).build();

        ImageLoader.getInstance().init(config);
        if (ImageLoader.getInstance().isInited()) {
            imageLoader = ImageLoader.getInstance();
        }
    }

    public boolean isDebugBuild() {

        try {
            String packageName = getPackageName();

            Bundle bundle = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA).metaData;
            String manifest_pkg = null;
            if (bundle != null) {
                manifest_pkg = bundle.getString("manifest_pkg", null);
            }
            if (manifest_pkg != null) {
                packageName = manifest_pkg;
            }
            final Class<?> buildConfig = Class.forName(packageName + ".BuildConfig");
            final Field DEBUG = buildConfig.getField("DEBUG");
            DEBUG.setAccessible(true);
            return DEBUG.getBoolean(null);
        } catch (final Throwable t) {
            final String message = t.getMessage();
            if (message != null && message.contains("BuildConfig")) {
                return false;
            } else {
                return BuildConfig.DEBUG;
            }
        }
    }
    //    public void getTranslateService() {
    //
    //        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    //        StrictMode.setThreadPolicy(policy);
    //
    //        try (InputStream is =new FileInputStream("AIzaSyBVsRPq3hKB8R89fCcSM9JLR2Jq1xuAr9A")) {
    //
    //            //Get credentials:
    //            final GoogleCredentials myCredentials = GoogleCredentials.fromStream(is);
    //
    //            //Set credentials and get translate service:
    //            TranslateOptions translateOptions = TranslateOptions.newBuilder().setCredentials(myCredentials).build();
    //            translate = translateOptions.getService();
    //
    //        } catch (IOException ioe) {
    //            ioe.printStackTrace();
    //
    //        }
    //    }
    //
    //    public String translate(String input,String type) {
    //
    //        //Get input text to be translated:
    //        String originalText = input;
    //        Translation translation = translate.translate(originalText,
    //                Translate.TranslateOption.targetLanguage(type), Translate.TranslateOption.model(
    //                        "base"));
    //        String translatedText = translation.getTranslatedText();
    //
    //        //Translated text and original text are set to TextViews:
    //       return translatedText;
    //
    //    }


}

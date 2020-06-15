package com.imuons.saddaadda;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.FirebaseApp;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class SaddaAdda extends Application {
    public static ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        FirebaseApp.initializeApp(this);
        if(imageLoader==null){
            intImageLoader();
           // getTranslateService();
        }
    }
    private void intImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();

        ImageLoader.getInstance().init(config);
        if (ImageLoader.getInstance().isInited()){
            imageLoader=imageLoader.getInstance();
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

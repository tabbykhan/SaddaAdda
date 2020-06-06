package com.imuons.saddaadda.retrofit;

/**
 * Created by Rahul V. on 31-03-2016.
 */


import android.os.RecoverySystem;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;


public class MultipartRequest extends Request<NetworkResponse> implements RecoverySystem.ProgressListener {
    private final Response.Listener<NetworkResponse> mListener;
    private final Response.ErrorListener mErrorListener;
    private final Map<String, String> mHeaders;
    private final String mMimeType;
    private final byte[] mMultipartBody;
    private final String tag="MultipartRequest";


    public MultipartRequest(String url, Map<String, String> headers, String mimeType, byte[] multipartBody, Response.Listener<NetworkResponse> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.mListener = listener;
        this.mErrorListener = errorListener;
        this.mHeaders = headers;
        this.mMimeType = mimeType;
        this.mMultipartBody = multipartBody;
        Log.d(tag, "-param--"+new String(this.mMultipartBody));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Log.d("get header","-----------------");
        return (mHeaders != null) ? mHeaders : super.getHeaders();
    }

    @Override
    public String getBodyContentType() {
        Log.d("get body content typpe","-----------------");
        return mMimeType;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        Log.d("get body ","-----------------");
        getParamsEncoding();
        return mMultipartBody;
    }

    @Override
    protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
        try {
            Log.d("network response ","-----------------"+response.toString());
            return Response.success(
                    response,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(NetworkResponse response) {

        mListener.onResponse(response);
        Log.d("delivery response  ", "-----------------"+response.toString());
    }

    @Override
    public void deliverError(VolleyError error) {
        mErrorListener.onErrorResponse(error);
        Log.d("delivery error  ", "-----------------"+error.toString());
    }

    @Override
    public void onProgress(int progress) {
        Log.d("progress ", "-----------------");
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }
}
package contacto.humano.com.get_data_async.register;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import contacto.humano.com.m_interfaces.i_general_string;
import contacto.humano.com.m_interfaces.i_process_dialog;
import contacto.humano.com.utils.Utils;

/**
 * Created by Ibrahim Ali Khan on 10/23/2016.
 */

public class mGetJSONData extends AsyncTask<String, Void, String> {

    private ArrayList mInterface;
    private String mUrl;
    private String mRequestMethod;

    public mGetJSONData(String url, ArrayList Interfaces){
        mUrl = url;
        mInterface = Interfaces;
        mRequestMethod = "GET";
    }

    public mGetJSONData(String url, String requestMethod, ArrayList interfaces) {
        mUrl = url;
        mInterface = interfaces;
        mRequestMethod = requestMethod;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        int len = mInterface.size();
        if(len > 0){
            ((i_process_dialog) mInterface.get(0)).onDataBeingLoaded();
        }
    }

    @Override
    protected String doInBackground(String... params) {

        URL urlCould;
        HttpURLConnection connection;
        InputStream inputStream = null;
        try {
            urlCould = new URL(mUrl);
            connection = (HttpURLConnection) urlCould.openConnection();
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.setRequestMethod(mRequestMethod);
            connection.connect();

            inputStream = connection.getInputStream();

        } catch (MalformedURLException ignored) {
        } catch (IOException IOEx) {
            Log.e("Utils", "HTTP failed to fetch data");
            return null;
        }
//        }
        return Utils.readStream(inputStream);
    }

    @Override
    protected void onPostExecute(String data) {
        int len = mInterface.size();
        if(len > 0){
            ((i_process_dialog) mInterface.get(0)).onDataLoadingComplete();
        }
        if(data != null && !data.equals("") && len > 1){
            ((i_general_string) mInterface.get(1)).onStringTransfer(data);
        }
    }
}

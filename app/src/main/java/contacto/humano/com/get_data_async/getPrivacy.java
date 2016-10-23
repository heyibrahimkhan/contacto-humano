package contacto.humano.com.get_data_async;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import contacto.humano.com.MainActivity;
import contacto.humano.com.m_interfaces.i_general_array;
import contacto.humano.com.m_interfaces.i_general_string;

/**
 * Created by Ibrahim Ali Khan on 10/24/2016.
 */

public class getPrivacy extends myGet {

    ArrayList<Object> mInterfaces;

    public getPrivacy(ArrayList Interfaces){
        mInterfaces = Interfaces;
        url = "http://con-tactohumano.com/privacy-policy/" + MainActivity.lang;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try{
            int len_mInterfaces = mInterfaces.size();
            Document document = Jsoup.connect(url).get();
            Elements lis = document.select("ol li");
            if(len_mInterfaces > 0) ((i_general_string) mInterfaces.get(0)).onStringTransfer(getLis(lis));
        }
        catch (Exception ignored){
            System.out.println("Exception occurred");
            System.out.println(ignored.toString());
            System.out.println(ignored.getMessage());
            MainActivity.setErrorFrag("blog", url);
        }
        return null;
    }

    private String getLis(Elements lis) {
        String s = "";
        int len = lis.size();
        for (int i = 0; i < len; i++){
            s = s.concat((i+1) + ". " + lis.get(i).text() + "\n\n");
        }
        return s;
    }
}

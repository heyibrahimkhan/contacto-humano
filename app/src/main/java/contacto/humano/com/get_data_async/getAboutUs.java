package contacto.humano.com.get_data_async;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import contacto.humano.com.m_fragments.Frag_getAbout;
import contacto.humano.com.m_interfaces.about_us.i_about_us;
import contacto.humano.com.utils.BitmapWorkerTask;

/**
 * Created by Ibrahim Ali Khan on 10/11/2016.
 */

public class getAboutUs extends myGet {

    ArrayList<Object> mInterfaces;

    public getAboutUs(ArrayList Interfaces){
        mInterfaces = Interfaces;
        url = "http://con-tactohumano.com/about-us/";
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
            ArrayList<String> list = new ArrayList<>();
            list.add(getImageUrl(document.select("img.alignright")));
            getParas(document.select("div.text_block wpb_text_column clearfix"), list);
            if (len_mInterfaces > 0){
                ((i_about_us) mInterfaces.get(0)).onAboutUsLoaded(list);
            }
        }
        catch (Exception e){

        }
        return null;
    }

    private void getParas(Elements paras, ArrayList<String> list) {
        for (int i = 0; i < 3; i ++){
            System.out.println("paras = "+paras.get(i).text());
            list.add(paras.get(i).text());
        }
    }

    private String getImageUrl(Elements imgUrl) {
        String url = "";
        int len = imgUrl.size();
        if(len == 1){
            for (Element e : imgUrl){
                url = e.absUrl("src");
            }
        }
        return url;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

}

package contacto.humano.com.get_data_async;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import contacto.humano.com.MainActivity;
import contacto.humano.com.m_adapters.home.rv_hp_adapter;
import contacto.humano.com.m_interfaces.i_general_array;

/**
 * Created by Ibrahim Ali Khan on 11/6/2016.
 */

public class getPartners extends myGet {

    ArrayList<Object> mInterfaces;

    public getPartners(ArrayList Interfaces){
        mInterfaces = Interfaces;
        url = "http://con-tactohumano.com/nosotros/socios-y-aliados-estrategicos-de-con%E2%80%A2tacto-humano/" + MainActivity.lang;
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
            Elements titles = document.select("h4.no_stripe");
            Elements text = document.select("div.description");
            Elements imagesUrl = document.select("div.image img");
            if (len_mInterfaces > 0){
                ((i_general_array) mInterfaces.get(0)).onArrayListLoaded(getData(imagesUrl, titles, text));
            }
        }
        catch (Exception ignored){

        }
        return null;
    }

    private ArrayList getData(Elements imgUrl, Elements titles, Elements text) {

        ArrayList l = new ArrayList<rv_hp_adapter.class_home_post>();
        int len = imgUrl.size();
        for (int i = 0; i < len; i++){
            rv_hp_adapter.class_home_post obj = new rv_hp_adapter.class_home_post(imgUrl.get(i).absUrl("src"), titles.get(i).text(),
                    null, text.get(i).text());
            l.add(obj);
        }
        return l;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

}

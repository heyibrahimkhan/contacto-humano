package contacto.humano.com.get_data_async;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import contacto.humano.com.MainActivity;
import contacto.humano.com.m_interfaces.about_us.i_about_us;

/**
 * Created by Ibrahim Ali Khan on 10/11/2016.
 */

public class getAboutUs extends myGet {

    ArrayList<Object> mInterfaces;

    public getAboutUs(ArrayList<Object> Interfaces){
        mInterfaces = Interfaces;
        url = "http://con-tactohumano.com/about-us/"+ MainActivity.lang;
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
            list = getParas(document.select("div.entry-content div p"), list);
            if (len_mInterfaces > 0){
                ((i_about_us) mInterfaces.get(0)).onAboutUsLoaded(list);
            }
        }
        catch (Exception ignored){

        }
        return null;
    }

    private ArrayList<String> getParas(Elements paras, ArrayList<String> list) {
        String s = "";
        for (Element e : paras){
            if(e.text().contains("e")) {
                s = s.concat(e.text()+"\n\n");
//                list.add(e.text());
            }
        }
        list.add(s);
        return list;
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

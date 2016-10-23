package contacto.humano.com.get_data_async;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import contacto.humano.com.MainActivity;
import contacto.humano.com.m_adapters.home.rv_h_th_adapter;
import contacto.humano.com.m_interfaces.i_general_array;

/**
 * Created by Ibrahim Ali Khan on 10/12/2016.
 */

public class getTestimonials extends myGet {

    ArrayList<Object> mInterfaces;

    public getTestimonials(ArrayList Interfaces){
        mInterfaces = Interfaces;
        url = "http://con-tactohumano.com/testimonials-page/"+ MainActivity.lang;
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
            ArrayList list = new ArrayList<>();
            Elements imgUrl = document.select("span.katb_avatar_round_image img");
            Elements names = document.select("span.katb_author");
            Elements titles = document.select("span.katb_title");
            Elements texts = document.select("div.katb_test_text p");
            list = getData(imgUrl, names, titles, texts);
            if (len_mInterfaces > 0){
                ((i_general_array) mInterfaces.get(0)).onArrayListLoaded(list);
            }
        }
        catch (Exception ignored){
//            System.out.println("Exception = "+ignored.toString());
            MainActivity.setErrorFrag("testimonials", url);
        }
        return null;
    }

    private ArrayList getData(Elements imgUrl, Elements names, Elements titles, Elements texts) {
        ArrayList l = new ArrayList<rv_h_th_adapter.class_testimonial>();
        int len = imgUrl.size();
        for(int i = 0; i < len; i++){
            rv_h_th_adapter.class_testimonial obj = new rv_h_th_adapter.class_testimonial(imgUrl.get(i).absUrl("src"),
                    names.get(i).text(), titles.get(i).text(), texts.get(i).text()+"         .");
            l.add(obj);
//            System.out.println("img = "+imgUrl.get(i).absUrl("src"));
//            System.out.println("name = "+names.get(i).text());
//            System.out.println("title = "+titles.get(i).text());
//            System.out.println("text = "+texts.get(i).text());
        }
        return l;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

}

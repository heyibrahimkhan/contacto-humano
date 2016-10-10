package contacto.humano.com.getWebMat;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import contacto.humano.com.Frag_getHome;
import contacto.humano.com.MainActivity;
import contacto.humano.com.mInterfaces.home.i_home_achieve;
import contacto.humano.com.mInterfaces.home.i_home_decor;
import contacto.humano.com.mInterfaces.home.i_home_news_post;
import contacto.humano.com.mInterfaces.home.i_home_test;
import contacto.humano.com.m_adapters.home.rv_h_th_adapter;
import contacto.humano.com.m_adapters.home.rv_hp_adapter;

/**
 * Created by Ibrahim Ali Khan on 10/8/2016.
 */

public class getHome extends myGet {

    public i_home_decor homeDecor;
    public i_home_achieve homeAchieve;
    private i_home_test hometest;
    private ArrayList<Object> mInterfaces;

    public getHome(ArrayList interfaces){
        url = "http://con-tactohumano.com/"; // Put page url
        mInterfaces = interfaces;
    }

    public getHome(i_home_decor HomeDecor, i_home_test HomeTest){
        homeDecor = HomeDecor;
        hometest = HomeTest;
        url = "http://con-tactohumano.com/"; // Put page url
    }

    public getHome(String URL){
        url = URL;
        url = "http://con-tactohumano.com/"; // Put page url
    }

    public getHome(){
        url = "http://con-tactohumano.com/"; // Put page url
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
//            String vc_custom_heading = document.select("div.vc_custom_heading").text();
//            String b_vc_custom_heading = document.select("div.vc_cta3-actions").text();

            //Decor Items
            Elements decor_title = document.select("h6.no_stripe");
            Elements decor_detail = document.select("div.info_box_text p");
            ((i_home_decor)mInterfaces.get(0)).onDecorItemsLoaded(getHomeDecorItems(decor_title, decor_detail));

            //Achievements Items
            if(mInterfaces.size() > 1) {
            ((i_home_achieve)mInterfaces.get(1)).onHomeAchiLoaded(getHomeAchiList(document.select("h3.no_stripe")));
            }

            if(len_mInterfaces > 2){
                Elements test_name = document.select("span.katb_author");
                Elements test_title = document.select("span.katb_title");
                Elements test_text = document.select("div.katb_test_text p");
                ((i_home_test) mInterfaces.get(2)).onHomeTestLoaded(getTestItems(test_name, test_title, test_text));
            }

            if(len_mInterfaces > 3){
                Elements news_post_url = document.select("div.image img");
                for(Element e : news_post_url){
                    System.out.println("post = "+e.absUrl("src"));
                }
                Elements news_post_goto = document.select("h5.no_stripe a");
                for(Element e : news_post_goto){
                    System.out.println("Name = "+e.text());
                    System.out.println("Leads to = "+e.absUrl("href"));
                }
                Elements news_post_date = document.select("div.date");
                for(Element e : news_post_date){
                    System.out.println("Date = "+e.text());
                }
                ((i_home_news_post) mInterfaces.get(3)).onHomeNewsPostLoaded(getNewsPostMat(news_post_url, news_post_goto, news_post_date));
            }

//            if(ems == null){
//                System.out.println("Null");
//            }
//            else{
//                System.out.println(ems.toString());
//                System.out.println("Not null");
//            }
//            for(Element e : ems){
//                String text = e.text();
//                System.out.println("e = "+text);
//            }

//            System.out.println(vc_custom_heading);
//            System.out.println(b_vc_custom_heading);
        }
        catch (Exception ignored){
            MainActivity.setErrorFrag("home");
        }
        return super.doInBackground(objects);
    }

    private ArrayList<rv_hp_adapter.class_home_post> getNewsPostMat(Elements news_post_url, Elements news_post_goto, Elements news_post_date) {
        ArrayList<String> list = new ArrayList<>();
        for(Element e : news_post_url){
            list.add(e.absUrl("src"));
        }
        for(Element e : news_post_goto){
            list.add(e.text());
        }
        for(Element e : news_post_goto){
            list.add(e.absUrl("href"));
        }
        for(Element e : news_post_date){
            list.add(e.text());
        }
        ArrayList<rv_hp_adapter.class_home_post> l = new ArrayList<>();
        int len_news = news_post_url.size();
        int len = list.size();
        for(int i = 0; i < len; i++){
            if(i + (len_news * 3) < len) {
                rv_hp_adapter.class_home_post obj = new rv_hp_adapter.class_home_post(list.get(i), list.get(i + len_news),
                        list.get(i + (len_news * 2)), list.get(i + (len_news * 3)));
                l.add(obj);
            }
        }
        return l;
    }

    private ArrayList getTestItems(Elements test_author, Elements test_title, Elements test_text) {
        ArrayList list = new ArrayList<String>();
        int len_ta = test_author.size();
        for(Element e : test_author){
            list.add(e.text());
        }
        for(Element e : test_title){
            list.add(e.text());
        }
        for(Element e : test_text){
            list.add(e.text());
        }
        ArrayList<rv_h_th_adapter.class_test> l = new ArrayList<>();
        int len = list.size();
        for(int i = 0; i < len; i++){
            if(i+(len_ta*2) < len) {
                rv_h_th_adapter.class_test obj = new rv_h_th_adapter.class_test(list.get(i).toString(),
                        list.get(i + len_ta).toString(), list.get(i + (len_ta*2)).toString());
                l.add(obj);
            }
        }
        return l;
    }

    private ArrayList getHomeAchiList(Elements home_achi_num) {
        ArrayList<String> s = new ArrayList<>();
        for (Element e : home_achi_num){
            s.add(e.text());
//            System.out.println("achi = "+e.text());
        }
        return s;
    }

    private ArrayList getHomeDecorItems(Elements decor_title, Elements decor_detail) {
        ArrayList l = new ArrayList();
        if(decor_title.size() == decor_detail.size()){
            for (Element e : decor_title){
                l.add(e.text());
            }
            for (Element e : decor_detail){
                l.add(e.text());
            }
        }
        return l;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}

package contacto.humano.com.get_data_async;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import contacto.humano.com.MainActivity;
import contacto.humano.com.m_interfaces.home.i_home_achieve;
import contacto.humano.com.m_interfaces.home.i_home_decor;
import contacto.humano.com.m_interfaces.home.i_home_news_post;
import contacto.humano.com.m_interfaces.home.i_home_test;
import contacto.humano.com.m_adapters.home.rv_h_th_adapter;
import contacto.humano.com.m_adapters.home.rv_hp_adapter;
import contacto.humano.com.m_interfaces.i_general_array;

/**
 * Created by Ibrahim Ali Khan on 10/8/2016.
 */

public class getHome extends myGet {

    private ArrayList<Object> mInterfaces;

    public getHome(ArrayList interfaces){
        url = "http://con-tactohumano.com/"+MainActivity.lang; // Put page url
        mInterfaces = interfaces;
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

            String vc_custom_heading = document.select("header.vc_cta3-content-header div h2").text();
            Elements b_vc_custom_heading = document.select("div.vc_cta3-actions div a");
            if(len_mInterfaces > 4){
                ((i_general_array) mInterfaces.get(4)).onArrayListLoaded(getItemWithButtom(vc_custom_heading, b_vc_custom_heading));
            }

            //Decor Items
            Elements decor_title = document.select("div.title h6 span");
            Elements decor_detail = document.select("a.read_more span");
            Elements decor_image_url = document.select("div.info_box_image img");
            Elements decor_readMore = document.select("a.read_more");
//            ((i_home_decor) mInterfaces.get(0)).onDecorItemsLoaded(getHomeDecorItems(decor_title, decor_detail));
            ((i_general_array) mInterfaces.get(0)).onArrayListLoaded(getHomeDecorItems(decor_title, decor_detail, decor_image_url, decor_readMore));


            //Achievements Items
            Elements achiName = document.select("div.counter_title");
            Elements achi = document.select("h3.no_stripe");
            if(mInterfaces.size() > 1) {
                ((i_home_achieve)mInterfaces.get(1)).onHomeAchiLoaded(getHomeAchiList(achi, achiName));
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
        }
        catch (Exception ignored){
            if(!isCancelled())
            MainActivity.setErrorFrag("home", url);
        }
        return super.doInBackground(objects);
    }

    private ArrayList getHomeDecorItems(Elements decor_title, Elements decor_detail, Elements decor_image_url, Elements readMore) {
        ArrayList list = new ArrayList<String>();
        int len = decor_detail.size();
//        System.out.println("Len = "+len);
        for (int i = 0; i < len; i++){
            list.add(decor_title.get(i).text());
            list.add(decor_detail.get(i).text());
            list.add(decor_image_url.get(i).absUrl("src"));
            list.add(readMore.get(i).absUrl("href"));
//            System.out.println("Title = "+decor_title.get(i).text());
//            System.out.println("Detail = "+decor_detail.get(i).text());
//            System.out.println("absUrl = "+decor_image_url.get(i).absUrl("src"));
//            System.out.println("href = "+readMore.get(i).absUrl("href"));
        }
        return list;
    }

    private ArrayList getItemWithButtom(String vc_custom_heading, Elements b_vc_custom_heading) {
        ArrayList list = new ArrayList();
        list.add(vc_custom_heading);
        list.add(b_vc_custom_heading.text());
        list.add(b_vc_custom_heading.attr("href"));
//        for (int i = 0; i < list.size(); i++){
//            System.out.println("My Elements = "+list.get(i).toString());
//        }
        return list;
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
        ArrayList<rv_h_th_adapter.class_testimonial> l = new ArrayList<>();
        int len = list.size();
        for(int i = 0; i < len; i++){
            if(i+(len_ta*2) < len) {
                rv_h_th_adapter.class_testimonial obj = new rv_h_th_adapter.class_testimonial(list.get(i).toString(),
                        list.get(i + len_ta).toString(), list.get(i + (len_ta*2)).toString());
                l.add(obj);
            }
        }
        return l;
    }

    private ArrayList getHomeAchiList(Elements home_achi_num, Elements achiName) {
        ArrayList<String> s = new ArrayList<>();
        int len = achiName.size();
//        System.out.println("len = "+len);
        for (int i = 0; i < len; i++){
            s.add(achiName.get(i).text());
            s.add(home_achi_num.get(i).text());
//            System.out.println("achi = "+achiName.get(i).text());
//            System.out.println("num = "+home_achi_num.get(i).text());
        }
//        for (Element e : home_achi_num){
//            s.add(e.text());
//            System.out.println("achi = "+e.text());
//        }
        return s;
    }

    private ArrayList getHomeDecorItems(Elements decor_title, Elements decor_detail) {
        ArrayList l = new ArrayList();
        if(decor_title.size() == decor_detail.size()){
            for (Element e : decor_title){
//                System.out.println("title = "+e.text());
                l.add(e.text());
            }
            for (Element e : decor_detail){
//                System.out.println("message = "+e.text());
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

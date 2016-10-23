package contacto.humano.com.get_data_async;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import contacto.humano.com.MainActivity;
import contacto.humano.com.m_adapters.blog.adapter_short_blog;
import contacto.humano.com.m_interfaces.i_general_array;

/**
 * Created by Ibrahim Ali Khan on 10/18/2016.
 */

public class getBlog extends myGet {

    ArrayList<Object> mInterfaces;

    public getBlog(ArrayList<Object> Interfaces){
        mInterfaces = Interfaces;
        url = "http://con-tactohumano.com/blog-2/"+MainActivity.lang;
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
            Elements archives = document.select("aside#archives-2");
            Elements categories = document.select("aside#categories-2");
            Elements blog_Titles = document.select("h4.stripe_2");
            Elements blog_dates = document.select("li.post_date");
            Elements blog_postedBys = document.select("li.post_by");
            Elements blog_cats = document.select("li.post_cat");
            Elements blog_comments = document.select("div.comments_num a");
            Elements imgUrls = document.select("div.post_thumbnail img");
            Elements shortTexts = document.select("div.post_excerpt p");
            Elements readMoreUrls = document.select("div.post_read_more a");
            if(len_mInterfaces > 0){
                ((i_general_array) mInterfaces.get(0)).onArrayListLoaded(getSides(archives));
            }
            if(len_mInterfaces > 1){
                ((i_general_array) mInterfaces.get(1)).onArrayListLoaded(getSides(categories));
            }
            if(len_mInterfaces > 2){
                ((i_general_array) mInterfaces.get(2)).onArrayListLoaded(getShortBlog(blog_Titles, blog_dates, blog_postedBys,
                        blog_cats, imgUrls, shortTexts, readMoreUrls, blog_comments));
            }
        }
        catch (Exception ignored){
            System.out.println("Exception occurred");
            System.out.println(ignored.toString());
            System.out.println(ignored.getMessage());
            MainActivity.setErrorFrag("blog", url);
        }
        return null;
    }

    private ArrayList getShortBlog(Elements blog_titles, Elements blog_dates, Elements blog_postedBys, Elements blog_cats, Elements imgUrls, Elements shortTexts, Elements readMoreUrls, Elements blog_comments) {
        ArrayList l = new ArrayList<adapter_short_blog.short_blog>();
        int len = blog_titles.size();
        for (int i = 0; i < len; i++){
            adapter_short_blog.short_blog obj = new adapter_short_blog.short_blog(blog_titles.get(i).text(), blog_dates.get(i).text(),
                    blog_comments.get(i).text(), blog_postedBys.get(i).text(), blog_cats.get(i).text(), shortTexts.get(i).text(),
                    imgUrls.get(i).absUrl("src"), readMoreUrls.get(i).absUrl("href"));
            l.add(obj);
        }
        return l;
    }

    private ArrayList getSides(Elements side) {
        ArrayList si = new ArrayList<>();
        Elements title = side.select("h5");
        si.add(title.text());
        Elements subs = side.select("ul li a");
        int len = subs.size();
        for (int i = 0; i < len; i++){
            si.add(subs.get(i).text());
            si.add(subs.get(i).absUrl("href"));
        }
        return si;
    }
}

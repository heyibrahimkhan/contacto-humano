package contacto.humano.com.get_data_async;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import contacto.humano.com.MainActivity;
import contacto.humano.com.m_adapters.blog.adapter_short_blog;
import contacto.humano.com.m_interfaces.i_general_array;

/**
 * Created by Ibrahim Ali Khan on 10/22/2016.
 */

public class getBlogFull extends myGet {

    ArrayList<Object> mInterfaces;

    public getBlogFull(String blogUrl, ArrayList<Object> Interfaces){
        mInterfaces = Interfaces;
        url = blogUrl + MainActivity.lang;
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
            Elements sidebar = document.select("aside.widget.rpjc_widget_cat_recent_posts.widget_recent_entries");
//            Elements categories = document.select("aside#categories-2");
//            Elements sideBarElements = document.select("ul li a");

            Elements blog_Titles = document.select("h1.h2");
            Elements blog_dates = document.select("li.post_date");
            System.out.println("Clock = "+blog_dates.toString());
            Elements blog_postedBys = document.select("li.post_by");
            Elements blog_cats = document.select("li.post_cat");
            Elements blog_comments = document.select("div.comments_num a");
            Elements imgUrls = document.select("div.post_thumbnail img");
            Elements fullTexts = document.select("div.wpb_text_column p");
//            Elements readMoreUrls = document.select("div.post_read_more a");
            if(len_mInterfaces > 0){
                ((i_general_array) mInterfaces.get(0)).onArrayListLoaded(getSides(sidebar));
            }
            if(len_mInterfaces > 1){
                ((i_general_array) mInterfaces.get(1)).onArrayListLoaded(getFullBlog(blog_Titles, blog_dates, blog_postedBys,
                        blog_cats, imgUrls, fullTexts, blog_comments));
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

    private ArrayList getFullBlog(Elements blog_titles, Elements blog_dates, Elements blog_postedBys
            , Elements blog_cats, Elements imgUrls, Elements Texts, Elements blog_comments) {
        ArrayList l = new ArrayList<adapter_short_blog.short_blog>();
        String fullText = getTexts(Texts);
        int len = blog_titles.size();
        System.out.println("Blog len = "+len);
        for (int i = 0; i < len; i++){
            System.out.println();
            adapter_short_blog.short_blog obj = new adapter_short_blog.short_blog(blog_titles.get(i).text(), blog_dates.get(i).text(),
                    blog_comments.get(i).text(), blog_postedBys.get(i).text(), blog_cats.get(i).text(), fullText,
                    imgUrls.get(i).absUrl("src"), "");
            l.add(obj);
        }
        return l;
    }

    private String getTexts(Elements texts) {
        String s = "";
        int len = texts.size();
        for (int i = 0; i < len; i++){
            s = s.concat(texts.get(i).text()+"\n\n");
        }
        return s;
    }

    private ArrayList getSides(Elements sidebar) {
        ArrayList si = new ArrayList<>();
//        System.out.println("Title = "+sidebar.select("h5").text());
        si.add(sidebar.select("h5").text());
        Elements sidebarElements = sidebar.select("ul li a");
        int len = sidebarElements.size();
        for (int i = 0; i < len; i++){
//            System.out.println("sub = "+sidebarElements.get(i).text());
//            System.out.println("href = "+sidebarElements.get(i).absUrl("href"));
            si.add(sidebarElements.get(i).text());
            si.add(sidebarElements.get(i).absUrl("href"));
        }
        return si;
    }
}


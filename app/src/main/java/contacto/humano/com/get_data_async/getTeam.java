package contacto.humano.com.get_data_async;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import contacto.humano.com.MainActivity;
import contacto.humano.com.m_adapters.team.rv_t_mem_adapter;
import contacto.humano.com.m_interfaces.i_general_array;

/**
 * Created by Ibrahim Ali Khan on 10/12/2016.
 */

public class getTeam extends myGet {

    ArrayList<Object> mInterfaces;

    public getTeam(ArrayList<Object> Interfaces){
        mInterfaces = Interfaces;
        url = "http://con-tactohumano.com/team/" + MainActivity.lang;
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
            Elements names = document.select("div.vc_custom_heading.text_align_center h4");
            Elements text = document.select("div.wpb_wrapper p");
            Elements imagesUrl = document.select("img.vc_single_image-img");
            if (len_mInterfaces > 0){
                ((i_general_array) mInterfaces.get(0)).onArrayListLoaded(getData(imagesUrl, names, text));
            }
        }
        catch (Exception ignored){

        }
        return null;
    }

    private ArrayList getData(Elements imgUrl, Elements names, Elements text) {
//        ArrayList<Object> list = new ArrayList<>();
//        int len = imgUrl.size();
//        for (Element e : imgUrl){
//            list.add(e.absUrl("src"));
//        }
//        for (Element e : names){
//            list.add(e.text());
//        }
////        int len = occupation.size();
//        for (int i = 0; i < len; i+=2){
//            list.add(occupation.get(i).text());
//        }
//        for (int i = 1; i < len; i+=2){
//            list.add(occupation.get(i).text());
//        }
//        int list_size = list.size();
//        len = imgUrl.size();
        ArrayList l = new ArrayList<rv_t_mem_adapter.teamMember>();
//        for (int i = 0; i < list_size; i++){
//            if(i + (len * 3) < list_size){
//                rv_t_mem_adapter.teamMember obj = new rv_t_mem_adapter.teamMember(list.get(i).toString(), list.get(i + len).toString(),
//                        list.get(i + (len * 2)).toString(), list.get(i + (len * 3)).toString());
//                l.add(obj);
//            }
//        }
        int len = imgUrl.size();
        for (int i = 0, j = 0; i < len; i++, j+=2){
            rv_t_mem_adapter.teamMember obj = new rv_t_mem_adapter.teamMember(imgUrl.get(i).absUrl("src"), names.get(i).text(),
                    text.get(j).text(), text.get(j+1).text());
            l.add(obj);
        }

        return l;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

}

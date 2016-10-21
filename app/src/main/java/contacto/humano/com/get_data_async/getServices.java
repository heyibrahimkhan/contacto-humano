package contacto.humano.com.get_data_async;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import contacto.humano.com.MainActivity;
import contacto.humano.com.m_adapters.services.adapter_services;
import contacto.humano.com.m_interfaces.i_general;

/**
 * Created by Ibrahim Ali Khan on 10/13/2016.
 */

public class getServices extends myGet {

    ArrayList<Object> mInterfaces;
    private String type;

    public getServices(String Url, String Type, ArrayList<Object> Interfaces){
        mInterfaces = Interfaces;
        url = Url + MainActivity.lang;
        type = Type;
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
            if(type.equalsIgnoreCase("academics")){
                Elements imgUrl = document.select("a.woocommerce-LoopProduct-link img");
                Elements title = document.select("a.woocommerce-LoopProduct-link h3");
                Elements postedBy = document.select("div.author");
                Elements href = document.select("a.button.product_type_simple.ajax_add_to_cart.icon_right.bordered");
                Elements productId = document.select("a.button.product_type_simple.ajax_add_to_cart.icon_right.bordered");
                if(mInterfaces.size() > 0){
                    ((i_general) mInterfaces.get(0)).onArrayListLoaded(getData(imgUrl, title, postedBy, href, productId));
                }
            }
            else if(type.equalsIgnoreCase("professionals")){

            }
//            list.add(getImageUrl(document.select("img.alignright")));
//            list = getParas(document.select("div.wpb_wrapper p"), list);
//            if (len_mInterfaces > 0){
//                ((i_about_us) mInterfaces.get(0)).onAboutUsLoaded(list);
//            }
        }
        catch (Exception ignored){
            MainActivity.setErrorFrag(type, url);
        }
        return null;
    }

    private ArrayList getData(Elements imgUrl, Elements title, Elements postedBy, Elements href, Elements productId) {
        ArrayList l = new ArrayList<adapter_services.item_services>();
        int len = imgUrl.size();
        for (int i = 1; i < len; i++){ //Bug Here. No consistency
//            System.out.println("imgUrl = "+imgUrl.get(i).absUrl("src"));
//            System.out.println("title = "+title.get(i).text());
//            System.out.println("postedBy = "+postedBy.get(i).text());
//            System.out.println("href = "+productId.get(i).absUrl("href"));
//            System.out.println("productId = "+productId.get(i).attr("data-product_id"));
            adapter_services.item_services obj = new adapter_services.item_services(imgUrl.get(i).absUrl("src"),
                    productId.get(i).absUrl("href"), title.get(i).text(), productId.get(i).attr("data-product_id"),
                    postedBy.get(i).text());
            l.add(obj);
        }
        return l;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

}

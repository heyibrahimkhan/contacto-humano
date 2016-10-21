package contacto.humano.com.m_adapters.services;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import contacto.humano.com.R;
import contacto.humano.com.utils.BitmapWorkerTask;

/**
 * Created by Ibrahim Ali Khan on 10/13/2016.
 */

public class adapter_services extends RecyclerView.Adapter<adapter_services.item_services_holder> {

    public adapter_services(ArrayList list) {
        list_items = list;
    }

    @Override
    public adapter_services.item_services_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_academics, parent, false);
        return new item_services_holder(v);
    }

    @Override
    public void onBindViewHolder(adapter_services.item_services_holder holder, int position) {
        new BitmapWorkerTask(holder.iv, list_items.get(position).imgUrl, null).execute();
        holder.title.setText(list_items.get(position).title);
        holder.postedBy.setText(list_items.get(position).postedBy);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("CardView");
            }
        });
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }

    public class item_services_holder extends RecyclerView.ViewHolder {

        private CardView cv;
        private ImageView iv;
        private TextView title;
        private TextView postedBy;
        private Button addToCart;

        public item_services_holder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.ita_cv);
            iv = (ImageView) itemView.findViewById(R.id.ita_iv);
            title = (TextView) itemView.findViewById(R.id.ita_title);
            postedBy = (TextView) itemView.findViewById(R.id.ita_postedby);
            addToCart = (Button) itemView.findViewById(R.id.ita_cart);
        }

    }

    public ArrayList<item_services> list_items;

    public static class item_services{

        private String imgUrl, href, title, productId, postedBy;

        public item_services(String Url, String Href, String Title, String ProductId, String PostedBy){
            imgUrl = Url;
            href = Href;
            title = Title;
            productId = ProductId;
            postedBy = PostedBy;
        }

    }
}

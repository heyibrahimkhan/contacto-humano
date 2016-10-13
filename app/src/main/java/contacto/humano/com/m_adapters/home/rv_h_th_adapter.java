package contacto.humano.com.m_adapters.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import contacto.humano.com.R;

/**
 * Created by Ibrahim Ali Khan on 10/9/2016.
 */

public class rv_h_th_adapter extends  RecyclerView.Adapter<rv_h_th_adapter.test_author_holder>{

    public rv_h_th_adapter(ArrayList list) {
        list_test = list;
    }

    public class test_author_holder extends RecyclerView.ViewHolder{

        TextView title, detail, name;
        ImageView test_image;

        public test_author_holder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.test_name);
            title = (TextView) itemView.findViewById(R.id.test_title);
            detail = (TextView) itemView.findViewById(R.id.test_appraisal);
            test_image = (ImageView) itemView.findViewById(R.id.test_image);
        }
    }

    ArrayList<class_testimonial> list_test;

    public static class class_testimonial {
        public String title, text, name, imageUrl;

        public class_testimonial(String Url, String Name, String Title, String Text){
            imageUrl = Url;
            name = Name;
            title = Title;
            text = Text;
        }

        public class_testimonial(String Name, String Title, String Text){
            name = Name;
            title = Title;
            text = Text;
        }
    }

    @Override
    public test_author_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_testimon, parent, false);
        return new test_author_holder(v);
    }

    @Override
    public void onBindViewHolder(test_author_holder holder, int position) {
        holder.name.setText(list_test.get(position).name);
        holder.title.setText(list_test.get(position).title);
        holder.detail.setText(list_test.get(position).text);
//        holder.name.setText(list_test.get(position).name);
    }

    @Override
    public int getItemCount() {
        return list_test.size();
    }
}

package contacto.humano.com.m_adapters.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import contacto.humano.com.R;
import contacto.humano.com.m_interfaces.i_general_string;
import contacto.humano.com.utils.BitmapWorkerTask;

/**
 * Created by Ibrahim Ali Khan on 11/7/2016.
 */

public class rv_h_partners extends RecyclerView.Adapter<rv_h_partners.homePHolder> {

    private i_general_string igs;

    public rv_h_partners(ArrayList list, i_general_string Igs) {
        list_post = list;
        igs = Igs;
    }

    @Override
    public homePHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sim_img, parent, false);
        return new rv_h_partners.homePHolder(v);
    }

    @Override
    public void onBindViewHolder(homePHolder holder, int position) {
//        System.out.println("position = "+list_post.get(position));
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("IV Clicked");
                igs.onStringTransfer("");
            }
        });
        new BitmapWorkerTask(holder.iv, list_post.get(position), null).execute();
    }

    @Override
    public int getItemCount() {
        return list_post.size();
    }

    public class homePHolder extends RecyclerView.ViewHolder{

        private ImageView iv;

        public homePHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_h_partner);
        }
    }

//    public class

    ArrayList<String> list_post;
}

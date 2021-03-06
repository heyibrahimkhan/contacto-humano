package contacto.humano.com.m_adapters.home;

import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import contacto.humano.com.m_fragments.Frag_getHome;
import contacto.humano.com.R;
import contacto.humano.com.m_interfaces.i_general_string;
import contacto.humano.com.utils.BitmapWorkerTask;

/**
 * Created by Ibrahim Ali Khan on 10/9/2016.
 */

public class rv_hp_adapter extends RecyclerView.Adapter<rv_hp_adapter.homepostHolder> {

    static int imagesDownlaoded = 0;
    private int timeInt;
    private i_general_string mListener;

    public rv_hp_adapter(ArrayList<class_home_post> list, i_general_string Listener) {
        list_post = list;
        imagesDownlaoded = 0;
        mListener = Listener;
    }

    public class homepostHolder extends RecyclerView.ViewHolder{

        private CardView cv;
        private ImageView hp_image;
        private TextView hp_title;
        private TextView hp_time;

        public homepostHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv_fhp);
            hp_image = (ImageView) itemView.findViewById(R.id.home_post_image);
            hp_title = (TextView) itemView.findViewById(R.id.home_post_title);
            hp_time = (TextView) itemView.findViewById(R.id.home_post_time);
        }
    }

    ArrayList<class_home_post> list_post;

    public static class class_home_post{

        public String name, url_image, url_goto, date;

        public class_home_post(String Url_Image, String Name, String Url_Goto, String Date){
            name = Name;
            url_image = Url_Image;
            url_goto = Url_Goto;
            date = Date;
        }

    }

    @Override
    public rv_hp_adapter.homepostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_post, parent, false);
        return new homepostHolder(v);
    }

    @Override
    public void onBindViewHolder(rv_hp_adapter.homepostHolder holder, final int position) {
        holder.hp_title.setText(list_post.get(position).name);
        holder.hp_time.setText(list_post.get(position).date);
        new BitmapWorkerTask(holder.hp_image, list_post.get(position).url_image, new BitmapWorkerTask.downloadCompleteCallBack() {
            @Override
            public void onDownComp(Bitmap bitmap) {
                System.out.println("Bitmap Downloaded");
                Frag_getHome.fadeBitmap.add(bitmap);
                if(timeInt < 1) {
                    Frag_getHome.startFadeAnim();
                    timeInt++;
                }
            }
        }).execute();
        if(mListener != null) {
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onStringTransfer(list_post.get(position).url_goto);
                }
            });
        }
        //logic for going to link, if goto link is null don't do anything
    }

    @Override
    public int getItemCount() {
        return list_post.size();
    }
}

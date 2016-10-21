package contacto.humano.com.m_adapters.blog;

import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import contacto.humano.com.R;
import contacto.humano.com.utils.BitmapWorkerTask;

/**
 * Created by Ibrahim Ali Khan on 10/20/2016.
 */

public class adapter_short_blog extends RecyclerView.Adapter<adapter_short_blog.holder_short_blog> {

    private ArrayList<short_blog> itemList;

    public adapter_short_blog(ArrayList list){
        itemList = list;
    }

    public static class short_blog{

        private String title, date, comments, postedBy, category, text, urlIv, urlReadMore;

        public short_blog(String Title, String Date_Time, String Comments, String PostedBy, String Category, String Text, String UrlIv, String UrlReadMore){
            title = Title;
            date = Date_Time;
            comments = Comments;
            postedBy = PostedBy;
            category = Category;
            text = Text;
            urlIv = UrlIv;
            urlReadMore = UrlReadMore;
        }

    }

    @Override
    public holder_short_blog onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog_short, parent, false);
        return new holder_short_blog(v);
    }

    @Override
    public void onBindViewHolder(holder_short_blog holder, int position) {
        holder.tv_title.setText(itemList.get(position).title);
        holder.tv_date.setText(itemList.get(position).date);
        holder.tv_postedBy.setText(itemList.get(position).postedBy);
        holder.tv_category.setText(itemList.get(position).category);
        holder.tv_text.setText(itemList.get(position).text);
        holder.tv_comments.setText(itemList.get(position).comments);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("CardView Clicked");
            }
        });
        new BitmapWorkerTask(holder.iv, itemList.get(position).urlIv, null).execute();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class holder_short_blog extends RecyclerView.ViewHolder {

        private CardView cv;
        private TextView tv_title, tv_date, tv_comments, tv_postedBy, tv_category, tv_text;
        private ImageView iv;

        public holder_short_blog(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.ibs_cv);
            tv_title = (TextView) itemView.findViewById(R.id.ibs_title);
            tv_date = (TextView) itemView.findViewById(R.id.ibs_tv_time);
            tv_category = (TextView) itemView.findViewById(R.id.ibs_tv_category);
            tv_comments = (TextView) itemView.findViewById(R.id.ibs_tv_comments);
            tv_postedBy = (TextView) itemView.findViewById(R.id.ibs_tv_postedby);
            tv_text = (TextView) itemView.findViewById(R.id.ibs_tv_postmat);
            iv = (ImageView) itemView.findViewById(R.id.ibs_iv_post);
        }
    }

}

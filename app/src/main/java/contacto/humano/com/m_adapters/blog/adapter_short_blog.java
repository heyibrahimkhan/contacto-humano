package contacto.humano.com.m_adapters.blog;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import contacto.humano.com.R;
import contacto.humano.com.m_interfaces.i_general_string;
import contacto.humano.com.utils.BitmapWorkerTask;

/**
 * Created by Ibrahim Ali Khan on 10/20/2016.
 */

public class adapter_short_blog extends RecyclerView.Adapter<adapter_short_blog.holder_short_blog> {

    private ArrayList<short_blog> itemList;
    private i_general_string igs;

    public adapter_short_blog(ArrayList list, i_general_string Igs){
        itemList = list;
        igs = Igs;
    }

    public adapter_short_blog(ArrayList list){
        itemList = list;
    }

    public static class short_blog{

        private String title;
        private String date;
        private String comments;
        private String postedBy;
        private String category;
        private String text;
        private String urlIv;
        private String urlReadMore;

        public short_blog(String Title, String Date_Time, String Comments, String PostedBy, String Category, String Text, String UrlIv, String UrlReadMore){
            setTitle(Title);
            setDate(Date_Time);
            setComments(Comments);
            setPostedBy(PostedBy);
            setCategory(Category);
            setText(Text);
            setUrlIv(UrlIv);
            setUrlReadMore(UrlReadMore);
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getPostedBy() {
            return postedBy;
        }

        public void setPostedBy(String postedBy) {
            this.postedBy = postedBy;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUrlIv() {
            return urlIv;
        }

        public void setUrlIv(String urlIv) {
            this.urlIv = urlIv;
        }

        public String getUrlReadMore() {
            return urlReadMore;
        }

        public void setUrlReadMore(String urlReadMore) {
            this.urlReadMore = urlReadMore;
        }
    }

    @Override
    public holder_short_blog onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog_short, parent, false);
        return new holder_short_blog(v);
    }

    @Override
    public void onBindViewHolder(holder_short_blog holder, final int position) {
        holder.tv_title.setText(itemList.get(position).getTitle());
        holder.tv_date.setText(itemList.get(position).getDate());
        holder.tv_postedBy.setText(itemList.get(position).getPostedBy());
        holder.tv_category.setText(itemList.get(position).getCategory());
        holder.tv_text.setText(itemList.get(position).getText());
        holder.tv_comments.setText(itemList.get(position).getComments());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("CardView Clicked");
                igs.onStringTransfer(itemList.get(position).getUrlReadMore());
            }
        });
        new BitmapWorkerTask(holder.iv, itemList.get(position).getUrlIv(), null).execute();
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

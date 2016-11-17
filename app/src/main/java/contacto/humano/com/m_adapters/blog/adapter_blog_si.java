package contacto.humano.com.m_adapters.blog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import contacto.humano.com.R;
import contacto.humano.com.m_interfaces.i_general_string;

/**
 * Created by Ibrahim Ali Khan on 10/19/2016.
 */

public class adapter_blog_si extends RecyclerView.Adapter<adapter_blog_si.b_si_holder> {

    private ArrayList<blog_side_item> item_list;
    private Context mContext;
    private i_general_string igs;

    public adapter_blog_si(Context context, ArrayList list, i_general_string Igs){
//        System.out.println("Setting Adapter");
        mContext = context;
        item_list = list;
        int len = item_list.size();
        for (int i = 0; i < len; i++){
//            System.out.println("List Item = "+item_list.get(i).text);
        }
        igs = Igs;
    }

    @Override
    public b_si_holder onCreateViewHolder(ViewGroup parent, int viewType) {
//        System.out.println("On Create View Holder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_side, parent, false);
        return new b_si_holder(v);
    }

    @Override
    public void onBindViewHolder(b_si_holder holder, final int position) {
//        System.out.println("On Bind View Holder");
        System.out.println("Text = "+item_list.get(position).text);
        holder.tv_text.setText(item_list.get(position).text);
        holder.tv_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                igs.onStringTransfer(item_list.get(position).href);
//                System.out.println("This is the href = "+item_list.get(position).href);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }

    public static class blog_side_item{

        private String href, text;

        public blog_side_item(String Text, String Href){
            text = Text;
            href = Href;
        }

    }

    public class b_si_holder extends RecyclerView.ViewHolder{

        private TextView tv_text;

        public b_si_holder(View itemView) {
            super(itemView);
            tv_text = (TextView) itemView.findViewById(R.id.si_tv);
//            tv_text.setTextColor(mContext.getResources().getColor(R.color.mblack));
        }
    }
}

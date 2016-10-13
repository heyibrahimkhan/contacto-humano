package contacto.humano.com.m_adapters.team;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import contacto.humano.com.R;
import contacto.humano.com.utils.BitmapWorkerTask;

/**
 * Created by Ibrahim Ali Khan on 10/12/2016.
 */

public class rv_t_mem_adapter extends RecyclerView.Adapter<rv_t_mem_adapter.holder_team_member> {

    public ArrayList<teamMember> list_team;

    public rv_t_mem_adapter(ArrayList list){
        list_team = new ArrayList<>();
        list_team = list;
        System.out.println(list_team.size()+" is the size");
//        for (int i = 0; i < len; i++){
//            System.out.println(i+" = "+list_team.get(i).mem_name);
//            System.out.println(i+" = "+list_team.get(i).mem_occu);
//            System.out.println(i+" = "+list_team.get(i).mem_about);
//            System.out.println(i+" = "+list_team.get(i).mem_imgUrl);
//        }
    }

    public static class teamMember{

        public String mem_imgUrl, mem_name, mem_occu, mem_about;

        public teamMember(String url, String name, String occupation, String about){
            mem_imgUrl = url;
            mem_name = name;
            mem_occu = occupation;
            mem_about = about;
        }

    }

    @Override
    public holder_team_member onCreateViewHolder(ViewGroup parent, int viewType) {
//        System.out.println("On Create View");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_mem, parent, false);
        return new holder_team_member(v);
    }

    @Override
    public void onBindViewHolder(holder_team_member holder, int position) {
        holder.tv_name.setText(list_team.get(position).mem_name);
        holder.tv_occupation.setText(list_team.get(position).mem_occu);
        holder.tv_message.setText(list_team.get(position).mem_about);
        new BitmapWorkerTask(holder.iv, list_team.get(position).mem_imgUrl, null).execute();
    }

    @Override
    public int getItemCount() {
        return list_team.size();
    }

    public class holder_team_member extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView tv_name, tv_occupation, tv_message;

        public holder_team_member(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.itm_iv);
            tv_name = (TextView) itemView.findViewById(R.id.itm_name);
            tv_occupation = (TextView) itemView.findViewById(R.id.itm_occupation);
            tv_message = (TextView) itemView.findViewById(R.id.itm_about);
        }
    }
}

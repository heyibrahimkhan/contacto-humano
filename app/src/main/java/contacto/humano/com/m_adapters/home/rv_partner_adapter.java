package contacto.humano.com.m_adapters.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import contacto.humano.com.R;

/**
 * Created by Ibrahim Ali Khan on 10/9/2016.
 */

public class rv_partner_adapter extends RecyclerView.Adapter<rv_partner_adapter.PartnerHolder> {

    public class PartnerHolder extends RecyclerView.ViewHolder {

        ImageView partnerImage;

        public PartnerHolder(View itemView) {
            super(itemView);
            partnerImage = (ImageView) itemView.findViewById(R.id.partner_image);
        }
    }

    @Override
    public PartnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_partner, parent, false);
        return new PartnerHolder(v);
    }

    @Override
    public void onBindViewHolder(PartnerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

package com.imuons.saddaadda.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.saddaadda.DataModel.SaddaxReportDataModel;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.View.ActivitySaddaShatak;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemSaddaShatakListView extends RecyclerView.Adapter<ItemSaddaShatakListView.ViewHolder> {
    Context context;
    List<SaddaxReportDataModel> listmodel;

    public ItemSaddaShatakListView(Context applicationContext, ActivitySaddaShatak activitySaddaShatak, ArrayList<SaddaxReportDataModel> data) {
        context = applicationContext;
        listmodel = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_sadda_shatak_list_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SaddaxReportDataModel dataModel = listmodel.get(position);
        holder.txt_number.setText(dataModel.getProductName());
        holder.txt_amount.setText("Rs." + dataModel.getAmount());
    }


    @Override
    public int getItemCount() {
        return listmodel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_minus)
        ImageView iv_minus;
        @BindView(R.id.iv_plus)
        ImageView iv_plus;
        @BindView(R.id.txt_amount)
        TextView txt_amount;
        @BindView(R.id.txt_number)
        TextView txt_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

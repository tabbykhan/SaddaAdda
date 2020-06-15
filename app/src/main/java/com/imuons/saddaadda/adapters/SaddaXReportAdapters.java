package com.imuons.saddaadda.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.saddaadda.DataModel.SaddaxReportDataModel;
import com.imuons.saddaadda.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaddaXReportAdapters extends RecyclerView.Adapter<SaddaXReportAdapters.SaddaXHolder> {

    Activity activity;
    //ArrayList<SaddaxReportDataModel> reportDataArrayList;
    ArrayList<SaddaxReportDataModel> reportDataArrayList;

    public SaddaXReportAdapters(Activity activity, ArrayList<SaddaxReportDataModel> reportDataArrayList) {
        this.activity = activity;
        this.reportDataArrayList = reportDataArrayList;
    }

    @NonNull
    @Override
    public SaddaXHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_saddax_list, parent, false);
        return new SaddaXHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SaddaXReportAdapters.SaddaXHolder holder, int position) {
        SaddaxReportDataModel reportData = reportDataArrayList.get(position);
        holder.txtProductName.setText(reportData.getProductName());

        holder.txtAmount.setText(activity.getString(R.string.rs)+ String.valueOf(reportData.getAmount()));

    }



    @Override
    public int getItemCount() {
        return reportDataArrayList.size();
    }
    public void update(ArrayList<SaddaxReportDataModel> reportData) {
        reportDataArrayList = reportData;
        notifyDataSetChanged();
    }

    public void update(ArrayList<SaddaxReportDataModel> reportData, int pos) {
        reportDataArrayList = reportData;
        notifyItemChanged(pos);
    }


    public class SaddaXHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtProductName)
        TextView txtProductName;
        @BindView(R.id.txtAmount)
        TextView txtAmount;


        public SaddaXHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}

package com.imuons.saddaadda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportHolder> {
    @NonNull
    @Override
    public ReportHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_report, parent, false);
        return new ReportHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ReportHolder extends RecyclerView.ViewHolder {
        public ReportHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

package com.imuons.saddaadda.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.saddaadda.DataModel.CompleteSlotRecord;
import com.imuons.saddaadda.DataModel.WinningDateRecord;
import com.imuons.saddaadda.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WinningDateAdapter extends RecyclerView.Adapter<WinningDateAdapter.WinningDateHolder> {

    Activity activity;
    ArrayList<WinningDateRecord> reportDataArrayList;

    public WinningDateAdapter(Activity activity, ArrayList<WinningDateRecord> reportDataArrayList) {
        this.activity = activity;
        this.reportDataArrayList = reportDataArrayList;
    }

    @NonNull
    @Override
    public WinningDateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_date, parent, false);
        return new WinningDateAdapter.WinningDateHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WinningDateHolder holder, int position) {
        WinningDateRecord winningDateRecord = reportDataArrayList.get(position);
        if (winningDateRecord.getDate() != null) {
            holder.winnigDate.setText(String.valueOf(winningDateRecord.getDate()));

        } else {
            holder.winnigDate.setText("Select Date");
        }
    }

    @Override
    public int getItemCount() {
        return reportDataArrayList.size();
    }
    public void update(ArrayList<WinningDateRecord> reportData) {
        reportDataArrayList = reportData;
        notifyDataSetChanged();
    }

    public void update(ArrayList<WinningDateRecord> reportData, int pos) {
        reportDataArrayList = reportData;
        notifyItemChanged(pos);
    }

    public class WinningDateHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.winnigDate)
        TextView winnigDate;

        public WinningDateHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

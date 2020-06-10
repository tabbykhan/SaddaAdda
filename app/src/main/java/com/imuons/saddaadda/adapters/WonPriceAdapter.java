package com.imuons.saddaadda.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.saddaadda.DataModel.TopupDatum;
import com.imuons.saddaadda.DataModel.WinningDateRecord;
import com.imuons.saddaadda.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WonPriceAdapter extends RecyclerView.Adapter<WonPriceAdapter.WonPriceHolder> {

    Activity activity;
    ArrayList<TopupDatum> reportDataArrayList;

    public WonPriceAdapter(Activity activity, ArrayList<TopupDatum> reportDataArrayList) {
        this.activity = activity;
        this.reportDataArrayList = reportDataArrayList;
    }

    @NonNull
    @Override
    public WonPriceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_won_number, parent, false);
        return new WonPriceAdapter.WonPriceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WonPriceHolder holder, int position) {
        TopupDatum winningDateRecord = reportDataArrayList.get(position);
        if (winningDateRecord.getAmount() != null) {
            holder.amount.setText(String.valueOf(winningDateRecord.getAmount()));

        } else {
            holder.amount.setText("");
        }
        if (winningDateRecord.getProductName() != null) {
            holder.number.setText(String.valueOf(winningDateRecord.getProductName()));

        } else {
            holder.number.setText("");
        }
        if (winningDateRecord.getStatus() != null) {
            holder.status.setText(String.valueOf(winningDateRecord.getStatus()));

        } else {
            holder.status.setText("");
        }
        if (winningDateRecord.getWinningAmount() != null) {
            holder.Winning_money.setText(String.valueOf(winningDateRecord.getWinningAmount()));

        } else {
            holder.Winning_money.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return reportDataArrayList.size();
    }

    public void update(ArrayList<TopupDatum> reportData) {
        reportDataArrayList = reportData;
        notifyDataSetChanged();
    }

    public void update(ArrayList<TopupDatum> reportData, int pos) {
        reportDataArrayList = reportData;
        notifyItemChanged(pos);
    }

    public class WonPriceHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.Winning_money)
        TextView Winning_money;

        public WonPriceHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}



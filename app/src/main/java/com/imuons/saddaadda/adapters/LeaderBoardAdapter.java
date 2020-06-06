package com.imuons.saddaadda.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.saddaadda.DataModel.CompleteSlotRecord;
import com.imuons.saddaadda.DataModel.UpcomingSlotData;
import com.imuons.saddaadda.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.LeaderBoardHolder> {


    Activity activity;
    ArrayList<CompleteSlotRecord> reportDataArrayList;

    public LeaderBoardAdapter(Activity activity, ArrayList<CompleteSlotRecord> reportDataArrayList) {
        this.activity = activity;
        this.reportDataArrayList = reportDataArrayList;
    }

    @NonNull
    @Override
    public LeaderBoardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_leaderboard_slot, parent, false);
        return new LeaderBoardAdapter.LeaderBoardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderBoardHolder holder, int position) {
        CompleteSlotRecord completeSlotRecord = reportDataArrayList.get(position);
        if (completeSlotRecord.getDate()!=null){
            holder.date.setText(String.valueOf(completeSlotRecord.getDate()));

        }else {
            holder.date.setText("");
        }
        if (completeSlotRecord.getTime()!=null){
            holder.time.setText(String.valueOf(completeSlotRecord.getTime()));

        }else {
            holder.time.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return reportDataArrayList.size();
    }

    public void update(ArrayList<CompleteSlotRecord> reportData) {
        reportDataArrayList = reportData;
        notifyDataSetChanged();
    }

    public void update(ArrayList<CompleteSlotRecord> reportData, int pos) {
        reportDataArrayList = reportData;
        notifyItemChanged(pos);
    }

    public class LeaderBoardHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.time)
        TextView time;

        public LeaderBoardHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

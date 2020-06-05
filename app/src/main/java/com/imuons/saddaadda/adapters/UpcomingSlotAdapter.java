package com.imuons.saddaadda.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.saddaadda.DataModel.ReportData;
import com.imuons.saddaadda.DataModel.UpcomingSlotData;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.View.UpcomingSlotActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpcomingSlotAdapter extends RecyclerView.Adapter<UpcomingSlotAdapter.UpcomingSlotHolder> {

    Activity activity;
    ArrayList<UpcomingSlotData> reportDataArrayList;

    public UpcomingSlotAdapter(Activity activity, ArrayList<UpcomingSlotData> reportDataArrayList) {
        this.activity = activity;
        this.reportDataArrayList = reportDataArrayList;
    }

    @NonNull
    @Override
    public UpcomingSlotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_upcoming_slot, parent, false);
        return new UpcomingSlotHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingSlotHolder holder, int position) {
        UpcomingSlotData upcomingSlotData = reportDataArrayList.get(position);
        holder.price.setText(String.valueOf(upcomingSlotData.getEntryTime()));
        holder.dateTime.setText(String.valueOf(upcomingSlotData.getEntryTime()));
        holder.starDate.setText(String.valueOf(upcomingSlotData.getFromTime()));
        holder.starTime.setText(String.valueOf(upcomingSlotData.getToTime()));
       // holder.ticketPrice.setText(String.valueOf(upcomingSlotData.getSlotNo()));


    }

    @Override
    public int getItemCount() {
        return reportDataArrayList.size();
    }

    public void update(ArrayList<UpcomingSlotData> reportData) {
        reportDataArrayList = reportData;
        notifyDataSetChanged();
    }

    public void update(ArrayList<UpcomingSlotData> reportData, int pos) {
        reportDataArrayList = reportData;
        notifyItemChanged(pos);
    }

    public class UpcomingSlotHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dateTime)
        TextView dateTime;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.starDate)
        TextView starDate;
        @BindView(R.id.starTime)
        TextView starTime;
        @BindView(R.id.ticketPrice)
        TextView ticketPrice;
        @BindView(R.id.btnJoin)
        TextView btnJoin;


        public UpcomingSlotHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

      /*  @OnClick({R.id.expend_list ,R.id.expend_image , R.id.sno })
        void expend() {
            ((UpcomingSlotActivity) activity).checkOpen(getAdapterPosition());

        }*/
    }

}

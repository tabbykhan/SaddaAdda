package com.imuons.saddaadda.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.saddaadda.DataModel.UpcomingSlotData;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.View.ActivitySaddaShatak;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaddaShatakUpcomingSlotAdapter extends RecyclerView.Adapter<SaddaShatakUpcomingSlotAdapter.UpcomingSlotHolder> {

    Activity activity;
    ArrayList<UpcomingSlotData> reportDataArrayList;

    public SaddaShatakUpcomingSlotAdapter(Activity activity, ArrayList<UpcomingSlotData> reportDataArrayList) {
        this.activity = activity;
        this.reportDataArrayList = reportDataArrayList;
    }

    @NonNull
    @Override
    public UpcomingSlotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shatak_upcoming_slot, parent, false);
        return new UpcomingSlotHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingSlotHolder holder, int position) {
        UpcomingSlotData upcomingSlotData = reportDataArrayList.get(position);
        holder.row.setAnimation(AnimationUtils.loadAnimation(activity,R.anim.item_animation_fall_down));
        holder.price.setText(String.valueOf(upcomingSlotData.getSlotNo()));
       // holder.dateTime.setText(parseDate1(String.valueOf(upcomingSlotData.getEntryTime())));
        holder.dateTime.setText(String.valueOf(upcomingSlotData.getFromDaytime()));
        holder.starDate.setText(String.valueOf(upcomingSlotData.getFromDate()));
        holder.starTime.setText(String.valueOf(upcomingSlotData.getFromTime()));
        holder.endDate.setText(String.valueOf(upcomingSlotData.getToDate()));
        holder.endTime.setText(String.valueOf(upcomingSlotData.getToTime()));
        holder.btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slotid = upcomingSlotData.getSlotNo(); // get Id
                Intent intent = new Intent(activity, ActivitySaddaShatak.class);
                intent.putExtra("pos", slotid); // Pass Id
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
            }
        });

    }

    private String parseDate1(String entryTime) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "EEEE , h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(entryTime);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;

    }

    private String parseDate(String entryTime) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd MMM yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(entryTime);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;

    }

    private String parseDate2(String entryTime) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "h : mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(entryTime);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;

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
        @BindView(R.id.endDate)
        TextView endDate;
        @BindView(R.id.endTime)
        TextView endTime;
        @BindView(R.id.btnJoin)
        TextView btnJoin;
        @BindView(R.id.row)
        RelativeLayout row;


        public UpcomingSlotHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

       /* @OnClick(R.id.btnJoin)
        void expend() {
            ((UpcomingSlotActivity) activity).Open(getAdapterPosition());

        }*/
    }

}

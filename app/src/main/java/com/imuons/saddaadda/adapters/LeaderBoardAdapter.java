package com.imuons.saddaadda.adapters;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.saddaadda.DataModel.CompleteSlotRecord;
import com.imuons.saddaadda.DataModel.UpcomingSlotData;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.View.LeaderBoardActivity;
import com.imuons.saddaadda.View.SevenUpDown;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.LeaderBoardHolder> {

    AnimationDrawable anim;
    Activity activity;
    ArrayList<CompleteSlotRecord> reportDataArrayList;
    int selectedPos = -1;

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
        if(completeSlotRecord.isSeleted()){
            holder.time.setTextColor(activity.getResources().getColor(R.color.green));
           // holder.slot.setBackgroundTintList(activity.getResources().getColorStateList(R.color.green));
        }else {
            holder.time.setTextColor(activity.getResources().getColor(R.color.yellow));
        }
        holder.slot.setAnimation(AnimationUtils.loadAnimation(activity, R.anim.item_animation_fall_down));

        if (completeSlotRecord.getDate() != null) {
            holder.date.setText(String.valueOf(completeSlotRecord.getDate()));

        } else {
            holder.date.setText("");
        }
        if (completeSlotRecord.getTime() != null) {
            holder.time.setText(String.valueOf(completeSlotRecord.getTime()));

        } else {
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
        //reportDataArrayList = reportData;
        reportDataArrayList.clear();
        reportDataArrayList.addAll(reportData);
        notifyItemChanged(pos);
    }

   /* public void selectedBox(ArrayList<CompleteSlotRecord> reportDataArrayList) {
       this.reportDataArrayList = reportDataArrayList;
       // selectedPos = adapterPosition;
        notifyDataSetChanged();
       // notifyItemChanged(selectedPos);
    }
*/
    public class LeaderBoardHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.slot)
        LinearLayout slot;

        public LeaderBoardHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        @OnClick(R.id.slot)
        void selectSlot(){

            ((LeaderBoardActivity)activity).selectedSlot(getAdapterPosition());
            //reportDataArrayList.get(getAdapterPosition()).setSeleted(true);
        }

    }

}

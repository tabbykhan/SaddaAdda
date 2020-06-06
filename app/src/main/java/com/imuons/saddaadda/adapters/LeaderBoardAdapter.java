package com.imuons.saddaadda.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.saddaadda.R;

import butterknife.BindView;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.LeaderBoardHolder> {


    @NonNull
    @Override
    public LeaderBoardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderBoardHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class LeaderBoardHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.time)
        TextView time;

        public LeaderBoardHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

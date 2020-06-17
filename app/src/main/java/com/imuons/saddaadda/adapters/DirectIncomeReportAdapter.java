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

import com.imuons.saddaadda.DataModel.DirectRecord;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.View.IncomeDirectReport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DirectIncomeReportAdapter extends RecyclerView.Adapter<DirectIncomeReportAdapter.DirectHolder> {
    Activity activity;
    ArrayList<DirectRecord> directRecordArrayList;
    public DirectIncomeReportAdapter(Activity activity, ArrayList<DirectRecord> directRecordArrayList) {
        this.activity = activity;
        this.directRecordArrayList = directRecordArrayList;
    }

    @NonNull
    @Override
    public DirectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.direct_report, parent, false);
        return new DirectHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DirectHolder holder, int position) {
        if (position != 0) {
            holder.top_bar.setVisibility(View.GONE);
            holder.nxt.setVisibility(View.VISIBLE);
            DirectRecord reportData = directRecordArrayList.get(position);
            holder.expend_image.setVisibility(View.GONE);
            reportData.setOpen(true);
            if (reportData.isOpen()) {
                holder.dropdown_menu.setVisibility(View.VISIBLE);
                holder.expend_image.setImageDrawable(activity.getResources().getDrawable(R.drawable.expend_1));
            } else {
                holder.dropdown_menu.setVisibility(View.GONE);
                holder.expend_image.setImageDrawable(activity.getResources().getDrawable(R.drawable.expend));
            }
            holder.sno.setText(String.valueOf(position));
            holder.amt.setText(String.valueOf(reportData.getAmount()));
            //holder.cBal.setText(String.valueOf(reportData.getAmount()));
            //  holder.pBal.setText(String.valueOf(reportData.getPrevBalance()));
           // holder.remark.setText(reportData.getRemark());
            holder.status.setText(reportData.getStatus());
            holder.date.setText(parseDate(reportData.getEntryTime()));
            //holder.type.setText(reportData.getType());
        } else {
            holder.top_bar.setVisibility(View.VISIBLE);
            holder.nxt.setVisibility(View.GONE);
            holder.dropdown_menu.setVisibility(View.GONE);
        }
    }
    private String parseDate(String entryTime) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd-MMM-yy";
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
        return directRecordArrayList.size();
    }

    public void updateRecord(ArrayList<DirectRecord> directRecordArrayList) {
        this.directRecordArrayList = directRecordArrayList;
        notifyDataSetChanged();
    }

    public void update(ArrayList<DirectRecord> directRecordArrayList, int adapterPosition) {
        this.directRecordArrayList = directRecordArrayList;
        notifyItemChanged(adapterPosition);
    }

    public class DirectHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sno)
        TextView sno;

        @BindView(R.id.amt)
        TextView amt;


      /*  @BindView(R.id.cBal)
        TextView cBal;*/
        @BindView(R.id.date)
        TextView date;


        @BindView(R.id.tranType)
        TextView status;

        @BindView(R.id.nxt)
        RelativeLayout nxt;

        @BindView(R.id.expend_image)
        ImageView expend_image;

        @BindView(R.id.top_bar)
        LinearLayout top_bar;

        @BindView(R.id.dropdown_menu)
        RelativeLayout dropdown_menu;

        public DirectHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
        @OnClick({R.id.expend_list ,R.id.expend_image , R.id.sno })
        void expend() {
            ((IncomeDirectReport) activity).checkOpen(getAdapterPosition());

        }
    }
}

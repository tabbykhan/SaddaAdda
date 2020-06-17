package com.imuons.saddaadda;

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
import com.imuons.saddaadda.DataModel.SellRecord;
import com.imuons.saddaadda.View.ReportActivity;
import com.imuons.saddaadda.View.SellHistoryReportActivity;
import com.imuons.saddaadda.responseModel.SellHistoryReport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SellReportAdapter extends RecyclerView.Adapter<SellReportAdapter.SellHolder> {
    Activity activity;
    ArrayList<SellRecord> reportDataArrayList;
    int offset = 20;

    public SellReportAdapter(Activity activity, ArrayList<SellRecord> sellRecordArrayList) {
        this.activity = activity;
        this.reportDataArrayList = sellRecordArrayList;
    }

    @NonNull
    @Override
    public SellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sell, parent, false);
        return new SellHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull SellHolder holder, int position) {
        if (position != 0) {
            holder.top_bar.setVisibility(View.GONE);
            holder.nxt.setVisibility(View.VISIBLE);
            SellRecord reportData = reportDataArrayList.get(position);
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
            holder.cBal.setText(String.valueOf(reportData.getAmount()));
         //   holder.pBal.setText(String.valueOf(reportData.getPrevBalance()));
            holder.remark.setText(reportData.getRemark());
            holder.status.setText(reportData.getStatus());

            holder.tranDate.setText(parseDate(reportData.getSellDate()));
            //holder.type.setText(reportData.getType());


        } else {
            holder.top_bar.setVisibility(View.VISIBLE);
            holder.nxt.setVisibility(View.GONE);
            holder.dropdown_menu.setVisibility(View.GONE);
        }
        if(position == offset){
            if (activity instanceof SellHistoryReportActivity)
                ((SellHistoryReportActivity) activity).callapi(position);
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

    public void updateList(ArrayList<SellRecord> reportData , int offsetLevel) {
        offset = 20*(offsetLevel+1);
        reportDataArrayList = reportData;
        notifyDataSetChanged();
    }
    public void update(ArrayList<SellRecord> reportData, int pos) {
        reportDataArrayList = reportData;
        notifyItemChanged(pos);
    }

    @Override
    public int getItemCount() {
        return reportDataArrayList.size();
    }

    public class SellHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sno)
        TextView sno;

        @BindView(R.id.amt)
        TextView amt;


        @BindView(R.id.cBal)
        TextView cBal;
        @BindView(R.id.tranDate)
        TextView tranDate;
        @BindView(R.id.rmark)
        TextView remark;
        @BindView(R.id.status)
        TextView status;

        @BindView(R.id.nxt)
        RelativeLayout nxt;

        @BindView(R.id.expend_image)
        ImageView expend_image;

        @BindView(R.id.top_bar)
        LinearLayout top_bar;

        @BindView(R.id.dropdown_menu)
        RelativeLayout dropdown_menu;

        public SellHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.expend_list, R.id.expend_image, R.id.sno})
        void expend() {
            ((SellHistoryReportActivity) activity).checkOpen(getAdapterPosition());

        }
    }
}

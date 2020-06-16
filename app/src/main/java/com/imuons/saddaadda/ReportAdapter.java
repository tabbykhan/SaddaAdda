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

import com.imuons.saddaadda.DataModel.BuyRecord;
import com.imuons.saddaadda.DataModel.ReportData;
import com.imuons.saddaadda.View.BuyActivityHistory;
import com.imuons.saddaadda.View.ReportActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportHolder> {
    Activity activity;
    ArrayList<ReportData> reportDataArrayList;
    int offset = 20;

    public ReportAdapter(Activity activity, ArrayList<ReportData> reportDataArrayList) {
        this.activity = activity;
        this.reportDataArrayList = reportDataArrayList;
    }

    @NonNull
    @Override
    public ReportHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_report_new, parent, false);
        return new ReportHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportHolder holder, int position) {
        if (position != 0) {
            holder.top_bar.setVisibility(View.GONE);
            holder.nxt.setVisibility(View.VISIBLE);
            ReportData reportData = reportDataArrayList.get(position);
            if (reportData.isOpen()) {
                holder.dropdown_menu.setVisibility(View.VISIBLE);
                holder.expend_image.setImageDrawable(activity.getResources().getDrawable(R.drawable.expend_1));
            } else {
                holder.dropdown_menu.setVisibility(View.GONE);
                holder.expend_image.setImageDrawable(activity.getResources().getDrawable(R.drawable.expend));
            }
            holder.sno.setText(String.valueOf(position));
            holder.amt.setText(String.valueOf(reportData.getAmount()));
            holder.cBal.setText(String.valueOf(reportData.getBalance()));
            holder.pBal.setText(String.valueOf(reportData.getPrevBalance()));
            holder.remark.setText(reportData.getRemark());
            holder.tranType.setText(reportData.getType());
            holder.tranDate.setText(parseDate(reportData.getEntryTime()));

            //holder.type.setText(reportData.getType());
           /* if(position == reportDataArrayList.size()-1){
                holder.botto_line.setVisibility(View.VISIBLE);
            }else
                holder.botto_line.setVisibility(View.GONE);*/
        } else {
            holder.top_bar.setVisibility(View.VISIBLE);
            holder.nxt.setVisibility(View.GONE);
        }
        if(position == offset){
            ((ReportActivity)activity).callapi(position);
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
        return reportDataArrayList.size();
    }

    public void update(ArrayList<ReportData> reportData) {
        reportDataArrayList = reportData;
        notifyDataSetChanged();
    }
    public void updateList(ArrayList<ReportData> reportData , int offsetLevel) {
        offset = 20*(offsetLevel+1);
        reportDataArrayList = reportData;
        notifyDataSetChanged();
    }

    public void update(ArrayList<ReportData> reportData, int pos) {
        reportDataArrayList = reportData;
        notifyItemChanged(pos);
    }

    public class ReportHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sno)
        TextView sno;

        @BindView(R.id.pBal)
        TextView pBal;

        /*@BindView(R.id.type)
        TextView type;
*/
        @BindView(R.id.amt)
        TextView amt;

        @BindView(R.id.tranType)
        TextView tranType;
        @BindView(R.id.cBal)
        TextView cBal;
        @BindView(R.id.tranDate)
        TextView tranDate;
        @BindView(R.id.remark)
        TextView remark;

        @BindView(R.id.nxt)
        RelativeLayout nxt;

        @BindView(R.id.expend_list)
        RelativeLayout expend_list;

        @BindView(R.id.expend_image)
        ImageView expend_image;

        /*@BindView(R.id.top)
        RelativeLayout top;*/
        @BindView(R.id.top_bar)
        LinearLayout top_bar;
        @BindView(R.id.dropdown_menu)
        RelativeLayout dropdown_menu;


       /* @BindView(R.id.botto_line)
        View botto_line;*/


        public ReportHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.expend_list ,R.id.expend_image , R.id.sno })
        void expend() {
            ((ReportActivity) activity).checkOpen(getAdapterPosition());

        }
    }
}

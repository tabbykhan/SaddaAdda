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

import com.imuons.saddaadda.DataModel.BuyRecord;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.View.BuyActivityHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuyReportAdapter extends RecyclerView.Adapter<BuyReportAdapter.BuyHolder> {
    Activity activity;
    ArrayList<BuyRecord> reportDataArrayList;

    public BuyReportAdapter(Activity activity, ArrayList<BuyRecord> sellRecordArrayList) {
        this.activity = activity;
        this.reportDataArrayList = sellRecordArrayList;
    }

    @NonNull
    @Override
    public BuyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sell, parent, false);
        return new BuyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyHolder holder, int position) {
        if (position != 0) {
            holder.top_bar.setVisibility(View.GONE);
            holder.nxt.setVisibility(View.VISIBLE);
            BuyRecord reportData = reportDataArrayList.get(position);
            if (reportData.isOpen()) {
                holder.dropdown_menu.setVisibility(View.VISIBLE);
                holder.expend_image.setImageDrawable(activity.getResources().getDrawable(R.drawable.expend_1));
            } else {
                holder.dropdown_menu.setVisibility(View.GONE);
                holder.expend_image.setImageDrawable(activity.getResources().getDrawable(R.drawable.expend));
            }
            holder.sno.setText(String.valueOf(position));
            holder.amt.setText(String.valueOf(reportData.getBuyAmount()));
            //holder.cBal.setText(String.valueOf(reportData.getAmount()));
            //   holder.pBal.setText(String.valueOf(reportData.getPrevBalance()));
            holder.remark.setText(reportData.getRemark());

            //holder.tranDate.setText(parseDate(reportData.getEntryTime()));
            //holder.type.setText(reportData.getType());


        } else {
            holder.top_bar.setVisibility(View.VISIBLE);
            holder.nxt.setVisibility(View.GONE);
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

    public void update(ArrayList<BuyRecord> reportData) {
        reportDataArrayList = reportData;
        notifyDataSetChanged();
    }

    public void update(ArrayList<BuyRecord> reportData, int pos) {
        reportDataArrayList = reportData;
        notifyItemChanged(pos);
    }

    @Override
    public int getItemCount() {
        return reportDataArrayList.size();
    }

    public class BuyHolder extends RecyclerView.ViewHolder {
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

        @BindView(R.id.nxt)
        RelativeLayout nxt;

        @BindView(R.id.expend_image)
        ImageView expend_image;

        @BindView(R.id.top_bar)
        LinearLayout top_bar;

        @BindView(R.id.dropdown_menu)
        RelativeLayout dropdown_menu;

        public BuyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.expend_list, R.id.expend_image, R.id.sno})
        void expend() {
            ((BuyActivityHistory) activity).checkOpen(getAdapterPosition());

        }
    }
}

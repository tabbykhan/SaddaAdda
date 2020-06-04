package com.imuons.saddaadda.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.saddaadda.DataModel.TicketRecordModel;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.View.BuyCoinActivity;

import java.util.List;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketHolder> {
    Context context;
    List<TicketRecordModel> records = null;
    int offset = 9;
    Activity activity;

    public TicketAdapter(Context context, List<TicketRecordModel> records, Activity activity) {
        this.context = context;
        this.records = records;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TicketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ticket, parent, false);
        return new TicketHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketHolder holder, int position) {
        TicketRecordModel ticketRecordModel = records.get(position);


        if (ticketRecordModel.getRemark().equals("BUY Coin")) {
            if (ticketRecordModel.getBuyAmount() != null) {
                holder.txtAmount.setText(String.valueOf(ticketRecordModel.getBuyAmount()));
            } else {
                holder.txtAmount.setText("");

            }
            if (ticketRecordModel.getToFullname() != null) {
                holder.txtRecName.setText(String.valueOf(ticketRecordModel.getToFullname()));
            } else {
                holder.txtRecName.setText("");

            }
            if (ticketRecordModel.getToMobile() != null) {
                holder.txtBuy.setText(String.valueOf(ticketRecordModel.getToMobile()));
            } else {
                holder.txtBuy.setText("");

            }
            if (ticketRecordModel.getToMobile() != null) {
                holder.txtToUserId.setText(String.valueOf(ticketRecordModel.getToUser()));
            } else {
                holder.txtToUserId.setText("");

            }
            if (ticketRecordModel.getTranid() != null) {
                holder.txt_trasactionID.setText("Transaction ID " + String.valueOf(ticketRecordModel.getTranid()));
            } else {
                holder.txt_trasactionID.setText("Transaction ID " + "");

            }
            if (ticketRecordModel.getTranid() != null) {
                holder.txtAssignDate.setText(String.valueOf(ticketRecordModel.getAssignDate()));
            } else {
                holder.txtAssignDate.setText("");

            }
            holder.relativeLayout.setBackgroundResource(R.drawable.ticketbg_yellow);
            holder.btn_SendSMS.setBackgroundResource(R.drawable.btn_ticketbuy);
            holder.btn_Details.setBackgroundResource(R.drawable.btn_ticketbuy);

        } else if (ticketRecordModel.getRemark().equals("SELL Coin")) {
            if (ticketRecordModel.getBuyAmount() != null) {
                holder.txtAmount.setText(String.valueOf(ticketRecordModel.getBuyAmount()));
            } else {
                holder.txtAmount.setText("");
            }
            if (ticketRecordModel.getFromFullname() != null) {
                holder.txtRecName.setText(String.valueOf(ticketRecordModel.getFromFullname()));
            } else {
                holder.txtRecName.setText("");
            }
            if (ticketRecordModel.getFromMobile() != null) {
                holder.txtBuy.setText(String.valueOf(ticketRecordModel.getFromMobile()));
            } else {
                holder.txtBuy.setText("");
            }
            if (ticketRecordModel.getFromUser() != null) {
                holder.txtToUserId.setText(String.valueOf(ticketRecordModel.getFromUser()));
            } else {
                holder.txtToUserId.setText("");
            }
            if (ticketRecordModel.getTranid() != null) {
                holder.txt_trasactionID.setText("Transaction ID " + String.valueOf(ticketRecordModel.getTranid()));
            } else {
                holder.txt_trasactionID.setText("Transaction ID " + "");
            }
            if (ticketRecordModel.getTranid() != null) {
                holder.txtAssignDate.setText(String.valueOf(ticketRecordModel.getAssignDate()));
            } else {
                holder.txtAssignDate.setText("");
            }

            holder.relativeLayout.setBackgroundResource(R.drawable.ticketbg_green);
            holder.btn_SendSMS.setBackgroundResource(R.drawable.btn_ticketbuy_green);
            holder.btn_Details.setBackgroundResource(R.drawable.btn_ticketbuy_green);
        }

        /*if (position == offset) {
            ((BuyCoinActivity) activity).callapi(position);
        }*/
    }

    @Override
    public int getItemCount() {

        //    return (records == null) ? 0 : records.size();
        return records.size();
    }

    public void upDateList(List<TicketRecordModel> records, int offsetLevel) {
        offset = 9 * (offsetLevel + 1);
        this.records = records;

        notifyDataSetChanged();
    }

    public class TicketHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtAmount)
        TextView txtAmount;
        @BindView(R.id.txtRecName)
        TextView txtRecName;
        @BindView(R.id.txtBuy)
        TextView txtBuy;
        @BindView(R.id.txtAssignDate)
        TextView txtAssignDate;
        @BindView(R.id.txtToUserId)
        TextView txtToUserId;
        @BindView(R.id.txt_trasactionID)
        TextView txt_trasactionID;
        @BindView(R.id.btn_Details)
        TextView btn_Details;
        @BindView(R.id.btn_SendSMS)
        TextView btn_SendSMS;
        @BindView(R.id.relativeLayout)
        RelativeLayout relativeLayout;

        public TicketHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

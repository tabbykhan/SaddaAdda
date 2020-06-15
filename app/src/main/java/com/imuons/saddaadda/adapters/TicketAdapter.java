package com.imuons.saddaadda.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.saddaadda.DataModel.TicketRecordModel;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.View.HelpDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketHolder> {
    Context context;
    List<TicketRecordModel> records = null;
    int offset = 9;
    Activity activity;
    ClickEvent clickEvent;

    public TicketAdapter(Context context, List<TicketRecordModel> records, Activity activity) {
        this.context = context;
        this.records = records;
        this.activity = activity;
        clickEvent = (ClickEvent) activity;
    }

    @NonNull
    @Override
    public TicketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new TicketHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketHolder holder, int position) {
        TicketRecordModel ticketRecordModel = records.get(position);


        if (ticketRecordModel.getRemark().equals("BUY Coin")) {
            if (ticketRecordModel.getBuyAmount() != null) {
                holder.txtAmount.setText(ticketRecordModel.getBuyAmount());
            } else {
                holder.txtAmount.setText("");

            }
            if (ticketRecordModel.getToFullname() != null) {
                holder.txtRecName.setText(ticketRecordModel.getToFullname());
            } else {
                holder.txtRecName.setText("");

            }
            if (ticketRecordModel.getToMobile() != null) {
                holder.txtBuy.setText(ticketRecordModel.getToMobile());

            } else {
                holder.txtBuy.setText("");

            }
            if (ticketRecordModel.getToMobile() != null) {
                holder.txtToUserId.setText(String.valueOf(ticketRecordModel.getToUser()));
            } else {
                holder.txtToUserId.setText("");

            }
            if (ticketRecordModel.getTranid() != null) {
                holder.txt_trasactionID.setText(context.getString(R.string.buy_no) + ticketRecordModel.getTranid());
            } else {
                holder.txt_trasactionID.setText(context.getString(R.string.buy_no)+ "");

            }
            if (ticketRecordModel.getTranid() != null) {
                holder.txtAssignDate.setText(String.valueOf(ticketRecordModel.getAssignDate()));
            } else {
                holder.txtAssignDate.setText("");

            }

            holder.relativeLayout.setBackgroundResource(R.drawable.ticketbg_yellow);
            holder.btn_SendSMS.setBackgroundResource(R.drawable.btn_ticketbuy);
            holder.btn_Details.setBackgroundResource(R.drawable.btn_ticketbuy);

            if (ticketRecordModel.getStatus().equals("Pending") && ticketRecordModel.getToid() == AppCommon.getInstance(context).getID()) {
                holder.btn_reject.setBackgroundResource(R.drawable.btn_ticketbuy);
                holder.btn_reject.setVisibility(View.VISIBLE);
            } else {
                holder.btn_reject.setVisibility(View.GONE);

            }
            if (ticketRecordModel.getStatus().equals("Pending") && ticketRecordModel.getToid() == AppCommon.getInstance(context).getID() && ticketRecordModel.getReceiptStatus() > 0) {
                holder.btn_confirm.setBackgroundResource(R.drawable.btn_ticketbuy);
                holder.btn_confirm.setVisibility(View.VISIBLE);
            } else {
                holder.btn_confirm.setVisibility(View.GONE);
            }

            if (ticketRecordModel.getStatus().equals("Pending") && ticketRecordModel.getFromid() == AppCommon.getInstance(context).getID() && ticketRecordModel.getReceiptStatus() == 0) {
                holder.btn_upload.setBackgroundResource(R.drawable.btn_ticketbuy);
                holder.btn_upload.setVisibility(View.VISIBLE);
            } else {
                holder.btn_upload.setVisibility(View.GONE);
            }
            if (ticketRecordModel.getReceiptStatus() > 0) {
                holder.btn_view_slip.setBackgroundResource(R.drawable.btn_ticketbuy);
                holder.btn_view_slip.setVisibility(View.VISIBLE);
            } else {
                holder.btn_view_slip.setVisibility(View.GONE);
            }


        } else if (ticketRecordModel.getRemark().equals("SELL Coin")) {
            if (ticketRecordModel.getBuyAmount() != null) {
                holder.txtAmount.setText(ticketRecordModel.getBuyAmount());
            } else {
                holder.txtAmount.setText("");
            }
            if (ticketRecordModel.getFromFullname() != null) {
                holder.txtRecName.setText(ticketRecordModel.getFromFullname());
                holder.name.setText(context.getResources().getString(R.string.givers_name));
            } else {
                holder.txtRecName.setText("");
            }
            if (ticketRecordModel.getFromMobile() != null) {
                holder.txtBuy.setText(ticketRecordModel.getFromMobile());
                holder.status.setText(context.getString(R.string.Sell));
            } else {
                holder.txtBuy.setText("");
            }
            if (ticketRecordModel.getFromUser() != null) {
                holder.txtToUserId.setText(ticketRecordModel.getFromUser());
            } else {
                holder.txtToUserId.setText("");
            }
            if (ticketRecordModel.getTranid() != null) {
                holder.txt_trasactionID.setText(context.getString(R.string.buy_no) + ticketRecordModel.getTranid());
            } else {
                holder.txt_trasactionID.setText(context.getString(R.string.buy_no) + "");
            }
            if (ticketRecordModel.getTranid() != null) {
                holder.txtAssignDate.setText(String.valueOf(ticketRecordModel.getAssignDate()));
            } else {
                holder.txtAssignDate.setText("");
            }

            holder.relativeLayout.setBackgroundResource(R.drawable.ticketbg_green);
            holder.btn_SendSMS.setBackgroundResource(R.drawable.btn_ticketbuy_green);
            holder.btn_Details.setBackgroundResource(R.drawable.btn_ticketbuy_green);
            if (ticketRecordModel.getStatus().equals("Pending") && ticketRecordModel.getToid() == AppCommon.getInstance(context).getID()) {
                holder.btn_reject.setBackgroundResource(R.drawable.btn_ticketbuy_green);
                holder.btn_reject.setVisibility(View.VISIBLE);
            } else {
                holder.btn_reject.setVisibility(View.GONE);

            }
            if (ticketRecordModel.getStatus().equals("Pending") && ticketRecordModel.getToid() == AppCommon.getInstance(context).getID() && ticketRecordModel.getReceiptStatus() > 0) {
                holder.btn_confirm.setBackgroundResource(R.drawable.btn_ticketbuy_green);
                holder.btn_confirm.setVisibility(View.VISIBLE);
            } else {
                holder.btn_confirm.setVisibility(View.GONE);
            }

            if (ticketRecordModel.getStatus().equals("Pending") &&
                    ticketRecordModel.getToid() == AppCommon.getInstance(context).getID() &&
                    ticketRecordModel.getReceiptStatus() == 0) {
                holder.btn_upload.setBackgroundResource(R.drawable.btn_ticketbuy_green);
                holder.btn_upload.setVisibility(View.GONE);
            } else {
                holder.btn_upload.setVisibility(View.GONE);

            }
            if (ticketRecordModel.getReceiptStatus() > 0) {
                holder.btn_view_slip.setBackgroundResource(R.drawable.btn_ticketbuy_green);
                holder.btn_view_slip.setVisibility(View.VISIBLE);
            } else {
                holder.btn_view_slip.setVisibility(View.GONE);
            }
        }
        clickEvent(holder, ticketRecordModel);

        /*if (position == offset) {
            ((BuyCoinActivity) activity).callapi(position);
        }*/
    }

    private void clickEvent(TicketHolder holder, TicketRecordModel ticketRecordModel) {
        holder.btn_Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HelpDetailsActivity.class);
                intent.putExtra("tran_id", ticketRecordModel.getTranid());
                context.startActivity(intent);
            }
        });
        holder.btn_SendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent.sendSms(ticketRecordModel);
            }
        });
        holder.btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent.clickButton(true, ticketRecordModel);
            }
        });
        holder.btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent.clickButton(false, ticketRecordModel);
            }
        });
        holder.btn_view_slip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent.viewSlip(ticketRecordModel);
            }
        });
        holder.btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent.clickUpload(ticketRecordModel);
            }
        });
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

    public interface ClickEvent {
        void clickButton(boolean is_confirm_pop, TicketRecordModel recordModel);

        void clickUpload(TicketRecordModel recordModel);

        void viewSlip(TicketRecordModel recordModel);

        void sendSms(TicketRecordModel recordModel);
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
        @BindView(R.id.btn_view_slip)
        TextView btn_view_slip;

        @BindView(R.id.btn_confirm)
        TextView btn_confirm;
        @BindView(R.id.btn_reject)
        TextView btn_reject;
        @BindView(R.id.btn_upload)
        TextView btn_upload;


        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.status)
        TextView status;


        public TicketHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

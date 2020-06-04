package com.imuons.saddaadda.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.saddaadda.DataModel.TicketRecordModel;
import com.imuons.saddaadda.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketHolder>{
    Context context;
    ArrayList<TicketRecordModel> records=null;
    int offset = 9;

    public TicketAdapter(Context context, ArrayList<TicketRecordModel> records) {
        this.context = context;
        this.records = records;
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
        holder.txtAmount.setText(String.valueOf(ticketRecordModel.getBuyAmount()));
        holder.txtRecName.setText(String.valueOf(ticketRecordModel.getToFullname()));
        holder.txtBuy.setText(String.valueOf(ticketRecordModel.getBuyAmount()));
        holder.txtToUserId.setText(String.valueOf(ticketRecordModel.getToUser()));
        holder.txt_trasactionID.setText("Transaction ID "+String.valueOf(ticketRecordModel.getTranid()));
        holder.txtAssignDate.setText(String.valueOf(ticketRecordModel.getAssignDate()));
    }

    @Override
    public int getItemCount() {

        return (records == null) ? 0 : records.size();
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

        public TicketHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package com.example.aci;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import AppClasses.Orders;
import AppClasses.OrdersDetails;

public class Orders_Adapter extends RecyclerView.Adapter<Orders_Adapter.ItemViewHolder>{
    Context context;
    private List<OrdersDetails> ordersDetailsList;
    private OrdersInterface ordersInterface;

    public Orders_Adapter(List<OrdersDetails> ordersDetailsList,OrdersInterface ordersInterface) {
        this.ordersDetailsList = ordersDetailsList;
        this.ordersInterface=ordersInterface;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.home_order_frame, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.itemView.setLongClickable(true);

        final OrdersDetails ordersDetails = ordersDetailsList.get(position);
        holder.lbl_order_supplier.setText(ordersDetails.getSupplier());


    }

    @Override
    public int getItemCount() {
        return ordersDetailsList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView lbl_order_supplier;



        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            lbl_order_supplier = itemView.findViewById(R.id.lbl_order_supplier);



        }
    }
}

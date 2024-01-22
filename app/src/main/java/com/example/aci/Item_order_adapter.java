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

import AppClasses.Items;
import AppClasses.Orders;

public class Item_order_adapter extends RecyclerView.Adapter<Item_order_adapter.ItemViewHolder>{
    Context context;
    private List<Items> itemsList;
    private ItemsInterface itemsInterface;

    public Item_order_adapter(List<Items> itemsList, ItemsInterface itemsInterface) {
        this.itemsList = itemsList;
        this.itemsInterface = itemsInterface;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_order_frame, parent, false);
        Item_order_adapter.ItemViewHolder viewHolder = new Item_order_adapter.ItemViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.itemView.setLongClickable(true);

        final Items itemsDetails = itemsList.get(position);
        holder.lbl_itemname.setText(itemsDetails.getItemName());
        holder.lbl_qty.setText(itemsDetails.getQty());
        holder.lbl_customer.setText(itemsDetails.getCustomer());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView lbl_itemname;
        private TextView lbl_qty;
        private TextView lbl_customer;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            lbl_itemname = itemView.findViewById(R.id.lbl_itemname);
            lbl_qty = itemView.findViewById(R.id.lbl_qty);
            lbl_customer = itemView.findViewById(R.id.lbl_customer);


        }
    }
}

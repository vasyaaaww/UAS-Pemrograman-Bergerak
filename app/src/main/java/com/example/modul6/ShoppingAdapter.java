
package com.example.modul6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.VH> {

    private final List<ShoppingItem> items;

    public ShoppingAdapter(List<ShoppingItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ShoppingItem it = items.get(position);
        holder.tvName.setText(it.name);
        holder.tvQty.setText(String.format(Locale.getDefault(), "Qty: %d", it.qty));
        holder.tvPrice.setText(String.format(Locale.getDefault(), "Harga: Rp %.0f", it.price));
        holder.tvSubtotal.setText(String.format(Locale.getDefault(), "Subtotal: Rp %.0f", it.subtotal()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvName, tvQty, tvPrice, tvSubtotal;
        VH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvQty = itemView.findViewById(R.id.tvQty);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvSubtotal = itemView.findViewById(R.id.tvSubtotal);
        }
    }
}


package com.example.modul6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class ShoppingSummaryFragment extends Fragment {

    public ShoppingSummaryFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shopping_summary, container, false);

        TextView tvItems = view.findViewById(R.id.tvItems);
        TextView tvQty = view.findViewById(R.id.tvQty);
        TextView tvTotal = view.findViewById(R.id.tvTotal);

        int items = ShoppingRepository.getItems().size();
        int qty = ShoppingRepository.totalQty();
        double total = ShoppingRepository.totalPrice();

        tvItems.setText(String.format(Locale.getDefault(), "%d", items));
        tvQty.setText(String.format(Locale.getDefault(), "%d", qty));
        tvTotal.setText(String.format(Locale.getDefault(), "Rp %.0f", total));

        view.findViewById(R.id.btnGoList).setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.shopping_fragment_container, new ShoppingListFragment())
                        .addToBackStack(null)
                        .commit()
        );

        return view;
    }
}

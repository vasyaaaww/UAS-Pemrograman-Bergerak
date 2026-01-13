
package com.example.modul6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

public class ShoppingListFragment extends Fragment {

    public ShoppingListFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        RecyclerView rv = view.findViewById(R.id.rvShopping);
        TextView tvEmpty = view.findViewById(R.id.tvEmpty);
        MaterialButton btnAddQuick = view.findViewById(R.id.btnAddQuick);
        MaterialButton btnClearAll = view.findViewById(R.id.btnClearAll);
        MaterialButton btnRefresh = view.findViewById(R.id.btnRefresh);

        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        ShoppingAdapter adapter = new ShoppingAdapter(ShoppingRepository.getItems());
        rv.setAdapter(adapter);

        Runnable refresh = () -> {
            adapter.notifyDataSetChanged();
            boolean empty = ShoppingRepository.getItems().isEmpty();
            tvEmpty.setVisibility(empty ? View.VISIBLE : View.GONE);
            rv.setVisibility(empty ? View.GONE : View.VISIBLE);
        };
        refresh.run();

        btnAddQuick.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.shopping_fragment_container, new ShoppingAddFragment())
                .addToBackStack(null)
                .commit());

        btnClearAll.setOnClickListener(v -> {
            ShoppingRepository.clearAll();
            refresh.run();
        });

        btnRefresh.setOnClickListener(v -> refresh.run());

        return view;
    }
}


package com.example.modul6;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class ShoppingAddFragment extends Fragment {

    public ShoppingAddFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shopping_add, container, false);

        TextInputEditText etName = view.findViewById(R.id.etName);
        TextInputEditText etQty = view.findViewById(R.id.etQty);
        TextInputEditText etPrice = view.findViewById(R.id.etPrice);

        Button btnSave = view.findViewById(R.id.btnSave);
        Button btnReset = view.findViewById(R.id.btnReset);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText() != null ? etName.getText().toString().trim() : "";
            String qtyStr = etQty.getText() != null ? etQty.getText().toString().trim() : "";
            String priceStr = etPrice.getText() != null ? etPrice.getText().toString().trim() : "";

            if (TextUtils.isEmpty(name)) { etName.setError("Nama wajib"); etName.requestFocus(); return; }
            if (TextUtils.isEmpty(qtyStr)) { etQty.setError("Qty wajib"); etQty.requestFocus(); return; }
            if (TextUtils.isEmpty(priceStr)) { etPrice.setError("Harga wajib"); etPrice.requestFocus(); return; }

            try {
                int qty = Integer.parseInt(qtyStr);
                double price = Double.parseDouble(priceStr);

                if (qty <= 0 || price < 0) {
                    Toast.makeText(requireContext(), "Qty > 0 dan harga >= 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                ShoppingRepository.add(new ShoppingItem(name, qty, price));
                Toast.makeText(requireContext(), "Item ditambahkan", Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().popBackStack();
            } catch (NumberFormatException e) {
                Toast.makeText(requireContext(), "Input tidak valid", Toast.LENGTH_SHORT).show();
            }
        });

        btnReset.setOnClickListener(v -> {
            etName.setText("");
            etQty.setText("");
            etPrice.setText("");
            etName.requestFocus();
        });

        return view;
    }
}

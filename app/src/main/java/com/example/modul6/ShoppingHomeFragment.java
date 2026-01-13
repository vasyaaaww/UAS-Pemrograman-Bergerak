
package com.example.modul6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

public class ShoppingHomeFragment extends Fragment {

    public ShoppingHomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shopping_home, container, false);

        MaterialCardView cardList = view.findViewById(R.id.cardList);
        MaterialCardView cardAdd = view.findViewById(R.id.cardAdd);
        MaterialCardView cardSummary = view.findViewById(R.id.cardSummary);

        cardList.setOnClickListener(v -> open(new ShoppingListFragment()));
        cardAdd.setOnClickListener(v -> open(new ShoppingAddFragment()));
        cardSummary.setOnClickListener(v -> open(new ShoppingSummaryFragment()));

        return view;
    }

    private void open(Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.shopping_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}

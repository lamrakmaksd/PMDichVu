package com.example.btcuoiki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<SpinnerSort> {

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<SpinnerSort> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected, parent, false);
        TextView tvSelected = convertView.findViewById(R.id.tv_selected);

        SpinnerSort spinnerSort = this.getItem(position);
        if (spinnerSort != null) {
            tvSelected.setText(spinnerSort.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spn_sort, parent, false);
        TextView tvCategory = convertView.findViewById(R.id.tv_category);

        SpinnerSort spinnerSort = this.getItem(position);
        if (spinnerSort != null) {
            tvCategory.setText(spinnerSort.getName());
        }
        return convertView;
    }
}

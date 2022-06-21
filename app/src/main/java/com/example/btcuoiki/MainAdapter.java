package com.example.btcuoiki;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<String> name;
    private List<String> gia;
    private List<String> images;
    private List<Integer> id;
    private Context context;
    private static final String TAG = "MainAdapter";
    public MainAdapter(List<String> name, List<String> gia, List<String> images, List<Integer> id ,Context context) {
        this.name = name;
        this.gia = gia;
        this.images = images;
        this.id = id;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        
        holder.tv_name.setText(name.get(position));
        holder.tv_gia.setText(gia.get(position));
        Picasso.get().load(images.get(position)).into(holder.img_name);
        Integer idHangHoa = id.get(position);
//        holder.img_name.setImageResource(images.get(position));
        holder.layoutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InformationActivity.class);
                intent.putExtra("idhanghoa",idHangHoa);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name, tv_gia;
        private ImageView img_name;
        private CardView layoutCardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_gia = itemView.findViewById(R.id.tv_gia);
            img_name = itemView.findViewById(R.id.img_name);
            layoutCardView = itemView.findViewById(R.id.layoutCardView);
        }
    }
}

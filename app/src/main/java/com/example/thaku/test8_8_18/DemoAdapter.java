package com.example.thaku.test8_8_18;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DemoAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ModelClass> data;

    public DemoAdapter(Context context,ArrayList<ModelClass>data) {
        this.context= context;
        this.data=data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewHolder viewHolder=new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder=(ViewHolder)holder;


        viewHolder.title.setText(data.get(position).getTitle());

        String url=data.get(position).getUrlToImage();
        Picasso.with(context).load(url).into(viewHolder.images);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        ImageView images;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.images);
            title=itemView.findViewById(R.id.title);

        }
    }
}

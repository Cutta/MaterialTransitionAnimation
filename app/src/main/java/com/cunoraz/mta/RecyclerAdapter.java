package com.cunoraz.mta;

/**
 * Created by cuneytcarikci on 27/10/2016.
 */


import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Item> itemList;

    private ItemClick itemClick;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        View coloredView;
        CardView cardView;

        MyViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.text);
            coloredView = view.findViewById(R.id.colored_view);
            cardView = (CardView)view.findViewById(R.id.item_root);
        }
    }


    RecyclerAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = new MyCardView(parent.getContext());
       // View itemView = LayoutInflater.from(parent.getContext())
         //       .inflate(R.layout.item_list, parent, false);
        itemClick = (ItemClick) parent.getContext();

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,int position) {
        Item item = itemList.get(position);
        holder.text.setText(item.getText());
        holder.coloredView.setBackgroundColor(Color.parseColor(item.getColor()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onClick(view,holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    interface ItemClick{
         void onClick(View v,int position);
    }
}
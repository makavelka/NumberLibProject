package com.example.numberlib.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.numberlib.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {

    private ArrayList<String> mList;

    public SimpleAdapter(ArrayList<String> mList) {
        this.mList = mList;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple, parent, false);
        return new SimpleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.number.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_textView_simpleItem)
        TextView number;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

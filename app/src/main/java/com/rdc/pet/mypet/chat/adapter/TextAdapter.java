package com.rdc.pet.mypet.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rdc.pet.mypet.R;
import com.rdc.pet.mypet.chat.model.ListData;

import java.util.List;

public class TextAdapter extends BaseAdapter {

    private Context context;
    private RelativeLayout relativeLayout;
    private List<ListData> listDatas;

    public TextAdapter(Context context, List<ListData> listDatas) {
        this.context = context;
        this.listDatas = listDatas;
    }

    @Override
    public int getCount() {
        return listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if(listDatas.get(position).getFlag() == ListData.receiver) {
            relativeLayout = (RelativeLayout) inflater.inflate(R.layout.item_left, null);
        } else if(listDatas.get(position).getFlag() == ListData.send) {
            relativeLayout = (RelativeLayout) inflater.inflate(R.layout.item_right, null);
        }
        TextView timeView = (TextView) relativeLayout.findViewById(R.id.tv_time);
        TextView contentView = (TextView) relativeLayout.findViewById(R.id.tv_content);
        timeView.setText(listDatas.get(position).getTime());
        contentView.setText(listDatas.get(position).getContent());
        return relativeLayout;
    }
}

package com.minhien.musicappasm.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minhien.musicappasm.R;
import com.minhien.musicappasm.model.Song;
import com.minhien.musicappasm.model.SongCategory;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private List<SongCategory> data;
    private Context context;

    public CategoryAdapter(List<SongCategory> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_list_category, null);
            TextView name = convertView.findViewById(R.id.tv_category_name);
            ViewHolder holder = new ViewHolder(name);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        SongCategory songcategory = (SongCategory) getItem(position);
        holder.name.setText(songcategory.getCatrgory_name());
        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        public ViewHolder(TextView _name) {
            this.name = _name;
        }
    }
}

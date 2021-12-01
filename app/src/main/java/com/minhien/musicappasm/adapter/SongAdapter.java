package com.minhien.musicappasm.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minhien.musicappasm.R;
import com.minhien.musicappasm.activity.HomeActivity;
import com.minhien.musicappasm.activity.MainActivity;
import com.minhien.musicappasm.activity.UpdateSongActivity;
import com.minhien.musicappasm.model.Song;
import com.minhien.musicappasm.myritrofit.IRetrofitService;
import com.minhien.musicappasm.myritrofit.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongAdapter extends BaseAdapter {

    Context context;
    private List<Song> list;
    private IRetrofitService service;

    public SongAdapter(Context context, List<Song> list) {
        this.context = context;
        this.list = list;
    }

    public void updateData(List<Song> data) {
        data.clear();
        data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_list_song, null);
            TextView songName = convertView.findViewById(R.id.tv_song_name);
            TextView singerName = convertView.findViewById(R.id.tv_singer_name);
            ImageView image = convertView.findViewById(R.id.iv_image);
            ImageView ivEdit = convertView.findViewById(R.id.edit);
            ImageView ivDelete = convertView.findViewById(R.id.delete);
            ViewHolder holder = new ViewHolder(songName, singerName, image, ivEdit, ivDelete);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        Song song = (Song) getItem(position);
        holder.tvSongName.setText(song.getSong_name());
        holder.tvSingerName.setText(song.getSinger_name());
        Glide.with(context)
                .load(song.getImage_url())
                .into(holder.ivImage);
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit(song);
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        TextView tvSongName, tvSingerName;
        ImageView ivImage, ivEdit, ivDelete;

        public ViewHolder(TextView _tvSongName, TextView _tvSingerName, ImageView ivImage, ImageView ivEdit, ImageView ivDelete) {
            this.tvSongName = _tvSongName;
            this.tvSingerName = _tvSingerName;
            this.ivImage = ivImage;
            this.ivEdit = ivEdit;
            this.ivDelete = ivDelete;
        }
    }
    private void edit(Song song) {
        Intent intent = new Intent(context, UpdateSongActivity.class);
        intent.putExtra("id_song", song.getId_Song());
        context.startActivity(intent);
    }
}

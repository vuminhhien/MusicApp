package com.minhien.musicappasm.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.minhien.musicappasm.R;
import com.minhien.musicappasm.model.MessageModel;

import java.util.List;

public class MessageAdapter extends BaseAdapter {
    List<MessageModel> data;
    Context context;

    public MessageAdapter(List<MessageModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void updateData(List<MessageModel> data){
        data.clear();
        data.addAll(data);
        notifyDataSetChanged();
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
            convertView = View.inflate(parent.getContext(), R.layout.layout_item_message, null);
            TextView message = convertView.findViewById(R.id.tvMessage);
            ViewHolder holder = new ViewHolder(message);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        MessageModel msg = (MessageModel) getItem(position);
        holder.tvMessage.setText(msg.getData());
        if (msg.getFromMe()){
            holder.tvMessage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.tvMessage.setBackgroundResource(R.drawable.button_bg_gmail);
        }else{
            holder.tvMessage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.tvMessage.setBackgroundResource(R.drawable.button_bg);
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView tvMessage;
        public ViewHolder(TextView _tvMessage) {
            this.tvMessage = _tvMessage;
        }
    }
}

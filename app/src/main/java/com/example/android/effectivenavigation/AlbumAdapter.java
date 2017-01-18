package com.example.android.effectivenavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nikhil on 17/1/17.
 */

public class AlbumAdapter extends BaseAdapter {
    Context context;
    List<Album> albumList;
    int column_width;
    int column_height;

    AlbumAdapter(Context context, List<Album> albumList,int columnWidth,int columnHeight){
        this.context=context;
        this.albumList=albumList;
        this.column_width=columnWidth;
        this.column_height=columnHeight;
    }
    @Override
    public int getCount() {
        return albumList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder ;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.album_card, null);
            viewHolder.thumbnail=(ImageView) convertView.findViewById(R.id.thumbnail);
            viewHolder.overflow = (ImageView) convertView.findViewById(R.id.overflow);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.linearLayout=(LinearLayout)convertView.findViewById(R.id.linerLayout);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        final Album album = albumList.get(position);
        viewHolder.title.setText(album.getName());
        Picasso.with(context).load(album.getImageFile()).into(viewHolder.thumbnail);

        android.widget.AbsListView.LayoutParams parms = new android.widget.AbsListView.LayoutParams(column_width, column_height);
        viewHolder.linearLayout.setLayoutParams(parms);


        return convertView;
    }

    public class ViewHolder{
        public TextView title;
        public ImageView thumbnail, overflow;
        LinearLayout linearLayout;
    }
}

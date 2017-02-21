package com.example.aditya.prototypehabba.map.GridviewMenu.card6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aditya.prototypehabba.R;
import com.example.aditya.prototypehabba.map.GridviewMenu.Album;
import com.example.aditya.prototypehabba.map.Scroll.Scroll;

import java.util.List;


public class Lifestyle_AlbumsAdapter extends RecyclerView.Adapter<Lifestyle_AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;
    public String st;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);

        }
    }


    public Lifestyle_AlbumsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs() + " songs");
        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                switch (position) {
                    case 0:
                        Intent i = new Intent(view.getContext(),Scroll.class);
                        i.putExtra("num",24);
                        view.getContext().startActivity(i);
                        break;
                    case 1:
                        Intent i1 = new Intent(view.getContext(),Scroll.class);
                        i1.putExtra("num",25);
                        view.getContext().startActivity(i1);

                        break;
                    case 2:
                        Intent i2 = new Intent(view.getContext(),Scroll.class);
                        i2.putExtra("num",26);
                        view.getContext().startActivity(i2);

                        break;
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}

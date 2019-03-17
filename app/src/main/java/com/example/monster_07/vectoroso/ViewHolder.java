package com.example.monster_07.vectoroso;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView tv;
    public ImageView im;
    private itemClickListener itemClickListener_;
    //    View mview;

    public ViewHolder( View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.book_title);
        im = itemView.findViewById(R.id.book_img);
        //mview = itemView;

        itemView.setOnClickListener(this);

    }

   /* public void setDetails(Context ctx, String title, String image){

        TextView mtextview = mview.findViewById(R.id.book_title);
        ImageView mimage = mview.findViewById(R.id.book_img);

        mtextview.setText(title);
        Picasso.get().load(image).into(mimage);

    }*/

    public void setItemClickListener_(itemClickListener itemClickListener_) {
        this.itemClickListener_ = itemClickListener_;
    }

    @Override
    public void onClick(View v) {
        itemClickListener_.onClick(v,getAdapterPosition(),false);
    }
}

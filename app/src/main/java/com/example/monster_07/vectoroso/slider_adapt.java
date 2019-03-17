/*package com.example.monster_07.vectoroso;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class slider_adapt extends PagerAdapter{

    Context context;
    LayoutInflater inflater;

    public int[] imagearray = {R.drawable.firstslide, R.drawable.bg};
    public String[] titlearray = {"1","2"};


    public slider_adapt(Context context)
    {

        this.context = context;

    }

    @Override
    public boolean isViewFromObject( View view,  Object o) {
        return (view==o);
    }

    @Override
    public int getCount() {
        return titlearray.length;
    }

    @Override
    public void destroyItem( ViewGroup container, int position,  Object object) {
        container.removeView((LinearLayout)object);

    }


    @Override
    public Object instantiateItem( ViewGroup container, int position) {

        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container, false);

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearlayout);
        ImageView imageView = (ImageView)view.findViewById(R.id.slide_img);
        imageView.setImageResource(imagearray[position]);
        container.addView(view);

        return view;
    }
}*/

package com.modul2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private final Context mContext;

    // Pakai ikon bawaan Android (tanpa file gambar di drawable)
    public Integer[] mThumbIds = {
            android.R.drawable.ic_menu_camera,
            android.R.drawable.ic_menu_compass,
            android.R.drawable.ic_menu_gallery,
            android.R.drawable.ic_menu_manage,
            android.R.drawable.ic_menu_call,
            android.R.drawable.ic_menu_day,
            android.R.drawable.ic_menu_directions,
            android.R.drawable.ic_menu_help,
            android.R.drawable.ic_menu_info_details,
            android.R.drawable.ic_menu_mapmode,
            android.R.drawable.ic_menu_myplaces,
            android.R.drawable.ic_menu_send,
            android.R.drawable.ic_menu_slideshow,
            android.R.drawable.ic_menu_zoom
    };

    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);

            // 90dp -> px biar ukurannya konsisten
            int sizePx = dpToPx(90);
            imageView.setLayoutParams(new GridView.LayoutParams(sizePx, sizePx));

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            int pad = dpToPx(4);
            imageView.setPadding(pad, pad, pad, pad);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    private int dpToPx(int dp) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}

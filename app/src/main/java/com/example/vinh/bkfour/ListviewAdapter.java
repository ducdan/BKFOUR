package com.example.vinh.bkfour;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinh.bkfour.Model.Product;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by HoDucDan_51200482 on 3/26/2016.
 */
public class ListviewAdapter extends BaseAdapter {
    int width;
    int height;
    //public ImageLoader imageLoader;
    Context context;
    ArrayList<Product> data;

    public ListviewAdapter(Context context, ArrayList<Product> items, int width, int height) {
        this.width = width;
        this.height = height;
        this.context = context;
        this.data = items;
        // imageLoader = new ImageLoader(context);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.item_detail, null);
            holder = new ViewHolder();
            holder.txt_itemName = (TextView) convertView.findViewById(R.id.item_name);
            holder.itemImage = (ImageView) convertView.findViewById(R.id.item_photo);
            holder.des = (TextView) convertView.findViewById(R.id.item_des);
            holder.cost = (TextView) convertView.findViewById(R.id.item_cost);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Product item = data.get(position);
        ImageView image = holder.itemImage;
        image.getLayoutParams().height = width / 6;
        image.getLayoutParams().width = width / 6;
        image.setBackgroundColor(Color.WHITE);
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageOnLoading(R.drawable.ic_loading)
                .build();
        imageLoader.displayImage("http://166.62.93.250/HuyAPI/Picture/SGK_tieng_viet_1.jpg", image, options);
        //holder.cost.setText();
        holder.txt_itemName.setText(item.getProductName());
        holder.des.setText(item.getDescription());
        return convertView;
    }

    static class ViewHolder {
        TextView txt_itemName;
        ImageView itemImage;
        TextView des;
        TextView cost;
    }
}

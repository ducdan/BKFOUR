package com.example.vinh.bkfour;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.vinh.bkfour.Model.Product;
import com.example.vinh.bkfour.Model.User;
import com.example.vinh.bkfour.Model.Variable;

import java.util.ArrayList;

/**
 * Created by HoDucDan_51200482 on 3/26/2016.
 */
public class ListItemActivity extends Activity implements IOnServerResponse{
    String playlistID;
    private boolean isLoading;
    ListviewAdapter adapter;
    private String nextPage = null;
    private boolean endData = false;
    private View footer;
    ListView lv;
    ArrayList<Product> itemInfo = new ArrayList<Product>();
    int Measuredwidth = 0;
    int Measuredheight = 0;
    ProgressBar pro2;

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        Bundle bundle=getIntent().getExtras();
        int pos=bundle.getInt(Variable.ITEM_POS);
        Point size = new Point();
        WindowManager w = getWindowManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            w.getDefaultDisplay().getSize(size);
            Measuredwidth = size.x;
            Measuredheight = size.y;
        } else {
            Display d = w.getDefaultDisplay();
            Measuredwidth = d.getWidth();
            Measuredheight = d.getHeight();
        }
        ServerHandler handler=new ServerHandler(this);
        handler.getListProducts(2);
        lv = (ListView) findViewById(R.id.listview);
        adapter = new ListviewAdapter(getApplicationContext(), itemInfo,
                Measuredwidth, Measuredheight);
        lv.setAdapter(adapter);


    }

    @Override
    public void OnPostLoginRes(User user) {

    }

    @Override
    public void OnPostRegisterRes(User user) {

    }

    @Override
    public void OnGetListProductsRes(ArrayList<Product> lstProds) {
        Product item=new Product();
        item.setProductName("Sach tv");
        item.setDescription("hay hay");
        item.setPrice("1000");
        item.setProductPicture("http://166.62.93.250/HuyAPI/Picture/SGK_tieng_viet_1.jpg");
        itemInfo.add(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnGetProductDetailRes(Product product, ArrayList<User> lstUserNeed, ArrayList<User> lstUserTransport) {

    }

    @Override
    public void OnAddProduct(Product product) {

    }

    @Override
    public void OnGetUserDetail(User user) {

    }
}
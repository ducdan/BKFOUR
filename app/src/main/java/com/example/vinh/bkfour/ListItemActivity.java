package com.example.vinh.bkfour;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vinh.bkfour.Model.Product;
import com.example.vinh.bkfour.Model.Variable;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoDucDan_51200482 on 3/26/2016.
 */
public class ListItemActivity extends Activity {
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
        Bundle bundle = getIntent().getExtras();
        String pos = bundle.getString(Variable.ITEM_POS);
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

        lv = (ListView) findViewById(R.id.listview);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Product");
        query.orderByDescending("createdAt");
        query.whereEqualTo(Variable.CATEGORY_NAME, pos);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                for (int i = 0; i < objects.size(); i++) {
                    ParseObject obj = objects.get(i);
                    Product item = new Product();
                    item.setProductName(obj.getString(Variable.PRODUC_TNAME));
                    // item.setProductName(obj.getString(Variable.LONG_LOCATION));
                    //item.setProductName(obj.getString(Variable.LAT_LOCATION));
                    item.setDescription(obj.getString(Variable.DESCRIPTION));
                    item.setQuantity(obj.getString(Variable.QUANTITY));
                    //item.setProductName(obj.getString(Variable.UNIT));
                    item.setAddress(obj.getString(Variable.ADDRESS));
                    item.setPrice(obj.getString(Variable.COST));
                    item.phone = (obj.getString(Variable.TELEPHONE));
                    //item.setProductName(obj.getString(Variable.PICTURE));
                    itemInfo.add(item);
                }
                ProgressBar pro = (ProgressBar) findViewById(R.id.pro1);
                pro.setVisibility(View.GONE);
                if (itemInfo.size() == 0) {
                    TextView txt=(TextView)findViewById(R.id.txt_no);
                    txt.setVisibility(View.VISIBLE);
                }
                adapter = new ListviewAdapter(getApplicationContext(), itemInfo,
                        Measuredwidth, Measuredheight);
                lv.setAdapter(adapter);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Gson gson = new Gson();
//                String value = gson.toJson(itemInfo.get(position));
//                 Intent intent=new Intent(ListItemActivity.this,ProductDetailActivity.class);
//                 intent.putExtra(Variable.ITEM_DATA,value);
//                startActivity(intent);
            }
        });
    }
}
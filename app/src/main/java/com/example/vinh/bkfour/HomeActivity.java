package com.example.vinh.bkfour;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinh.bkfour.Model.Variable;
import com.example.vinh.bkfour.Utility.CircleButton;


/**
 * Created by HoDucDan_51200482 on 3/26/2016.
 */

public class HomeActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView mainView = (GridView) findViewById(R.id.gridView0);
        mainView.setAdapter(new ImageAdapter(this));
        mainView.setVerticalScrollBarEnabled(false);
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;

        public ImageAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mThumbs.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @SuppressLint("InflateParams")
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            // TODO Auto-generated method stub
            View myView;
            if (convertView == null) {
                LayoutInflater li = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                myView = li.inflate(R.layout.item_circle, null);
            } else
                myView = convertView;
            TextView tv = (TextView) myView.findViewById(R.id.myText);
            tv.setText(contentString[position]);
            CircleButton myc;
            myc = (CircleButton) myView.findViewById(R.id.myCircle);
            myc.setColor(Color.parseColor(colors[position]));
            myc.setImageResource(mThumbs[position]);
            myc.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    switch (position) {
                        case 0: {
                            Intent intent = new Intent(HomeActivity.this,
                                    ListItemActivity.class);
                            intent.putExtra(Variable.ITEM_POS, position);
                            startActivityForResult(intent, 0);
                            break;
                        }
                        case 1: {
                            Intent intent = new Intent(HomeActivity.this,
                                    LoginActivity.class);
                            intent.putExtra(Variable.ITEM_POS, contentString[position]);
                            startActivityForResult(intent, 0);
                            break;
                        }
                        case 2: {
                            Intent intent = new Intent(HomeActivity.this,
                                    ListItemActivity.class);
                            intent.putExtra(Variable.ITEM_POS, position);
                            startActivityForResult(intent, 0);
                            break;
                        }
                        case 3: {
                            Intent intent = new Intent(HomeActivity.this,
                                    LoginActivity.class);
                            intent.putExtra(Variable.ITEM_POS, contentString[position]);
                            startActivityForResult(intent, 0);
                            break;
                        }
                        case 4: {
                            Intent intent = new Intent(HomeActivity.this,
                                    LoginActivity.class);
                            intent.putExtra(Variable.ITEM_POS, contentString[position]);
                            startActivityForResult(intent, 0);
                            break;
                        }
                        case 5: {
                            Intent intent = new Intent(HomeActivity.this,
                                    LoginActivity.class);
                            intent.putExtra(Variable.ITEM_POS, contentString[position]);
                            startActivityForResult(intent, 0);
                            break;
                        }
                        case 6: {
                            Intent intent = new Intent(HomeActivity.this,
                                    LoginActivity.class);
                            intent.putExtra(Variable.ITEM_POS, contentString[position]);
                            startActivityForResult(intent, 0);
                            break;
                        }
                        case 7: {
                            Intent intent = new Intent(HomeActivity.this,
                                    LoginActivity.class);
                            intent.putExtra(Variable.ITEM_POS, contentString[position]);
                            startActivityForResult(intent, 0);
                            break;
                        }
                        case 8: {
                            Intent intent = new Intent(HomeActivity.this,
                                    LoginActivity.class);
                            intent.putExtra(Variable.ITEM_POS, contentString[position]);
                            startActivityForResult(intent, 0);
                            break;
                        }
                        case 9: {
                            Intent intent = new Intent(HomeActivity.this,
                                    LoginActivity.class);
                            startActivityForResult(intent, 0);
                            break;
                        }
                        default:
                            break;
                    }
                }
            });
            return myView;
        }

        public String[] contentString = {"Food",
                "Book", "Health Aid", "Electronic device",
                "Sport Equipment", "Clothes",
                "Vehicle", "Others", "Contact Us",
                "Setting"};
        public Integer[] mThumbs = {R.drawable.ic_communnicate,
                R.drawable.ic_child, R.drawable.ic_toeic, R.drawable.ic_ielts,
                R.drawable.ic_imterview, R.drawable.ic_shaw,
                R.drawable.ic_effortless, R.drawable.ic_pronuncation,
                R.drawable.ic_grammar, R.drawable.ic_favourite,
              };
        public String[] colors = {"#47C7F1", "#FFB600", "#8A48B7", "#2DA630",
                "#FF6600", "#2563C1", "#FFB600", "#C28532", "#2563C1",
                "#47C7F1"};
    }

    boolean exit = false;

    @Override
    public void onBackPressed() {
        if (this.exit) {
            super.onBackPressed();
            return;
        }
        this.exit = true;
        Toast.makeText(getApplicationContext(),
                "Nhấn back thêm lần nữa để thoát", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                exit = false;
            }
        }, 2000);
    }
}

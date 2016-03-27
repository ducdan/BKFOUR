package com.example.vinh.bkfour;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by danhk on 26-Mar-16.
 */
public class NewProductActivity extends AppCompatActivity {
    String productName,description, quantity, unit;
    boolean isFree = true;
    Bitmap imgBitMap;
    ImageView capturedImageView;
    EditText txtProductName, txtDescription, txtQuantity, txtUnit,txtPrice;
    CheckBox checkBoxFree;
    Spinner spinnerCategoryID;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.new_product_layout);


        this.txtProductName = (EditText) findViewById(R.id.editTextProductName);
        this.txtDescription = (EditText) findViewById(R.id.editTextDescription);
        this.txtQuantity = (EditText) findViewById(R.id.editTextQuantity);
        txtQuantity.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.txtUnit = (EditText) findViewById(R.id.editTextUnit);
        this.txtPrice = (EditText) findViewById(R.id.editTextPrice);
        txtPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        txtPrice.setEnabled(false);
        this.checkBoxFree = (CheckBox) findViewById(R.id.checkBoxFree);
        checkBoxFree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    txtPrice.setEnabled(false);

                    txtPrice.setText("0");
                }
                else {
                    txtPrice.setText("");
                    txtPrice.setHint("0");

                    txtPrice.setEnabled(true);
                }
            }
        });
        this.capturedImageView = (ImageView) findViewById(R.id.imgViewCamera);
        this.capturedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        super.onCreate(savedInstanceState);
    }



    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
           imgBitMap= (Bitmap) extras.get("data");
            capturedImageView.setImageBitmap(imgBitMap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

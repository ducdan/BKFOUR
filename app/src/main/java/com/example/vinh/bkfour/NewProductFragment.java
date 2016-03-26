package com.example.vinh.bkfour;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by danhk on 26-Mar-16.
 */
public class NewProductFragment extends android.support.v4.app.Fragment{
    String productName,description, quantity, unit;
    boolean isFree = true;
    Bitmap imgBitMap;
    ImageView capturedImageView;
    EditText txtProductName, txtDescription, txtQuantity, txtUnit,txtPrice;
    CheckBox checkBoxFree;
    Spinner spinnerCategoryID;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = null;
        v = inflater.inflate(R.layout.new_product_layout, container,false);
        this.txtProductName = (EditText) v.findViewById(R.id.editTextProductName);
        this.txtDescription = (EditText) v.findViewById(R.id.editTextDescription);
        this.txtQuantity = (EditText) v.findViewById(R.id.editTextQuantity);
        txtQuantity.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.txtUnit = (EditText) v.findViewById(R.id.editTextUnit);
        this.txtPrice = (EditText) v.findViewById(R.id.editTextPrice);
        txtPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        txtPrice.setEnabled(false);
        this.checkBoxFree = (CheckBox) v.findViewById(R.id.checkBoxFree);
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
        this.capturedImageView = (ImageView) v.findViewById(R.id.imgViewCamera);
        this.capturedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        return v;
    }


    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
           imgBitMap= (Bitmap) extras.get("data");
            capturedImageView.setImageBitmap(imgBitMap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

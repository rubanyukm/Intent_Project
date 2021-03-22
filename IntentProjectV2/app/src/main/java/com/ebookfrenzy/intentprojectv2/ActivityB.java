package com.ebookfrenzy.intentprojectv2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityB extends AppCompatActivity {

    Data data = new Data();
    int[] images = data.getImages();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        TextView title = findViewById(R.id.itemTitle);
        TextView detail = findViewById(R.id.itemDetail);
        ImageView image = findViewById(R.id.image);
        byte[] imageAsBytes=null;

        getIncomingIntent();

    }

     private void getIncomingIntent() {
        ImageView image = findViewById(R.id.image);
        String itemTitle = getIntent().getStringExtra("itemTitleK");
        String itemDetail = getIntent().getStringExtra("itemDetailK");
        Bundle extras = getIntent().getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");
        Bitmap resized = Bitmap.createScaledBitmap(bmp, 200, 200, true);
        image.setImageBitmap(resized);

        setTexts(itemTitle, itemDetail);

    }

   private void setTexts(String itemTitle, String itemDetail) {
        TextView title = findViewById(R.id.itemTitle);
        title.setText(itemTitle);
        TextView detail = findViewById(R.id.itemDetail);
        detail.setText(itemDetail);

    }


}
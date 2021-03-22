package com.ebookfrenzy.intentprojectv2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import java.io.ByteArrayOutputStream;
import java.time.Instant;
import java.util.Random;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Random r = new Random();
    Data data = new Data();
    String[] titles = data.getTitles();
    String[] details = data.getDetails();
    int[] images = data.getImages();
    int randomIndex = (int) (Math.random() * images.length);


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemTitle;
        TextView itemDetail;

        ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemDetail = itemView.findViewById(R.id.item_detail);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Snackbar.make(v, "Click detected on item " + (position + 1), Snackbar.LENGTH_LONG).setAction("Action", null).show();

                    Intent intent = new Intent(v.getContext(), ActivityB.class);
                    String title = itemTitle.getText().toString().trim();
                    String detail = itemDetail.getText().toString().trim();
                    itemImage.buildDrawingCache();
                    Bitmap image = itemImage.getDrawingCache();
                    Bundle extras = new Bundle();

                    extras.putParcelable("imagebitmap", image);
                    intent.putExtras(extras);
                    intent.putExtra("itemTitleK", title);
                    intent.putExtra("itemDetailK", detail);

                    v.getContext().startActivity(intent);
                }
                private int getDrawableId(ImageView iv) {
                    return (Integer) iv.getTag();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public int getIndex() {
        int a = 0;
        int randomIndex = 0;
        while (a < 8) {
            randomIndex = (int) (Math.random() * titles.length);
            a++;
        }
        return randomIndex;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(titles[getIndex()]);
        viewHolder.itemDetail.setText(details[getIndex()]);
        viewHolder.itemImage.setImageResource(images[getIndex()]);

    }

}

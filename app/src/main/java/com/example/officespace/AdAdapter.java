package com.example.officespace;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;



public class AdAdapter extends FirestoreRecyclerAdapter<Ad, AdAdapter.AdHolder> {

    public AdAdapter(@NonNull FirestoreRecyclerOptions<Ad> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdHolder adHolder, int i, @NonNull Ad ad) {
        adHolder.textViewTitle.setText(ad.getTitle());
        adHolder.textViewLocation.setText(ad.getLocation());
        adHolder.textViewCompanyName.setText(ad.getCompanyName());
        adHolder.textViewSalary.setText(ad.getSalary());

        Picasso.get().load(ad.getImageUri())
                .into(adHolder.imageViewCompanyPhoto);

    }

    @NonNull
    @Override
    public AdHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_item,parent,false);
        return new AdHolder(v);
    }

    class AdHolder extends RecyclerView.ViewHolder {
        ImageView imageViewCompanyPhoto;
        TextView textViewCompanyName;
        TextView textViewSalary;
        TextView textViewTitle;
        TextView textViewLocation;

        public AdHolder(@NonNull View itemView)
        {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewSalary = itemView.findViewById(R.id.text_view_salary);
            textViewCompanyName = itemView.findViewById(R.id.text_view_company_name);
            textViewLocation = itemView.findViewById(R.id.text_view_location);
            imageViewCompanyPhoto = itemView.findViewById(R.id.image_view_company_photo);


        }
    }
}

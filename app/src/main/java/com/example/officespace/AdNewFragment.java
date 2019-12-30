package com.example.officespace;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class AdNewFragment extends Fragment {
    private EditText editTextTitle;
    private EditText editTextCompanyName;
    private EditText editTextAbout;
    private EditText editTextSalary;
    private EditText editTextLocation;
    private EditText editTextImage;
    private AdView mAdView;
    public AdNewFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ad_new, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextTitle = Objects.requireNonNull(getActivity()).findViewById(R.id.edit_text_title);
        editTextAbout = getActivity().findViewById(R.id.edit_text_about);
        editTextImage = getActivity().findViewById(R.id.edit_text_image_uri);
        editTextCompanyName = getActivity().findViewById(R.id.edit_text_company_name);
        editTextLocation = getActivity().findViewById(R.id.edit_text_location);
        editTextSalary = getActivity().findViewById(R.id.edit_text_salary);
        mAdView = getActivity().findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuInflater menuInflater = Objects.requireNonNull(getActivity()).getMenuInflater();
        menuInflater.inflate(R.menu.new_ad_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_ad) {
            saveAd();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveAd()
    {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String ownerId = null;
        if (currentUser != null) {
            ownerId = currentUser.getUid();
        }
        String title = editTextTitle.getText().toString();
        String about = editTextAbout.getText().toString();
        String companyName = editTextCompanyName.getText().toString();
        String imageUri = editTextImage.getText().toString();
        String location = editTextLocation.getText().toString();
        String salary = editTextSalary.getText().toString();
        if( title.trim().isEmpty() || about.trim().isEmpty() || companyName.trim().isEmpty() || imageUri.trim().isEmpty() || location.trim().isEmpty() || salary.trim().isEmpty())
        {
            Toast.makeText(getActivity(), "Ad failed to create fill info",Toast.LENGTH_LONG).show();
            return;
        }

        CollectionReference adbookRef = FirebaseFirestore.getInstance()
                .collection("ads");
        adbookRef.add(new Ad(companyName,imageUri,location,ownerId,salary,title,about));

        FragmentTransaction ft = Objects.requireNonNull(getFragmentManager()).beginTransaction();
        new AddDialog().show(ft, "OKEY");
    }
}

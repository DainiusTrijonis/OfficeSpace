package com.example.officespace;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AdFragment extends Fragment {

    private Ad ad;
    private String id;

    private TextView textViewTitle;
    private TextView textViewAbout;
    private TextView textViewCompany;
    public AdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        ad = (Ad) bundle.getSerializable("ad");
        id = bundle.getString("id");
        textViewTitle = getActivity().findViewById(R.id.text_title);
        textViewAbout = getActivity().findViewById(R.id.text_description);
        textViewCompany = getActivity().findViewById(R.id.text_company_name);


        textViewTitle.setText(ad.getTitle());
        textViewCompany.setText(ad.getCompanyName());
        textViewAbout.setText(ad.getAbout());
    }
}

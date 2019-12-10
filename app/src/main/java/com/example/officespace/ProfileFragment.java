package com.example.officespace;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private Button signOutButton;

    private FirebaseUser firebaseUser;
    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        TextView nameTextView = getView().findViewById(R.id.textViewName);
        TextView emailTextView = getView().findViewById(R.id.emailTextView);


        if(user != null) {
            signOutButton = view.findViewById(R.id.signOutButton);
            signOutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AuthUI.getInstance()
                            .signOut(getContext())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                public void onComplete(@NonNull Task<Void> task) {
                                    Navigation.findNavController(getView()).navigate(R.id.action_profileFragment_to_authenticationFragment);
                                }
                            });
                }
            });

            //emailTextView.setText(firebaseUser.getEmail());
            //nameTextView.setText(firebaseUser.getDisplayName());
        }
        else {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_authenticationFragment);
        }
    }





}

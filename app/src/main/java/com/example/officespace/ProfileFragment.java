package com.example.officespace;


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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private Button signOutButton;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInAccount googleAccount;
    public ProfileFragment() {
        // Required empty public constructor
    }

    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView nameTextView = getView().findViewById(R.id.textViewName);
        TextView emailTextView = getView().findViewById(R.id.emailTextView);


        //Google auth
        googleAccount = GoogleSignIn.getLastSignedInAccount(getContext());
        if(googleAccount != null) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
            signOutButton = view.findViewById(R.id.signOutButton);
            signOutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signOut(v);
                }
            });

            //emailTextView.setText(googleAccount.getEmail());
            //mAuth = FirebaseAuth.getInstance();
            //FirebaseUser currentUser = mAuth.getCurrentUser();
            //Log.v("SUKA ",currentUser.getEmail());
            //nameTextView.setText(googleAccount.getDisplayName());


        }
        else {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_authenticationFragment);
        }
    }

    private void signOut(final View view) {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_authenticationFragment);
                    }
                });
    }




}

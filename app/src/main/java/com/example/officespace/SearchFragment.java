package com.example.officespace;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Objects;

public class SearchFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference adbookRef = db.collection("ads");
    private AdAdapter adapter;
    private RecyclerView recyclerView;

    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = Objects.requireNonNull(getActivity()).findViewById(R.id.recycler_view2);
        final EditText editTextTitle = getActivity().findViewById(R.id.edit_text_title);
        Button buttonSearch = getActivity().findViewById(R.id.search_button);

        Query query = adbookRef;
        FirestoreRecyclerOptions<Ad> options = new FirestoreRecyclerOptions.Builder<Ad>()
                .setQuery(query,Ad.class)
                .build();
        adapter = new AdAdapter(options);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.stopListening();
                Query query = adbookRef.whereEqualTo("title",editTextTitle.getText().toString());
                FirestoreRecyclerOptions<Ad> options = new FirestoreRecyclerOptions.Builder<Ad>()
                        .setQuery(query,Ad.class)
                        .build();
                adapter = new AdAdapter(options);
                adapter.startListening();
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new AdAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                        Ad ad = documentSnapshot.toObject(Ad.class);
                        String id = documentSnapshot.getId();
                        Bundle bundle = new Bundle();
                        bundle.putString("id",id);
                        bundle.putSerializable("ad",ad);
                        Navigation.findNavController(Objects.requireNonNull(getView())).navigate(R.id.action_searchFragment_to_adFragment,bundle);
                    }
                });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}

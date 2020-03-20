package cl.dmix.desafiolatam.doggappmvp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.dmix.desafiolatam.doggappmvp.R;
import cl.dmix.desafiolatam.doggappmvp.adapters.DogAdapter;

public class DogFragment extends Fragment implements DogAdapter.onLongDogClickListener{
    private static final String ARG_PARAM1 = "dogPictures";

    private List<String> dogPictures;

    private ImageView dogImageView;

    public DogFragment() {
        // Required empty public constructor
    }

    static DogFragment newInstance(List<String> dogPictures) {
        DogFragment fragment = new DogFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, (ArrayList<String>) dogPictures);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dogPictures = getArguments().getStringArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dog, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.doggy_recyclerView);
        DogAdapter dogAdapter = new DogAdapter(dogPictures, getContext(),this);
        recyclerView.setAdapter(dogAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onLongClick(DogAdapter.ViewHolder viewHolder, String dogURL) {
        Toast.makeText(getContext(), "Obtuviste el enlace: "+dogURL, Toast.LENGTH_SHORT).show();
    }
}

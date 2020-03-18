package cl.dmix.desafiolatam.doggappmvp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.dmix.desafiolatam.doggappmvp.R;
import cl.dmix.desafiolatam.doggappmvp.adapters.BreedAdapter;

public class BreedFragment extends Fragment implements BreedAdapter.OnItemClickListener{
    private RecyclerView recyclerView;
    private BreedAdapter breedAdapter;

    private static final String ARG_PARAM1 = "breedList";
    private List<String> breedList;

    public BreedFragment() {
        // Required empty public constructor
    }

    public static BreedFragment newInstance(List<String> breedList) {
        BreedFragment fragment = new BreedFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, (ArrayList<String>) breedList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            breedList = getArguments().getStringArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_breed, container, false);
        Log.i("onCreateView", String.valueOf(breedList));
        recyclerView = view.findViewById(R.id.breedList_recyclerView);
        breedAdapter = new BreedAdapter(breedList, getContext(), this);
        recyclerView.setAdapter(breedAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void OnClick(BreedAdapter.ViewHolder viewHolder, String dogBreed) {
        Toast.makeText(getContext(), "Has hecho click en: "+dogBreed, Toast.LENGTH_SHORT).show();
    }
}

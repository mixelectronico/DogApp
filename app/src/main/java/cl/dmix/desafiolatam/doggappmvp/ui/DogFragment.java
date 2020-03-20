package cl.dmix.desafiolatam.doggappmvp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.dmix.desafiolatam.doggappmvp.R;

public class DogFragment extends Fragment {
    private static final String ARG_PARAM1 = "dogPictures";

    private List<String> dogPictures;

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
        Log.e("PROCESO", "Estás en el nuevo fragmento y tienes la siguiente lista: "+dogPictures);
        Toast.makeText(getContext(), "ESTAS EN EL NUEVO FRAGMENTO", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog, container, false);
    }
}

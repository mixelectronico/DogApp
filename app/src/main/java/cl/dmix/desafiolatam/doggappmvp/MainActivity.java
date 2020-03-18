package cl.dmix.desafiolatam.doggappmvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.dmix.desafiolatam.doggappmvp.adapters.BreedAdapter;
import cl.dmix.desafiolatam.doggappmvp.api.RetrofitClient;
import cl.dmix.desafiolatam.doggappmvp.api.apiDog;
import cl.dmix.desafiolatam.doggappmvp.model.BreedListResponse;
import cl.dmix.desafiolatam.doggappmvp.ui.BreedFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiDog service = RetrofitClient.getRetrofitInstance().create(apiDog.class);
        Call<BreedListResponse> callBreedList = service.getBreedList();
        callBreedList.enqueue(new Callback<BreedListResponse>() {
            @Override
            public void onResponse(Call<BreedListResponse> call, Response<BreedListResponse> response) {

                if (response.body() != null) {
                    List<String> breedListFromApi;
                    breedListFromApi = response.body().getBreedList();
                    Log.i("onCreate", "La respuesta no es nula y contiene "+breedListFromApi);
                    makeBreedListFragment(breedListFromApi);
                }else{
                    Log.e("PROCESO", "La respuesta de la API está vacía");
                }
            }
            @Override
            public void onFailure(Call<BreedListResponse> call, Throwable t) {
                Log.e("PROCESO", String.valueOf(t));
            }
        });
    }

    public void makeBreedListFragment(List<String> breedList){
        BreedFragment breedFragment = BreedFragment.newInstance(breedList);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_frame_layout,breedFragment, "DetailFragment")
                .commit();
    }
}

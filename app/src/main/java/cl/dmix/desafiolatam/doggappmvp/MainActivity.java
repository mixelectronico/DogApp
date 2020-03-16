package cl.dmix.desafiolatam.doggappmvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.dmix.desafiolatam.doggappmvp.adapters.BreedAdapter;
import cl.dmix.desafiolatam.doggappmvp.api.RetrofitClient;
import cl.dmix.desafiolatam.doggappmvp.api.apiDog;
import cl.dmix.desafiolatam.doggappmvp.model.BreedListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BreedAdapter breedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DECLARO Y USO LA API.
        apiDog service = RetrofitClient.getRetrofitInstance().create(apiDog.class);
        Call<BreedListResponse> callBreedList = service.getBreedList();
        callBreedList.enqueue(new Callback<BreedListResponse>() {
            @Override
            public void onResponse(Call<BreedListResponse> call, Response<BreedListResponse> response) {
                List<String> breedListFromApi;
                if (response.body() != null) {
                    breedListFromApi = response.body().getBreedList();
                    /*
                    *      INICIO DEL RECYCLERVIEW
                    * */
                    recyclerView = findViewById(R.id.breedListRecyclerView);
                    breedAdapter = new BreedAdapter(breedListFromApi);
                    recyclerView.setAdapter(breedAdapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    /*
                    *      FIN DEL RECYCLERVIEW
                    * */
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
}

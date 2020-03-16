package cl.dmix.desafiolatam.doggappmvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import cl.dmix.desafiolatam.doggappmvp.api.RetrofitClient;
import cl.dmix.desafiolatam.doggappmvp.api.apiDog;
import cl.dmix.desafiolatam.doggappmvp.model.BreedListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<String> breedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queryBreedList();
    }

    /*
    * Este método trae la lista de razas de perro de la API https://dog.ceo/.
    * */
    public void queryBreedList(){
        apiDog service = RetrofitClient.getRetrofitInstance().create(apiDog.class);
        Call<BreedListResponse> callBreedList = service.getBreedList();
        callBreedList.enqueue(new Callback<BreedListResponse>() {
            @Override
            public void onResponse(Call<BreedListResponse> call, Response<BreedListResponse> response) {
                List<String> breedListFromApi;
                if (response.body() != null) {
                    breedListFromApi = response.body().getBreedList();
                    Log.i("PERRITOS",String.valueOf(breedListFromApi));
                    breedList = breedListFromApi;
                }else{
                    Log.e("PERRITOS", "ES NULO");
                }

            }

            @Override
            public void onFailure(Call<BreedListResponse> call, Throwable t) {
                Log.e("PERRITOS", String.valueOf(t));
            }
        });
    }
}

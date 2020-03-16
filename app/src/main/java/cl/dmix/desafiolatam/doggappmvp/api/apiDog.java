package cl.dmix.desafiolatam.doggappmvp.api;

import cl.dmix.desafiolatam.doggappmvp.model.BreedImageListResponse;
import cl.dmix.desafiolatam.doggappmvp.model.BreedListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface apiDog {

    @GET("api/breeds/list/")
    Call<BreedListResponse> getBreedList();

    @GET("api/breed/{breed}/images/")
    Call<BreedImageListResponse> getBreedImageList(@Path("breed") String breed);
}

package service;

import java.util.List;

import model.DeveloperModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetData {

    @GET("/search/users")
    Call<List<DeveloperModel>> getDevelopers();
}

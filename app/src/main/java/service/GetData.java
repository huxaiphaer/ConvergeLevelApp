package service;

import java.util.List;

import model.DeveloperModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetData {

    @GET("search/users?q=type:User+location:Nairobi+language:JAVA")
    Call<List<DeveloperModel>> getDevelopers();
}

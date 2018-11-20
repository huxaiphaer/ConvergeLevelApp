package service;

import model.DeveloperModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetData {

    @GET("search/users?q=type:User+location:Nairobi+language:JAVA")
    Call<DeveloperModel> getDevelopers();
}

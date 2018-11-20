package presenter;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.levelapp.converge.convergelevelapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import adapter.DevelopersAdapter;
import model.DeveloperModel;
import model.Items;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import service.GetData;
import service.RetrofitClient;

public class GithubPresenter {

    private Context context;
    private Activity activity;
    private ProgressBar pb;

    public GithubPresenter(Context context, Activity activity) {
        this.context =context;
        this.activity= activity;
    }

    public void getJavaDevelopers(){

        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);

        Call<DeveloperModel> call = service.getDevelopers();

        call.enqueue(new Callback<DeveloperModel>() {
            @Override
            public void onResponse(@NonNull Call<DeveloperModel> call, Response<DeveloperModel> response) {


                try {
                    DeveloperModel jsonResponse = response.body();
                    ArrayList<Items> data = new ArrayList<>(Arrays.asList(jsonResponse.getItems()));

                    RecyclerView rv;
                    rv = activity.findViewById(R.id.rv);


                    pb= activity.findViewById(R.id.pb);
                    pb.setVisibility(View.VISIBLE);

                    rv.setLayoutManager(new LinearLayoutManager(context));
                    DevelopersAdapter developersAdapter = new DevelopersAdapter(data, context);
                    rv.setAdapter(developersAdapter);

                    pb.setVisibility(View.INVISIBLE);
                } catch (Exception e) {

                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(@NonNull  Call<DeveloperModel> call, Throwable t) {

                Toast.makeText(context, "Unable to load users.", Toast.LENGTH_SHORT).show();

            }
        });

    }


}

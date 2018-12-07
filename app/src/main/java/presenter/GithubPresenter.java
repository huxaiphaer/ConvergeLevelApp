package presenter;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import android.support.test.espresso.idling.CountingIdlingResource;
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

public class GithubPresenter extends Activity{

    private Context context;
    private Activity activity;
    private ProgressBar pb;
    private RecyclerView rv;

    public GithubPresenter(Context context, Activity activity) {
        this.context =context;
        this.activity= activity;
    }

    public void getJavaDevelopers(final CountingIdlingResource countingIdlingResource){

        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);
        countingIdlingResource.increment();

        Call<DeveloperModel> call = service.getDevelopers();

        call.enqueue(new Callback<DeveloperModel>() {
            @Override
            public void onResponse(@NonNull Call<DeveloperModel> call, Response<DeveloperModel> response) {

                try {
                    DeveloperModel jsonResponse = response.body();
                    ArrayList<Items> data = new ArrayList<>(Arrays.asList(jsonResponse.getItems()));
                    rv = activity.findViewById(R.id.rv);

                    pb= activity.findViewById(R.id.pb);
                    pb.setVisibility(View.VISIBLE);

                    DevelopersAdapter developersAdapter = new DevelopersAdapter(data, context);
                    rv.setAdapter(developersAdapter);

                    pb.setVisibility(View.INVISIBLE);
                    countingIdlingResource.decrement();
                } catch (Exception e) {

                    e.printStackTrace();
                    countingIdlingResource.decrement();

                }
            }

            @Override
            public void onFailure(@NonNull  Call<DeveloperModel> call, Throwable t) {

                Toast.makeText(context, "Unable to load users.", Toast.LENGTH_SHORT).show();
                countingIdlingResource.decrement();
            }
        });
    }


}

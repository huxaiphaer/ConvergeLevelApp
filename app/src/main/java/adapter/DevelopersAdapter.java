package adapter;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.levelapp.converge.convergelevelapp.DetailsActivity;
import com.levelapp.converge.convergelevelapp.R;

import java.util.List;

import model.DeveloperModel;

public class DevelopersAdapter extends  RecyclerView.Adapter<DevelopersAdapter.DeveloperHolder> {


    private List<DeveloperModel> itemList;
    private Context context;
    public static final String USERNAME = "username";
    public static final String URL ="url";
    public static final String PROFILE_PIC ="profile_pic";

    public DevelopersAdapter(List<DeveloperModel> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }


    @NonNull
    @Override
    public DeveloperHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_developers, parent, false);
        return new DeveloperHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull DeveloperHolder holder, int position) {


        final DeveloperModel dm = itemList.get(position);
        final String username = dm.getName();
        final String  profile_pic = dm.getProfile_pic();
        final String url = dm.getUrl();

        holder.usernameTxt.setText(username);

        Ion.with(DevelopersAdapter.this.context)
                .load(profile_pic)
                 .intoImageView(holder.profileImg);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DevelopersAdapter.this.context, DetailsActivity.class);
                i.putExtra(USERNAME,username);
                i.putExtra(URL,url);
                i.putExtra(PROFILE_PIC,profile_pic);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static final class DeveloperHolder extends RecyclerView.ViewHolder

    {
        private View root;
        private ImageView profileImg;
        private TextView usernameTxt;

        private DeveloperHolder(View itemView) {
            super(itemView);
            root = itemView;
            profileImg = itemView.findViewById(R.id.profile_img);
            usernameTxt = itemView.findViewById(R.id.username_txt);
        }
    }
}
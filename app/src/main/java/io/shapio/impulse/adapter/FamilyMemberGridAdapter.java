package io.shapio.impulse.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.shapio.impulse.R;
import io.shapio.impulse.activity.DashboardActivity;
import io.shapio.impulse.activity.IndividualPortfolioActivity;
import io.shapio.impulse.model.User;

/**
 * Created by TheOSka on 16/4/2016.
 */
public class FamilyMemberGridAdapter extends RecyclerView.Adapter<FamilyMemberGridAdapter.MyViewHolder> {

    ArrayList<User> data = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    private LinearLayout gotRowTags;
    private boolean isButtomSheet;


    public FamilyMemberGridAdapter(Context context, ArrayList<User> data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // get the root of the custom row
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_family_portfolio, parent, false);

        // pass the view to the view holder
        MyViewHolder holder = new MyViewHolder(view);
        Log.v("result", "in the MyViewHolder");
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        User current = data.get(position);
        Log.v("bottomSheet", "the bs name" + current.getName());

        holder.memberName.setText(data.get(position).getName());
        holder.memberIcon.setImageResource(data.get(position).getMemberIcon());
    }


    public void addItem(User item, int index) {

        data.add(item);
        notifyItemInserted(index);
    }

    @Override
    public int getItemCount() {
        Log.v("result ", "the getItemCount size is " + data.size());
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView memberIcon;
        TextView memberName;

        public MyViewHolder(View itemView) {
            super(itemView);
            memberIcon = (ImageView) itemView.findViewById(R.id.member_icon);
            memberName= (TextView) itemView.findViewById(R.id.member_name);
            memberIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, IndividualPortfolioActivity.class));
                        Toast.makeText(context.getApplicationContext(),"test",Toast.LENGTH_LONG).show();
                    }
                });
            }

        @Override
        public void onClick(View v) {

        }
    }
}


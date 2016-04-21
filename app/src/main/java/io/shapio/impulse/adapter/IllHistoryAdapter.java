package io.shapio.impulse.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import io.shapio.impulse.R;
import io.shapio.impulse.activity.ChatDoctorActivity;
import io.shapio.impulse.model.HomePageItem;
import io.shapio.impulse.model.IllHistoryItem;

/**
 * Created by TheOSka on 16/4/2016.
 */
public class IllHistoryAdapter extends RecyclerView.Adapter<IllHistoryAdapter.MyViewHolder> {

    ArrayList<IllHistoryItem> data = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    private LinearLayout gotRowTags;
    private boolean isButtomSheet;


    public IllHistoryAdapter(Context context, ArrayList<IllHistoryItem> data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // get the root of the custom row
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_homepage_item, parent, false);

        // pass the view to the view holder
        MyViewHolder holder = new MyViewHolder(view);
        Log.v("result", "in the MyViewHolder");
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        IllHistoryItem current = data.get(position);

        holder.diseaseName.setText(data.get(position).getDiseaseName());
        holder.diseaseDesc.setText(data.get(position).getDiseaseDesc());
        holder.date.setText(data.get(position).getDate());
    }


    public void addItem(IllHistoryItem item, int index) {

        data.add(item);
        notifyItemInserted(index);
    }

    @Override
    public int getItemCount() {
        Log.v("result ", "the getItemCount size is " + data.size());
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView diseaseName, diseaseDesc, date;

        public MyViewHolder(View itemView) {
            super(itemView);
            diseaseName = (TextView) itemView.findViewById(R.id.disease_name);
            diseaseDesc = (TextView) itemView.findViewById(R.id.disease_desc);
            date = (TextView) itemView.findViewById(R.id.create_date);
//            itemIcon.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        HomePageItem current = data.get(getPosition());
//                        Log.v("wish", "clicked image " + current.getItemName());
//                        Snackbar.make(v, "Clicked  " + current.getItemName(), Snackbar.LENGTH_SHORT).show();
//                        if ( current.getItemName().equals(context.getResources().getString(R.string.item_name_chat))){
//                            Log.i("oscar","in");
//                                context.startActivity(new Intent(context, ChatDoctorActivity.class));
//                        }
//                    }
//                });
            }

        @Override
        public void onClick(View v) {

        }
    }
}

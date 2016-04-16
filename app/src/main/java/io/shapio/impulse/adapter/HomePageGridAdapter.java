package io.shapio.impulse.adapter;

import android.content.Context;
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
import io.shapio.impulse.model.HomePageItem;

/**
 * Created by TheOSka on 16/4/2016.
 */
public class HomePageGridAdapter  extends RecyclerView.Adapter<HomePageGridAdapter.MyViewHolder> {

    ArrayList<HomePageItem> data = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    private LinearLayout gotRowTags;
    private boolean isButtomSheet;

    private TextView gotTag1, gotTag2, gotTag3, responseNum;

    public HomePageGridAdapter(Context context, ArrayList<HomePageItem> data) {
        this.context = context;
        this.data = data;
        this.isButtomSheet = isButtomSheet;
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

        HomePageItem current = data.get(position);
        Log.v("bottomSheet", "the bs name" + current.getItemName());

        holder.itemText.setText(data.get(position).getItemName());
        holder.itemIcon.setImageResource(data.get(position).getItemIcon());
    }


    public void addItem(HomePageItem item, int index) {

        data.add(item);
        notifyItemInserted(index);
    }

    @Override
    public int getItemCount() {
        Log.v("result ", "the getItemCount size is " + data.size());
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView itemIcon;
        TextView itemText;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemIcon = (ImageView) itemView.findViewById(R.id.item_icon);
            itemText = (TextView) itemView.findViewById(R.id.item_name);
            itemIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HomePageItem current = data.get(getPosition());
                        Log.v("wish", "clicked image " + current.getItemName());
                        Snackbar.make(v, "Added type tag " + current.getItemName(), Snackbar.LENGTH_SHORT).show();
                        gotTag1.setVisibility(View.VISIBLE);
                        gotTag1.setText("# " + current.getItemName());
                    }
                });
            }

        @Override
        public void onClick(View v) {

        }
    }
}


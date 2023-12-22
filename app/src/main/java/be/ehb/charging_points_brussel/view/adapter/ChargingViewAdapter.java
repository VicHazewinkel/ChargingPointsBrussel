package be.ehb.charging_points_brussel.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.ehb.charging_points_brussel.R;
import be.ehb.charging_points_brussel.model.ChargingEntityTable;


public class ChargingViewAdapter extends RecyclerView.Adapter<ChargingViewAdapter.ViewHolder>{ // extends RecyclerView.Adapter<ChargingViewAdapter.ViewHolder> {    // idk kent ViewHolder niet for some reason

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView typedutTV, gemeenteTV, cpTV, rueTV, nrTV;

        public ViewHolder(@Nullable android.view.View itemView) {
            super(itemView);
            typedutTV = itemView.findViewById(R.id.tv_row_typedut);
            gemeenteTV = itemView.findViewById(R.id.tv_row_gemeente);
            cpTV = itemView.findViewById(R.id.tv_row_cp);
            rueTV = itemView.findViewById(R.id.tv_row_rue);
            nrTV = itemView.findViewById(R.id.tv_row_nr);
        }
    }

    private ArrayList<ChargingEntityTable> items;

    public ChargingViewAdapter() {
        this.items = new ArrayList<ChargingEntityTable>();
    }

    public void addItems(List<ChargingEntityTable> newItems){
        this.items = new ArrayList<ChargingEntityTable>();
        this.items.addAll(newItems);
    }

    @Nullable
    @Override   //why not work
    public ViewHolder onCreateViewHolder(@Nullable ViewGroup parent, int viewtype){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_row, parent, false);
        return new ViewHolder(v);
    }

    @Override   //why not work
    public void onBindViewHolder(@Nullable ViewHolder holder, int position) {
        ChargingEntityTable currentItem = items.get(position);
        holder.typedutTV.setText(currentItem.getTypedut());
        holder.gemeenteTV.setText(currentItem.getGemeente());
        holder.cpTV.setText(""+currentItem.getCp());           // cp is long, but setText() only accepts String or int
                                                            // cast cp to String
        holder.rueTV.setText(currentItem.getRue());
        holder.nrTV.setText(""+currentItem.getNr());           // cp is long, but setText() only accepts String or int
                                                            // cast nr to String
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //
    //referencing "adapter" in SQLite
    //




}

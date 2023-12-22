package be.ehb.charging_points_brussel.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.ehb.charging_points_brussel.R;
import be.ehb.charging_points_brussel.model.ChargingEntityTable;
import be.ehb.charging_points_brussel.view.adapter.ChargingViewAdapter;
import be.ehb.charging_points_brussel.viewmodel.DataViewModel;

public class OverviewFragment extends Fragment {
    public  OverviewFragment() {
    }

    public static OverviewFragment newInstance() {
        OverviewFragment fragment = new OverviewFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.overview_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {                            //--> createa a RecyclerView method with adapter for displaying the charging point data
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvData = view.findViewById(R.id.rv_data);

        ChargingViewAdapter chargingViewAdapter = new ChargingViewAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        rvData.setAdapter(chargingViewAdapter);
        rvData.setLayoutManager(layoutManager);

        DataViewModel viewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);

        viewModel.getAllData().observe(getViewLifecycleOwner(), (List<ChargingEntityTable> items) -> {        // getAllData van DAO
            chargingViewAdapter.addItems(items);
            chargingViewAdapter.notifyDataSetChanged();
        });
    }
}

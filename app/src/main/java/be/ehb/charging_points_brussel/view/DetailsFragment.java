package be.ehb.charging_points_brussel.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import be.ehb.charging_points_brussel.R;
import be.ehb.charging_points_brussel.model.ChargingEntityTable;
import be.ehb.charging_points_brussel.model.DAO;

public class DetailsFragment extends Fragment {
    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance() {
        DetailsFragment fragment = new DetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.id.details_fraglent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ChargingEntityTable chargingDetails = (ChargingEntityTable) getArguments().getSerializable("details");

        TextView typedut = view.findViewById(R.id.tv_details_typedut);
        TextView gemeente = view.findViewById(R.id.tv_details_gemeente);
        TextView cp = view.findViewById(R.id.tv_details_cp);
        TextView rue = view.findViewById(R.id.tv_details_rue);
        TextView nr = view.findViewById(R.id.tv_details_nr);

        typedut.setText(chargingDetails.getTypedut());
        gemeente.setText(chargingDetails.getGemeente());
        cp.setText(""+chargingDetails.getCp());
        rue.setText(chargingDetails.getRue());
        nr.setText(""+chargingDetails.getNr());
    }


}

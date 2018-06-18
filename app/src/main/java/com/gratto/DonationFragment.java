package com.gratto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gratto.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonationFragment extends Fragment {


    public DonationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation, container, false);

        Button btnAnonymouslyDonation = (Button) view.findViewById(R.id.btnAnonymouslyDonation);
        btnAnonymouslyDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DonationsDifferencesFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_main, fragment).addToBackStack("");
                fragmentTransaction.commit();
            }
        });

        Button btnLoggedDonation = (Button) view.findViewById(R.id.btnLoggedDonation);
        btnLoggedDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DonationsDifferencesFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_main, fragment).addToBackStack("");
                fragmentTransaction.commit();
            }
        });

        Button btnReturnInstitution = (Button) view.findViewById(R.id.btnReturnInstitution);
        btnReturnInstitution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new InstitutionFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_main, fragment).addToBackStack("");
                fragmentTransaction.commit();
            }
        });

        TextView lblDonationsDifferences = (TextView) view.findViewById(R.id.lblDonationsDifferences);
        lblDonationsDifferences.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFragmentManager().beginTransaction().replace(R.id.content_main, new DonationsDifferencesFragment()).addToBackStack("").commit();
            }
        });

        return view;
    }

}

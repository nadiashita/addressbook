package com.example.addressbook.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.addressbook.Model.EmpModel;
import com.example.addressbook.EmployeeActivity;
import com.example.addressbook.R;
import com.example.addressbook.Api.RetroServer;
import com.example.addressbook.Model.AllModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;
    private Context context;
    private List<EmpModel> objList;
    Map<String, List<Double>> hashmap;
    Map<String, String> hashmap2;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2) {

        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_details, container, false);
        context = getActivity();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        TextView name = v.findViewById(R.id.Name);
        TextView city = v.findViewById(R.id.City);
        TextView phone = v.findViewById(R.id.Phone);
        TextView member = v.findViewById(R.id.MemberSince);
        TextView email = v.findViewById(R.id.Email);

        try {
            int id = getActivity().getIntent().getIntExtra("id", 0);
            RetroServer.getService(id + "/").RetrieveData().enqueue(new Callback<AllModel>() {
                @Override
                public void onResponse(Call<AllModel> call, Response<AllModel> response) {
                    int statuscode = response.body().getStatusCode();

                    if (statuscode == 200) {
                        objList = response.body().getEmployees();

                        for (EmpModel data : objList) {
                            name.setText(data.getName().getTitle() + ". " + data.getName().getFirst() + " " + data.getName().getLast());
                            city.setText("City : " + data.getLocation().getCity());
                            phone.setText( "Phone : " + data.getPhone());
                            member.setText("Member since : " + data.getRegistered().getDate());
                            email.setText( "Email :" + data.getEmail());

                            List<Double> latlong = new ArrayList<>();
                            latlong.add(data.getLocation().getCoordinates().getLatitude());
                            latlong.add(data.getLocation().getCoordinates().getLongitude());
                            hashmap = new HashMap<>();
                            hashmap.put("latlong", latlong);
                            hashmap2 = new HashMap<>();
                            hashmap2.put("city", data.getLocation().getCity());

                            SupportMapFragment supportMapFragmen = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
                            supportMapFragmen.getMapAsync(DetailsFragment.this);
                        }

                    } else {
                        Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AllModel> call, Throwable t) {
                    Toast.makeText(context, "Gagal menghubungi server " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        v.findViewById(R.id.tvAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmployeeActivity.objListGlobal.add(objList.get(0));
                AddressFragment fragment = new AddressFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_view, AddressFragment.newInstance("DetailsFragment", "AddressFragment", EmployeeActivity.objListGlobal));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return v;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        map = googleMap;

        LatLng Location = new LatLng(hashmap.get("latlong").get(0), hashmap.get("latlong").get(1));
        map.addMarker(new MarkerOptions().position(Location).title(hashmap2.get("city")));
        map.moveCamera(CameraUpdateFactory.newLatLng(Location));
    }
}
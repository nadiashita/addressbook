package com.example.addressbook.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.addressbook.Adapter.AddressAdapter;
import com.example.addressbook.Model.EmpModel;
import com.example.addressbook.R;
import com.google.android.gms.maps.GoogleMap;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddressFragment extends Fragment {

    private GoogleMap map;
    private Context context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<EmpModel> model;
    private RecyclerView rcAddress;


    public AddressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdressBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddressFragment newInstance(String param1, String param2, List<EmpModel> model) {
        AddressFragment fragment = new AddressFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.model = model;
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_adress, container, false);
        context = getActivity();

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        rcAddress = v.findViewById(R.id.rcAddress);

        AddressAdapter adapter = new AddressAdapter(context, model);
        rcAddress.setAdapter(adapter);
        rcAddress.setLayoutManager(new LinearLayoutManager(context));
        rcAddress.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        return v;
    }

}
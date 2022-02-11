package com.example.myaddressbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myaddressbook.adapter.EmployeeAdapter;
import com.example.myaddressbook.api.APIRequestData;
import com.example.myaddressbook.api.RetroServer;
import com.example.myaddressbook.model.AllRespModel;
import com.example.myaddressbook.model.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rcEmployee;
    private Context context;
    public static MainActivity mainActivity;
    public List<EmployeeModel> objList;
    public static List<EmployeeModel> objListGlobal = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;
        rcEmployee = findViewById(R.id.rcEmployee);
        context = getApplicationContext();

        getSupportActionBar().hide();

        findViewById(R.id.btnsearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tvSearch = findViewById(R.id.edtsearch);
                String keyword = tvSearch.getText().toString().trim().toUpperCase();
                retrieveData(1, keyword);
            }
        });

        retrieveData(0, null);
    }

    public void retrieveData(final int type, final String keyword) {
        try {
            RetroServer.getService("").RetrieveData().enqueue(new Callback<AllRespModel>() {
                @Override
                public void onResponse(Call<AllRespModel> call, Response<AllRespModel> response) {
                    int statuscode = response.body().getStatusCode();

                    if (statuscode == 200) {
                        objList = response.body().getEmployees();
                        if (type == 1) {
                            List<EmployeeModel> objList2 = new ArrayList<>();
                            for (EmployeeModel data : objList) {
                                String title = data.getName().getTitle().trim().toUpperCase();
                                String first = data.getName().getFirst().trim().toUpperCase();
                                String last = data.getName().getLast().trim().toUpperCase();
                                String name = title + ". " + first + last;
                                if (name.contains(keyword)) {
                                    objList2.add(data);
                                    EmployeeAdapter adapter = new EmployeeAdapter(context, objList2);
                                    rcEmployee.setAdapter(adapter);
                                    rcEmployee.setLayoutManager(new LinearLayoutManager(context));
                                    rcEmployee.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
                                }
                            }
                        } else {
                            EmployeeAdapter adapter = new EmployeeAdapter(context, objList);
                            rcEmployee.setAdapter(adapter);
                            rcEmployee.setLayoutManager(new LinearLayoutManager(context));
                            rcEmployee.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
                        }

                    } else {
                        Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AllRespModel> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Gagal menghubungi server karena " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
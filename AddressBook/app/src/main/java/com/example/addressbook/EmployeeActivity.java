package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addressbook.Adapter.EmpAdapter;
import com.example.addressbook.Api.RetroServer;
import com.example.addressbook.Model.AllModel;
import com.example.addressbook.Model.EmpModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeActivity extends AppCompatActivity {

    private RecyclerView rcEmp;
    private Context ctx;
    public static EmployeeActivity employeeActivity;
    public List<EmpModel> objList;
    public static List<EmpModel> objListGlobal = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        employeeActivity = this;
        ctx = getApplicationContext();
        rcEmp = findViewById(R.id.rcEmployee);

        getSupportActionBar().hide();

        findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView tvSearch = findViewById(R.id.edtSearch);
                String keyword = tvSearch.getText().toString().trim().toUpperCase();
                retrieveData(1, keyword);
            }
        });

        retrieveData(0, null);
    }

    public void retrieveData(int type, String keyword) {
        try {

            RetroServer.getService("").RetrieveData().enqueue(new Callback<AllModel>() {
                @Override
                public void onResponse(Call<AllModel> call, Response<AllModel> response) {

                    int statuscode = response.body().getStatusCode();

                    if (statuscode == 200) {
                        objList = response.body().getEmployees();
                        if (type == 1) {

                            List<EmpModel> objList2 = new ArrayList<>();
                            for (EmpModel data : objList) {

                                String title = data.getName().getTitle().trim().toUpperCase();
                                String first = data.getName().getFirst().trim().toUpperCase();
                                String last = data.getName().getLast().trim().toUpperCase();
                                String name = title + ". " + first + last;
                                if (name.contains(keyword)) {

                                    objList2.add(data);
                                    EmpAdapter adapter = new EmpAdapter(ctx, objList2);
                                    rcEmp.setAdapter(adapter);
                                    rcEmp.setLayoutManager(new LinearLayoutManager(ctx));
                                    rcEmp.addItemDecoration(new DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL));
                                }
                            }
                        } else {

                            EmpAdapter adapter = new EmpAdapter(ctx, objList);
                            rcEmp.setAdapter(adapter);
                            rcEmp.setLayoutManager(new LinearLayoutManager(ctx));
                            rcEmp.addItemDecoration(new DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL));
                        }

                    } else {

                        Toast.makeText(ctx, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AllModel> call, Throwable t) {
                    Toast.makeText(EmployeeActivity.this, "Gagal menghubungi server karena " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
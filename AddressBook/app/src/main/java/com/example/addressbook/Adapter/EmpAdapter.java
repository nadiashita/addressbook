package com.example.addressbook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.addressbook.DetailsActivity;
import com.example.addressbook.R;
import com.example.addressbook.EmployeeActivity;
import com.example.addressbook.Model.EmpModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EmpAdapter extends RecyclerView.Adapter<EmpAdapter.HolderData> {
    private Context ctx;
    private List<EmpModel> objList;
    private EmpModel emodel;

    public EmpAdapter(Context ctx, List<EmpModel> listEmp) {
        this.ctx = ctx;
        this.objList = listEmp;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        emodel = objList.get(position);

        Picasso.get().load(emodel.getPicture().getLarge() == null ? ctx.getResources().getString(R.string.Image) : emodel.getPicture().getLarge()).into(holder.img);
        holder.name.setText(emodel.getName().getTitle() + ". " + emodel.getName().getFirst() + " " + emodel.getName().getLast());
        holder.city.setText("City : " + emodel.getLocation().getCity() + ", " + emodel.getLocation().getCountry());
        holder.phone.setText("Phone : " + emodel.getPhone());
        holder.member.setText("Member Since : " + emodel.getRegistered().getDate());
    }

    @Override
    public int getItemCount() {
        return objList.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView name, city, phone, member;

        ImageView img;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            city = itemView.findViewById(R.id.city);
            phone = itemView.findViewById(R.id.phone);
            member = itemView.findViewById(R.id.memberSince);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EmpModel empModel = objList.get(getAdapterPosition());
                    Intent intent = new Intent(ctx, DetailsActivity.class);
                    intent.putExtra("id", empModel.getEmployeeId());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);
                    EmployeeActivity.employeeActivity.finish();

                }
            });
        }
    }
}
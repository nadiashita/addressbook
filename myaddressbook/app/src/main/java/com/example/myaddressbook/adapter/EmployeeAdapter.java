package com.example.myaddressbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaddressbook.DetailActivity;
import com.example.myaddressbook.MainActivity;
import com.example.myaddressbook.R;
import com.example.myaddressbook.model.EmployeeModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.HolderData> {
    private Context ctx;
    private List<EmployeeModel> objList;
    private EmployeeModel emodel;

    public EmployeeAdapter(Context ctx, List<EmployeeModel> listHotel) {
        this.ctx = ctx;
        this.objList = listHotel;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        emodel = objList.get(position);

        Picasso.get().load(emodel.getPicture().getLarge() == null ? ctx.getResources().getString(R.string.emptyImage) : emodel.getPicture().getLarge()).into(holder.img);
        holder.tvName.setText(emodel.getName().getTitle() + ". " + emodel.getName().getFirst() + " " + emodel.getName().getLast());
        holder.tvCity.setText("City : " + emodel.getLocation().getCity() + ", " + emodel.getLocation().getCountry());
        holder.tvPhone.setText("Phone : " + emodel.getPhone());
        holder.tvMember.setText("Member Since : " + emodel.getRegistered().getDate());
    }

    @Override
    public int getItemCount() {
        return objList.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvName, tvCity, tvPhone, tvMember;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgEmployee);
            tvName = itemView.findViewById(R.id.tvName);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvMember = itemView.findViewById(R.id.tvMemberSince);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EmployeeModel employeeModel = objList.get(getAdapterPosition());
                    Intent intent = new Intent(ctx, DetailActivity.class);
                    intent.putExtra("id", employeeModel.getEmployeeId());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);
                    MainActivity.mainActivity.finish();

                }
            });
        }
    }
}
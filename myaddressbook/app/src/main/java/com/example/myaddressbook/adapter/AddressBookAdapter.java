package com.example.myaddressbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaddressbook.R;
import com.example.myaddressbook.model.EmployeeModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AddressBookAdapter extends RecyclerView.Adapter<AddressBookAdapter.HolderData> {
    private Context context;
    private List<EmployeeModel> objList;
    private EmployeeModel em;

    public AddressBookAdapter(Context context, List<EmployeeModel> listHotel) {
        this.context = context;
        this.objList = listHotel;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.addressbook_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        em = objList.get(position);

        Picasso.get().load(em.getPicture().getLarge() == null ? context.getResources().getString(R.string.emptyImage) : em.getPicture().getLarge()).into(holder.img);
        holder.tvName.setText(em.getName().getTitle() + ". " + em.getName().getFirst() + " " + em.getName().getLast());
        holder.tvCity.setText("City: " + em.getLocation().getCity() + ", " + em.getLocation().getCountry());

    }

    @Override
    public int getItemCount() {
        return objList.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvName, tvCity;
        Button btnCall, btnEmail;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgEmployee);
            tvName = itemView.findViewById(R.id.tvName);
            tvCity = itemView.findViewById(R.id.tvCity);
            btnCall = itemView.findViewById(R.id.btnCall);
            btnEmail = itemView.findViewById(R.id.btnEmail);

            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phone = objList.get(getAdapterPosition()).getPhone();
                    Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    context.startActivity(i);
                }
            });
            btnEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", objList.get(getAdapterPosition()).getEmail(), null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello " + objList.get(getAdapterPosition()).getName().getTitle() + ". " + objList.get(getAdapterPosition()).getName().getFirst() + " " + objList.get(getAdapterPosition()).getName().getLast());
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Hai ...");
                    context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
                }
            });
        }
    }
}
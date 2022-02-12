package com.example.addressbook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.addressbook.Model.EmpModel;
import com.example.addressbook.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.HolderData> {
    private Context context;
    private EmpModel em;

    private List<EmpModel> objList;

    public AddressAdapter(Context context, List<EmpModel> listAddress) {
        this.context = context;
        this.objList = listAddress;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        HolderData h = new HolderData(layout);
        return h;

    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {


        em = objList.get(position);

        Picasso.get().load(em.getPicture().getLarge() == null ? context.getResources().getString(R.string.Image) : em.getPicture().getLarge()).into(holder.img);
        holder.name.setText(em.getName().getTitle() + ". " + em.getName().getFirst() + " " + em.getName().getLast());
        holder.city.setText("City: " + em.getLocation().getCity() + ", " + em.getLocation().getCountry());

    }

    @Override
    public int getItemCount() {
        return objList.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, city;
        Button btnCall, btnEmail;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            city = itemView.findViewById(R.id.city);
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
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", objList.get(getAdapterPosition()).getEmail(), null));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Hi " + objList.get(getAdapterPosition()).getName().getTitle() + ". " + objList.get(getAdapterPosition()).getName().getFirst() + " " + objList.get(getAdapterPosition()).getName().getLast());
                    intent.putExtra(Intent.EXTRA_TEXT, "");
                    context.startActivity(Intent.createChooser(intent, "Send"));
                }
            });
        }
    }
}
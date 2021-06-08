package com.manish.assignment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;
import com.manish.assignment.db.Appdatabase;
import com.manish.assignment.db.User;
import com.manish.assignment.pojo.MyListData;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LinkedList<MyListData> listdata;
    private Context context;

    public Adapter(LinkedList<MyListData> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        final MyListData myListData = listdata.get(position);
        Appdatabase appdatabase = Appdatabase.getDbInstance(context);
        List<User> userList = appdatabase.userDao().getAllUsers();
        Uri uri = Uri.parse(listdata.get(position).getFlag());
        holder.name.setText(listdata.get(position).getDescription());
        GlideToVectorYou
                .init()
                .with(context)
                .withListener(new GlideToVectorYouListener() {
                    @Override
                    public void onLoadFailed() {
                    }

                    @Override
                    public void onResourceReady() {
                    }
                })
                .load(uri, holder.flag);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+ myListData.getDescription(), Toast.LENGTH_LONG).show();
                System.out.println(userList.get(position).name);
                System.out.println(userList.get(position).capital);
                System.out.println(userList.get(position).region);
                System.out.println(userList.get(position).subregion);
                System.out.println(userList.get(position).languages);
                System.out.println(userList.get(position).flag);
                System.out.println(userList.get(position).borders);
                System.out.println(userList.get(position).population);

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("name", userList.get(position).name);
                intent.putExtra("capital", userList.get(position).capital);
                intent.putExtra("region", userList.get(position).region);
                intent.putExtra("subregion", userList.get(position).subregion);
                intent.putExtra("languages", userList.get(position).languages);
                intent.putExtra("flag", userList.get(position).flag);
                intent.putExtra("borders", userList.get(position).borders);
                intent.putExtra("population", userList.get(position).population);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView flag;
        public TextView name;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.flag = itemView.findViewById(R.id.flag);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}

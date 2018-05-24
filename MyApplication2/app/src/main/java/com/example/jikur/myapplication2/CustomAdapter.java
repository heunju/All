package com.example.jikur.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

/////


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {

    private Uri fileUri; //에러 방지

    Context context;
    ArrayList<FamilyCallItem> data = new ArrayList<FamilyCallItem>();


    public CustomAdapter(Context context, ArrayList<FamilyCallItem> data) {
        this.context = context;
        this.data = data;
    }

    //
    @Override
    public CustomAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.familycallitem, null);
        return new Holder(view);
    }

    //데이터를 넣어주는
    @Override
    public void onBindViewHolder(CustomAdapter.Holder holder, final int position) {

        holder.imageView.setImageResource(data.get(position).getResImg());

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FamilyCallAddActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    //public class Holder extends RecyclerView.ViewHolder implements  View.OnCreateContextMenuListener{
    public class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ImageButton imageButton;

        public Holder(View View) {
            super(View);

            imageButton = View.findViewById(R.id.imgBtn);
            imageView = View.findViewById(R.id.imgView);

        }

    }
}


        /*
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

            contextMenu.setHeaderTitle("메뉴");
            contextMenu.add(0, 1, 100, "사진앨범");
            contextMenu.add(0, 2, 200, "사진촬영");

            }

            */


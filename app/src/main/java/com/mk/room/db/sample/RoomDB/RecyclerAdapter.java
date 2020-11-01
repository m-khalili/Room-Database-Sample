package com.mk.room.db.sample.RoomDB;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mk.room.db.sample.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context mContext;
    List<MainData> mainDataList;
    RoomDB database;

    public RecyclerAdapter(Context mContext, List<MainData> mainDataList) {
        this.mContext = mContext;
        this.mainDataList = mainDataList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_item , parent , false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        MainData data = mainDataList.get(position);
        database = RoomDB.getInstance(mContext);
        holder.txt.setText(data.getText());

        holder.delete.setOnClickListener(V->{
            MainData data1 = mainDataList.get(holder.getAdapterPosition());
            database.mainDoa().delete(data1);
            mainDataList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,mainDataList.size());
        });

        holder.edit.setOnClickListener(V->{

            MainData d = mainDataList.get(holder.getAdapterPosition());
            int ID = d.getID();
            String sText = d.getText();

            Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.dialog_edit);
            int width = WindowManager.LayoutParams.MATCH_PARENT;
            int height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width,height);
            dialog.show();
            EditText editText = dialog.findViewById(R.id.edit_text);
            Button update = dialog.findViewById(R.id.update);
            editText.setText(sText);

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    String uText = editText.getText().toString();
                    database.mainDoa().update(ID , uText);
                    mainDataList.clear();
                    mainDataList.addAll(database.mainDoa().getAll());
                    notifyDataSetChanged();
                }
            });
        });


    }

    private void updateItem() {

    }

    @Override
    public int getItemCount() {
        return mainDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        ImageButton edit,delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
        }
    }
}

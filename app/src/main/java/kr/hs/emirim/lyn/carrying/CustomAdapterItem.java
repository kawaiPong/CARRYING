package kr.hs.emirim.lyn.carrying;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterItem extends RecyclerView.Adapter<CustomAdapterItem.CustomViewHolder> {
    private ArrayList<checkList_item> mList;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected CheckBox checkBox;

        public CustomViewHolder(View view) {
            super(view);
            this.checkBox=(CheckBox)view.findViewById(R.id.checkBox);
        }


    }


    public CustomAdapterItem(ArrayList<checkList_item> list) {
        this.mList = list;
    }



    @Override
    public CustomAdapterItem.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_check_list, viewGroup, false);

        CustomAdapterItem.CustomViewHolder viewHolder = new CustomAdapterItem.CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.checkBox.setText(mList.get(position).getName());
        holder.checkBox.setChecked(true);
    }



    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}

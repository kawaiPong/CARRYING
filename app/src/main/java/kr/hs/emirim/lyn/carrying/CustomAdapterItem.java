package kr.hs.emirim.lyn.carrying;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.hs.emirim.lyn.carrying.Retrofit.checkListItem;

//public class CustomAdapterItem extends RecyclerView.Adapter<CustomAdapterItem.CustomViewHolder> {
//    private ArrayList<checkListItem> mList;
//
//    public class CustomViewHolder extends RecyclerView.ViewHolder {
//        protected CheckBox checkBox;
//
//        public CustomViewHolder(View view) {
//            super(view);
//            this.checkBox=(CheckBox)view.findViewById(R.id.checkBox);
//        }
//
//
//    }
//
//
//    public CustomAdapterItem(ArrayList<checkListItem> list) {
//        this.mList = list;
//    }
//
//
//
//    @Override
//    public CustomAdapterItem.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//
//        View view = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.activity_check_list, viewGroup, false);
//
//        CustomAdapterItem.CustomViewHolder viewHolder = new CustomAdapterItem.CustomViewHolder(view);
//
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
//        Log.d("mytag","들어왓나?");
//        holder.checkBox.setText(mList.get(position).getName());
//        holder.checkBox.setChecked(true);
//    }
//
//
//
//    @Override
//    public int getItemCount() {
//        return (null != mList ? mList.size() : 0);
//    }
//}




public class CustomAdapterItem extends RecyclerView.Adapter<CustomAdapterItem.CustomViewHolder> {

    private List<checkListItem> mList;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected CheckBox checkBox;


        public CustomViewHolder(View view) {
            super(view);
            this.checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        }
    }


    public CustomAdapterItem(List<checkListItem> list) {
        this.mList = list;
    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_item, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {
        viewholder.checkBox.setText(mList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}


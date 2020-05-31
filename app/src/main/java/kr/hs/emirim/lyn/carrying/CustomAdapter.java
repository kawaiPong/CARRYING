package kr.hs.emirim.lyn.carrying;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Dictionary> mList;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView start_date;
        protected TextView finish_date;


        public CustomViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title_item);
            this.start_date = (TextView) view.findViewById(R.id.start_date);
            this.finish_date = (TextView) view.findViewById(R.id.finish_date);
        }


    }


    public CustomAdapter(ArrayList<Dictionary> list) {
        this.mList = list;
    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.start_date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.finish_date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        viewholder.title.setGravity(Gravity.CENTER);
        viewholder.start_date.setGravity(Gravity.CENTER);
        viewholder.finish_date.setGravity(Gravity.CENTER);



        viewholder.title.setText(mList.get(position).getTitle());
        viewholder.start_date.setText(mList.get(position).getStart_date());
        viewholder.finish_date.setText(mList.get(position).getFinish_date());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
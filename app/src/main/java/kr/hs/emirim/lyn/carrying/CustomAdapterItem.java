package kr.hs.emirim.lyn.carrying;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.hs.emirim.lyn.carrying.Retrofit.CheckList;
import kr.hs.emirim.lyn.carrying.Retrofit.RetrofitExService;
import kr.hs.emirim.lyn.carrying.Retrofit.checkListItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    private CustomAdapter.OnItemClickListener mListener = null ;

    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }


    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(CustomAdapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }




    private List<checkListItem> mList;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected CheckBox checkBox;

        public final View mView;


        public CustomViewHolder(View view) {

            super(view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Log.d("mytag position","클릭,"+position);
                    if(position != RecyclerView.NO_POSITION){
                        if(mListener !=null){
                            mListener.onItemClick(v,position);
                        }
                    }

                }
            });

            mView = itemView;

            // 레이아웃 객체화 findViewById
            this.checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        }
    }






    public CustomAdapterItem(List<checkListItem> list) {
        Log.d("mytag CAI","들어오는지 ");
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
        if(mList.get(position).getStatus()==1)viewholder.checkBox.setChecked(true);
        Log.d("mytag CAI","들어오는지 "+mList.get(position));

        checkListItem item = mList.get(position);

        viewholder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mytag CAI","온쿨뤽 "+mList.get(position));
                Context context = v.getContext();
                Log.d("mytag",position +"");
                Toast.makeText(context, position +"", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}


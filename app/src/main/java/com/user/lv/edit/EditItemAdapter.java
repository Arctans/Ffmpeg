package com.user.lv.edit;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.user.lv.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EditItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = EditItemAdapter.class.getSimpleName();
    private List<EditItem> mEditItemList;

    public EditItemAdapter(List<EditItem> mList){
        Log.d(TAG, "EditItemAdapter: ");
        mEditItemList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder item;
        Log.d(TAG, "onBindViewHolder: ");
        if(holder instanceof  ItemViewHolder){
            item = (ItemViewHolder)holder;
            item.bind(mEditItemList.get(position),position);
        }
    }

    @Override
    public int getItemCount() {
        int size = mEditItemList.size();
        Log.d(TAG, "getItemCount: "+size);
        return size;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        final TextView mTvItem;
            final ImageView mIvItem;
        final View mDvRight;
        private final View mRvRoot;

        public ItemViewHolder(@NonNull View itemView) {

            super(itemView);
            Log.d(TAG, "ItemViewHolder: ");
            mTvItem = itemView.findViewById(R.id.tv_item);
            mIvItem = itemView.findViewById(R.id.iv_item);
            mDvRight = itemView.findViewById(R.id.dv_right);
            mRvRoot = itemView.findViewById(R.id.rl_item);
            ViewGroup.LayoutParams layoutParams =  mRvRoot.getLayoutParams();
            layoutParams.width = 1024/3;
            layoutParams.height =1024/3;
            mRvRoot.setLayoutParams(layoutParams);

            ViewGroup.LayoutParams layoutParams2 =  mIvItem.getLayoutParams();
            layoutParams.width = 1024/10;
            layoutParams.height =1024/10;
            mIvItem.setLayoutParams(layoutParams2);


        }
        public void bind(EditItem item,int position){
            Log.d(TAG, "bind: ");
            mIvItem.setImageResource(item.imgRes);
            mTvItem.setText(item.text);
            mRvRoot.setTag(item);
            if(position%3 == 2){
                mDvRight.setVisibility(View.GONE);
            }else{
                mDvRight.setVisibility(View.VISIBLE);
            }
        }
    }
}

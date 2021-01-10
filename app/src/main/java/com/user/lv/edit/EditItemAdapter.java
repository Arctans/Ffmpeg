package com.user.lv.edit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.user.lv.R;
import com.user.lv.common.Click;
import com.user.lv.utils.DeviceUtils;
import com.user.lv.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EditItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements  View.OnClickListener{
    private static final String TAG = EditItemAdapter.class.getSimpleName();
    private List<EditItem> mEditItemList;
    private Click.OnObjectClickListener<EditItem> OnObjectClickListener;
    public EditItemAdapter(List<EditItem> mList){
        mEditItemList = mList;
    }

    public void setEditItemOnClickListener(Click.OnObjectClickListener<EditItem> onObjectClickListener){
        this.OnObjectClickListener = onObjectClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit,parent,false);
        view.setOnClickListener(this);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder item;
        Log.d(TAG, "onBindViewHolder: position "+position);
        if(holder instanceof  ItemViewHolder){
            item = (ItemViewHolder)holder;
            item.bind(mEditItemList.get(position),position);
        }
    }

    @Override
    public int getItemCount() {
        int size = mEditItemList.size();
        return size;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: id "+v.getId()+" Tag "+v.getTag());
        if(v.getTag() instanceof EditItem){
            OnObjectClickListener.onObjectClick((EditItem) v.getTag());
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        final TextView mTvItem;
        final ImageView mIvItem;
        final View mDvRight;
        private final View mRvRoot;

        public ItemViewHolder(@NonNull View itemView) {

            super(itemView);
            Log.d(TAG, "ItemViewHolder: ");
            mTvItem  = itemView.findViewById(R.id.tv_item);
            mIvItem  = itemView.findViewById(R.id.iv_item);
            mDvRight = itemView.findViewById(R.id.dv_right);
            mRvRoot  = itemView.findViewById(R.id.rl_item);
            int item_size  = DeviceUtils.getDisplayWidth(mRvRoot.getContext())/8;
            int image_size =item_size-DeviceUtils.dip2px(mRvRoot.getContext(),32);
            ViewUtils.setViewSize(mRvRoot,item_size,item_size);
            ViewUtils.setViewSize(mIvItem,image_size,image_size);

        }
        public void bind(EditItem item,int position){
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

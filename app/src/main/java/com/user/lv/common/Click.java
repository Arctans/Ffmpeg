package com.user.lv.common;

public class Click  {

    /**
     * 监听对象可能是任意一个抽象的实体
     */
    public interface OnObjectClickListener<T> {
        void onObjectClick(T t);
    }

}

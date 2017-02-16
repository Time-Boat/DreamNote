package com.dreamnote.widget.gradation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ListView;

public class NoScrollListview extends RecyclerView {
  
    public NoScrollListview(Context context) {
        // TODO Auto-generated method stub  
        super(context);  
    }  
  
    public NoScrollListview(Context context, AttributeSet attrs) {
        // TODO Auto-generated method stub  
        super(context, attrs);  
    }  
  
    public NoScrollListview(Context context, AttributeSet attrs, int defStyle) {
        // TODO Auto-generated method stub  
        super(context, attrs, defStyle);  
    }  
  
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        // TODO Auto-generated method stub  
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);  
    }



}  
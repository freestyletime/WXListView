package cn.christian.wxlistview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by IntelliJ IDEA in WXListView
 * cn.christian.wxlistview.view
 *
 * @Author: ChristisnChen
 * @Time: 2017/6/29 14:15
 * @Description:
 */
public class WXListView extends ListView {


    public void setOnResizeListener(OnResizeListener listener) {
        this.listener = listener;
    }

    private OnResizeListener listener;

    public WXListView(Context context) {
        super(context);
    }

    public WXListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WXListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface OnResizeListener {
        void OnResize(int w, int h, int oldw, int oldh);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        listener.OnResize(w, h, oldw, oldh);
    }

}

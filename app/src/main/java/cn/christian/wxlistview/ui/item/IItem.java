package cn.christian.wxlistview.ui.item;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by IntelliJ IDEA in WXListView
 * cn.christian.wxlistview.ui.item
 *
 * @Author: ChristisnChen
 * @Time: 2017/6/29 14:20
 * @Description:
 */
public interface IItem {
    View getView(Context context, int position, View view, ViewGroup viewGroup);
}

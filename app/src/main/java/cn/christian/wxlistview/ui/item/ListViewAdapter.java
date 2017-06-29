package cn.christian.wxlistview.ui.item;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA in WXListView
 * cn.christian.wxlistview.ui.item
 *
 * @Author: ChristisnChen
 * @Time: 2017/6/29 14:40
 * @Description:
 */
public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<IItem> datas = new ArrayList<>();

    public ListViewAdapter(Context context, List<IItem> datas){
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public IItem getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return datas.get(i).getView(context, i, view, viewGroup);
    }
}

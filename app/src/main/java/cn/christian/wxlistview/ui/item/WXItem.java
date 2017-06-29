package cn.christian.wxlistview.ui.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.christian.wxlistview.R;
import cn.christian.wxlistview.bean.DataBean;
import cn.christian.wxlistview.ui.MainActivity;

/**
 * Created by IntelliJ IDEA in WXListView
 * cn.christian.wxlistview.ui.item
 *
 * @Author: ChristisnChen
 * @Time: 2017/6/29 14:50
 * @Description:
 */
public class WXItem implements IItem {

    private Context context;
    private DataBean bean;
    private int position;

    @Bind(R.id.tv_name)
    TextView name;

    @OnClick(R.id.tv_reply)
    void reply(){
        if(context instanceof MainActivity){
            MainActivity activity = (MainActivity) context;
            activity.setBottomOffset(position);
        }
    }

    public WXItem(DataBean bean){
        this.bean = bean;
    }

    @Override
    public View getView(Context context, int position, View view, ViewGroup viewGroup) {
        this.position = position;
        this.context = context;

        view = LayoutInflater.from(context).inflate(R.layout.item, null);
        ButterKnife.bind(this, view);
        name.setText(bean.name);

        return view;
    }
}

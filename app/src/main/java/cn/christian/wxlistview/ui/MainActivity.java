package cn.christian.wxlistview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnTouch;
import cn.christian.wxlistview.R;
import cn.christian.wxlistview.bean.DataBean;
import cn.christian.wxlistview.ui.item.IItem;
import cn.christian.wxlistview.ui.item.ListViewAdapter;
import cn.christian.wxlistview.ui.item.WXItem;
import cn.christian.wxlistview.util.InputMethodUtils;
import cn.christian.wxlistview.view.WXListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by IntelliJ IDEA in WXListView
 * cn.christian.wxlistview.ui
 *
 * @Author: ChristisnChen
 * @Time: 2017/6/29 14:19
 * @Description:
 */
public class MainActivity extends AppCompatActivity implements WXListView.OnResizeListener {

    private ListViewAdapter adapter;
    private List<IItem> datas = new ArrayList<>();

    private int position;
    private int oldPosition = 0;
    private int bottomOffset = 0;
    private AtomicBoolean isShowComment = new AtomicBoolean(false);

    private void putOffset(int offset) {
        list.smoothScrollBy(offset, 1000);
        oldPosition = position;
    }

    private boolean deal(int position) {
        if (isShowComment.get()) {
            if (oldPosition != position) {
                int offset = -(list.getHeight() - bottomOffset - inputLayout.getHeight());
                putOffset(offset);
            }
            return true;
        }
        return false;
    }

    private void hiddenInputLayout() {
        comment.setText("");
        isShowComment.set(false);
        inputLayout.setVisibility(View.GONE);
        InputMethodUtils.hiddenSoftKeyboard(this);
    }

    private void showInputLyaout() {
        isShowComment.set(true);
        inputLayout.setVisibility(View.VISIBLE);
        InputMethodUtils.showSoftKeyboard(comment);
    }

    @Bind(R.id.lv_wxlist)
    WXListView list;
    @Bind(R.id.input_layer)
    View inputLayout;
    @Bind(R.id.et_comment)
    EditText comment;

    @OnTouch(R.id.lv_wxlist)
    boolean onTouch(View v, MotionEvent event) {
        if (MotionEvent.ACTION_MOVE == event.getAction()) {
            hiddenInputLayout();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new ListViewAdapter(this, datas);

        View footer = LayoutInflater.from(this).inflate(R.layout.footer, null, false);
        list.addFooterView(footer);
        list.setOnResizeListener(this);

        for (int i = 1; i <= 100; i++) {
            datas.add(new WXItem(new DataBean("ChristianChen - index - " + i)));
        }

        list.setAdapter(adapter);
    }

    @Override
    public void OnResize(int w, int h, int oldw, int oldh) {
        if (oldh > h) {
            int offset = (oldh - h + inputLayout.getHeight()) - (oldh - bottomOffset);
            putOffset(offset);
        }
    }

    public void setBottomOffset(int position) {
        bottomOffset = list.getChildAt(position - list.getFirstVisiblePosition()).getBottom();
        if(!deal(position)) showInputLyaout();
    }
}

package lanou.test0810.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ZhangRui on 16/10/21.
 * Fragment 的基类
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater.inflate(getLayout(),container,false);
        // 如果Fragment没有制定布局
        // 默认 就加在一个空布局,防止程序崩溃
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }
    protected <T extends View> T bindView(int id) {
        return (T) getView().findViewById(id);
    }
    // 指定在那个View里findViewById
    protected <T extends View> T bindView(View view ,int id) {
        return (T) getView().findViewById(id);
    }

    protected abstract void initData();
    protected abstract void initView();
    protected abstract int getLayout();
}

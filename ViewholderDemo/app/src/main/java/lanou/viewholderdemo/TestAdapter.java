package lanou.viewholderdemo;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by ZhangRui on 16/10/31.
 */
public class TestAdapter extends RecyclerView.Adapter<CommonVH> {

    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent,R.layout.item_main);
    }

    @Override
    public void onBindViewHolder(CommonVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

package lanou.rcycler;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ZhangRui on 16/10/31.
 */
public abstract class EndLess extends RecyclerView.OnScrollListener {
    private LinearLayoutManager linearLayoutManager;
    private int curentPage = 0;
    // 已经加载的item数量
    private int totalItemCount;
    // 主要用来存储上一个totalLitem
    private int previousTotal = 0;
    // 在屏幕上可见的item数量
    private int visibleItemCount;
    // 在屏幕上可见的Item中的第一个
    private int firstVisbleItem;
    // 是否上啦数据
    private boolean loading = true;

    public EndLess(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisbleItem = linearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                //说明加载结束
                loading = false;
                previousTotal = totalItemCount;

            }
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisbleItem) {
            curentPage++;
            onLoadMores(curentPage);
            loading = true;
        }
    }

    protected abstract void onLoadMores(int curentPage);

}
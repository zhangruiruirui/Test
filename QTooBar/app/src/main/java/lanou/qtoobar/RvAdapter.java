package lanou.qtoobar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ZhangRui on 16/11/23.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.Adapter> {
    private ArrayList<RvAdapter> mRvAdapters;

    public RvAdapter(ArrayList<RvAdapter> rvAdapters) {
        mRvAdapters = rvAdapters;
    }
    private Context mContext;

    public RvAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Adapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);

        return null;
    }

    @Override
    public void onBindViewHolder(Adapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class
    Adapter extends RecyclerView.ViewHolder {
        public Adapter(View itemView) {
            super(itemView);
        }
    }
}

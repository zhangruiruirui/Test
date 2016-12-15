package lanou.a;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ZhangRui on 16/11/25.
 */
public class MyAdapter extends RecyclerView.Adapter {
//    private String aBC [] = {"a","b","c","d","e","f","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private List<String> mList;
    public MyAdapter(){
        for (int i = 0;i <26;i++) {
            mList.add(String.valueOf((char)(i+'A')));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent,R.layout.item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommonViewHolder viewHolder = (CommonViewHolder) holder;
        viewHolder.setText(R.id.item_tv,mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

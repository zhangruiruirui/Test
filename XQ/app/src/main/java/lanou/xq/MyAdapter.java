package lanou.xq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by ZhangRui on 16/11/24.
 */
public class MyAdapter extends BaseAdapter {

    String a[] ={"a","b","c","d","e"};

    int icon[] ={R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};


    @Override
    public int getCount() {
        return a.length;
    }

    @Override
    public Object getItem(int position) {
        return a[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(parent,R.layout.item);
        commonViewHolder.setText(R.id.tv,a[position]);
        commonViewHolder.setImage(R.id.item_iv,icon[position]);
        return commonViewHolder.getItemView();
    }
}

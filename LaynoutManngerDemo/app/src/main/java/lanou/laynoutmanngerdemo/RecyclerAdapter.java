package lanou.laynoutmanngerdemo;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ZhangRui on 16/11/9.
 */
public class RecyclerAdapter extends RecyclerView.Adapter{
    private List<Bean> beanList;
    private static final int TYPE_TEXT = 0;
    private static final int TYPE_IMG = 1;

    public void setBeanList(List<Bean> beanList) {
        this.beanList = beanList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return beanList.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if (viewType == TYPE_TEXT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            return new TextViewHolder(view);
        }else  {
            View view = LayoutInflater.from(context).inflate(R.layout.item_img,parent,false);
            return new ImgViewHolder(view);

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
       RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        GridLayoutManager manager1 =

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_TEXT) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            textViewHolder.textView.setText(beanList.get(i).getImgId());
        }

    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }
    class  TextViewHolder {
        private TextView textView;

        public TextViewHolder(View view) {
            this.textView = (TextView) textView.findViewById(R.id.main_tv);
        }
    }
    class  ImgViewHolder{
        private ImageView imageView;

        public ImgViewHolder(View view) {
            this.imageView = (ImageView) imageView.findViewById(R.id.main_iv);
        }
    }
}

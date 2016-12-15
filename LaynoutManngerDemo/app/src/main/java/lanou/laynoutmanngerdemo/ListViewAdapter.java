package lanou.laynoutmanngerdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ZhangRui on 16/11/9.
 */
public class ListViewAdapter extends BaseAdapter {
    private List<Bean> beanList;
    private Context context;
    private static final int TYPE_TEXT = 0;
    private static final int TYPE_IMG = 1;

    public ListViewAdapter(Context context) {
        this.context = context;
    }

    public void setBeanList(List<Bean> beanList) {
        this.beanList = beanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
          return beanList == null ? 0 : beanList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // 告诉Adapter 一共几种航布局
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return beanList.get(position).getType();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextViewHolder textViewHolder = null;
        ImgViewHolder imgViewHolder = null;
        if (view == null) {
            Context context = viewGroup.getContext();
            if (beanList.get(i).getType() == TYPE_TEXT) {
                view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
                textViewHolder = new TextViewHolder(view);
                view.setTag(textViewHolder);
            }else {
                view = LayoutInflater.from(context).inflate(R.layout.item_img,viewGroup,false);
                imgViewHolder = new ImgViewHolder(view);
                view.setTag(imgViewHolder);
            }
        }else {
            if (beanList.get(i).getType() == TYPE_TEXT) {
                textViewHolder = (TextViewHolder) view.getTag();
            }else  {
                imgViewHolder = (ImgViewHolder) view.getTag();
            }
        }
        // 设置值
        if (beanList.get(i).getType() == TYPE_TEXT) {
            textViewHolder.textView.setText(beanList.get(i).getText());
        }else  {
            imgViewHolder.imageView.setImageResource(beanList.get(i).getImgId());
        }
        return view;
    }
    class  TextViewHolder {
        private TextView  textView;

        public TextViewHolder(View textView) {
            this.textView = (TextView) textView.findViewById(R.id.main_tv);
        }
    }
    class  ImgViewHolder{
        private ImageView imageView;

        public ImgViewHolder(View imageView) {
            this.imageView = (ImageView) imageView.findViewById(R.id.main_iv);
        }
    }

}

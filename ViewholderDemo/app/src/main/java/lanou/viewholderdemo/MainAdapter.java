package lanou.viewholderdemo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by ZhangRui on 16/10/31.
 */
public class MainAdapter extends BaseAdapter{



    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CommonVH viewHolder = CommonVH.getViewHolder(convertView,parent,R.layout.item_main);
//        CommonVH viewHolder = null;
//        if (convertView == null){
//            Context context = parent.getContext();
//            View view = LayoutInflater.from(context).inflate(R.layout.item_main,parent,false);
//            viewHolder = new CommonVH(convertView);
//            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (CommonVH) convertView.getTag();
//        }
        //设置数据

//        TextView itemTv = (TextView) viewHolder.getView(R.id.item_tv);
//        itemTv.setText("测试");
        // 链式编程
        viewHolder.setText(R.id.item_main,"测试").
                setImage(R.id.main_Iv,R.mipmap.ic_launcher).
                setViewClick(R.id.item_main, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return viewHolder.getItemView();

    }
    //返回行布局的方法



//   class ViewHolder {
//
//        private  TextView itemTv;
//
//        public ViewHolder(View itemView){
//            itemTv = (TextView) itemView.findViewById(R.id.item_tv);
//        }
//    }
}

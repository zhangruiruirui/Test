package lanou.recyc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ZhangRui on 16/11/3.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHloder> {
    Context context;
    ArrayList<String> arrayList;
    OnltemTouchOnClickLsistenner listene;

    public void setListene(OnltemTouchOnClickLsistenner listene) {
        this.listene = listene;
        notifyDataSetChanged();
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public MyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHloder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        ViewHloder viewHloder = new ViewHloder(view);
        return viewHloder;
    }
    public void move(int from , int to) {
        arrayList.add(to,arrayList.remove(from));
        notifyItemMoved(from,to);
    }

    @Override
    public void onBindViewHolder(final ViewHloder holder, final int position) {

        holder.tv.setText(arrayList.get(position).toString());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listene.OnClick(position,holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ViewHloder extends RecyclerView.ViewHolder {

        private final TextView tv;
        private final LinearLayout ll;

        public ViewHloder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
        }
    }
}

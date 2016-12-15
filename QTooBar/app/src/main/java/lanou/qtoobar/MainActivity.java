package lanou.qtoobar;

import android.media.midi.MidiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRv = (RecyclerView) findViewById(R.id.rv);


        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arrayList.add("lala" + i);
        }
        RvAdapter rvAdapter = new RvAdapter(this);
        mRv.setAdapter(rvAdapter);

        LinearLayoutManager mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(mManager);
        mRv.addOnScrollListener(new OnRv() {
            @Override
            public void hide() {

            }

            @Override
            public void show() {

            }
        });
    }

    private abstract class OnRv extends RecyclerView.OnScrollListener {
        private static final int SCROLL_DISTANCE = 50;//华东的距离
        private int totaScrollDistance;
        private boolean isShow = true;

        /**
         * @param recyclerView
         * @param dx           横向滑动
         * @param dy           纵向 滑动
         */
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int firstVist = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            if (firstVist == 0) {
                return;
            }
            if ((dy > 0 && isShow) || (dy < 0 && !isShow)) {
                totaScrollDistance += dy;
            }
            if (totaScrollDistance > SCROLL_DISTANCE && isShow) {
                hide();
                isShow = false;
                totaScrollDistance = 0;
            } else if (totaScrollDistance < -SCROLL_DISTANCE && !isShow) {
                show();
                isShow = false;
                totaScrollDistance = 0;
            }

        }

        public abstract void hide();

        public abstract void show();
    }

}

package lanou.bmobdemo;

import cn.bmob.v3.BmobUser;

/**
 * Created by ZhangRui on 16/11/4.
 */
public class MyUser extends BmobUser {
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

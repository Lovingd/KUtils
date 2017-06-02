package cn.kutils.security;

/**
 * Created by Administrator on 2016/5/19.
 */
public interface onGetMac {
    void onSucc(String mac);

    void onError(String errorMsg);
}

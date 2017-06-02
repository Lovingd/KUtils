package cn.kutils.permissionchecker;

import java.util.ArrayList;

/**
 * Created by TedPark on 16. 2. 17..
 */
public interface PermissionListener {

     void onPermissionGranted();

     void onPermissionDenied(ArrayList<String> deniedPermissions);

}

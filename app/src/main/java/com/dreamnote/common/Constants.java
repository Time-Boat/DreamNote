package com.dreamnote.common;


/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class Constants {
    private final String TAG = this.getClass().getSimpleName();
    /**
     * 不允许new
     */
    private Constants() {
        throw new Error("Do not need instantiate!");
    }
    //SD卡路径
//    public static final String PATH_DATA = DirectoryUtils.getDiskCacheDirectory(BaseApplication.mContext, "data").getAbsolutePath();
//    public static final String PATH_NET_CACHE = PATH_DATA + File.separator + "NetCache";
//    public static final String PATH_APK_CACHE = PATH_DATA + File.separator + "ApkCache";
    //基地址
    public static final String BASE_URL = "";
    //网络链接超时时间
    public static final int DEFAULT_TIMEOUT = 5;

    public static final String FROM_TO = "from_to";

    public static final String ISFIRSTENTRY = "is_first_entry";

    public static final int FROM_SPLASH = 0;
    public static final int FROM_MAIN = 1;

    //EventBus系列,值是随便取，只要不相同即可。**********************************************************************
    public static final int EVENT_FINISH_LAUNCH = 0;

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public static final String USER_INFO_BEAN = "user_info_bean";

    //盐值？
    public static final String ENCRYPT_KEY = "leguang";

    //分页查询
    public static final int PAGE_SIZE = 30;

}

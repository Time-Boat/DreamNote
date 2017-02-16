package cn.itsite.abase.common;


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
    public static final String BASE_URL = "https://www.baidu.com/";
    //网络链接超时时间
    public static final int DEFAULT_TIMEOUT = 5;

    public static final String FROM_TO = "from_to";

    public static final String ISFIRSTENTRY = "is_first_entry";

    public static final int FROM_SPLASH = 0;
    public static final int FROM_MAIN = 1;

    public static final String DOMAIN_1 = "drean";
    public static final String DOMAIN_2 = "d-dream";
}

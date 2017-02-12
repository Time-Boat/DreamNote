package com.dreamnote.common;

import com.dreamnote.bean.CheckUpdateBean;
import com.dreamnote.bean.UserInfoBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public interface ApiService {

    //登录验证
    @GET("app.do?appLogin")
    Call<UserInfoBean> login(@Query("account") String account, @Query("userPwd") String userPwd, @Query("OSType") String OSType, @Query("regCode") String regCode);

    //更新检测
    @GET("s?cl=3&tn=baidutop10&fr=top1000&wd=%E5%A4%A7%E5%B8%88%E7%8E%8B%E6%9E%97%E5%9B%A0%E7%97%85%E6%AD%BB%E4%BA%A1&rsv_idx=2")
    Call<CheckUpdateBean> checkUpdate();

//    //注册
//    @GET("app.do?appRegister")
//    Observable<RegisterBean> register(@Query("machineCode") String machineCode, @Query("phoneBrand") String phoneBrand, @Query("phoneSysVersion") String phoneSysVersion, @Query("phoneModel") String phoneModel);
//
//    //下载工点信息
//    @GET("app.do?gdxxsDownload")
//    Observable<GongdianInfoBean> gongdianInfoDownload(@Query("userId") String userId);
//
//    //下载工点信息
//    @GET("app.do?dmxxsDownload")
//    Observable<DuanmianInfoBean> duanmianInfoDownload(@Query("gongDianId") String gongDianId);
//
//    //下载测点信息
//    @GET("app.do?ljcdsDownload")
//    Observable<CedianInfoBean> cedianInfoDownload(@Query("duanMianId") String duanMianId);
//
//    //下载预设水准线信息
//    @GET("app.do?ysszxsDownload")
//    Observable<YusheshuizhunxianInfoBean> yusheshuizhunxianInfoDownload(@Query("departId") String departId);
//
//    //下载基点信息
//    @GET("app.do?gzjdsDownload")
//    Observable<JidianBean> jidianDownload();
//
//    //下载人员信息
//    @GET("app.do?obsDownload")
//    Observable<StaffBean> staffDownload();
//
//    //上传水准线
//    @POST("app.do?originalDataUpload")
//    Call<ResponseBody> upload(@Query("original") String shuizhunxianBeen);
}

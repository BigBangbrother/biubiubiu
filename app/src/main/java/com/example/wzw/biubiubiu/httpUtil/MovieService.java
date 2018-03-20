package com.example.wzw.biubiubiu.httpUtil;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @项目名称 :biubiubiu
 * @类描述 :
 * @创建人 : wzw
 * @创建时间 : 2018/3/20 21:46
 * @修改人：wzw
 * @修改时间：2018/3/20 21:46
 * @修改备注：
 */

public interface MovieService {
        //获取豆瓣Top250 榜单
        @GET("top250")
        Observable<MovieSubject> getTop250(@Query("start") int start, @Query("count")int count);

        @FormUrlEncoded
        @POST("/x3/weather")
        Call<String> getWeather(@Field("cityId") String cityId, @Field("key") String key);

}

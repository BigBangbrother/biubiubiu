package com.example.wzw.biubiubiu.recycle.movie;

import com.example.wzw.biubiubiu.HttpUtil.ObjectLoader;
import com.example.wzw.biubiubiu.HttpUtil.RetrofitServiceManager;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by wzw on 2018/3/21.
 */

public class MovieLoader extends ObjectLoader {
        private MovieService mMovieService;

        public MovieLoader(){
            mMovieService = RetrofitServiceManager.getInstance().create(MovieService.class);
        }


    public Observable<List<Movie.ResultsBean>> getGirl(String type, int numb, int page){
        return observe(mMovieService.getGirl(type,numb,page))
                .map(new Func1<Movie, List<Movie.ResultsBean>>() {
                    @Override
                    public List<Movie.ResultsBean> call(Movie movie) {

                        return movie.getResults();
                    }
                });
    }


    public interface MovieService{
        @GET("data/{category}/{pagecount}/{page}")
        Observable<Movie> getGirl( @Path("category") String category,
                                          @Path("pagecount") int countPerPage,
                                          @Path("page") int page);

    }
}

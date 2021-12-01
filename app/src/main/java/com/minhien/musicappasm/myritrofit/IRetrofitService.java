package com.minhien.musicappasm.myritrofit;

import com.minhien.musicappasm.model.Response2PikModel;
import com.minhien.musicappasm.model.SongCategory;
import com.minhien.musicappasm.model.ResponseModel;
import com.minhien.musicappasm.model.Song;
import com.minhien.musicappasm.model.User;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IRetrofitService {

    @POST("views/user_login.php")
    Call<ResponseModel> login(@Body User user);

    @POST("views/user_register.php")
    Call<ResponseModel> register(@Body User user);

    @POST("views/song_get_all.php")
    Call<List<Song>> getAllSong();

    @POST("views/song_category_get_all.php")
    Call<List<SongCategory>> getAllCategory();

    //forgot password
    @POST("views/user_forgot_password.php")
    Call<ResponseModel> forgot(@Body User users);

    @Multipart
    @POST("/")
    Call<Response2PikModel> upload2pik(@Part MultipartBody.Part image);

    @POST("views/add_song.php")
    Call<ResponseModel> insert(@Body Song song);

    @POST("views/song_get_by_id.php")
    Call<Song> getProductById(@Body Song product);

    @POST("views/update_song.php")
    Call<ResponseModel> update(@Body Song song);

    @POST("views/delete_song.php")
    Call<ResponseModel> delete(@Body Song song);
}

package com.minhien.musicappasm.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.minhien.musicappasm.R;
import com.minhien.musicappasm.adapter.CategoryAdapter;
import com.minhien.musicappasm.model.Response2PikModel;
import com.minhien.musicappasm.model.ResponseModel;
import com.minhien.musicappasm.model.Song;
import com.minhien.musicappasm.model.SongCategory;
import com.minhien.musicappasm.myritrofit.IRetrofitService;
import com.minhien.musicappasm.myritrofit.RetrofitBuilder;

import java.io.ByteArrayOutputStream;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateSongActivity extends AppCompatActivity {
    Button btnBack, btnSave, btnTakePhoto;
    EditText edtSongName, edtSingerName;
    Spinner spnCategory;
    ImageView imvImage;
    IRetrofitService service;
    CategoryAdapter adapter;
    List<SongCategory> list;
    private Integer category_id = -1;
    private Integer songId = -1;
    private static String BASE_2PIK_URL = "https://2.pik.vn/";
    private String image_url = null;
    private Song model = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_song);

        btnBack = findViewById(R.id.cancel);
        btnSave = findViewById(R.id.add);
        btnTakePhoto = findViewById(R.id.takephoto);
        edtSongName = findViewById(R.id.edt_song_name);
        edtSingerName = findViewById(R.id.edt_singer_name);
        spnCategory = findViewById(R.id.spn_category);
        imvImage = findViewById(R.id.hinh);

        songId = getIntent().getIntExtra("id_song", -1);

        service = new RetrofitBuilder().createService(IRetrofitService.class);

        service.getAllCategory().enqueue(getAllCategoryCB);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song s = new Song();
                s.setSong_name(edtSongName.getText().toString());
                s.setSinger_name(edtSingerName.getText().toString());
                s.setImage_url(image_url);
                s.setCategory_id(category_id);
                s.setId_Song(songId);
                if (songId == -1){
                    service.insert(s).enqueue(insert_update_CB);
                }
                else {
                    service.update(s).enqueue(insert_update_CB);
                }
            }
        });

        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SongCategory songCategory = (SongCategory)
                        adapterView.getItemAtPosition(i);
                category_id = songCategory.getId_category();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

            encoded = "data:image/png;base64," + encoded;
            MultipartBody.Part part = MultipartBody.Part.createFormData("image", encoded);
            IRetrofitService service = new RetrofitBuilder().createService2Pik(IRetrofitService.class,BASE_2PIK_URL);
            service.upload2pik(part).enqueue(uploadCB);
        }
    }

    Callback<Song> getByIdCB = new Callback<Song>() {
        @SuppressLint("LongLogTag")
        @Override
        public void onResponse(Call<Song> call, Response<Song> response) {
            if (response.isSuccessful()){
                model = response.body();
                edtSongName.setText(model.getSong_name());
                edtSingerName.setText(model.getSinger_name());
                int index = getIndex(list, model.getCategory_id());
                spnCategory.setSelection(index);
                image_url = model.getImage_url();
                Glide.with(UpdateSongActivity.this)
                        .load(model.getImage_url())
                        .into(imvImage);
            } else {
                Log.e("getByIdCB onResponse>>>>", response.message());
            }
        }
        @Override
        public void onFailure(Call<Song> call, Throwable t) {
            Log.e("getByIdCB onFailure>>>>", t.getMessage());
        }
    };

    Callback<List<SongCategory>> getAllCategoryCB = new Callback<List<SongCategory>>() {
        @Override
        public void onResponse(Call<List<SongCategory>> call, Response<List<SongCategory>> response) {
            if(response.isSuccessful()){
                list = response.body();
                adapter = new CategoryAdapter(list, UpdateSongActivity.this);
                spnCategory.setAdapter(adapter);
                spnCategory.setSelection(0);
                if (songId != -1){
                    service = new RetrofitBuilder().createService(IRetrofitService.class);
                    service.getProductById(new Song(songId, -1, null, null, null))
                            .enqueue(getByIdCB);
                }
            }else{
                Log.e("onResponse", response.message());
            }
        }

        @Override
        public void onFailure(Call<List<SongCategory>> call, Throwable t) {
            Log.e("onFailure", t.getMessage());
        }
    };

    Callback<Response2PikModel> uploadCB = new Callback<Response2PikModel>() {
        @Override
        public void onResponse(Call<Response2PikModel> call, Response<Response2PikModel> response) {
            if (response.isSuccessful()){
                Response2PikModel model = response.body();
                image_url = model.getSaved();
                Log.i("image url : ", image_url);
                Glide.with(UpdateSongActivity.this)
                        .load(image_url)
                        .into(imvImage);
            } else {
                Log.i("uploadCB Error: ", response.message());
            }
        }

        @Override
        public void onFailure(Call<Response2PikModel> call, Throwable t) {
            Log.i("onFailure uploadCB: ", t.getMessage());
        }
    };

    Callback<ResponseModel> insert_update_CB = new Callback<ResponseModel>() {
        @Override
        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
            if (response.isSuccessful()){
                ResponseModel model = response.body();
                if (model.getStatus()){
                    finish();
                } else {
                    Toast.makeText(UpdateSongActivity.this, "Insert failed",
                            Toast.LENGTH_LONG).show();
                }
            } else {
                Log.e("insertCB onResponse>>>>", response.message());
            }
        }

        @Override
        public void onFailure(Call<ResponseModel> call, Throwable t) {
            Log.e("insertCB onFailure>>>>", t.getMessage());
        }
    };

    private Integer getIndex(List<SongCategory> _data, int category_id){
        for (int i = 0; i< _data.size(); i++) {
            if (_data.get(i).getId_category() == category_id){
                return i;
            }
        }
        return 0;
    }
}
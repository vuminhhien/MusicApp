package com.minhien.musicappasm.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.minhien.musicappasm.model.ResponseModel;
import com.minhien.musicappasm.myritrofit.IRetrofitService;
import com.minhien.musicappasm.R;
import com.minhien.musicappasm.adapter.SongAdapter;
import com.minhien.musicappasm.model.Song;
import com.minhien.musicappasm.myritrofit.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private List<Song> list = new ArrayList<>();
    private SongAdapter adapter;
    ImageView back;
    FloatingActionButton floatingActionButton;
    private IRetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        back = findViewById(R.id.back_home);
        floatingActionButton = findViewById(R.id.flacbtn);

        service = new RetrofitBuilder().createService(IRetrofitService.class);
        service.getAllSong().enqueue(getAllSongCB);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddSongActivity.class));
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // khởi tạo AlertDialog từ đối tượng Builder. tham số truyền vào ở đây là context.
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // set Message là phương thức thiết lập câu thông báo
                builder.setMessage("Bạn có muốn xóa không ?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Song s = (Song) parent.getItemAtPosition(position);
                                service = new RetrofitBuilder().createService(IRetrofitService.class);
                                service.delete(s).enqueue(deleteCB);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                // tạo dialog và hiển thị
                builder.create().show();
                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadScreen();
    }

    public void reloadScreen() {
        service = new RetrofitBuilder().createService(IRetrofitService.class);
        service.getAllSong().enqueue(getAllSongCB);
    }

    Callback<ResponseModel> deleteCB = new Callback<ResponseModel>() {
        @Override
        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
            if (response.isSuccessful()) {
                ResponseModel responseModel = response.body();
                if (responseModel.getStatus()){
                    reloadScreen();
                }
            } else {
                Log.i("Error", response.message());
            }
        }

        @Override
        public void onFailure(Call<ResponseModel> call, Throwable t) {
            Log.i("Error", call.toString());
        }
    };

    Callback<List<Song>> getAllSongCB = new Callback<List<Song>>() {
        @Override
        public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
            if (response.isSuccessful()) {
                if (list.size() == 0) {
                    list = response.body();
                    adapter = new SongAdapter(MainActivity.this, list);
                    lv.setAdapter(adapter);
                } else {
                    list.clear();
                    list.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            } else {
                Log.i("Error", response.message());
            }
        }

        @Override
        public void onFailure(Call<List<Song>> call, Throwable t) {
            Log.i("Error", call.toString());
        }
    };
}
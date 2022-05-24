package com.example.spreadsheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.spreadsheet.Adapter.DataAdapter;
import com.example.spreadsheet.Model.DataModel;
import com.example.spreadsheet.Utill.ApiInterfaces;
import com.example.spreadsheet.Utill.DataApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.todkars.shimmer.ShimmerRecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private DataAdapter dataAdapter;
    private List<DataModel> dataModelList;
    private ApiInterfaces apiInterfaces;
    private ShimmerRecyclerView mShimmerRecyclerView;
    FloatingActionButton fab;

    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mShimmerRecyclerView = findViewById(R.id.recycler_data);

        mShimmerRecyclerView.setLayoutManager(new LinearLayoutManager(this),
                R.layout.item_list);

        // Fungsi untuk menyembunyikan navbar

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // Inisialisasi fab
        fab =   (FloatingActionButton) findViewById(R.id.btn_add) ;

        // Fungsi saat fab di klik
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, MainActivity.class));
            }
        });

        // Mengubah warna tint fab

        fab.setColorFilter(getResources().getColor(R.color.white));


        // Inisialisasi swiperefresh

        swipeRefreshLayout = findViewById(R.id.swipe);

        // Fungsi saat refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });


        mShimmerRecyclerView.setAdapter(dataAdapter);
        // Inisialisasi recyclerview

        layoutManager = new LinearLayoutManager(this);
        mShimmerRecyclerView.setLayoutManager(layoutManager);
        mShimmerRecyclerView.setHasFixedSize(true);

        apiInterfaces = DataApi.getDataApi().create(ApiInterfaces.class);


        // Memamanggil method getData

        getData();



    }

    public void getData(){
        Call<List<DataModel>> call = apiInterfaces.getData();
        call.enqueue(new Callback<List<DataModel>>() {

            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {

                dataModelList = response.body();
                dataAdapter = new DataAdapter(DashboardActivity.this, dataModelList);
                mShimmerRecyclerView.setAdapter(dataAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }


            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {

                // Menampilkan toast saat no connection

                Toast.makeText(DashboardActivity.this, "No connection, please try again", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }
}
package com.example.spreadsheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.spreadsheet.Adapter.DataAdapter;
import com.example.spreadsheet.Model.DataModel;
import com.example.spreadsheet.Utill.ApiInterfaces;
import com.example.spreadsheet.Utill.DataApi;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mShimmerRecyclerView = findViewById(R.id.recycler_data);

        mShimmerRecyclerView.setLayoutManager(new LinearLayoutManager(this),
                R.layout.item_list);

        mShimmerRecyclerView.setAdapter(dataAdapter);
        // Inisialisasi recyclerview

        layoutManager = new LinearLayoutManager(this);
        mShimmerRecyclerView.setLayoutManager(layoutManager);
        mShimmerRecyclerView.setHasFixedSize(true);

        apiInterfaces = DataApi.getDataApi().create(ApiInterfaces.class);

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
//                swipeRefreshLayout.setRefreshing(false);
            }


            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {

                // Menampilkan toast saat no connection

                Toast.makeText(DashboardActivity.this, "No connection, please try again", Toast.LENGTH_LONG).show();
//                swipeRefreshLayout.setRefreshing(false);

//                Toast.makeText(MainActivity.this, "Error : "+ t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
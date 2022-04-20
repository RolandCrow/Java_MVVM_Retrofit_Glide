package com.example.java_mvvm_retrofit_glide;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.java_mvvm_retrofit_glide.adapter.MovieListAdapter;
import com.example.java_mvvm_retrofit_glide.model.MovieModel;
import com.example.java_mvvm_retrofit_glide.viewmodel.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.ItemClickListener {

    private List<MovieModel> movieModelList;
    private MovieListAdapter adapter;
    private MovieListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final TextView noResultTv = findViewById(R.id.noResultTv);

        LinearLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
         adapter = new MovieListAdapter(this, movieModelList, this);
        recyclerView.setAdapter(adapter);

       viewModel =  ViewModelProviders.of(this).get(MovieListViewModel.class);
       viewModel.getMovieListObserver().observe(this, new Observer<List<MovieModel>>() {
           @Override
           public void onChanged(List<MovieModel> movieModels) {
               if(movieModels != null) {
                   movieModelList = movieModels;
                   adapter.setMovieList(movieModels);
                   noResultTv.setVisibility(View.GONE);
               } else {
                    noResultTv.setVisibility(View.VISIBLE);
               }
           }
       });

       viewModel.makeApiCall();
    }

    @Override
    public void onMovieClick(MovieModel movieModel) {
        Toast.makeText(this, "Clicked Movie Name is" + movieModel.getTitle(), Toast.LENGTH_SHORT).show();

    }
}
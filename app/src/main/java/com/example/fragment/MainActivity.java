package com.example.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView number;
    TextView result;
    Double firstValues, secondValues;
    String operation;
    RecyclerView recyclerView;
    MainAdapter adapter;
    ArrayList<String> arrayList = new ArrayList<>();
    CalculatorFragment calculatorFragment;
    Button open,close;
    private static final String FIRST = "FISRT";
    private static final String SECOND = "SECOND";
    private static final String OPERATION = "OPERATION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);
        open = findViewById(R.id.btncalc1);
        close = findViewById(R.id.btncalc2);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    public void Share(View view) {
        share();
    }

    public void Calculator(View view) {
        calc();
    }

    public void calc() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        calculatorFragment = new CalculatorFragment();
        transaction.replace(R.id.conteiner, calculatorFragment);
        transaction.commit();
        close.setVisibility(View.VISIBLE);
        open.setVisibility(View.GONE);

    }

    public void share() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ShareFragment shareFragment = new ShareFragment();
        transaction.replace(R.id.conteinr,shareFragment);
        transaction.commit();
    }


    public void shareOpen() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,number.getText().toString());
        intent.setType("text/plain");
        startActivity(intent);
    }




    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (firstValues != null) {
            outState.putDouble(FIRST, firstValues);
        }
        if (secondValues != null) {
            outState.putDouble(SECOND, secondValues);
        }
        if (operation != null) {
            outState.putString(OPERATION, operation);
        } }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            firstValues = savedInstanceState.getDouble(FIRST);
            secondValues = savedInstanceState.getDouble(SECOND);
            operation = savedInstanceState.getString(OPERATION);}


    }
    public void Save(String s) {
        arrayList.add(s);
        update();
    }

    public  void  update(){
        adapter.update(arrayList);
        adapter.notifyDataSetChanged();
    }

    public void Calculator2(View view) {
        getSupportFragmentManager().beginTransaction().remove(calculatorFragment).commit();
        open.setVisibility(View.VISIBLE);
        close.setVisibility(View.GONE);
    }
}


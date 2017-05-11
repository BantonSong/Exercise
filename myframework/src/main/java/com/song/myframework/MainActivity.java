package com.song.myframework;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.song.myframework.model.DetailModel;
import com.song.myframework.network.MyObserver;
import com.song.myframework.network.Network;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private TextView tvDetail;
    private Button btnRquest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tvDetail = (TextView) findViewById(R.id.tvDetail);
        btnRquest = (Button) findViewById(R.id.btnRquest);

        btnRquest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyObserver<DetailModel> myObserver = new MyObserver<DetailModel>(MainActivity.this) {
                    @Override
                    public void onNext(DetailModel model) {
                        tvDetail.setText(model.getContent());
                    }
                };
                Network.getInstance().query(myObserver, "一心一意");
//                Network.getApiService().queryArray("一心一意").subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new MyObserver<List<DetailModel>>(MainActivity.this) {
//                    @Override
//                    public void onNext(List<DetailModel> model) {
//                        if (model != null && model.size() > 0) {
//                            tvDetail.setText(model.get(0).getContent());
//                        }
//                    }
//                });
//                Network.getApiService().queryString("").subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new MyObserver<String>(MainActivity.this) {
//                    @Override
//                    public void onNext(String model) {
//                        tvDetail.setText(model);
//                    }
//                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ((MyApplication) getApplication()).appExit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

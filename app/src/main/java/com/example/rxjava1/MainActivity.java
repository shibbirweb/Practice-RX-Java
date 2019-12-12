package com.example.rxjava1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String greeting = "Allahu Akbar";

    private Observable<String> myObservable;
    private DisposableObserver<String > myObserver;
    private DisposableObserver<String > myObserver2;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    // private Disposable disposable;


    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);

        myObservable = Observable.just(greeting);

        // myObservable.subscribeOn(Schedulers.io());

        // myObservable.observeOn(AndroidSchedulers.mainThread());


        /*myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

                Log.i(TAG, "onSubscribe: invoked");

                disposable = d;
            }

            @Override
            public void onNext(String s) {

                Log.i(TAG, "onNext: invoked");
                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) {

                Log.i(TAG, "onError: invoked");
            }

            @Override
            public void onComplete() {

                Log.i(TAG, "onComplete: invoked");
            }
        };*/


        /*myObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {

                textView.setText(s);

                Log.i(TAG, "onNext: invoked");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: invoked");
            }
        };

        compositeDisposable.add(myObserver);
        myObservable.subscribe(myObserver);*/

        // simple way
        compositeDisposable.add(
        myObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(myObserver)
        );

        /*myObserver2 = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {

                textView.setText(s);

                Log.i(TAG, "onNext: invoked");

                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: invoked");
            }
        };
        compositeDisposable.add(myObserver2);
        myObservable.subscribe(myObserver2);*/

        // simple way
        compositeDisposable.add(
                myObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(myObserver2)
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        // myObserver.dispose();
        // myObserver2.dispose();


        // disposable.dispose();;
        compositeDisposable.clear();
    }
}

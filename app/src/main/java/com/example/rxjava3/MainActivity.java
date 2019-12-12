package com.example.rxjava3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    private Observable<Integer> myObservable;
    private DisposableObserver<Integer> myObserver;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    /*private String[] greetings = {
            "Hello A",
            "Hello B",
            "Hello C"
    };*/

    private Integer[] numbers = {
            1, 2, 3, 4, 5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myObservable = Observable.fromArray(numbers);

        compositeDisposable.add(
          myObservable
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeWith(getObserver())
        );

    }




    private DisposableObserver getObserver(){

        myObserver = new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        };

        return myObserver;
    }
}

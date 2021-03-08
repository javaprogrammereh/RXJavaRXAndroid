package com.elmira.rxjavarxandroid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.elmira.rxjavarxandroid.R;
import com.elmira.rxjavarxandroid.model.Task;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class Activity4 extends AppCompatActivity {

    private static final String TAG = "Activity4...";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        /*2*/
        Observable<Task> observable = Observable
                .range(0,9)
                .subscribeOn(Schedulers.io())
                .map(new Function<Integer, Task>() {
                    @Override
                    public Task apply(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "apply::" +Thread.currentThread().getName() );
                        return new Task("this is a task with priority : "+String.valueOf(integer),
                                false,integer);
                    }
                })
                .takeWhile(new Predicate<Task>() {
                    @Override
                    public boolean test(@NonNull Task task) throws Exception {
                        return task.getPriority() < 9;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task task) {
                Log.d(TAG, "onNext: single task: " +task.getPriority() );

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });



        /*1*/
//        Observable<Integer> observable = Observable
//                .range(0,9)
//                .observeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//        observable.subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull Integer integer) {
//                Log.d(TAG, "onNext: single task: " +integer );
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }
}
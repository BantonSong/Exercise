package com.song.exercise.rxjava;

import android.util.Log;

import com.song.exercise.mvp.bean.Course;
import com.song.exercise.mvp.bean.User;
import com.song.exercise.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaTest {
    public static void test() {
//        test2();
        flatMap();
    }

    /**
     * 确实不在同一线程
     */
    public static void test1() {
        final String str = RxJavaTest.class.getSimpleName();

        final List<User> list = new ArrayList<>();
        User user = new User();
        user.setUserName("1");
        list.add(user);
        User user1 = new User();
        user1.setUserName("2");
        list.add(user1);
        User user2 = new User();
        user2.setUserName("3");
        list.add(user2);

        Log.e(str, "currentThreadId:" + Thread.currentThread().getId());
        Observable<User> observable = Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                Log.e(str, "currentThreadId:" + Thread.currentThread().getId());
                for (User user : list) {
                    try {
                        user.setUserName(user.getUserName() + "test");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(user);
                }
                subscriber.onCompleted();
            }
        });

        Subscriber<User> subscriber = new Subscriber<User>() {
            @Override
            public void onCompleted() {
                Log.e(str, "currentThreadId:" + Thread.currentThread().getId());
                Log.e(str, "结束");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(User user) {
                Log.e(str, "currentThreadId:" + Thread.currentThread().getId());
                Log.e(str, user.getUserName());
            }
        };

        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }

    public static void test2() {
        final String str = RxJavaTest.class.getSimpleName();

        final List<User> list = new ArrayList<>();
        User user = new User();
        user.setUserName("1");
        list.add(user);
        User user1 = new User();
        user1.setUserName("2");
        list.add(user1);
        User user2 = new User();
        user2.setUserName("3");
        list.add(user2);

        Log.e(str, "currentThreadId:" + Thread.currentThread().getId());

        // 更改了map中的执行线程
        Observable.from(list).subscribeOn(Schedulers.io()).map(new Func1<User, User>() {
            @Override
            public User call(User user) {
                Log.e(str, "currentThreadId:Func1:call:" + Thread.currentThread().getId());
                user.setUserName(user.getUserName() + "new");
                return user;
            }
            // 不能用subscribeOn修改线程了得用observeOn，subscribeOn,只能用一次
        }).observeOn(AndroidSchedulers.mainThread()).map(new Func1<User, User>() {
            @Override
            public User call(User user) {
                Log.e(str, "currentThreadId:Func1:call:2:" + Thread.currentThread().getId());
                user.setUserName(user.getUserName() + "new");
                return user;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                Log.e(str, "currentThreadId:Action1:call" + Thread.currentThread().getId());
                Log.e(str, user.getUserName());
            }
        });
    }

    public static void flatMap() {
        final String str = RxJavaTest.class.getSimpleName();

        final List<User> list = new ArrayList<>();
        User user = new User();
        user.setUserName("1");
        list.add(user);
        User user1 = new User();
        user1.setUserName("2");
        list.add(user1);
        User user2 = new User();
        user2.setUserName("3");
        list.add(user2);

        Utils.log(str);
        // 更改了map中的执行线程
        Observable.from(list).subscribeOn(Schedulers.io()).map(new Func1<User, User>() {
            @Override
            public User call(User user) {
                Utils.log(str);
                List<Course> courseList = new ArrayList();
                Course course = new Course();
                course.setName(user.getUserName() + "语文");
                Course course1 = new Course();
                course1.setName(user.getUserName() + "数学");
                Course course2 = new Course();
                course2.setName(user.getUserName() + "英语");

                courseList.add(course);
                courseList.add(course1);
                courseList.add(course2);
                user.setCourses(courseList);
                return user;
            }
        }).flatMap(new Func1<User, Observable<Course>>() {
            @Override
            public Observable<Course> call(User user) {
                return Observable.from(user.getCourses());
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Course>() {
            @Override
            public void call(Course course) {
                Utils.log(str);
                Utils.log(str, course.getName());
            }
        });

        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package com.example.mydome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static int i = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myMethod(new main() {
            @Override
            public String call() {
                return null;
            }

            public void eat() {
                System.out.println("qqqqqqq");
            }
        });


    }

    public static void myMethod(GenericMethod g) {
        if (i == 2) {
            System.out.println("This method called " + g.call());
        } else {
            g.eat();
        }

    }

    /*接口*/
    public interface GenericMethod {
        public String call();

        public void eat();
    }

    //简单的回调
    public class main implements GenericMethod {

        @Override
        public String call() {
            return "3";
        }

        @Override
        public void eat() {
            System.out.println("yyyyyy");
        }
    }
}
package com.example.myapplication;

import android.os.Handler;

public class ThreadGame extends Thread {
    private Handler handler;
    public ThreadGame(Handler handler) {

        this.handler=handler;
    }

    @Override
    public void run() {
        super.run();
        while (true)
        {
            try {
                sleep(2);
                handler.sendEmptyMessage(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

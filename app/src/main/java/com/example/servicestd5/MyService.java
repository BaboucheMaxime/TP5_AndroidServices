package com.example.servicestd5;


class MyService implements Runnable{

    private Boolean stop = false;

    public void run(){

        while(!stop){

            //some business logic
        }
    }
    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}

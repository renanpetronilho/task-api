package com.petronilho.taskapi.status;

import java.io.Serializable;

/**
 * Created by renanpetronilho on 31/07/17.
 */
public class Status implements Serializable{

    private String status;

    public Status() {
    }

    public Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

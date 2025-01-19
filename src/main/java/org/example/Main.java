package org.example;

import Controller.DBConnect;
import View.Loading;

import java.sql.Connection;

public class Main{
    public static void main(String[] args) {
        new Loading().RUN();
//        Connection x = DBConnect.getInstance();
    }
}
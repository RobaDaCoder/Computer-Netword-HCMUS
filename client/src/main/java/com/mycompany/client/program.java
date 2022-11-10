/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.client;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author LEGION
 */
public class program {
    public static Socket sclient;
    public static BufferedReader is;
    public static BufferedWriter os;
    public static Image img1;
    public static String ip;
    public static void main(String args[]) throws IOException, InterruptedException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new client().setVisible(true);
                
            }
        });
    }
}

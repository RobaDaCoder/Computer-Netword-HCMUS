/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.server;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import javax.swing.JOptionPane;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author LEGION
 */

public class server extends javax.swing.JFrame {

    /**
     * Creates new form server
     */
    public server() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Server = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Server.setText("Mở server");
        Server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(Server, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(Server, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void receiveSignal()
    {
        try{
//           program.is = new BufferedReader(new InputStreamReader(program.sserver.getInputStream()));
           program.signal = program.is.readLine();
        }catch (IOException e) {
           program.signal = "QUIT";
        }
    }
    
    private void ServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServerActionPerformed
       ServerSocket listener = null;
       System.out.println("Server is waiting to accept user...");
       try {
           listener = new ServerSocket(6920);
           program.sserver = listener.accept();
           program.is = new BufferedReader(new InputStreamReader(program.sserver.getInputStream()));
           program.os = new BufferedWriter(new OutputStreamWriter(program.sserver.getOutputStream()));
           boolean perform = true;
           while (perform)
           {
               receiveSignal();
               switch (program.signal)
               {
                    case "SCREENSHOT" -> takepic();
                    case "START" -> start();
                    case "SHUTDOWN" -> shutdown();
                    case "KILL" -> kill();
//                    case "CHECKSCREEN" -> checkscreen();
                    case "XEMAPP" -> xemapp();
                    case "SIGNOUT" -> signout();
                    case "XEMPROCESS" -> xemprocess();
                    case "KEYLOG" -> keylog();
                    case "EXIT" -> {
                        program.sserver.close();
                        listener.close();
                        return;
                    }
                    case "QUIT" -> {
                        break;
                    }
               }
           }
       } catch (IOException e) {
           JOptionPane.showMessageDialog(rootPane, "Không thể mở server");
       } 
    }//GEN-LAST:event_ServerActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Flatlaf Light".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new server().setVisible(true);
//            }
//        });
//    }
    
    // Keylogger
    
    int counthookKey = 0;
    public void hookKey(KeyLogger k){
        counthookKey += 1;
        if(counthookKey == 1){
            try{
                GlobalScreen.registerNativeHook();
            }catch (NativeHookException e){
                e.printStackTrace();
            }
            GlobalScreen.addNativeKeyListener(k);
            
        } else{
            if(counthookKey > countunHook){
                GlobalScreen.removeNativeKeyListener(k);
            }
            
            GlobalScreen.addNativeKeyListener(k);
                          
        }
    }
    
    int countunHook = 1;
    public void unHookKey(KeyLogger k) throws NativeHookException{
        countunHook += 1;
        GlobalScreen.removeNativeKeyListener(k);
        k.store = "";
    }

    public void print(KeyLogger k) {

        String mes = "";

        try {
            if(k == null){
                program.os.write("");
                program.os.newLine();
                program.os.flush();
            }
            else{
                mes = k.store;
                if(mes != ""){                  
                    program.os.write(mes);
                    program.os.newLine();
                    program.os.flush();
                    k.store = "";
                }
                else {            
                    program.os.write("");
                    program.os.newLine();
                    program.os.flush();
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void keylog() throws IOException{     
        KeyLogger k = new KeyLogger();
        while(true){
            receiveSignal();
            switch (program.signal){
                case "HOOK" ->{
                    hookKey(k);
                    break;
                    
                }
                case "UNHOOK"->{
                    try {
                        unHookKey(k);
                        break;
                    } catch (NativeHookException ex) {
                        Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                      break;
                }

                case "PRINT"->{
                    print(k);
                    k.store = "";
                    break;
                }
                case "DELETE"->{
                    k.store = "";
                    break;
                }
                case "QUIT"->{
                    break;
                }
            }
        }
    }
    
    // End Keylogger
    public void shutdown()
    {
        try{
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("shutdown -s -t 5");
            System.exit(0);
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    public void signout()
    {
        try{
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("shutdown -l");
            System.exit(0);
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    public void xemprocess()
    {
        try {
                        String line = null;
                        p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
                        input = new BufferedReader(new InputStreamReader(p.getInputStream()));                  
                        int soprocess = 0;
                        while(input.readLine() != null){
                            soprocess++;
                        }
                        String soprocess1 = Integer.toString(soprocess);
                        program.os.write(soprocess1);
                        program.os.newLine();
                        program.os.flush();
                        p1 = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
                        input = new BufferedReader(new InputStreamReader(p1.getInputStream()));
                        out = new ObjectOutputStream(program.sserver.getOutputStream());
                        try {
                            for(int i = 0; (i<soprocess) ;i++) {
                                line = input.readLine();
                                line = line.trim();
                                if (i>=3)
                                {
                                    for (int u =0; u < line.length()-2;u++)
                                    {
                                        if ((line.charAt(u)>64 && line.charAt(u)<=122)&&(line.charAt(u+2)>64 && line.charAt(u+2)<=122) && line.charAt(u+1)==' ')
                                        {
                                            line = line.substring(0,u+1)+"_"+line.substring(u+2,line.length());
                                        }
                                    }
                                    String[] splitline = line.split("\\s{1,100}");
                                    String data[] = {splitline[0],splitline[1],splitline[2],splitline[3],splitline[4]+splitline[5]};
                                    out.writeObject(data);
                                    out.flush();
                                }
                            }
                        }catch(IOException e)
                        {
                          JOptionPane.showMessageDialog(null,e);
                        }
                        
                    }
                    catch(IOException e)
                    {
                      JOptionPane.showMessageDialog(null,e);
                    }
    }
    public void xemapp()
    {
        try {
                        String line = null;
                        p = Runtime.getRuntime().exec("powershell.exe Get-Process | Where-Object { $_.MainWindowTitle } | Format-Table ID,Name,Mainwindowtitle –AutoSize");
                        input = new BufferedReader(new InputStreamReader(p.getInputStream()));                  
                        int soprocess = 0;
                        while(input.readLine() != null){
                            soprocess++;
                        }
                        String soprocess1 = Integer.toString(soprocess);
//                        program.os = new BufferedWriter(new OutputStreamWriter(program.sserver.getOutputStream()));
                        program.os.write(soprocess1);
                        program.os.newLine();
                        program.os.flush();
                        p1 = Runtime.getRuntime().exec("powershell.exe Get-Process | Where-Object { $_.MainWindowTitle } | Format-Table ID,Name,Mainwindowtitle –AutoSize");
                        input = new BufferedReader(new InputStreamReader(p1.getInputStream()));
                        out = new ObjectOutputStream(program.sserver.getOutputStream());
                        try {
                            for(int i = 0; (i<soprocess) ;i++) {
                                line = input.readLine();
                                line = line.trim();
                                if (i>=3) 
                                {
                                    if (i == soprocess-2)
                                    {
                                        break;
                                    }
                                    line = line.replaceAll("\\s{1,100}", " ");
                                    String[] splitline = line.split(" ",3);
                                    String data[] = {splitline[0],splitline[1],splitline[2]};
                                    out.writeObject(data);
                                    out.flush();
                                }
                            }
                        }
                    catch(IOException e)
                    {
                      JOptionPane.showMessageDialog(null,e);
                    }
                    }catch(IOException e)
                    {
                      JOptionPane.showMessageDialog(null,e);
                    }
    }
    public void kill() throws IOException
    {
        boolean work = true;
                    while (work) {
                        receiveSignal();
                        switch(program.signal)
                        {
                            case "KILLID" -> {
                                String pid = program.is.readLine();
                                if (pid != null)
                                {
                                try {
                                    String[] cmd = {"taskkill", "/F", "/T", "/PID", pid};
                                    ProcessBuilder p = new ProcessBuilder();
                                    p.command(cmd);
                                    p.start();
                                    program.os.write("Successfully kill a process!");
                                    program.os.newLine();
                                    program.os.flush();
                                } catch (IOException ex) {
                                    program.os.write("There is an error, please try again!");
                                    program.os.newLine();
                                    program.os.flush();
                                    break;
                                }
                                } else {
                                program.os.write("There is an error, please try again!");
                                program.os.newLine();
                                program.os.flush();
                                break;
                                }
                            }
                            case "QUIT" -> {
                                work = false;
                                break;
                            }
                        }
    }
    }
    public void start() throws IOException
    {
        boolean work = true;
                    while (work) {
                        receiveSignal();
                        switch(program.signal)
                        {
                            case "STARTEXE" -> {
                                String exe = program.is.readLine();
                                exe = exe+".exe";
                                if (!"ERROR".equals(exe))
                                {
                                try {
//                                    String[] run = {"cmd","/c","start", exe};
                                    ProcessBuilder p = new ProcessBuilder();
                                    p.command(exe);
                                    p.start();
                                    program.os.write("Successfully run the program!");
                                    program.os.newLine();
                                    program.os.flush();
                                } catch (IOException ex) {
                                    program.os.write("There is an error, please try again!");
                                    program.os.newLine();
                                    program.os.flush();
                                }
                                } else {
                                program.os.write("There is an error, please try again!");
                                program.os.newLine();
                                program.os.flush();
                                break;
                                }
                            }
                            case "QUIT" -> {
                                work = false;
                                break;
                            }
                        }
                    }
    }
    
   
 
public void takepic() throws IOException
    {
        try{
            robot = new Robot();
            ous = new ByteArrayOutputStream();
            bimg = robot.createScreenCapture(new Rectangle(0,0,(int) d.getWidth(), (int) d.getHeight()));
            ImageIO.write(bimg, "png", ous);
            byte[] bytes = ous.toByteArray();
            out = new ObjectOutputStream(program.sserver.getOutputStream()) ;
            out.writeObject(bytes); 
            } catch(AWTException | IOException ex){
                JOptionPane.showMessageDialog(null,ex);
            }
    }
    
//    public void checkscreen()
//    {
//        boolean indo = true;
//        while (indo)
//        {
//            receiveSignal();
//            switch(program.signal)
//            {
//                case "START" ->                 {
//                    try{
//                        Robot robot = new Robot();
//                        Toolkit toolkit = Toolkit.getDefaultToolkit();
//                        Dimension d = toolkit.getScreenSize();
//
//                        while (true){
//                            ous = new ByteArrayOutputStream();
//                            BufferedImage img = robot.createScreenCapture(new Rectangle(0,0,(int) d.getWidth(), (int) d.getHeight()));
//                            ImageIO.write(img, "png", ous);
//                            program.sserver.getOutputStream().write(ous.toByteArray());
//                            ous.flush();
//                            ous.reset();
//                            ous.close();
//                            try {
//                                Thread.sleep(30);
//                            } catch (Exception e) {
//                            }
//                        }
//                    } catch(Exception ex){
//                            JOptionPane.showMessageDialog(null,ex);
//                    }
//                }
//                case "QUIT" ->                 {
//                    indo = false;
//                    break;
//                }
//            }
//        }
//    }
ByteArrayOutputStream ous = null;
    ObjectOutputStream out = null;
    BufferedReader input = null;
    OutputStream os = null;
    Process p = null;
    Process p1 = null;
    private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private Image newimg;
    private Robot robot;
    private static BufferedImage bimg;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Server;
    // End of variables declaration//GEN-END:variables
//    private ByteArrayOutputStream ous = new ByteArrayOutputStream();
    void setvisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pongban;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 *
 * @author Nam
 */
public class PONGBAN extends JFrame{
    private ControlWindow cw=new ControlWindow();
    public PONGBAN(){
        this.add(cw);
        this.pack();
        this.setTitle("PONG");
        this.setSize(Consts.w, Consts.h);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
       new PONGBAN();
        
    }
 class ControlWindow extends JPanel implements ActionListener, KeyListener{
     private Ball ball=new Ball(Consts.w/2, Consts.h/2, 30);
     private Timer timer=new Timer(30,this);
     private Player Ip=new Player(0,Consts.h/2);
     private Player IIp=new Player(Consts.w-35,Consts.h/2);
     private Font gameFont =new Font("Consolas",Font.PLAIN,30);
   @Override public void paintComponent(Graphics g){
       super.paintComponent(g);
       g.setColor(Color.red);
//       tạo hình chữ nhật rỗng bên trong
//       g.drawRect(100,100,100,100); 
//        hình chử nhật đầy màu
//        g.fillRect(100, , WIDTH, HEIGHT);
        g.fillOval(ball.x,ball.y, ball.diameter, ball.diameter);
        g.fillRect(Ip.x,Ip.y,Ip.w,Ip.h);
        g.fillRect(IIp.x,IIp.y,IIp.w,IIp.h);
        g.setColor(Color.white);
        g.setFont(gameFont);
        g.drawString("DIEM:"+Ip.socre,10,25);
        g.drawString("DIEM:"+IIp.socre,850,25);
   }
     public ControlWindow(){
         this.setBackground(Color.black);
         timer.start();
         this.addKeyListener(this);
         this.setFocusable(true);
     }

        @Override
        public void actionPerformed(ActionEvent e) {
            ball.x+=ball.speedX;
            ball.y+=ball.speedY; 
            
            Rectangle rectBall=new Rectangle(ball.x,ball.y,ball.diameter,ball.diameter);
            Rectangle rectlp=new Rectangle(Ip.x,Ip.y,Ip.w,Ip.h);
            Rectangle rectrp=new Rectangle(IIp.x,IIp.y,IIp.w,IIp.h);
            
            if(rectBall.intersects(rectrp)){
                ball.speedX=-Math.abs(ball.speedX);
                ball.speedX--;
                ball.speedY--;
                System.out.println(ball.speedX);
            }
            if(rectBall.intersects(rectlp)){
                ball.speedX=Math.abs(ball.speedX);
                ball.speedX++;
                ball.speedY++;
                 System.out.println(ball.speedX);
            }
            if(ball.y>=Consts.h-ball.diameter *2){
                ball.speedY *=-1;
            }
            if(ball.x>=Consts.w-ball.diameter){
                Ip.socre++;
                JOptionPane.showMessageDialog(this,"KET THUC BAM OK DE TIEP TUC");
                ball.speedX=10;
                ball.speedY=10;
                ball.x=Consts.w/2;
                ball.y=Consts.h/2;
            }
            if(ball.y<=0){
                ball.speedY *=-1;
            }
            if(ball.x<=0){
                IIp.socre++;
                ball.speedX=10;
                ball.speedY=10;
                JOptionPane.showMessageDialog(this,"KET THUC BAM OK DE TIEP TUC");
                ball.x=Consts.w/2;
                ball.y=Consts.h/2;
            }
            repaint();
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
             if(e.getKeyCode()==KeyEvent.VK_W){
            Ip.y-=Ip.speedY;
            
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            Ip.y+=Ip.speedY;
           
        }
//        2
         if(e.getKeyCode()==KeyEvent.VK_UP){
            IIp.y-=IIp.speedY;
         
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            IIp.y+=IIp.speedY;
        
        }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
 }
}
class Ball{
    public int x,y,diameter;
    public int speedY=6,speedX=6;

    public Ball(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

    public Ball() {
    }
    
}
class Player{
    public int socre=0;
    public int x,y;
    public int speedY=20;
    public int w=20,h=100;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }
     public Player() {
    }
    
}
class Consts{
    public static final int w=1000;
    public static  final int h=550;
}
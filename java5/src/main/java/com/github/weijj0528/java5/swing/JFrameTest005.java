package com.github.weijj0528.java5.swing;

import javax.swing.*;
import java.awt.*;

/**
 * 画图演示
 */
public class JFrameTest005 {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("绘图测试");
        jFrame.setSize(400, 300);
        JPanel comp = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(Color.RED);
                g.drawArc(0, 0, 100, 100, 0, 90);
                // 直线
                g.setColor(Color.YELLOW);
                g.drawLine(0, 0, 100, 100);
                // 椭圆
                g.setColor(Color.BLUE);
                g.drawOval(100, 100, 200, 100);
                // 矩形
                g.setColor(Color.BLACK);
                //g.drawRect(100,100,200,100);
                g.draw3DRect(100, 100, 200, 100, false);
                // 圆角矩形
                g.setColor(Color.CYAN);
                g.drawRoundRect(90, 90, 220, 120, 20, 20);
                // String
                g.setColor(Color.GREEN);
                //g.setColor(new Color(255,255,255));
                g.drawString("Hello", 200, 150);
            }
        };
        comp.setSize(400, 300);
        jFrame.setContentPane(comp);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}

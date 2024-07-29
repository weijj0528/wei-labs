package com.github.weijj0528.java5.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * JFrame简单演示
 * Step1:创建框架 JFrame jFrame = new JFrame()
 * Step2:创建并设置新的面板 JPanel jPanel = new JPanel() 可以使用框架中默认面板
 * Step3:向面板中添加组件
 * Step4:展示框架
 */
public class JFrameTest001 {

    public static void main(String[] args) {
        // 简单示例
        // 创建框架
        JFrame jFrame = new JFrame();
        // 框架设置
        jFrame.setTitle("JFrameTest");
        jFrame.setSize(400, 300);
        System.out.println(jFrame.getLayout());
        System.out.println(jFrame.getContentPane());
        // 可以使用框架中默认面板 注意面板布局与新建的不一样
        JPanel jPanel = (JPanel) jFrame.getContentPane();
        System.out.println(jPanel.getLayout());
        // 创建并设置新的面板
        jPanel = new JPanel();
        System.out.println(jPanel.getLayout());
        jFrame.setContentPane(jPanel);
        // 添加组件 按扭
        jPanel.add(new JButton("Press me"), BorderLayout.CENTER);
        // 添加组件 组合框
        String[] strings = {"One", "Two"};
        JComboBox jComboBox = new JComboBox(strings);
        jComboBox.addItem("Three");
        jComboBox.addItem("Four");
        jComboBox.addItem("Five");
        jComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox jcb = (JComboBox) e.getSource();
                System.out.println("actionPerformed:" + jcb.getSelectedItem());
            }
        });
        jComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JComboBox jcb = (JComboBox) e.getSource();
                System.out.println("itemStateChanged:" + jcb.getSelectedItem());
            }
        });
        jPanel.add(jComboBox);

        // 框架展示
//        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

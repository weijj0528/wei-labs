package com.github.weijj0528.java5.swing;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * 菜单演示
 */
public class JFrameTest003 {

    public static void main(String[] args) {
        // 简单示例
        JFrame jFrame = new JFrame();
        jFrame.setTitle("JListTest");
        jFrame.setSize(400, 300);

        JMenuBar menubar = new JMenuBar();
        JMenu popupMenu = new JMenu("文件");
        JMenuItem item = new JMenuItem("保存");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        popupMenu.add(item);
        menubar.add(popupMenu);
        menubar.add(new JMenu("编辑"));
        menubar.add(new JMenu("帮助"));
        JMenu quit = new JMenu("退出");
        quit.addMenuListener(new MenuListener() {

            public void menuSelected(MenuEvent e) {
                System.exit(9);
            }

            public void menuDeselected(MenuEvent e) {

            }

            public void menuCanceled(MenuEvent e) {

            }
        });
        menubar.add(quit);
        jFrame.setJMenuBar(menubar);

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

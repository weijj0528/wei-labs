package com.github.weijj0528.java5.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;

/**
 * 各组件演示
 */
public class JFrameTest004 {

    public static void main(String[] args) throws Exception {
        JFrame jFrame = new JFrame("组件演示");
        jFrame.setSize(400, 300);
        GridBagLayout gridBagLayout = new GridBagLayout();
        JPanel contentPane = new JPanel(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        addComponent(contentPane, getLabel(), gridBagLayout, constraints);
        addComponent(contentPane, getButtons(), gridBagLayout, constraints);

        jFrame.setContentPane(contentPane);
        jFrame.setVisible(true);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    private static ArrayList<Component> getButtons() throws Exception {
        ArrayList<Component> arrayList = new ArrayList<Component>();
        final JButton button1 = new JButton("按钮1");
        arrayList.add(button1);
        final JButton button2 = new JButton("按钮2");
        ImageIcon icon = new ImageIcon(new URL("https://tse2-mm.cn.bing.net/th/id/OIP.pTpFCZAahWRsyEjT78acHgHaHa?w=119&h=120&c=7&o=5&pid=1.7"));
        Image image = icon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
        icon.setImage(image);
        button2.setIcon(icon);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button1.setText("按钮1");
                button2.setText("按钮1让人点了");
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button2.setText("按钮2");
                button1.setText("按钮2让人点了");
            }
        });
        arrayList.add(button2);
        return arrayList;
    }

    private static ArrayList<Component> getLabel() throws Exception {
        ArrayList<Component> arrayList = new ArrayList<Component>();
        JLabel jLabel1 = new JLabel("标签1");
        arrayList.add(jLabel1);
        JLabel jLabel2 = new JLabel("标签2带图标");
        ImageIcon icon = new ImageIcon(new URL("https://tse2-mm.cn.bing.net/th/id/OIP.pTpFCZAahWRsyEjT78acHgHaHa?w=119&h=120&c=7&o=5&pid=1.7"));
        Image image = icon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
        icon.setImage(image);
        jLabel2.setIcon(icon);
        arrayList.add(jLabel2);
        return arrayList;
    }

    private static void addComponent(JPanel contentPane, ArrayList<Component> components, GridBagLayout gridBagLayout, GridBagConstraints constraints) {
        constraints.gridwidth = 1;
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            if (i == components.size() - 1) {
                constraints.gridwidth = GridBagConstraints.REMAINDER;
            }
            gridBagLayout.setConstraints(component, constraints);
            contentPane.add(component);
        }
    }

}

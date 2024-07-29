package com.github.weijj0528.java5.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 布局演示
 * 1: BorderLayout
 * 2: FlowLayout
 * 3: CardLayout
 * 4: GridLayout
 * 5: GridBagLayout
 * 6: BoxLayout
 */
public class JFrameTest002 {

    public static void main(String[] args) throws MalformedURLException {
        // 面板示例
        JFrame jFrame = new JFrame("面板示例");
        jFrame.setSize(400, 300);

        Panel contentPane = getBoxLayoutPanel();

        jFrame.setContentPane(contentPane);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private static Panel getBoxLayoutPanel() {
        Panel panel = new Panel();
        Box verticalBox = Box.createVerticalBox();
        Box horizontalBox = Box.createHorizontalBox();
        verticalBox.add(horizontalBox);
        panel.add(verticalBox);
        for (int i = 0; i < 10; i++) {
            verticalBox.add(new JLabel(String.valueOf(i)));
        }
        for (int i = 0; i < 10; i++) {
            horizontalBox.add(new JLabel(String.valueOf(i)));
        }
        return panel;
    }

    /**
     * GridBagLayout（网格包布局管理器）是在网格基础上提供复杂的布局，是最灵活、 最复杂的布局管理器。
     * GridBagLayout 不需要组件的尺寸一致，允许组件扩展到多行多列。
     * 每个 GridBagLayout 对象都维护了一组动态的矩形网格单元，每个组件占一个或多个单元，所占有的网格单元称为组件的显示区域。
     * <p>
     * GridBagLayout 所管理的每个组件都与一个 GridBagConstraints 约束类的对象相关。
     * 这个约束类对象指定了组件的显示区域在网格中的位置，以及在其显示区域中应该如何摆放组件。
     * 除了组件的约束对象，GridBagLayout 还要考虑每个组件的最小和首选尺寸，以确定组件的大小。
     * <p>
     * 为了有效地利用网格包布局管理器，在向容器中添加组件时，必须定制某些组件的相关约束对象。GridBagConstraints 对象的定制是通过下列变量实现的。
     * 1. gridx 和 gridy
     * 用来指定组件左上角在网格中的行和列。容器中最左边列的 gridx 为 0，最上边行的 gridy 为 0。这两个变量的默认值是 GridBagConstraints.RELATIVE，表示对应的组件将放在前一个组件的右边或下面。
     * 2. gridwidth 和 gridheight
     * 用来指定组件显示区域所占的列数和行数，以网格单元而不是像素为单位，默认值为 1。
     * 3. fill
     * 指定组件填充网格的方式，可以是如下值：GridBagConstraints.NONE（默认值）、GridBagConstraints.HORIZONTAL（组件横向充满显示区域，但是不改变组件高度）、GridBagConstraints.VERTICAL（组件纵向充满显示区域，但是不改变组件宽度）以及 GridBagConstraints.BOTH（组件横向、纵向充满其显示区域）。
     * 4. ipadx 和 ipady
     * 指定组件显示区域的内部填充，即在组件最小尺寸之外需要附加的像素数，默认值为 0。
     * 5. insets
     * 指定组件显示区域的外部填充，即组件与其显示区域边缘之间的空间，默认组件没有外部填充。
     * 6. anchor
     * 指定组件在显示区域中的摆放位置。可选值有 GridBagConstraints.CENTER（默认值）、GridBagConstraints.NORTH、GridBagConstraints.
     * NORTHEAST、GridBagConstraints.EAST、GridBagConstraints.SOUTH、GridBagConstraints.SOUTHEAST、GridBagConstraints.WEST、GridBagConstraints.SOUTHWEST 以及 GridBagConstraints.NORTHWEST。
     * 7. weightx 和 weighty
     * 用来指定在容器大小改变时，增加或减少的空间如何在组件间分配，默认值为 0，即所有的组件将聚拢在容器的中心，多余的空间将放在容器边缘与网格单元之间。weightx 和 weighty 的取值一般在 0.0 与 1.0 之间，数值大表明组件所在的行或者列将获得更多的空间。
     *
     * @return
     */
    private static Panel getGridBagLayoutPanel() {
        //创建GridBagLayout布局管理器
        GridBagLayout gbaglayout = new GridBagLayout();
        Panel contentPane = new Panel(gbaglayout);
        GridBagConstraints constraints = new GridBagConstraints();
        //组件填充显示区域
        constraints.fill = GridBagConstraints.BOTH;
        //恢复默认值
        constraints.weightx = 0.0;
        //结束行
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        // 创建组件，加入而已管理，添加到面板
        JTextField tf = new JTextField("13612345678");
        gbaglayout.setConstraints(tf, constraints);
        contentPane.add(tf);

        // 指定组件的分配区域
        constraints.weightx = 0.5;
        constraints.weighty = 0.2;
        constraints.gridwidth = 1;
        //调用方法，添加按钮组件
        makeButton("7", contentPane, gbaglayout, constraints);
        makeButton("8", contentPane, gbaglayout, constraints);
        //结束行
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        makeButton("9", contentPane, gbaglayout, constraints);
        //重新设置gridwidth的值
        constraints.gridwidth = 1;
        makeButton("4", contentPane, gbaglayout, constraints);
        makeButton("5", contentPane, gbaglayout, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        makeButton("6", contentPane, gbaglayout, constraints);
        constraints.gridwidth = 1;
        makeButton("1", contentPane, gbaglayout, constraints);
        makeButton("2", contentPane, gbaglayout, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        makeButton("3", contentPane, gbaglayout, constraints);
        constraints.gridwidth = 1;
        makeButton("返回", contentPane, gbaglayout, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        makeButton("拨号", contentPane, gbaglayout, constraints);
        return contentPane;
    }

    public static void makeButton(String title, Panel panel, GridBagLayout gridBagLayout, GridBagConstraints constraints) {
        //创建Button对象
        JButton button = new JButton(title);
        gridBagLayout.setConstraints(button, constraints);
        panel.add(button);
    }

    /**
     * GridLayout（网格布局管理器）为组件的放置位置提供了更大的灵活性。
     * 它将区域分割成行数（rows）和列数（columns）的网格状布局，组件按照由左至右、由上而下的次序排列填充到各个单元格中。
     * <p>
     * 提示：GridLayout 布局管理器总是忽略组件的最佳大小，而是根据提供的行和列进行平分。该布局管理的所有单元格的宽度和高度都是一样的。
     *
     * @return
     */
    private static Panel getGridLayoutPanel() {
        // 将组件间的横向和纵向间隙都设置为20像素
        Panel contentPane = new Panel(new GridLayout(4, 4, 5, 5));
        String[] labels = {"1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "*", "0", ".", "=", "/"};
        for (String label : labels) {
            contentPane.add(new JButton(label));

        }
        return contentPane;
    }

    /**
     * CardLayout（卡片布局管理器）能够帮助用户实现多个成员共享同一个显不空间，并且一次只显示一个容器组件的内容。
     * CardLayout 布局管理器将容器分成许多层，每层的显示空间占据整个容器的大小，但是每层只允许放置一个组件。
     *
     * @return
     */
    private static Panel getCardLayoutPanel() {
        final String[] cardNames = {"卡片1：用户注册", "卡片2：密码找回"};
        final Panel contentPane = new Panel(new BorderLayout());
        final CardLayout cardLayout = new CardLayout();
        final JPanel cards = new JPanel(cardLayout);
        for (String cardName : cardNames) {
            cards.add(new JLabel(cardName), cardName);
        }
        cardLayout.show(cards, cardNames[0]);
        contentPane.add(cards, BorderLayout.CENTER);
        // 卡片控制
        final AtomicInteger num = new AtomicInteger(0);
        JButton button = new JButton("切换");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = num.incrementAndGet();
                cardLayout.show(cards, cardNames[i % cardNames.length]);
            }
        });
        contentPane.add(button, BorderLayout.NORTH);
        return contentPane;
    }

    /**
     * FlowLayout（流式布局管理器）是 JPanel 和 JApplet 的默认布局管理器。
     * FlowLayout 会将组件按照从上到下、从左到右的放置规律逐行进行定位。
     * 与其他布局管理器不同的是，FlowLayout 布局管理器不限制它所管理组件的大小，而是允许它们有自己的最佳大小。
     *
     * @return
     */
    private static Panel getFlowLayoutPanel() {
        // 将组件间的横向和纵向间隙都设置为20像素
        Panel contentPane = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        contentPane.setBackground(Color.BLUE);
        for (int i = 0; i < 10; i++) {
            contentPane.add(new JButton(String.valueOf(i)));
        }
        return contentPane;
    }

    /**
     * BorderLayout（边框布局管理器）是 Window、JFrame 和 JDialog 的默认布局管理器。
     * 边框布局管理器将窗口分为 5 个区域：North、South、East、West 和 Center。
     * 其中，North 表示北，将占据面板的上方；
     * Soufe 表示南，将占据面板的下方；
     * East表示东，将占据面板的右侧；
     * West 表示西，将占据面板的左侧；
     * 中间区域 Center 是在东、南、西、北都填满后剩下的区域
     * <p>
     * 注意：边框布局管理器并不要求所有区域都必须有组件，如果四周的区域（North、South、East 和 West 区域）没有组件，则由 Center 区域去补充。
     * 如果单个区域中添加的不只一个组件，那么后来添加的组件将覆盖原来的组件，所以，区域中只显示最后添加的一个组件。
     *
     * @return
     */
    private static Panel getBorderLayoutPanel() {

        Panel contentPane = new Panel(new BorderLayout());
        contentPane.setBackground(Color.BLUE);
        // 上北下南 左西右东
        contentPane.add(new JButton("点我-中"), BorderLayout.CENTER);
        contentPane.add(new JButton("点我-上（北）"), BorderLayout.NORTH);
        contentPane.add(new JButton("点我-下（南）"), BorderLayout.SOUTH);
        contentPane.add(new JButton("点我-左（西）"), BorderLayout.WEST);
        contentPane.add(new JButton("点我-右（东）"), BorderLayout.EAST);

        return contentPane;
    }

}

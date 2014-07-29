/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jplus.action;

import com.jplus.model.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author hyberbin
 */
public class MainFrame extends Frame {

    public int size = 10;
    private static final int buttonsize = 25;//按钮大小相关参数
    private Button[][] button = new Button[size][size];

    public MainFrame() {
        setSize(size * buttonsize, size * buttonsize + 40);
        Toolkit tk = Toolkit.getDefaultToolkit();
        setLocation((tk.getScreenSize().width - getSize().width) / 2,
                (tk.getScreenSize().height - getSize().height) / 2);
        this.setTitle("扫雷");        
        Panel chessPanel = new Panel();
        //设置窗体布局为矩阵式
        chessPanel.setLayout(new GridLayout(size, size));
        //添加按钮
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                button[i][j] = new Button(i,j);
                chessPanel.add(button[i][j]);
            }
        }
        Button.setButton(button);
        setMines();
        add(chessPanel, "North");
        //设置关闭事件
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                if (JOptionPane.showConfirmDialog(null, "确认退出？") == 0) {
                    System.exit(0);
                }
            }
        });
        //设置窗体大小不可改变
        setResizable(false);
        //设置窗体可见
        setVisible(true);
    }

    /**
     * 随机放置地雷
     */
    private void setMines() {
        for (int i = 0; i < size; i++) {
            int randX = (int) (Math.random() * size);
            int randY = (int) (Math.random() * size);
            if (button[randX][randY].isMines()) {
                i--;
            } else {
                button[randX][randY].setIsMines(true);
                button[randX][randY].setNum(-1);
                setNum(randX, randY);
            }
        }
    }

    private void setNum(int x, int y) {
        if ((x - 1 >= 0 && y - 1 >= 0) && (button[x - 1][y - 1].getNum() >= 0)) {
            button[x - 1][y - 1].addNum();
        }
        if ((x - 1 >= 0) && (button[x - 1][y].getNum() >= 0)) {
            button[x - 1][y].addNum();
        }
        if ((x - 1 >= 0 && y + 1 < size) && (button[x - 1][y + 1].getNum() >= 0)) {
            button[x - 1][y + 1].addNum();
        }
        if ((y - 1 >= 0) && (button[x][y - 1].getNum() >= 0)) {
            button[x][y - 1].addNum();
        }
        if ((y + 1 < size) && (button[x][y + 1].getNum() >= 0)) {
            button[x][y + 1].addNum();
        }
        if ((x + 1 < size && y - 1 >= 0) && (button[x + 1][y - 1].getNum() >= 0)) {
            button[x + 1][y - 1].addNum();
        }
        if ((x + 1 < size) && (button[x + 1][y].getNum() >= 0)) {
            button[x + 1][y].addNum();
        }
        if ((x + 1 < size && y + 1 < size) && (button[x + 1][y + 1].getNum() >= 0)) {
            button[x + 1][y + 1].addNum();
        }
    }
}

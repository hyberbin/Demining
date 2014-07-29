/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jplus.model;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author hyberbin
 */
public class Button extends java.awt.Button implements MouseListener {

    private static Button[][] button;
    private int x;
    private int y;
    /** 是否是地雷 */
    private boolean isMines = false;
    private boolean isAlive = true;
    private int num = 0;
    private static int size;

    public Button(int x, int y) {
        this.x = x;
        this.y = y;
        this.addMouseListener(this);
    }

    public static void setButton(Button[][] button) {
        Button.button = button;
        size = button.length;
    }

    public boolean isMines() {
        return isMines;
    }

    public void setIsMines(boolean isMines) {
        this.isMines = isMines;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void addNum() {
        this.num++;
    }

    public int getNum() {
        return num;
    }

    public void showLabel() {
        if (!isAlive) {
            return;
        }
        isAlive = false;
        if (num == 0) {
            setBackground(Color.gray);
            if (x - 1 >= 0 && y - 1 >= 0) {
                button[x - 1][y - 1].showLabel();
            }
            if (x - 1 >= 0) {
                button[x - 1][y].showLabel();
            }
            if (x - 1 >= 0 && y + 1 < size) {
                button[x - 1][y + 1].showLabel();
            }
            if (y - 1 >= 0) {
                button[x][y - 1].showLabel();
            }
            if (y + 1 < size) {
                button[x][y + 1].showLabel();
            }
            if ((x + 1 < size && y - 1 >= 0)) {
                button[x + 1][y - 1].showLabel();
            }
            if (x + 1 < size) {
                button[x + 1][y].showLabel();
            }
            if ((x + 1 < size && y + 1 < size)) {
                button[x + 1][y + 1].showLabel();
            }
        } else if (num > 0) {
            setLabel(num + "");
        }
    }

    private void action() {
        if (isMines) {
            for (int i = 0; i < button.length; i++) {
                for (int j = 0; j < button[0].length; j++) {
                    if (button[i][j].isMines()) {
                        button[i][j].setBackground(Color.BLACK);
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "游戏结束");
            System.exit(0);
        } else  {
            showLabel();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!isAlive) {//每个雷只有点一次的机会点了就不能再点
            return;
        }
        System.out.println(num);
        action();
        System.out.println(e.getButton());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

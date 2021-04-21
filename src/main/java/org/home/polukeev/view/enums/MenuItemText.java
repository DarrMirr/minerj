package org.home.polukeev.view.enums;

import org.home.polukeev.CommonConstants;

import java.awt.event.KeyEvent;

/**
 * Created by onodee on 14.02.2016.
 * Перечисление пунктов меню
 */
public enum MenuItemText {
    MenuItemText01(CommonConstants.MENU_ITEM_NEW, KeyEvent.VK_N, 0),
    MenuItemText02(CommonConstants.MENU_ITEM_STAT, KeyEvent.VK_S, 0),
    MenuItemText03(CommonConstants.MENU_ITEM_OPTIONS, KeyEvent.VK_O, 0),
    MenuItemText04(CommonConstants.MENU_ITEM_EXIT, KeyEvent.VK_X, 0),
    MenuItemText05(CommonConstants.MENU_ITEM_ABOUT, KeyEvent.VK_A, 1);

    private String itemText;
    private int keyEvent;
    private int menuCount;

    MenuItemText(String itemText, int keyEvent, int menuCount){
        this.itemText = itemText;
        this.keyEvent = keyEvent;
        this.menuCount = menuCount;
    }

    public String getText(){
        return itemText;
    }

    public int getKeyEvent(){
        return keyEvent;
    }

    public int getMenuCount(){
        return menuCount;
    }
}

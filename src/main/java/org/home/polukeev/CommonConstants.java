package org.home.polukeev;

import java.awt.*;

/**
 * Created by onodee on 17.02.2016.
 * Класс констант
 */
public class CommonConstants {
    /**
     * Регулярные выражения для валидатора
     */
    public static final String REGEX_TEXT = "^.{0,255}$";
    public static final String REGEX_ROW_COL = "^[1-3]?9?|[12]\\d|30$";
    public static final String REGEX_MINES = "^[1]?[1-9]\\d?|[1-5]\\d\\d|[6][0][0]$";

    /**
     * Шрифты для компонентов
     */
    public static final Font FONT_17 = new Font("Sans Serif", Font.PLAIN, 17);
    public static final Font FONT_22 = new Font("Sans Serif", Font.PLAIN, 22);
    public static final Font FONT_32 = new Font("Sans Serif", Font.BOLD, 32);

    /**
     * String константы "О программе"
     */
    public static final String PROGRAM_AUTHOR = MinerJ.messages.getString("PROGRAM_AUTHOR");
    public static final String PROGRAM_DATE = "18.02.2016";
    public static final String PROGRAM_NAME = "MinerJ";

    /**
     * String констаны для JButton
     */
    public static final String BUTTON_OK = MinerJ.messages.getString("BUTTON_OK");
    public static final String BUTTON_CANCEL = MinerJ.messages.getString("BUTTON_CANCEL");
    public static final String BUTTON_PLAY_AGAIN = MinerJ.messages.getString("BUTTON_PLAY_AGAIN");
    public static final String BUTTON_EXIT = MinerJ.messages.getString("BUTTON_EXIT");
    public static final Dimension BUTTON_SIZE = new Dimension(100, 35);
    public static final Dimension BUTTON_SIZE_SUMMARY = new Dimension(130, 35);

    /**
     * String константы для заголовков Window
     */
    public static final String WINDOW_OPTION = MinerJ.messages.getString("WINDOW_OPTION");
    public static final String WINDOW_ABOUT = MinerJ.messages.getString("WINDOW_ABOUT");
    public static final String WINDOW_STAT = MinerJ.messages.getString("WINDOW_STAT");

    /**
     * String константы Summary
     */
    public static final String SUMMARY_WINNER = MinerJ.messages.getString("SUMMARY_WINNER");
    public static final String SUMMARY_LOSER = MinerJ.messages.getString("SUMMARY_LOSER");
    public static final String SUMMARY_TIME = MinerJ.messages.getString("SUMMARY_TIME");
    public static final String SUMMARY_SEC = MinerJ.messages.getString("SUMMARY_SEC");
    public static final String SUMMARY_DATE = MinerJ.messages.getString("SUMMARY_DATE");
    public static final String SUMMARY_MINE_COUNT = MinerJ.messages.getString("SUMMARY_MINE_COUNT");
    public static final String SUMMARY_DATE_FORMAT = "yyyy.MM.dd";

    /**
     * String константы MenuItemText
     */
    public static final String MENU_ITEM_NEW = MinerJ.messages.getString("MENU_ITEM_NEW");
    public static final String MENU_ITEM_STAT = MinerJ.messages.getString("MENU_ITEM_STAT");
    public static final String MENU_ITEM_OPTIONS = MinerJ.messages.getString("MENU_ITEM_OPTIONS");
    public static final String MENU_ITEM_EXIT = MinerJ.messages.getString("MENU_ITEM_EXIT");
    public static final String MENU_ITEM_ABOUT = WINDOW_ABOUT;

    /**
     * String константы MenuBar
     */
    public static final String MENU_BAR_FILE = MinerJ.messages.getString("MENU_BAR_FILE");
    public static final String MENU_BAR_HELP = MinerJ.messages.getString("MENU_BAR_HELP");

    /**
     * String константы OptionLabel
     */
    public static final String OPTION_OTHER = MinerJ.messages.getString("OPTION_OTHER");
    public static final String OPTION_WIDTH = MinerJ.messages.getString("OPTION_WIDTH");
    public static final String OPTION_HEIGHT = MinerJ.messages.getString("OPTION_HEIGHT");
    public static final String OPTION_MINES = MinerJ.messages.getString("OPTION_MINES");
    public static final String OPTION_TEXT_NEWBIE = MinerJ.messages.getString("OPTION_TEXT_NEWBIE");
    public static final String OPTION_TEXT_MIDDLE = MinerJ.messages.getString("OPTION_TEXT_MIDDLE");
    public static final String OPTION_TEXT_EXPERT = MinerJ.messages.getString("OPTION_TEXT_EXPERT");
    public static final String OPTION_TEXT_BORDER = MinerJ.messages.getString("OPTION_TEXT_BORDER");

    /**
     * String константы GAME_PANEL
     */
    public static final String GAME_PANEL_TIMER = MinerJ.messages.getString("GAME_PANEL_TIMER");
    public static final String GAME_PANEL_MINES = MinerJ.messages.getString("GAME_PANEL_MINES");

    /**
     *
     */
    public static final int STAT_TOP_COUNT = 10;
    public static final String STAT_TIME = MinerJ.messages.getString("STAT_TIME");
    public static final String STAT_DATE = MinerJ.messages.getString("STAT_DATE");
    public static final String STAT_NEWBIE = MinerJ.messages.getString("STAT_NEWBIE");
    public static final String STAT_MIDDLE = MinerJ.messages.getString("STAT_MIDDLE");
    public static final String STAT_EXPERT = MinerJ.messages.getString("STAT_EXPERT");

}

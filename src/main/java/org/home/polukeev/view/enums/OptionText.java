package org.home.polukeev.view.enums;

import org.home.polukeev.CommonConstants;

/**
 * Created by onodee on 13.02.2016.
 * Перечисление для OptionWindow TextArea
 */
public enum OptionText {
    OptionText01(CommonConstants.OPTION_TEXT_NEWBIE),
    OptionText02(CommonConstants.OPTION_TEXT_MIDDLE),
    OptionText03(CommonConstants.OPTION_TEXT_EXPERT);

    private String textGood;

    OptionText(String s){
        textGood = s;
    }

    public String getText(){
        return textGood;
    }
}

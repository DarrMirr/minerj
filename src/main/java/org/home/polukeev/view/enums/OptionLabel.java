package org.home.polukeev.view.enums;

import org.home.polukeev.CommonConstants;

/**
 * Created by onodee on 13.02.2016.
 * Перечисление для OptionWindow JLabel
 */
public enum OptionLabel {
    OptionLabel01(CommonConstants.OPTION_OTHER),
    OptionLabel02(CommonConstants.OPTION_WIDTH),
    OptionLabel03(CommonConstants.OPTION_HEIGHT),
    OptionLabel04(CommonConstants.OPTION_MINES);

    private String textLabel;

    OptionLabel(String s){
        textLabel = s;
    }

    public String getText(){
        return  textLabel;
    }
}

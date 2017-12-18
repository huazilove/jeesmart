package cn.jeesmart.common.utils;


import java.beans.PropertyEditorSupport;

/**
 * @author wjh
 */
public class DateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        setValue(DateHelper.parseDate(text));
    }

}

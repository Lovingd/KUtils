package cn.kutils.view.pickerview.model;

/**
 * ClassType:
 * User:wenyunzhao
 * ProjectName:LoveJob3
 * Package_Name:com.lovejob.model.bean
 * Created on 2016-11-28 20:17
 */

public class PriceBean implements IPickerViewData {
    private String type;

    public PriceBean(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getPickerViewText() {
        return type;
    }
}

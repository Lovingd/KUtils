package cn.kutils.sample.aty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.kutils.sample.R;
import cn.kutils.view.pickerview.OptionsPickerView;
import cn.kutils.view.pickerview.TimePickerView;
import cn.kutils.view.pickerview.model.PriceBean;

/**
 * 创建时间：2017/6/5  下午12:53
 * 创建人：赵文贇
 * 类描述：时间选择控件
 * 包名：cn.kutils.sample.aty
 * 待我代码编好，娶你为妻可好。
 */
public class PickerSampleAty extends AppCompatActivity {
    @Bind(R.id.bt_1)
    Button mBt1;
    @Bind(R.id.bt_2)
    Button mBt2;
    private ArrayList<PriceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private OptionsPickerView pvOptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickerview_sample);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_1, R.id.bt_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_1:
                TimePickerView timePickerView = new TimePickerView(this, TimePickerView.Type.ALL);
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                        Toast.makeText(getApplicationContext(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date), Toast.LENGTH_SHORT).show();
                    }
                });
                timePickerView.show();//四种选择模式，年月日时分，年月日，时分，月日时分  ALL, YEAR_MONTH_DAY, HOURS_MINS, MONTH_DAY_HOUR_MIN , YEAR_MONTH
                break;
            case R.id.bt_2:
                options1Items.add(new PriceBean("a"));
                options1Items.add(new PriceBean("b"));
                options1Items.add(new PriceBean("c"));

                ArrayList<String> a_1
                        = new ArrayList<>();
                a_1.add("a1");
                a_1.add("a2");
                a_1.add("a3");
                options2Items.add(a_1);
                ArrayList<String> b_1 = new ArrayList<>();
                b_1.add("b1");
                b_1.add("b2");
                b_1.add("b3");
                b_1.add("b4");
                options2Items.add(b_1);

                ArrayList<String> c_1 = new ArrayList<>();
                c_1.add("c1");
                c_1.add("c2");
                c_1.add("c3");
                c_1.add("c4");
                c_1.add("c5");
                c_1.add("c6");
                c_1.add("c7");
                options2Items.add(c_1);

                pvOptions = new OptionsPickerView(this);
                pvOptions.setPicker(options1Items, options2Items, true);
                pvOptions.setTitle("XXXX");
                pvOptions.setCyclic(false, false, false);
                //设置默认选中的三级项目
                //监听确定选择按钮
                pvOptions.setSelectOptions(0, 0, 0);
                pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3) {
                        Toast.makeText(getApplicationContext(), options1Items.get(options1).getPickerViewText(), Toast.LENGTH_SHORT).show();
                    }
                });
                pvOptions.show();
                break;
        }
    }
}

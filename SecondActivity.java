import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class SecondActivity extends Activity {
    private static final String TAG = "SecondActivity";

    private Intent mIntent;
    private Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        mIntent = getIntent();
        mBundle = mIntent.getExtras();

        // 记得判空
        if (mBundle == null) {
            return;
        }

        // 获取Bundle中的数据
        double height = mBundle.getDouble("height");
        String gender = mBundle.getString("gender");

        // 判断性别
        String genderText = "";
        if (gender.equals("M")) {
            genderText = "男性";
        } else {
            genderText = "女性";
        }

        // 获取标准体重
        String weight = getWeight(gender, height);

        // 设置需要显示的文字内容
        TextView textView = findViewById(R.id.textView2);
        textView.setText("你是一位" + genderText + "\n你的身高是" + height + "厘米\n你的标准体重是" + weight + "公斤");

        Button button = findViewById(R.id.buttonGoBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 设置结果，并关闭页面
                Log.i(TAG, "set RESULT_OK");
                setResult(RESULT_OK, mIntent);
                finish();
            }
        });
    }

    // 四舍五入格式化
    private String format(double num) {
        NumberFormat formatter = new DecimalFormat("0.00");
        return formatter.format(num);
    }

    // 计算标准体重的方法
    private String getWeight(String gender, double height) {
        String weight = "";
        if (gender.equals("M")) {
            weight = format((height - 80) * 0.7);
        } else {
            weight = format((height - 70) * 0.6);
        }
        return weight;
    }
}

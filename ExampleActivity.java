import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class ExampleActivity extends Activity {
    private static final String TAG = "ExampleActivity";

    private EditText mEditText;
    private RadioButton mRb1;
    private RadioButton mRb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_layout);

        Button button = findViewById(R.id.buttonGoToLayout2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText = findViewById(R.id.editText);
                // 获取输入的身高
                double height = Double.parseDouble(mEditText.getText().toString());

                // 获取性别
                String gender = "";
                mRb1 = findViewById(R.id.radioButtonMale);
                mRb2 = findViewById(R.id.radioButtonFemale);
                if (mRb1.isChecked()) {
                    gender = "M";
                } else {
                    gender = "F";
                }

                Intent intent = new Intent(ExampleActivity.this, SecondActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // 将数据传入第二个Activity
                Bundle bundle = new Bundle();
                bundle.putDouble("height", height);
                bundle.putString("gender", gender);
                intent.putExtras(bundle);

                startActivityForResult(intent, 0);
                // requestCode必须 >= 0，否则 onActivityResult不会被调用
                // 如果requestCode是个负数，那就等同于调用 startActivity
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult requestCode: " + requestCode + ", resultCode " + resultCode + ", data: " + data);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0) {
            Bundle bundle = data.getExtras();
            double height = bundle.getDouble("height");
            String gender = bundle.getString("gender");

            mEditText.setText("" + height);
            if (gender.equals("M")) {
                mRb1.setChecked(true);
            } else {
                mRb2.setChecked(true);
            }
        }
    }
}

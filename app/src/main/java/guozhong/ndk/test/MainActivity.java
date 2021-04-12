package guozhong.ndk.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.goodl.aes.FooTools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MainActivity extends AppCompatActivity {

    private EditText editContent;
    private Button jiami;
    private Button jiemi;
    private EditText resultText;
    private TextView changeFinalText;
    private FooTools fooTools;
    private Field finalTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fooTools = new FooTools();
        try {
            finalTextField = FooTools.class.getDeclaredField("finalText");
            finalTextField.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(finalTextField, finalTextField.getModifiers() & ~Modifier.FINAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        editContent = findViewById(R.id.editContent);
        jiami = findViewById(R.id.jiami);
        jiemi = findViewById(R.id.jiemi);
        resultText = findViewById(R.id.resultText);
        changeFinalText = findViewById(R.id.changeFinalText);
        jiami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editContent.getText().toString();
                String jiamiText = FooTools.method01(content);
                resultText.setText(jiamiText);
                setFinalTextFieldValue(jiamiText);
                updateChangeFinalTextView();
            }
        });
        jiemi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editContent.getText().toString();
                String jiemiText = FooTools.method02(content);
                resultText.setText(jiemiText);
                setFinalTextFieldValue(jiemiText);
                updateChangeFinalTextView();
            }
        });

    }


    /**
     * 测试反射是否能设置final字段的值
     * @param text
     */
    private void setFinalTextFieldValue(String text) {
        try {
            finalTextField.set(fooTools, text);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试反射是否能设置final字段的值
     */
    private void updateChangeFinalTextView() {
        try {
            String text = (String) finalTextField.get(fooTools);
            changeFinalText.setText(text);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
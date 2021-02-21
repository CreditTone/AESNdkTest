package guozhong.ndk.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.goodl.aes.FooTools;

public class MainActivity extends AppCompatActivity {

    private EditText editContent;
    private Button jiami;
    private Button jiemi;
    private EditText resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editContent = findViewById(R.id.editContent);
        jiami = findViewById(R.id.jiami);
        jiemi = findViewById(R.id.jiemi);
        resultText = findViewById(R.id.resultText);
        jiami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editContent.getText().toString();
                String jiamiText = FooTools.method01(content);
                resultText.setText(jiamiText);
            }
        });
        jiemi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editContent.getText().toString();
                resultText.setText(FooTools.method02(content));
            }
        });
    }

}
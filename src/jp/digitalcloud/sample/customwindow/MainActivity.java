package jp.digitalcloud.sample.customwindow;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private WindowManager windowManager = null;
    private View view = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText buttonShowToast = (EditText) findViewById(R.id.editTextShowWindow);
        buttonShowToast.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showWindow();
            }

        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (windowManager != null)
            windowManager.removeView(view);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void showWindow() {

        // get inflater and layout view
        LayoutInflater inflater = getLayoutInflater();
        view = inflater.inflate(R.layout.window_main, null);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | // タッチイベントを拾わない。ロック画面を邪魔しない。  
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // get TextView on layout
        Button button = (Button) view.findViewById(R.id.buttonWindowMessage);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.android.com/"));
                startActivity(intent);
                windowManager.removeView(view);
            }
        });

        windowManager.addView(view, layoutParams);
    }
}

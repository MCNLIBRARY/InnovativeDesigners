package trial.customlist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends Activity{
    public TextView appname;
    private Button parents;
    private Button kids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  if (Build.VERSION.SDK_INT < 16) {
           // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                 //   WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // }
        setContentView(R.layout.activity_home);

        appname = (TextView)findViewById(R.id.textView);
        parents = (Button)findViewById(R.id.button);
        kids = (Button)findViewById(R.id.button2);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/CookieMonster.ttf");
        appname.setTypeface(myCustomFont);
        appname.setPaintFlags(appname.getPaintFlags()| Paint.FAKE_BOLD_TEXT_FLAG);
        Typeface myCustomFont1 = Typeface.createFromAsset(getAssets(), "fonts/CookieMonster.ttf");
        parents.setTypeface(myCustomFont1);
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(), "fonts/CookieMonster.ttf");
        kids.setTypeface(myCustomFont2);

        parents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parents.setTextColor(Color.GREEN);
                parents.setBackgroundColor(Color.YELLOW);

                Intent i = new Intent(HomeActivity.this.getApplicationContext(), edit_activities.class);
                startActivity(i);
            }
        });

        kids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              kids.setTextColor(Color.GREEN);
              kids.setBackgroundColor(Color.YELLOW);
                Intent i = new Intent(HomeActivity.this.getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package trial.customlist;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;



import java.util.ArrayList;
import java.util.List;

public class MainActivity2Activity extends Activity

{
    private static final int spinner1 = 9000;
    public int imageId = 8000;
    public int imageId1;
    Button ab,b;
    Spinner sp;
    public static View v1,v3;
    ImageButton ib;
    public static final int REQUEST_CODE = 1;
    LinearLayout l2;
    Database db;
    picture_library p = new picture_library();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        // b=(Button)findViewById(R.id.button2);

        db=new Database(MainActivity2Activity.this);

        int planner=1;
        final ArrayList<Integer> rows2 = db.listPictures(planner);
        Integer pictures[] = rows2.toArray(new Integer[rows2.size()]);

        final ArrayList<String> rows = db.listActivities(planner);
        String activities[] = rows.toArray(new String[rows.size()]);


        final ArrayList<Integer> rows1 = db.listID(planner);
        Integer ID[] = rows1.toArray(new Integer[rows1.size()]);



        for(int i=0;i<rows.size();i++)

        {
            // db.addRowTest();
            //   Integer rec_id = db.recordId();
            Integer rec_id=ID[i];
            Integer image_id=p.photos[pictures[i]];

            LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout2);

            l2 = new LinearLayout(this);

            l2.setOrientation(LinearLayout.HORIZONTAL);
            // TableRow tr=(TableRow) findViewById(R.id.table_row);
            //add image View

            ib = new ImageButton(this);

            // p.library();
            //      ib.setBackgroundResource( p.photoidhsh.get("R.mipmap.a"));

            // ib.setBackgroundResource(p.photos[2]);
            ib.setBackgroundResource(image_id);
            ib.setMinimumWidth(55);
            ib.setTag(rec_id);
            // imageId=imageId+1;
            //   ib.setId(imageId);

            addListenerOnImageButtonSelection();
            l2.addView(ib);

//            // add text view
//            TextView tv = new TextView(this);
//            tv.setText("Activity");
//            l2.addView(tv);
            //add spinner
            sp = new Spinner(this);
            //  sp.setId(imageId);
            sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            List<String> list = new ArrayList<String>();
            list.add("Choose Activity");
            list.add("Do Homework");
            list.add("Brush your teeth");
            list.add("Have Breakfast");
            list.add("Clean Up");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(dataAdapter);
            sp.setTag(rec_id);

            String compareValue = activities[i];
            if (!compareValue.equals(null)) {
                int spinnerPosition = dataAdapter.getPosition(compareValue);
                sp.setSelection(spinnerPosition);
            }

            addListenerOnSpinnerItemSelection();
            l2.addView(sp);



            ll.addView(l2);

            if(i==(rows.size())-1)
            {
                //add Button
                ab=new Button(this);
                // ab.setText("Add More");
                ab.setBackgroundResource(R.mipmap.addicon);
                addListenerOnButtonSelection();
                l2.addView(ab);
            }


        }

        if((rows.size())==0)
        {
            addNewRow(v3);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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


    public void addNewRow(View v) {


        db.addRowTest();
        Integer rec_id = db.recordId();

        LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout2);

        l2 = new LinearLayout(this);

        l2.setOrientation(LinearLayout.HORIZONTAL);
        // TableRow tr=(TableRow) findViewById(R.id.table_row);
        //add image View

        ib = new ImageButton(this);
        picture_library p = new picture_library();
        p.library();
        //      ib.setBackgroundResource( p.photoidhsh.get("R.mipmap.a"));
        ib.setBackgroundResource(p.photos[2]);
        ib.setMinimumWidth(55);
        ib.setTag(rec_id);
        // imageId=imageId+1;
        //   ib.setId(imageId);

        addListenerOnImageButtonSelection();
        l2.addView(ib);

//        // add text view
//        TextView tv = new TextView(this);
//        tv.setText("Activity");
//        l2.addView(tv);

        //add spinner
        sp = new Spinner(this);
        //  sp.setId(imageId);
        sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        List<String> list = new ArrayList<String>();
        list.add("Brush your teeth");
        list.add("Have Breakfast");
        list.add("Clean Up");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(dataAdapter);
        sp.setTag(rec_id);
        addListenerOnSpinnerItemSelection();
        l2.addView(sp);

        //add Button
        ab=new Button(this);
        // ab.setText("Add More");
        ab.setBackgroundResource(R.mipmap.addicon);
        ab.setWidth(25);
        ab.setHeight(20);
        addListenerOnButtonSelection();
        l2.addView(ab);




        ll.addView(l2);
    }


    public void addListenerOnSpinnerItemSelection () {

        CustomOnItemSelectedListener ob=new CustomOnItemSelectedListener();


        sp.setOnItemSelectedListener(new CustomOnItemSelectedListener());


    }


    public void addListenerOnImageButtonSelection() {
        ib.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                v1=v;

                Intent i = new Intent(MainActivity2Activity.this.getApplicationContext(), image_library.class);

                MainActivity2Activity.this.startActivityForResult(i, REQUEST_CODE);
            }
        });
    }

    public void addListenerOnButtonSelection()

    {
        ab.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ab.setVisibility(View.GONE);
                addNewRow(v);

            }
        });
    }


    //  public static int mposition=0;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            try {

                picture_library piclib = new picture_library();
                int position= image_library.array_position;

                // if(mposition!=position) {
                //  mposition=position;
//                    if(v1.getTag()==null )
//                        v1.setTag(0);
//                    if(v1.getTag() !=position ) {

                v1.setBackgroundResource(piclib.photos[position]);

                int tagId=(Integer)v1.getTag();
                //  String new_image = Integer.toString(piclib.photos[position]);

                db.updateImage(position,tagId);
                db.viewImage(tagId);

            }catch(Exception e)
            {
                Log.e("error", e.toString());
            }

        }
    }

    public void navigate(View v)
    {
        try {

            Intent i = new Intent(MainActivity2Activity.this.getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        catch (Exception e)
        {
            Log.e("error", e.toString());
        }
    }



//public void onClick(View v) {
//    Toast toast;
//    Log.w("ANDROID DYNAMIC VIEWS:", "View Id: " + v.getId());
//
//            toast = Toast.makeText(this, "Clicked on my dynamically added button!", Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.TOP, 25, 400);
//            toast.show();
//          //  saveAnswers();
//
//        // More buttons go here (if any) ...
//
//    }
//    public void saveAnswers() {
//        LinearLayout root = (LinearLayout) findViewById(R.id.linearLayout1); //or whatever your root control is
//        loopQuestions(root);
//    }
//
//    private void loopQuestions(ViewGroup parent) {
//
//        for (int i = 0; i < parent.getChildCount(); i++) {
//            View child = parent.getChildAt(i);
//
//            if (child instanceof EditText) {
//                //Support for EditText
//                EditText et = (EditText) child;
//                Log.w("ANDROID DYNAMIC VIEWS:", "EdiText: " + et.getText());
//              // storeAnswer(cb.getId(), answer);
//            }
//            else if (child instanceof Spinner)
//            {
//                Spinner sp= (Spinner) child;
//               //
//
//            }
//
//            if(child instanceof ViewGroup) {
//                //Nested Q&A
//                ViewGroup group = (ViewGroup)child;
//                loopQuestions(group);
//            }
//        }
//    }
//    private void storeAnswer(int question, int answer) {
//        Log.w("ANDROID DYNAMIC VIEWS:", "Question: " + String.valueOf(question) + " * "+ "Answer: " + String.valueOf(answer) );
//
//        Toast toast = Toast.makeText(this, String.valueOf(question) + " * "+ "Answer: " + String.valueOf(answer), Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.TOP, 25, 400);
//        toast.show();
//
//
//    }


}
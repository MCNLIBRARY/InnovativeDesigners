package trial.customlist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class edit_activities extends ActionBarActivity {

    public static final int REQUEST_CODE = 1;
    public static View v1;
    Integer rec_id;
    public static Integer position_flag ;
    public static Integer picture_flag ;
    Database db;
    public static Button save;
    public static Integer plannerImage=5;
    Button b, navigate;
    LinearLayout ll,l2;
    ImageButton ib,ibTitle;
    int tagIdimage;
    public static int position;
    picture_library p = new picture_library();
    public static List<String> list,listTitle;

    public static int globalPlannerValue;

    public ArrayAdapter<String> dataAdapter,dataAdapterTitle;

    public Spinner sp,spTitle;
    ArrayList<Integer> rows2;
    public static int flag=0;
    ArrayList<String> rows;
   // int position_flag;
    ScrollView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activities);


        save=(Button)findViewById(R.id.saveButton);

        sv=(ScrollView)findViewById(R.id.scrollView1);

        navigate = (Button) findViewById(R.id.button2);
        db = new Database(edit_activities.this);

        Intent i=getIntent();
        globalPlannerValue=i.getIntExtra("PLANNER_ID",0);
       // globalPlannerValue=6;

        rows2 = db.listPictures(globalPlannerValue);
        rows = db.listActivities(globalPlannerValue);


        if ((rows.size())>0)
            fetchData();
        // db.addRowTest();

        b = (Button) findViewById(R.id.button);

         ib = (ImageButton) findViewById(R.id.imageButton);
//
        flag=0;
        picture_flag=0;
        addListenerOnImageButtonSelection(picture_flag);

        sp = (Spinner) findViewById(R.id.spinner3);
                                   sp.setTag(1000);

        list = new ArrayList<String>();

        //ENABLE TO ADD HARDCODE ACTIVITY SPINNER VALUES
      //db.spinnerArray();
        if (db.spinnerValues().size()==0)
        {
            db.spinnerArray();

        }
        list=db.spinnerValues();
        //Collections.sort(list);
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(dataAdapter);

        addListenerOnSpinnerItemSelection();

        //  sp.setTag(rec_id);

        //TITLE UI

      //ENABLE TO ADD HARDCODE TITLE SPINNER VALUES
       //db.plannerTitles();

        if (db.TitleValues().size()==0)
        {
            db.plannerTitles();
        }

        ibTitle = (ImageButton) findViewById(R.id.imageButton5);
        ibTitle.setTag(2001);
        addListenerOnImageButtonSelection1(picture_flag);

        spTitle= (Spinner) findViewById(R.id.spinner5);
        spTitle.setTag(1001);
        spTitle.setPrompt("Choose Planner Title");
        listTitle = new ArrayList<String>();

        listTitle=db.TitleValues();
      //  Collections.sort(listTitle);
      //  listTitle.add("TESTING");
        dataAdapterTitle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listTitle);
        dataAdapterTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTitle.setAdapter(dataAdapterTitle);
        spTitle.setSelection(globalPlannerValue-1);


        flag=1;
        addListenerOnSpinnerItemSelection();


    }

public void refreshspinner()
{

//    for (int i=0 ;i<=l2.getChildCount()-1;i++)
//    {
//       // l2.get
//
//    }

    List<String> list1=db.spinnerValues();
    Collections.sort(list1);
    dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list1);
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    sp.setAdapter(dataAdapter);

    addListenerOnSpinnerItemSelection();

    CustomOnItemSelectedListener cs = new CustomOnItemSelectedListener();
    String compareValue =cs.newValue1;
    if (!compareValue.equals(null)) {
        int spinnerPosition = dataAdapter.getPosition(compareValue);
        sp.setSelection(spinnerPosition);
    }

}
    public void refreshPlanner()
    {

//    for (int i=0 ;i<=l2.getChildCount()-1;i++)
//    {
//       // l2.get
//
//    }

        List<String> listTitle1=db.TitleValues();
        Collections.sort(listTitle1);
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listTitle1);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTitle.setAdapter(dataAdapter);

        addListenerOnSpinnerItemSelection();

        CustomOnItemSelectedListener cs = new CustomOnItemSelectedListener();
        String compareValue =cs.newValue;
        if (!compareValue.equals(null)) {
            int spinnerPosition = dataAdapter.getPosition(compareValue);
            spTitle.setSelection(spinnerPosition);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_activities, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        int j=1;
if(j==1){
        //noinspection SimplifiableIfStatement
        Intent i = new Intent(edit_activities.this.getApplicationContext(), HomeActivity.class);
        startActivity(i);
        return true;


    }
        return super.onOptionsItemSelected(item);
    }
  int ImageId=0;


    public void addNewRow(View v) {


        db.addRowTest();
        rec_id = db.recordId();

        sp.setTag(rec_id);
        int tagId = (Integer) sp.getTag();
        CustomOnItemSelectedListener ob = new CustomOnItemSelectedListener();
        db.updateSpinner(ob.result, tagId);

        db.updatePlannerTitle(ob.planner_id,tagId);
       // db.viewActivity(ob.tagId);

//// write only here
        ib.setTag(rec_id);
        tagIdimage = (Integer) ib.getTag();

        db.updateImage( ImageId, tagIdimage);

       
     fetchData1();

             }

    public void addListenerOnImageButtonSelection(final int flag) {
        ib.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                picture_flag = flag;
                v1 = v;

                Intent i = new Intent(edit_activities.this.getApplicationContext(), image_library.class);

                edit_activities.this.startActivityForResult(i, REQUEST_CODE);
            }
        });
    }

    public void addListenerOnImageButtonSelection1(final int flag) {
        ibTitle.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                picture_flag = flag;
                v1 = v;

                Intent i = new Intent(edit_activities.this.getApplicationContext(), image_library.class);

                edit_activities.this.startActivityForResult(i, REQUEST_CODE);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            try {

                picture_library piclib = new picture_library();
                position = image_library.array_position;


                v1.setBackgroundResource(piclib.photos[position]);

                v1.setLabelFor(position);


                 if(picture_flag==1)
                 {
                    int tagId = (Integer) v1.getTag();
                    //  String new_image = Integer.toString(piclib.photos[position]);

                    db.updateImage(position, tagId);
                    db.viewImage(tagId);
              }
                else
                     ImageId=position;

                if(v1.getTag()==2001)
                {
                    plannerImage=position;
                }

            } catch (Exception e) {
                Log.e("error", e.toString());
            }

        }
    }

    public void addListenerOnSpinnerItemSelection()
    {

        CustomOnItemSelectedListener cs = new CustomOnItemSelectedListener();
        cs.list1 =list;
        cs.obj=this;
        sp.setOnItemSelectedListener(cs);

            if (flag == 1)
            spTitle.setOnItemSelectedListener(cs);

    }

    public void addListenerOnSpinnerItemSelection1()
    {

        sp.setOnItemSelectedListener(new CustomOnItemSelectedListener1());

    }


    public void fetchData1() {

        rows2 = db.recordIdlAST();
        Integer lastRecord[] = rows2.toArray(new Integer[rows2.size()]);
        Integer ID=lastRecord[0];
        Integer pic_src=lastRecord[1];

        rows = db.recordIdlASTACTIVITY();
        String activities[] = rows.toArray(new String[rows.size()]);
        String act=activities[0];

            Integer rec_id = ID;
            Integer image_id = p.photos[pic_src];

            ll = (LinearLayout) findViewById(R.id.linearLayout2);

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
            picture_flag=1;
            addListenerOnImageButtonSelection(picture_flag);
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
        list=db.spinnerValues();

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(dataAdapter);
            sp.setTag(rec_id);

            String compareValue = act;
            if (!compareValue.equals(null)) {
                int spinnerPosition = dataAdapter.getPosition(compareValue);
                sp.setSelection(spinnerPosition);
            }

            addListenerOnSpinnerItemSelection1();

            l2.addView(sp);

            ll.addView(l2);

       sv.post(new Runnable() {
            @Override
            public void run() {
                sv.fullScroll(View.FOCUS_DOWN);
            }
        });
         //  i=-1;
    //    }

    }
    HashMap<String , Object> temp ;

    public void fetchData(        ) {


       rows2 = db.listPictures(globalPlannerValue);
        Integer pictures[] = rows2.toArray(new Integer[rows2.size()]);

       rows = db.listActivities(globalPlannerValue);
        String activities[] = rows.toArray(new String[rows.size()]);


        final ArrayList<Integer> rows1 = db.listID(globalPlannerValue);
        Integer ID[] = rows1.toArray(new Integer[rows1.size()]);

        temp=   new HashMap<String, Object>();
        for (int i=0 ;i<= rows.size()-1 ; i++)
        {
            // db.addRowTest();
            //   Integer rec_id = db.recordId();
            Integer rec_id = ID[i];
            Integer image_id = p.photos[pictures[i]];

            ll = (LinearLayout) findViewById(R.id.linearLayout2);

            l2 = new LinearLayout(this);

            l2.setOrientation(LinearLayout.HORIZONTAL);
            // TableRow tr=(TableRow) findViewById(R.id.table_row);
            //add image View

            ib = new ImageButton(this);

            ib.setBackgroundResource(image_id);
            ib.setMinimumWidth(55);
            ib.setTag(rec_id);
            // imageId=imageId+1;
            //   ib.setId(imageId);
           picture_flag=1;
           addListenerOnImageButtonSelection(picture_flag);
            l2.addView(ib);
            //add spinner
            sp = new Spinner(this);
            //  sp.setId(imageId);
            sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            List<String> list = new ArrayList<String>();
            list=db.spinnerValues();
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

            addListenerOnSpinnerItemSelection1();

            l2.addView(sp);


            ll.addView(l2);


        }


    }


    public void savePlannerTitle(View v)
    {
        try {
            CustomOnItemSelectedListener ob = new CustomOnItemSelectedListener();
            db.changePlanner(globalPlannerValue, ob.planner_id);
            db.savePlannerImage(plannerImage,ob.planner_id);
            List<Integer> list5 = new ArrayList<Integer>();
            list5=db.kidsProfiles(); // to retrive kids' Id
            Integer kidsId[]= list5.toArray(new Integer[list5.size()]);
            for(int j=0;j<list5.size();j++)
            {
                db.kidsPlanner(kidsId[j], ob.planner_id);
            }
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
    }
    public void changeButtonColor()
    {
        try {
            save.setBackgroundColor(Color.GREEN);
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
    }
}

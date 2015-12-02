package trial.customlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class CustomOnItemSelectedListener implements OnItemSelectedListener {
public static String result;
    public static Integer planner_id;
    public static int tagId;
    int rec_id;
    Database db;
    edit_activities obj;
    public List<String> list1;
    public static String newValue , newValue1;
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    //    Toast.makeText(parent.getContext(), "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
      db=new Database(parent.getContext());

        result = parent.getItemAtPosition(pos).toString();


        if ((Integer) parent.getTag() == 1001) {
            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            ((TextView) parent.getChildAt(0)).setTextSize(21);
             planner_id = db.PlannerID(parent.getItemAtPosition(pos).toString());
             obj.changeButtonColor();
        }

        if ( (pos == 1) && (Integer) parent.getTag() == 1001)

        {
            // Set an EditText view to get user input
            final EditText input = new EditText(parent.getContext());

            new AlertDialog.Builder(parent.getContext())
                    .setMessage("Create New Title")
                    .setView(input)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Editable editable = input.getText();


                            try {
                                db.addTitleValue(editable.toString());
                                newValue=editable.toString();

                                // obj.sp.postInvalidate();
                                //  obj.sp.setAdapter(obj.dataAdapter);
                                obj.refreshPlanner();

                            } catch (Exception e) {
                                Log.e("", e.toString());
                            }

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Do nothing.
                        }
                    }).show();
        }


//Spinner Values Addition

        if (pos == 1 && (Integer)parent.getTag() == 1000)
        {
            // Set an EditText view to get user input
            final EditText input = new EditText(parent.getContext());

            new AlertDialog.Builder(parent.getContext())
                    .setMessage("Create New Activity")
                    .setView(input)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Editable editable = input.getText();
                            newValue1=editable.toString();

                            try {
                                db.addSpinnerValue(editable.toString());


                                // obj.sp.postInvalidate();
                                //  obj.sp.setAdapter(obj.dataAdapter);
                                obj.refreshspinner();

                            } catch (Exception e) {
                                Log.e("", e.toString());
                            }

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Do nothing.
                        }
                    }).show();
        }


    }

    //Retrieving Planner ID from Database baseed on chosen title




    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}

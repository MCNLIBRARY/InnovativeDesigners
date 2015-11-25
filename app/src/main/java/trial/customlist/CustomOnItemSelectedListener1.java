package trial.customlist;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class CustomOnItemSelectedListener1 implements OnItemSelectedListener {
    public static String result;
    public static int tagId;
    int rec_id;

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        //    Toast.makeText(parent.getContext(), "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
        Database db=new Database(parent.getContext());
        result = parent.getItemAtPosition(pos).toString();


        edit_activities ob = new edit_activities();


//        {
//        tagId=(Integer)parent.getTag();
//        }
//      //  MainActivity2Activity ob = new MainActivity2Activity();



//
            tagId=(Integer)parent.getTag();
            db.updateSpinner(result, tagId);
//           // db.viewActivity(tagId);
//
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}

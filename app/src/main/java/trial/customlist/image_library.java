package trial.customlist;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;



public class image_library extends ListActivity {

    public static final int REQUEST_CODE = 1;
    LayoutInflater inflater;
    ArrayList<HashMap<String, Object>> players;
    picture_library piclib = new picture_library();
    HashMap<Integer, ImageView> viewholderarr=new HashMap<Integer, ImageView>();
    ImageView selectedimage;
    Button add_button;
    public static int array_position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_library);

        inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        players=new ArrayList<HashMap<String,Object>>();
        HashMap<String , Object> temp ;

        for(int i=0;i<piclib.photos.length;i++)
        {
            temp = new HashMap<String, Object>();
            temp.put("photo",piclib.photos[i]);
            players.add(temp);

        }
        //players.add(temp);
        final CustomAdapter adapter=new CustomAdapter(this, R.layout.image_library_customlist,players);
//
//         setListAdapter(adapter);

        GridView gridview = (GridView) findViewById(R.id.gridView);
       gridview.setAdapter(adapter);
//        final CustomAdapter gridViewAdapter = new CustomAdapter(this,R.layout.image_library_customlist,players);
//        setListAdapter(gridViewAdapter);


        }


    private class CustomAdapter extends ArrayAdapter<HashMap<String, Object>> {
            // boolean array for storing
            //the state of each CheckBox
            boolean[] checkBoxState;

            ViewHolder holder;

            public CustomAdapter(Context context, int textViewResourceId,
                                 ArrayList<HashMap<String, Object>> players) {

                //let android do the initializing :)
                super(context, textViewResourceId, players);

                //create the boolean array with
                //initial state as false
                checkBoxState = new boolean[players.size()];
            }


        private class ViewHolder {
            ImageView photo;
            CheckBox checkBox;
            int position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.image_library_customlist, null);
                holder = new ViewHolder();

                //cache the views
                holder.photo = (ImageView) convertView.findViewById(R.id.imageView1);
                holder.checkBox=(CheckBox) convertView.findViewById(R.id.checkBox2);
                holder.position = position;

                //link the cached views to the convertview
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();

            }

            int photoId = (Integer) players.get(position).get("photo");
            holder.photo.setImageDrawable(getResources().getDrawable(photoId));
            holder.position = position;
            viewholderarr.put(position,holder.photo);

            holder.checkBox.setChecked(checkBoxState[position]);
            holder.checkBox.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    if(((CheckBox)v).isChecked())
                    {
                        checkBoxState[position] = true;
                        selectedimage = viewholderarr.get(position);
                        array_position = position;
                        Intent i = image_library.this.getIntent();
                        image_library.this.setResult(RESULT_OK, i);
                        finish();
                    }


                    else {
                        checkBoxState[position] = false;
                        Toast.makeText(getApplicationContext(), "Lets' do it ", Toast.LENGTH_SHORT).show();

                    }
                }
            });

            //return the view to be displayed
            return convertView;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checklist_create, menu);
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

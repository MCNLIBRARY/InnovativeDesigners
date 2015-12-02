package trial.customlist;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    picture_library piclib=new picture_library();
    Animation animation = null;
    ImageView disney,disney1;
    Integer kidID=1;
    public static int plannerValue;

    MediaPlayer reward1;

    ArrayList<HashMap<String, Object>> players;
    HashMap<Integer, ImageView> viewholderarr=new HashMap<Integer, ImageView>();
    HashMap<Integer, TextView> viewholderarr1=new HashMap<Integer, TextView>();
    LayoutInflater inflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         reward1 = MediaPlayer.create(this,R.raw.kids);
        setContentView(R.layout.activity_main);
        ImageView plannerImage=(ImageView)findViewById(R.id.image2);

        inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        Intent intent=getIntent();
        plannerValue=intent.getIntExtra("PLANNER_ID",0);

         Database dblist = new Database(MainActivity.this);
      // dblist.addRow();


        final ArrayList<String> rows = dblist.listActivities(plannerValue);
        String names[] = rows.toArray(new String[rows.size()]);

//        String teams[]={"Real Madrid","Barcelona","Chelsea",
//                "Barcelona","Chelsea","Liverpool",
//                "ManU","Barcelona"};

//
       final ArrayList<Integer> imagesid1 = dblist.listPictures(plannerValue);
        Integer[] position=imagesid1.toArray(new Integer[imagesid1.size()]);

    picture_library obj = new picture_library();
    final ArrayList<Integer>imagesid=new ArrayList<Integer>();

        int j= dblist.plannerImage(plannerValue);
        plannerImage.setBackgroundResource(obj.photos[j]);

    for (int i = 0; i < imagesid1.size(); i++) {
        imagesid.add(obj.photos[position[i]]);
    }

        Integer[] photo1={R.mipmap.ic_launcher,R.mipmap.b,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher};
//        final ArrayList<Integer> picture_list = dblist.listPictures();
//         Integer[] position=picture_list.toArray(new Integer[picture_list.size()]);
//
//    picture_library obj = new picture_library();
//    Integer[] photo1 = new Integer[picture_list.size()];
//        try {
//    for (int i = 0; i < picture_list.size(); i++) {
//        photo1[i] = obj.photos[position[i]];
//    }
//}
//    catch(Exception e)
//    {
//        Log.e("error", e.toString());
//    }
        players=new ArrayList<HashMap<String,Object>>();

        //temporary HashMap for populating the
        //Items in the ListView
        HashMap<String , Object> temp;

        //total number of rows in the ListView
        int noOfPlayers=names.length;

        //now populate the ArrayList players
        for(int i=0;i<noOfPlayers;i++)
        {
            temp=new HashMap<String, Object>();

            temp.put("name", names[i]);
          //  temp.put("team", teams[i]);
          //  temp.put("photo",photos[i]);
          //  if(piclib.photoidhsh.containsKey(imagesid.get(i)))

               temp.put("photo",imagesid.get(i));
         //  else
          //     temp.put("photo",piclib.photos[1]);
           temp.put("photo1", photo1[1]);
            //add the row to the ArrayList
            players.add(temp);
        }

        final CustomAdapter adapter=new CustomAdapter(this, R.layout.customlist,players);

        setListAdapter(adapter);
    }


    //define your custom adapter
    private class CustomAdapter extends ArrayAdapter<HashMap<String, Object>>
    {
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
            checkBoxState=new boolean[players.size()];
        }

        //class for caching the views in a row
        private class ViewHolder
        {
            ImageView photo;
            TextView name;
          //  TextView team;
            CheckBox checkBox;
            ImageView photo1;
            int position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if(convertView==null)
            {
                convertView=inflater.inflate(R.layout.customlist,null);
                holder=new ViewHolder();

                //cache the views
                holder.photo=(ImageView) convertView.findViewById(R.id.photo);
                holder.name=(TextView) convertView.findViewById(R.id.name);
              //  holder.team=(TextView) convertView.findViewById(R.id.team);
                holder.checkBox=(CheckBox) convertView.findViewById(R.id.checkBox);
                holder.photo1=(ImageView) convertView.findViewById(R.id.photo1);
                holder.position = position;

                //link the cached views to the convertview
                convertView.setTag( holder);

            }
            else {
                holder = (ViewHolder) convertView.getTag();

            }
                    int photoId = (Integer) players.get(position).get("photo");
                    int photoId1 = (Integer) players.get(position).get("photo1");
                    //set the data to be displayed
                    holder.photo.setImageDrawable(getResources().getDrawable(photoId));
                    holder.name.setText(players.get(position).get("name").toString());

                     Database db = new Database(MainActivity.this);
                     String activity_name=players.get(position).get("name").toString();
                     Boolean status= db.checkStatus(activity_name,kidID);

                    //holder.team.setText(players.get(position).get("team").toString());
                    holder.photo1.setImageDrawable(getResources().getDrawable(photoId1));
                    holder.position = position;
                     viewholderarr.put(position,holder.photo1);
                     viewholderarr1.put(position,holder.name);

                //VITAL PART!!! Set the state of the
                //CheckBox using the boolean array

            holder.checkBox.setChecked(checkBoxState[position]);

            // for Database
           holder.checkBox.setChecked(status);

            //for managing the state of the boolean
            //array according to the state of the
            //CheckBox

            holder.checkBox.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    if(((CheckBox)v).isChecked())
                    {
                        checkBoxState[position] = true;
                     //   Toast.makeText(getApplicationContext(),"Wow...You did it..Good Job", Toast.LENGTH_LONG).show();

                       reward1.start();

                        reward((ImageView) viewholderarr.get(position));

                        Integer kidID=1;

                    //    String activity=((TextView) viewholderarr1.get(position).getText()).toString();
                        String activity=(viewholderarr1.get(position).getText()).toString();
                        Database db = new Database(MainActivity.this);
                        db.setStatus(activity,kidID);

                    }
                    else {
                        checkBoxState[position] = false;
                      //  Toast.makeText(getApplicationContext(), "Lets' do it ", Toast.LENGTH_SHORT).show();
                        again( (ImageView) viewholderarr.get(position));
                    }
                }
            });

            //return the view to be displayed
            return convertView;
        }

    }
    public void reward( ImageView photoId)
    {
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_in);
        disney=photoId;
        disney.startAnimation(animation);
    }
    public void again(ImageView photoId)
    {
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_out);
        disney=photoId;
        disney.startAnimation(animation);
    }
}
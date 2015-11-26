package trial.customlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class Database {


    public static final String DB_NAME = "DATABASE";
    public static final int DB_VERSION =6;

    private SQLHelper helper;
    private SQLiteDatabase db;
    private Context context;
    ArrayList<String> productRows;
    ArrayList<Integer> productRows2;
    public static final String DB_TABLE = "CHORES_CHECKLIST_ITEMS";
    public static final String DB_TABLE1 = "CHECKLIST_ITEMS";
    public static final String DB_TABLE2 = "SPINNER_ITEMS";
    public static final String DB_TABLE3 = "PLANNER";

    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (ACTIVITY_NAME String, PICTURE_ID String);";
    private static final String CREATE_TABLE1 = "CREATE TABLE " + DB_TABLE1 + " (ID INTEGER PRIMARY KEY, ACTIVITY_NAME String, PICTURE_SOURCE Integer, PLANNER_ID Integer);";
    private static final String CREATE_TABLE2 = "CREATE TABLE " + DB_TABLE2 + " (ID INTEGER PRIMARY KEY, SPINNER_VALUE String);";
    private static final String CREATE_TABLE3 = "CREATE TABLE " + DB_TABLE3 + " (ID INTEGER PRIMARY KEY,PLANNER_TITLE String);";
    public Database(Context c)
    {
        this.context = c;
        helper = new SQLHelper(c);
        this.db = helper.getWritableDatabase();
    }
    public Database openReadable() throws android.database.SQLException
    {
        helper = new SQLHelper(context);
        db = helper.getReadableDatabase();
        return this;
    }
    public void close() {
        helper.close();
    }

    public void addRow()
    {
        ContentValues newProduct = new ContentValues();
        ContentValues newProduct1 = new ContentValues();
        ContentValues newProduct2 = new ContentValues();
        ContentValues newProduct3 = new ContentValues();
        ContentValues newProduct4 = new ContentValues();
        ContentValues newProduct5 = new ContentValues();
        ContentValues newProduct6 = new ContentValues();
        ContentValues newProduct7 = new ContentValues();

        newProduct.put("ACTIVITY_NAME","Choose Activity" );
        newProduct.put("PICTURE_ID","R.mipmap.a" );
        newProduct1.put("ACTIVITY_NAME","Home Work" );
        newProduct1.put("PICTURE_ID","R.mipmap.b" );
        newProduct2.put("ACTIVITY_NAME","Exercise" );
        newProduct2.put("PICTURE_ID","R.mipmap.c" );
        newProduct3.put("ACTIVITY_NAME","Dinner" );
        newProduct3.put("PICTURE_ID","R.mipmap.d" );
        newProduct4.put("ACTIVITY_NAME","Read a Book" );
        newProduct4.put("PICTURE_ID","R.mipmap.e" );
        newProduct5.put("ACTIVITY_NAME","Clean up play area" );
        newProduct5.put("PICTURE_ID","R.mipmap.f" );
        newProduct6.put("ACTIVITY_NAME","Setting the Table" );
        newProduct6.put("PICTURE_ID","R.mipmap.g" );
        newProduct7.put("ACTIVITY_NAME","Watch Cartoon" );
        newProduct7.put("PICTURE_ID","R.mipmap.h" );

        try {
            db.insertOrThrow(DB_TABLE, null, newProduct);
            db.insertOrThrow(DB_TABLE, null, newProduct1);
            db.insertOrThrow(DB_TABLE, null, newProduct2);
            db.insertOrThrow(DB_TABLE, null, newProduct3);
            db.insertOrThrow(DB_TABLE, null, newProduct4);
            db.insertOrThrow(DB_TABLE, null, newProduct5);
            db.insertOrThrow(DB_TABLE, null, newProduct6);
            db.insertOrThrow(DB_TABLE, null, newProduct7);

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        db.close();


    }

    public ArrayList<String> listActivities(int chosenPlanner)
    {
        try {
            productRows = new ArrayList<String>();


          //  String[] columns1 = new String[]{ "ACTIVITY_NAME"};
          //  String selectQuery = "SELECT ACTIVITY_NAME FROM " + DB_TABLE1 + " WHERE PLANNER_ID= '"+rec+"'";
            String selectQuery = "SELECT ACTIVITY_NAME FROM " + DB_TABLE1 + " WHERE PLANNER_ID= '"+chosenPlanner+"'";

            if(db.isOpen()==false) {
                openReadable();
            }
           // Cursor cursor1 = db.query(DB_TABLE1, columns1, null, null, null, null, null);
            Cursor cursor1 = db.rawQuery(selectQuery, null);
            cursor1.moveToFirst();
            while (cursor1.isAfterLast() == false) {
                productRows.add(cursor1.getString(0));
                cursor1.moveToNext();
            }

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        return productRows;
    }
    public ArrayList<Integer> listPictures(int chosenPlanner)
    {
        try {


            productRows2 = new ArrayList<Integer>();
         //   String[] columns1 = new String[]{ "PICTURE_SOURCE"};
            String selectQuery = "SELECT PICTURE_SOURCE FROM " + DB_TABLE1 + " WHERE PLANNER_ID= '"+chosenPlanner+"'";

            if(db.isOpen()==false) {
                openReadable();
            }
         //   Cursor cursor1 = db.query(DB_TABLE1, columns1, null, null, null, null, null);
            Cursor cursor1 = db.rawQuery(selectQuery, null);
            cursor1.moveToFirst();
            while (cursor1.isAfterLast() == false) {
                productRows2.add(cursor1.getInt(0));
                cursor1.moveToNext();
            }

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        return productRows2;
    }

    public ArrayList<Integer>  recordIdlAST()
    {
        Integer ID=0;

        Integer pic_src=1;
        try {
            productRows2 = new ArrayList<Integer>();
            String[] columns1 = new String[]{ "ID","PICTURE_SOURCE"};

            if(db.isOpen()==false) {
                openReadable();
            }
            Cursor cursor1 = db.query(DB_TABLE1, columns1, null, null, null, null, "ID");
            cursor1.moveToFirst();
            while (cursor1.isAfterLast() == false) {

                ID = cursor1.getInt(0);
                pic_src=cursor1.getInt(1);
                cursor1.moveToNext();

            }
            productRows2.add(ID);
            productRows2.add(pic_src);

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        return productRows2;
    }

    public ArrayList<String>  recordIdlASTACTIVITY()
    {
        String activity="";

        try {
            productRows = new ArrayList<String>();
            String[] columns1 = new String[]{ "ACTIVITY_NAME"};
            if(db.isOpen()==false) {
                openReadable();
            }
            Cursor cursor1 = db.query(DB_TABLE1, columns1, null, null, null, null, "ID");
            cursor1.moveToFirst();
            while (cursor1.isAfterLast() == false) {

               activity = cursor1.getString(0);

                cursor1.moveToNext();

            }
            productRows.add(activity);


        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        return productRows;
    }

    public ArrayList<Integer> listID(int chosenPlanner) {
        try {
            productRows2 = new ArrayList<Integer>();
          //  String[] columns1 = new String[]{"ID"};
            String selectQuery = "SELECT ID FROM " + DB_TABLE1 + " WHERE PLANNER_ID= '"+chosenPlanner+"'";
            if (db.isOpen() == false) {
                openReadable();
            }
          //  Cursor cursor1 = db.query(DB_TABLE1, columns1, null, null, null, null, null);
            Cursor cursor1 = db.rawQuery(selectQuery, null);
            cursor1.moveToFirst();
            while (cursor1.isAfterLast() == false) {
                productRows2.add(cursor1.getInt(0));
                cursor1.moveToNext();
            }

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        return productRows2;

    }
    public Integer recordId()
    {
        Integer ID=0;
        try {
            productRows2 = new ArrayList<Integer>();
            String[] columns1 = new String[]{ "ID"};
            if(db.isOpen()==false) {
                openReadable();
            }
            Cursor cursor1 = db.query(DB_TABLE1, columns1, null, null, null, null, null);
            cursor1.moveToFirst();
            while (cursor1.isAfterLast() == false) {
                productRows2.add(cursor1.getInt(0));
                ID = cursor1.getInt(0);
                cursor1.moveToNext();

            }

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        return ID;
    }

    public void addRowTest()
    {
       ContentValues newProduct = new ContentValues();

               newProduct.put("ACTIVITY_NAME", "Choose Activity");
               newProduct.put("PICTURE_SOURCE",2);
               newProduct.put("PLANNER_ID",1);

        try {
            if(db.isOpen()==false) {
              openReadable();
            }
             db.insertOrThrow(DB_TABLE1, null, newProduct);

           // db.execSQL("UPDATE " + DB_TABLE + " SET renew= '" + ren + "',recall= '"+rec+ "' , availability = '" + avl + "' ,event= '" + evt + "'WHERE user_id ='" + user+ "'");
        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        db.close();

    }

    public void updateSpinner(String new_activity,Integer id)
    {

        try {
            if(db.isOpen()==false) {
                openReadable();
            }

            db.execSQL("UPDATE " + DB_TABLE1 + " SET ACTIVITY_NAME= '" + new_activity +  "' WHERE ID = '" + id +"'");

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        db.close();

    }

    public void updatePlannerTitle(Integer planner_id,Integer id)
    {

        try {
            if(db.isOpen()==false) {
                openReadable();
            }

            db.execSQL("UPDATE " + DB_TABLE1 + " SET PLANNER_ID= '" + planner_id +  "' WHERE ID = '" + id +"'");

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        db.close();

    }

    public void changePlanner(Integer oldPlanner,Integer newPlanner)
    {

        try {
            if(db.isOpen()==false) {
                openReadable();
            }

            db.execSQL("UPDATE " + DB_TABLE1 + " SET PLANNER_ID= '" + newPlanner +  "' WHERE PLANNER_ID = '" + oldPlanner +"'");

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        db.close();

    }
    public void viewActivity(int rec)
    {

        try {

            if(db.isOpen()==false) {
                openReadable();
            }
            String selectQuery = "SELECT  * FROM " + DB_TABLE1 + " WHERE ID= '"+rec+"'";
            Cursor cursor = db.rawQuery(selectQuery, null);

            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                 cursor.getInt(0);
                 cursor.getString(1);
                 cursor.getInt(2);

                cursor.moveToNext();

            }

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }

    }

    public void updateImage(Integer new_image,Integer id)
    {

        try {

            if(db.isOpen()==false) {
                openReadable();
            }
            db.execSQL("UPDATE " + DB_TABLE1 + " SET PICTURE_SOURCE= '" + new_image +  "' WHERE ID = '" + id +"'");

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        db.close();

    }
    public void viewImage(int rec)
    {

        try {

            if(db.isOpen()==false) {
                openReadable();
            }
            String selectQuery = "SELECT  * FROM " + DB_TABLE1 + " WHERE ID= '"+rec+"'";
            Cursor cursor = db.rawQuery(selectQuery, null);

            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                cursor.getInt(0);
                cursor.getString(1);
                cursor.getInt(2);

                cursor.moveToNext();

            }

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }

    }

    public void spinnerArray()
    {
        ContentValues newProduct = new ContentValues();
        ContentValues newProduct1 = new ContentValues();
        ContentValues newProduct2 = new ContentValues();
        ContentValues newProduct3 = new ContentValues();
        ContentValues newProduct4 = new ContentValues();
        ContentValues newProduct5 = new ContentValues();
        ContentValues newProduct6 = new ContentValues();
        ContentValues newProduct7 = new ContentValues();

        newProduct.put("SPINNER_VALUE","Choose Activity" );

        newProduct1.put("SPINNER_VALUE","Create New Activity");

        newProduct2.put("SPINNER_VALUE","Exercise" );

        newProduct3.put("SPINNER_VALUE","Dinner" );

        newProduct4.put("SPINNER_VALUE","Read a Book" );

        newProduct5.put("SPINNER_VALUE","Clean up play area" );

        newProduct6.put("SPINNER_VALUE","Setting the Table" );

        newProduct7.put("SPINNER_VALUE","Watch Cartoon" );


        try {
            db.insertOrThrow(DB_TABLE2, null, newProduct);
            db.insertOrThrow(DB_TABLE2, null, newProduct1);
            db.insertOrThrow(DB_TABLE2, null, newProduct2);
            db.insertOrThrow(DB_TABLE2, null, newProduct3);
            db.insertOrThrow(DB_TABLE2, null, newProduct4);
            db.insertOrThrow(DB_TABLE2, null, newProduct5);
            db.insertOrThrow(DB_TABLE2, null, newProduct6);
            db.insertOrThrow(DB_TABLE2, null, newProduct7);

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        db.close();


    }

    public ArrayList<String> spinnerValues()
    {
        try {
            productRows = new ArrayList<String>();
            String[] columns1 = new String[]{ "SPINNER_VALUE"};
            if(db.isOpen()==false) {
                openReadable();
            }
            Cursor cursor1 = db.query(DB_TABLE2, columns1, null, null, null, null, null);
            cursor1.moveToFirst();
            while (cursor1.isAfterLast() == false) {
                productRows.add(cursor1.getString(0));
                cursor1.moveToNext();
            }

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        return productRows;
    }



    public void addSpinnerValue(String newvalue)
    {
        ContentValues newProduct = new ContentValues();

        newProduct.put("SPINNER_VALUE", newvalue);

        try {
            if(db.isOpen()==false) {
                openReadable();
            }
            db.insertOrThrow(DB_TABLE2, null, newProduct);

            // db.execSQL("UPDATE " + DB_TABLE + " SET renew= '" + ren + "',recall= '"+rec+ "' , availability = '" + avl + "' ,event= '" + evt + "'WHERE user_id ='" + user+ "'");
        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        db.close();

    }

    public void addTitleValue(String newvalue)
    {
        ContentValues newProduct = new ContentValues();

        newProduct.put("PLANNER_TITLE", newvalue);

        try {
            if(db.isOpen()==false) {
                openReadable();
            }
            db.insertOrThrow(DB_TABLE3, null, newProduct);

            // db.execSQL("UPDATE " + DB_TABLE + " SET renew= '" + ren + "',recall= '"+rec+ "' , availability = '" + avl + "' ,event= '" + evt + "'WHERE user_id ='" + user+ "'");
        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        db.close();

    }

    public void plannerTitles()
    {
        ContentValues newProduct = new ContentValues();
        ContentValues newProduct1 = new ContentValues();
        ContentValues newProduct2 = new ContentValues();
        ContentValues newProduct3 = new ContentValues();
        ContentValues newProduct4 = new ContentValues();
        ContentValues newProduct5 = new ContentValues();
        ContentValues newProduct6 = new ContentValues();
        ContentValues newProduct7 = new ContentValues();

        newProduct.put("PLANNER_TITLE","Choose Title" );

        newProduct1.put("PLANNER_TITLE","Create New Title");

        newProduct2.put("PLANNER_TITLE","CHORES" );

        newProduct3.put("PLANNER_TITLE","On Travel" );

        newProduct4.put("PLANNER_TITLE","At Park" );

        newProduct5.put("PLANNER_TITLE","On Flight" );

        newProduct6.put("PLANNER_TITLE","At Museum" );

        newProduct7.put("PLANNER_TITLE","At Store" );


        try {
            db.insertOrThrow(DB_TABLE3, null, newProduct);
            db.insertOrThrow(DB_TABLE3, null, newProduct1);
            db.insertOrThrow(DB_TABLE3, null, newProduct2);
            db.insertOrThrow(DB_TABLE3, null, newProduct3);
            db.insertOrThrow(DB_TABLE3, null, newProduct4);
            db.insertOrThrow(DB_TABLE3, null, newProduct5);
            db.insertOrThrow(DB_TABLE3, null, newProduct6);
            db.insertOrThrow(DB_TABLE3, null, newProduct7);

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        db.close();


    }

    public ArrayList<String> TitleValues()
    {
        try {
            productRows = new ArrayList<String>();
            String[] columns1 = new String[]{ "PLANNER_TITLE"};
            if(db.isOpen()==false) {
                openReadable();
            }
            Cursor cursor1 = db.query(DB_TABLE3, columns1, null, null, null, null, null);
            cursor1.moveToFirst();
            while (cursor1.isAfterLast() == false) {
                productRows.add(cursor1.getString(0));
                cursor1.moveToNext();
            }

        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        return productRows;
    }

    public int PlannerID(String title)
    {
      int id=0;
        try {

            if(db.isOpen()==false) {
                openReadable();
            }
            String selectQuery = "SELECT  ID FROM " + DB_TABLE3 + " WHERE PLANNER_TITLE = '"+title+"'";
            Cursor cursor = db.rawQuery(selectQuery, null);

            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
               id= cursor.getInt(0);

                cursor.moveToNext();

            }


        } catch (Exception e) {
            Log.e("error", e.toString());
            e.printStackTrace();

        }
        return id;
    }

    public class SQLHelper extends SQLiteOpenHelper {
        public SQLHelper(Context c){
            super(c, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                db.execSQL(CREATE_TABLE1);
                db.execSQL(CREATE_TABLE2);
                db.execSQL(CREATE_TABLE3);


            } catch (Exception e) {
                Log.e("error", e.toString());
                e.printStackTrace();

            }
        }


        public void droptable()
        {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE1);
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE2);
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE3);


        }




        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w("Products table","Upgrading database i.e. dropping table and recreating it");
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE1);
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE2);
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE3);
            onCreate(db);
        }
    }

}

package trial.customlist;

import java.util.HashMap;

public class picture_library

{

     Integer[] photos={R.mipmap.disney,R.mipmap.a,R.mipmap.b,R.mipmap.d,R.mipmap.e,R.mipmap.f,R.mipmap.g,R.mipmap.h,
             R.mipmap.ic_launcher,R.mipmap.i,R.mipmap.j,R.mipmap.k,R.mipmap.l,R.mipmap.m,R.mipmap.q,R.mipmap.disney,R.mipmap.a,R.mipmap.b,R.mipmap.d,R.mipmap.e,R.mipmap.f,R.mipmap.g,R.mipmap.h,
             R.mipmap.ic_launcher,R.mipmap.i,R.mipmap.j,R.mipmap.k,R.mipmap.l,R.mipmap.m,R.mipmap.q,R.mipmap.disney,R.mipmap.a,R.mipmap.b,R.mipmap.d,R.mipmap.e,R.mipmap.f,R.mipmap.g,R.mipmap.h,
             R.mipmap.ic_launcher,R.mipmap.i,R.mipmap.j,R.mipmap.k,R.mipmap.l,R.mipmap.m,R.mipmap.q,R.mipmap.disney,R.mipmap.a,R.mipmap.b,R.mipmap.d,R.mipmap.e,R.mipmap.f,R.mipmap.g,R.mipmap.h,
             R.mipmap.ic_launcher,R.mipmap.i,R.mipmap.j,R.mipmap.k,R.mipmap.l,R.mipmap.m,R.mipmap.q,R.mipmap.disney,R.mipmap.a,R.mipmap.b,R.mipmap.d,R.mipmap.e,R.mipmap.f,R.mipmap.g,R.mipmap.h,
             R.mipmap.ic_launcher,R.mipmap.i,R.mipmap.j,R.mipmap.k,R.mipmap.l,R.mipmap.m,R.mipmap.q,R.mipmap.disney,R.mipmap.a,R.mipmap.b,R.mipmap.d,R.mipmap.e,R.mipmap.f,R.mipmap.g,R.mipmap.h,
             R.mipmap.ic_launcher,R.mipmap.i,R.mipmap.j,R.mipmap.k,R.mipmap.l,R.mipmap.m,R.mipmap.q,R.mipmap.disney,R.mipmap.a,R.mipmap.b,R.mipmap.d,R.mipmap.e,R.mipmap.f,R.mipmap.g,R.mipmap.h,
             R.mipmap.ic_launcher,R.mipmap.i,R.mipmap.j,R.mipmap.k,R.mipmap.l,R.mipmap.m,R.mipmap.q};


    HashMap<String, Integer> photoidhsh=new HashMap<String, Integer>();


public void library()
    {
       // photoidhsh=new HashMap<String, Integer>();
        photoidhsh.put ("R.mipmap.a", R.mipmap.a);
        photoidhsh.put("R.mipmap.b", R.mipmap.b);

        photoidhsh.put("R.mipmap.d", R.mipmap.d);
        photoidhsh.put("R.mipmap.e", R.mipmap.e);
        photoidhsh.put("R.mipmap.f", R.mipmap.f);
        photoidhsh.put("R.mipmap.g", R.mipmap.g);
        photoidhsh.put("R.mipmap.h", R.mipmap.h);
        photoidhsh.put("R.mipmap.ic_launcher", R.mipmap.ic_launcher);
        photoidhsh.put("R.mipmap.disney", R.mipmap.disney);
    }
}

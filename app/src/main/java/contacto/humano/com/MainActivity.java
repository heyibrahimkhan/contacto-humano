package contacto.humano.com;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import contacto.humano.com.m_fragments.Frag_Error;
import contacto.humano.com.m_fragments.Frag_getAbout;
import contacto.humano.com.m_fragments.Frag_getAcademics;
import contacto.humano.com.m_fragments.Frag_getHistory;
import contacto.humano.com.m_fragments.Frag_getHome;
import contacto.humano.com.m_fragments.Frag_getMission;
import contacto.humano.com.m_fragments.Frag_getTeam;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Frag_getHome.OnFragmentInteractionListener,
        Frag_Error.OnFragmentInteractionListener, Frag_getAbout.OnFragmentInteractionListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    FrameLayout mFrame;
    static RecyclerView rv_homeBar;
    private static FragmentManager fm;
    static String currentPage;
    static String currentUrl;
    public static String urlAppend;
    String[] language;
    private static String[] listItem = {"home", "about us", "history", "mission", "team", "academics", "professionals",
            "plans", "consulting", "press", "blog", "testimonials", "contact", "register"};
    private static Fragment frag[] = {new Frag_getHome(), new Frag_getAbout(), new Frag_getHistory(), new Frag_getMission(),
            new Frag_getTeam(), new Frag_getAcademics()};
    private static String[] listURL = {"http://con-tactohumano.com/", "http://con-tactohumano.com/about-us/",
            "http://con-tactohumano.com/historia/", "http://con-tactohumano.com/mission-and-values/",
            "http://con-tactohumano.com/team/", "http://con-tactohumano.com/academics/", "http://con-tactohumano.com/professional/",
            "http://con-tactohumano.com/plans/", "http://con-tactohumano.com/consulting/",
            "http://con-tactohumano.com/press/", "http://con-tactohumano.com/blog-2/",
            "http://con-tactohumano.com/testimonials-page/", "http://con-tactohumano.com/contact-us/",
            "http://con-tactohumano.com/profile/register/"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_homeBar = (RecyclerView) findViewById(R.id.homeBar);
        rv_homeBar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_homeBar.setItemAnimator(new DefaultItemAnimator());
        rv_homeBar.setAdapter(new rv_hb_adapter());
        rv_homeBar.setHasFixedSize(true);

        mFrame = (FrameLayout) findViewById(R.id.mFrame);
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.mFrame, new Frag_getHome()).commit();
        currentPage = "home";
        currentUrl = "http://con-tactohumano.com/";

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        urlAppend = "";

//        language = getResources().getStringArray(R.array.languages);


//        Spinner spinner = (Spinner) navigationView.getMenu().getItem(5);
//        if(spinner != null) {
//            spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, language));
//            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(MainActivity.this, language[position], Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                }
//            });
//        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_home) {
            currentPage = "home";
            currentUrl = "http://con-tactohumano.com/";
        }
        else if (id == R.id.menu_about_us) {
            currentPage = "about us";
            currentUrl = "http://con-tactohumano.com/about-us/";
        }
        else if (id == R.id.menu_history) {
            currentPage = "history";
            currentUrl = "http://con-tactohumano.com/historia/";
        }
        else if(id == R.id.menu_mission){
            currentPage = "mission";
            currentUrl = "http://con-tactohumano.com/mission-and-values/";
        }
        else if (id == R.id.menu_team) {
            currentPage = "team";
            currentUrl = "http://con-tactohumano.com/team/";
        }
        else if (id == R.id.menu_academics) {
            currentPage = "academics";
            currentUrl = "http://con-tactohumano.com/academics/";
        }
        else if (id == R.id.menu_professionals) {
            currentPage = "professionals";
            currentUrl = "http://con-tactohumano.com/professional/";
        }
        else if (id == R.id.menu_plans) {
            currentPage = "plans";
            currentUrl = "http://con-tactohumano.com/plans/";
        }
        else if (id == R.id.menu_consulting) {
            currentPage = "consulting";
            currentUrl = "http://con-tactohumano.com/consulting/";
        }
        else if (id == R.id.menu_press) {
            currentPage = "press";
            currentUrl = "http://con-tactohumano.com/press/";
        }
        else if (id == R.id.menu_blog) {
            currentPage = "blog";
            currentUrl = "http://con-tactohumano.com/blog-2/";
        }
        else if (id == R.id.menu_testimonial) {
            currentPage = "testimonials";
            currentUrl = "http://con-tactohumano.com/testimonials-page/";
        }
        else if (id == R.id.menu_contact) {
            currentPage = "contact";
            currentUrl = "http://con-tactohumano.com/contact-us/";
        }
        else if (id == R.id.menu_register) {
            currentPage = "register";
            currentUrl = "http://con-tactohumano.com/profile/register/";
        }

        replaceFragment(currentPage);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void replaceFragment() {
        String url = "";
        int len = listItem.length;
        Fragment f = null;
        for(int i = 0; i < len; i++){
            if(listItem[i].equalsIgnoreCase(currentPage)){
                url = listURL[i];
                currentUrl = url;
                f = frag[i];
                break;
            }
        }
        url = url.concat(urlAppend);
        fm.beginTransaction().replace(R.id.mFrame, f).commit();
    }

    public static void replaceFragment(String currPage) {
        String url = "";
        int len = listItem.length;
        Fragment f = null;
        for(int i = 0; i < len; i++){
            if(listItem[i].equalsIgnoreCase(currPage)){
                url = listItem[i];
                f = frag[i];
                break;
            }
        }
        url = url.concat(urlAppend);
        fm.beginTransaction().replace(R.id.mFrame, f).commit();
    }

    public static void replaceFragment(Fragment fragment){
        fm.beginTransaction().replace(R.id.mFrame, fragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static void setErrorFrag(String page, String url){
        currentPage = page;
        currentUrl = url;
        Frag_Error fe;
        fm.beginTransaction().replace(R.id.mFrame, new Frag_Error()).commit();
    }

    public static void reloadFragement() {
        replaceFragment();
    }

    public static class rv_hb_adapter extends RecyclerView.Adapter<rv_hb_adapter.homeBarTv>{

        private String[] listItem = {"home", "about us", "services",
                "plans", "consulting", "press", "blog", "testimonials", "contact", "register"};
        private String[] listURL = {"http://con-tactohumano.com/", "http://con-tactohumano.com/about-us/",
                "http://con-tactohumano.com/historia/", "http://con-tactohumano.com/mission-and-values/",
                "http://con-tactohumano.com/team/", "http://con-tactohumano.com/academics/", "http://con-tactohumano.com/professional/",
                "http://con-tactohumano.com/plans/", "http://con-tactohumano.com/consulting/",
                "http://con-tactohumano.com/press/", "http://con-tactohumano.com/blog-2/",
                "http://con-tactohumano.com/testimonials-page/", "http://con-tactohumano.com/contact-us/",
                "http://con-tactohumano.com/profile/register/"};

        public class homeBarTv extends RecyclerView.ViewHolder{

            public TextView tv;

            public homeBarTv(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv_hb);
            }
        }

        @Override
        public homeBarTv onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hb, parent, false);
            return new homeBarTv(v);
        }

        @Override
        public void onBindViewHolder(homeBarTv holder, int position) {
            String text = this.listItem[position];
            holder.tv.setText(text);
        }

        @Override
        public int getItemCount() {
            return this.listItem.length;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }
    }
}

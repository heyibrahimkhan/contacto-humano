package contacto.humano.com;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import contacto.humano.com.getWebMat.Frag_Error;
import contacto.humano.com.mInterfaces.PNConnection;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Frag_getHome.OnFragmentInteractionListener,
        Frag_Error.OnFragmentInteractionListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    FrameLayout mFrame;
    static RecyclerView rv_homeBar;
    private static FragmentManager fm;
    static String currentPage;
    static String currentUrl;

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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static void setErrorFrag(String page){
        Frag_Error fe;
        fm.beginTransaction().replace(R.id.mFrame, fe = new Frag_Error()).commit();
    }

    public static void reloadFragment(){
        Fragment f;
        currentUrl = ((rv_hb_adapter)(rv_homeBar.getAdapter())).getUrl(currentPage);
        f = getFragment(currentUrl);
        fm.beginTransaction().replace(R.id.mFrame, f).commit();
    }

    private static Fragment getFragment(String Url) {
        if(Url.equals("http://con-tactohumano.com/")){
            currentPage = "home";
            return new Frag_getHome();
        }
        else if(Url.equals("")){

        }
        return new Frag_Error();
    }

    public static class rv_hb_adapter extends RecyclerView.Adapter<rv_hb_adapter.homeBarTv>{

        private String[] listItem = {"home", "about us", "services", "plans", "consulting", "press", "blog", "testimonials", "contact"};
        private String[] listURL = {"http://con-tactohumano.com/"};

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
            String text = listItem[position];
            holder.tv.setText(text);
        }

        @Override
        public int getItemCount() {
            return listItem.length;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public String getUrl(String page){
            String url = "";
            int len = listItem.length;
            for (int i = 0; i < len; i++){
                if(listItem[i].equalsIgnoreCase(page)){
                    url = listURL[i];
                    break;
                }
            }
            return url;
        }
    }
}

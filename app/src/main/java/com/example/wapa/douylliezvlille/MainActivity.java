package com.example.wapa.douylliezvlille;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MainActivity extends Activity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;
ArrayList <Station>stations;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;


    public void setStations(ArrayList<Station> liste){this.stations= liste;}
    public ArrayList<Station> getStations(){return this.stations;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

Station rihour=new Station("Rihour",10,3.06247,50.6359,12,24);
        this.stations=new ArrayList<Station>();
       // this.stations.add(rihour);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);



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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.



            switch(position) {
                case 0:   return MainActivityFragment1.newInstance();
                case 1:return MainActivityFragment3.newInstance();
                case 2:   return MainActivityFragment2.newInstance();
                default:return MainActivityFragment1.newInstance();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    public static class MainActivityFragment1 extends Fragment {



        public static MainActivityFragment1 newInstance() {
            MainActivityFragment1 fragment = new MainActivityFragment1();

            return fragment;
        }

        public MainActivityFragment1() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_1, container, false);
            return rootView;
        }
    }


    public static class MainActivityFragment2 extends Fragment {

        MapView mMapView;
        private GoogleMap googleMap;

        public static MainActivityFragment2 newInstance() {
            MainActivityFragment2 fragment = new MainActivityFragment2();

            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // inflat and return the layout
            View v = inflater.inflate(R.layout.fragment_main_2, container,
                    false);
            mMapView = (MapView) v.findViewById(R.id.mapview);
            mMapView.onCreate(savedInstanceState);
            MapsInitializer.initialize(this.getActivity());
            mMapView.getMap();


            mMapView.onResume();// needed to get the map to display immediately




            googleMap = mMapView.getMap();
            // latitude and longitude
            double latitudeLille = 50.633333;
            double longitudeLille = 3.066667;


            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(latitudeLille, longitudeLille)).zoom(12).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


            // adding markers
            int i;MainActivity temps=(MainActivity)this.getActivity();
            if(temps.getStations()!=null){
                ArrayList <Station> stations= temps.getStations();
            for(i=0;i<stations.size();i++){
Station station=stations.get(i);
                    // create markers
            MarkerOptions marker = new MarkerOptions().position(
                    new LatLng(station.getLat(), station.getLng())).title(station.getName());

            // Changing marker icons
            marker.icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));


            // adding marker
             googleMap.addMarker(marker);}}


            // Perform any camera updates here*/
            return v;
        }

        @Override
        public void onResume() {
            super.onResume();
            mMapView.onResume();
        }

        @Override
        public void onPause() {
            super.onPause();
            mMapView.onPause();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mMapView.onDestroy();
        }

    }

    public static class MainActivityFragment3 extends Fragment {



        public static MainActivityFragment3 newInstance() {
            MainActivityFragment3 fragment = new MainActivityFragment3();

            return fragment;
        }

        public MainActivityFragment3() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main_3, container, false);

      /** Create a new layout to display the view */
            LinearLayout layout = new LinearLayout(this.getActivity());


            /** Create a new textview array to display the results */

         VlilleXMLParser parser= new VlilleXMLParser((MainActivity)this.getActivity());

parser.execute(5);
            TextView b = (TextView)rootView.findViewById(R.id.section_label1);
           if (((MainActivity) this.getActivity()).getStations().size()>0) b.setText("récuperation réussie !");

            return rootView;//layout.getRootView();
        }
    }


}
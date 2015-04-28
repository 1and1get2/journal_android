package com.chen.derek.journal.journal.ui;


import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.app.Fragment;
//import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.chen.derek.journal.backend.registration.Registration;
import com.chen.derek.journal.journal.R;
import com.chen.derek.journal.journal.common.activities.SampleActivityBase;
import com.chen.derek.journal.journal.common.logger.Log;
import com.chen.derek.journal.journal.ui.navigation.NavigationDrawerFragment;
import com.facebook.FacebookSdk;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainActivity extends SampleActivityBase
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private Fragment mNavigationDrawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);*/

/*        DrawerLayout dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView lv = (ListView) findViewById(R.id.main_navigation_drawer);
        lv.setAdapter(new ArrayAdapter<String>(
                this,
                R.layout.drawer_list_item,
                new String[]{
                        "a", "title 2"
                }
        ));*/



//        Fragment f = getSupportFragmentManager().findFragmentById(R.id.main_navigation_drawer);

//        mNavigationDrawerFragment = f;
//                (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.main_navigation_drawer);


//      initialize Facebook SDK
//        FacebookSdk.sdkInitialize(getApplicationContext());

//        new GcmRegistrationAsyncTask(this).execute();

        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Call some material design APIs here
        } else {
            // Implement this feature without material design
        }

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment ndf = NavigationDrawerFragment.newInstance("11","222");

        transaction.replace(R.id.main_navigation_drawer, ndf);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Log.d(TAG, "onNavigationDrawerItemSelected: " + position);

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
//        TODO find the correct fragment to attach
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, null)
//                .commit();
        Fragment fragment = new Fragment();
        Bundle args = new Bundle();
        args.putInt("Section", position);
        fragment.setArguments(args);

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();


    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
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


    class GcmRegistrationAsyncTask extends AsyncTask<Void, Void, String> {
        private Registration regService = null;
        private GoogleCloudMessaging gcm;
        private Context context;

        // TODO: change to your own sender ID to Google Developers Console project number, as per instructions above
        private static final String SENDER_ID = "937717765367";

        public GcmRegistrationAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(Void... params) {
            if (regService == null) {
                Registration.Builder builder = new Registration.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // Need setRootUrl and setGoogleClientRequestInitializer only for local testing,
                        // otherwise they can be skipped
                        .setRootUrl("http://10.0.0.3:8080/_ah/api/")
//                        .setRootUrl("http://localhost:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
                                    throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });

                // end of optional local run code
//              connect backend to App Engine
/*                Registration.Builder builder = new Registration.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://android-app-backend.appspot.com/_ah/api/");*/

                regService = builder.build();
            }

            String msg = "";
            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(context);
                }
                String regId = gcm.register(SENDER_ID);
                msg = "Device registered, registration ID=" + regId;

                // You should send the registration ID to your server over HTTP,
                // so it can use GCM/HTTP or CCS to send messages to your app.
                // The request to your server should be authenticated if your app
                // is using accounts.
                regService.register(regId).execute();

            } catch (IOException ex) {
                ex.printStackTrace();
                msg = "Error: " + ex.getMessage();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            Logger.getLogger("REGISTRATION").log(Level.INFO, msg);
        }
    }

}

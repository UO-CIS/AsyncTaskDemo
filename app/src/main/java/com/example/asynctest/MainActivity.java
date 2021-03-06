package com.example.asynctest;
// By Brian Bird, 7/14/2014
// This Activity demonstrates the use of the AsyncTask class

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	Button testButton;
	TextView testTextView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        testTextView = (TextView) findViewById(R.id.testTextView);
        testTextView.setText("A slow async task is being started");
        testButton = (Button) findViewById(R.id.testButton);
        testButton.setOnClickListener(this);
        
        // This is where we execute the asynchronous method
        // It will run on a separate thread (not the UI thread)
        new AsycTest().execute("the slow task");
    }

	@Override
	public void onClick(View v) {
		testTextView.setText("Button clicked!");
		
	}
	
	// This class will handle our asynchronous task
	public class AsycTest extends AsyncTask<String, Void, String>
	{
		@Override
		// This is called when we call: execute("the slow task"), above
		protected String doInBackground(String... params) {
			try {
				Thread.sleep(5000);    // wait 5 seconds
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "I'm finished with " + params[0];
		}

		@Override
		// This is called by AsyncTask when the method above has returned
		protected void onPostExecute(String result) {
			testTextView.setText(result);
			super.onPostExecute(result);
		}
		
	}
}

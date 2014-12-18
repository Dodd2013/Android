package com.example.movierating1;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText et1;
	EditText et2;
	EditText et3;
	EditText et4;
	EditText et5;
	EditText et6;
	Button btn1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et1=(EditText)findViewById(R.id.editText1);
		et2=(EditText)findViewById(R.id.editText2);
		et3=(EditText)findViewById(R.id.editText3);
		et4=(EditText)findViewById(R.id.editText4);
		et5=(EditText)findViewById(R.id.editText5);
		et6=(EditText)findViewById(R.id.editText6);
		btn1=(Button)findViewById(R.id.button1);
		btn1.setOnClickListener(new OnC());
	}
	
	public class OnC implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			String str1=et1.getText().toString();
			String str2=et2.getText().toString();
			String str3=et3.getText().toString();
			String str4=et4.getText().toString();
			String str5=et5.getText().toString();
			String str6=et6.getText().toString();
			if(str1.equals("")||str2.equals("")||str3.equals("")||str4.equals("")||str5.equals("")||str6.equals("")){
				Toast.makeText(getBaseContext(), "agfd",Toast.LENGTH_SHORT ).show();
			}
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_main, container,
					false);
			return rootView;
		}
	}

}

package com.sowapps.mvc.demo.controllers.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sowapps.mvc.demo.models.Credentials;
import com.sowapps.mvc.demo.views.LoginBasicView;
import com.sowapps.mvc.mvclibrary.views.LayoutView;

/**
 * Created by Florent HAZARD on 18/12/2015.
 *
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

	public static final String TAG = "MVC.LoginActivity";

	public static final String CREDENTIALS_EMAIL	= "credentials.username";

	protected LoginLayoutView layoutView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate(" + (savedInstanceState != null ? savedInstanceState.size() : 0) + ")");

		layoutView = new LoginBasicView(this);
//		layoutView = new LoginFeaturedView(this);

		setContentView(layoutView.getLayoutID());
		layoutView.setUp();
	}

	@Override
	protected void onStart() {
		super.onStart();

		// Restore credentials to the latest
		restoreCredentials();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume()");
		layoutView.reset();

		// Check if not already logged in and do what you need
	}


	public void saveCredentials(Credentials credentials) {
		PreferenceManager.getDefaultSharedPreferences(this).edit()
				.putString(CREDENTIALS_EMAIL, credentials.getEmail())
				.apply();
	}

	public void restoreCredentials() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		layoutView.setLoginEmail(sp.getString(CREDENTIALS_EMAIL, ""));
	}

	public void login(View view) {
		final Credentials credentials = layoutView.getLoginForm();
		layoutView.onLoginStart();
		saveCredentials(credentials);

		// Fake - DIY
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// Fake arbitrary condition
				if( credentials.getPassword().isEmpty() ) {
					// Success
					layoutView.onLoginSuccess();
				} else {
					// Failure
					layoutView.onLoginError();
				}
				layoutView.onLoginEnd();
			}
		}, 1700);
	}

	public void openRegisterForm(View view) {
		Toast.makeText(this, "Oops, it seems you have to implement it by yourself", Toast.LENGTH_LONG).show();
//		Intent intent = new Intent(this, RegisterActivity.class);
//		startActivity(intent);
	}

	private void exit() {
		finish();
	}

	public static interface LoginLayoutView extends LayoutView {

		public void reset();

		public void setUp();

		public void setLoginEmail(String email);

		public Credentials getLoginForm();

		public void onLoginStart();

		public void onLoginEnd();

		public void onLoginSuccess();

		public void onLoginError();

	}
}


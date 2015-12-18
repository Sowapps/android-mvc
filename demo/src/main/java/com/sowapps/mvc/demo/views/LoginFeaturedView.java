package com.sowapps.mvc.demo.views;

import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;
import com.sowapps.mvc.demo.R;
import com.sowapps.mvc.demo.controllers.activities.LoginActivity;
import com.sowapps.mvc.demo.models.Credentials;

/**
 * Created by Florent HAZARD on 12/12/2015.
 */
public class LoginFeaturedView implements LoginActivity.LoginLayoutView {

	protected EditText emailInput;
	protected EditText passwordInput;
	protected ActionProcessButton loginButton;

	protected LoginActivity activity;

	public LoginFeaturedView(LoginActivity activity) {
		this.activity = activity;
	}

	@Override
	public void reset() {
		enableForm();
		loginButton.setProgress(0);
	}

	@Override
	public void setUp() {
		emailInput	= (EditText) activity.findViewById(R.id.login_email_input);
		passwordInput = (EditText) activity.findViewById(R.id.login_password_input);
		loginButton = (ActionProcessButton) activity.findViewById(R.id.login_button);
		loginButton.setMode(ActionProcessButton.Mode.ENDLESS);
	}

	@Override
	public void setLoginEmail(String email) {
		
	}

	@Override
	public Credentials getLoginForm() {
		Credentials credentials	= new Credentials();
		credentials.setEmail(emailInput.getText().toString());
		credentials.setPassword(passwordInput.getText().toString());
		return credentials;
	}

	public void disableForm() {
		emailInput.setEnabled(false);
		passwordInput.setEnabled(false);
	}

	public void enableForm() {
		emailInput.setEnabled(true);
		passwordInput.setEnabled(true);
	}

	@Override
	public void onLoginStart() {
		disableForm();
		loginButton.setProgress(1);
	}

	@Override
	public void onLoginEnd() {
		enableForm();
//		loginButton.setProgress(0);
	}

	@Override
	public void onLoginSuccess() {
		loginButton.setProgress(100);
	}

	@Override
	public void onLoginError() {
		loginButton.setProgress(-1);
//		Toast.makeText(activity, R.string.report_unableToLogin, Toast.LENGTH_LONG).show();
	}

	@Override
	public int getLayoutID() {
		return R.layout.activity_login_featured;
	}
}

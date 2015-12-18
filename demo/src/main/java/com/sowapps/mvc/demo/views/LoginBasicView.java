package com.sowapps.mvc.demo.views;

import android.widget.EditText;
import android.widget.Toast;

import com.sowapps.mvc.demo.R;
import com.sowapps.mvc.demo.controllers.activities.LoginActivity;
import com.sowapps.mvc.demo.models.Credentials;

/**
 * Created by Florent HAZARD on 18/12/2015.
 */
public class LoginBasicView implements LoginActivity.LoginLayoutView {

	protected EditText emailInput;
	protected EditText passwordInput;

	protected LoginActivity activity;

	public LoginBasicView(LoginActivity activity) {
		this.activity = activity;
	}

	@Override
	public void setLoginEmail(String email) {
		emailInput.setText(email);
	}

	@Override
	public void reset() {
		enableForm();
	}

	@Override
	public void setUp() {
		emailInput	= (EditText) activity.findViewById(R.id.login_email_input);
		passwordInput = (EditText) activity.findViewById(R.id.login_password_input);
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
	}

	@Override
	public void onLoginEnd() {
		enableForm();
	}

	@Override
	public void onLoginSuccess() {
		Toast.makeText(activity, R.string.report_successLogin, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onLoginError() {
		Toast.makeText(activity, R.string.report_unableToLogin, Toast.LENGTH_LONG).show();
	}

	@Override
	public int getLayoutID() {
		return R.layout.activity_login_basic;
	}
}

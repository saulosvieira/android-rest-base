package br.com.mydev.androidrestbase.Network;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class UserSessionManager {

    // Shared Preferences reference
    SharedPreferences pref;

    // Editor reference for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    public static final String PREFER_NAME = "AndroidGOD";

    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    // User image (make variable public to access from outside)
    public static final String IS_DANGER = "danger";

    // User name (make variable public to access from outside)
    public static final String KEY_CPF = "cpf";

    // User image (make variable public to access from outside)
    public static final String KEY_ID = "id";
    public static final String KEY_TOKEN = "token";

    public static final String KEY_AVISO = "aviso";
    public static final String KEY_VENCIMENTO = "vencimento";
    public static final String KEY_TELEFONE_ENVIO = "telefone_envio";

    public static final String KEY_EMAIL_ENVIO = "email_envio";

    public static final String KEY_CONTADOR = "contador";
    public static final String KEY_TIMER = "timer";
    public static final String KEY_AVISO_MOMENTO = "avisoMomento";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // Constructor
    public UserSessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //Create login session
    public void createUserLoginSession(String name, String email, String id, String cpf, String telefoneEnvio, String emailEnvio, String token, String vencimento) {
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);
        // Storing name in pref
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_TELEFONE_ENVIO, telefoneEnvio);
        editor.putString(KEY_EMAIL_ENVIO, emailEnvio);
        editor.putString(KEY_CPF, cpf);
        // Storing name in pref
        editor.putString(KEY_ID, id);
        editor.putString(KEY_TOKEN, token);
        // Storing email in pref
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_VENCIMENTO, vencimento);
        // commit changes
        editor.commit();
    }

    public void setIsDanger(boolean danger) {
        editor.putBoolean(IS_DANGER, danger);
        editor.commit();
    }

    public void setContador(int count) {
        editor.putInt(KEY_CONTADOR, count);
        editor.commit();
    }


    public String getToken(){
        return pref.getString(KEY_TOKEN, "");
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     */
    public boolean checkLogin() {

        return this.isUserLoggedIn();
    }

    public String getGcmToken() {
        return pref.getString("gcm_token", null);
    }


    /**
     * Clear session details
     */
    public void logoutUser() {

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();
/*
        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, LoginActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
        */
    }

    public void setConfiguracoes(boolean aviso) {

        editor.putBoolean(KEY_AVISO, aviso);

        // commit changes
        editor.commit();

    }

    public void setConfiguracoes(int momento) {


        editor.putInt(KEY_AVISO_MOMENTO, momento);
        // commit changes
        editor.commit();
    }

    public Boolean getAviso() {
        return pref.getBoolean(KEY_AVISO, false);

    }

    public int getAvisoMomento() {
        return pref.getInt(KEY_AVISO_MOMENTO, 0);
    }


    // Check for login
    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    public boolean isDanger() {
        return pref.getBoolean(IS_DANGER, false);
    }
}
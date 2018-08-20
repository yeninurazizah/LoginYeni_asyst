package id.co.asyst.yeni.loginyeni.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionUtils {

    Context mContext;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SessionUtils(Context context) {
        this.mContext = context;
        preferences = context.getSharedPreferences("task", 0);
        editor = preferences.edit();
    }

    public void saveLogin(String username, String password) {
        editor.putString(Constant.KEY_USERNAME, username);
        editor.putString(Constant.KEY_PASSWORD, password);
        editor.commit();
    }

    public void saveIsLogin(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }


    public String loadUsername() {
        String user = preferences.getString(Constant.KEY_USERNAME, "");
        return user;
    }

    public String loadPassword() {
        String pass = preferences.getString(Constant.KEY_PASSWORD, "");
        return pass;
    }

    public boolean isLogin() {
        return preferences.getBoolean(Constant.IS_LOGIN, false);
    }
}

package com.example.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends PreferenceActivity  {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);

        final PreferenceManager preferenceManager = getPreferenceManager();

        final SwitchPreference switchPreference= (SwitchPreference) findPreference("music");
        switchPreference.setChecked(false);

        final MediaPlayer player = MediaPlayer.create(getApplicationContext(), R.raw.pump_it_up);
        player.setLooping(true);
        player.setVolume(100,100);

        findPreference("music").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setDefaultValue(false);
                boolean isCheck = (Boolean) newValue;
                Toast.makeText(getApplicationContext(),String.valueOf(isCheck), Toast.LENGTH_SHORT);
                if(isCheck){
                    player.start();
                }
                else{
                    player.pause();
                }
                return true;
            }
        });
    }
}

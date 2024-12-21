package com.example.question2_external_exam;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    private Switch soundSwitch, vibrationSwitch, ledSwitch, bannerSwitch, contentSwitch, lockScreenSwitch;
    private Button saveButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("NotificationPrefs", MODE_PRIVATE);

        soundSwitch = findViewById(R.id.switch_sound);
        vibrationSwitch = findViewById(R.id.switch_vibration);
        ledSwitch = findViewById(R.id.switch_led);
        bannerSwitch = findViewById(R.id.switch_banner);
        contentSwitch = findViewById(R.id.switch_content);
        lockScreenSwitch = findViewById(R.id.switch_lock_screen);
        saveButton = findViewById(R.id.button_save);

        loadPreferences();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationSheet();
            }
        });
    }

    private void loadPreferences() {
        soundSwitch.setChecked(sharedPreferences.getBoolean("sound", false));
        vibrationSwitch.setChecked(sharedPreferences.getBoolean("vibration", false));
        ledSwitch.setChecked(sharedPreferences.getBoolean("led", false));
        bannerSwitch.setChecked(sharedPreferences.getBoolean("banner", false));
        contentSwitch.setChecked(sharedPreferences.getBoolean("content", false));
        lockScreenSwitch.setChecked(sharedPreferences.getBoolean("lock_screen", false));
    }

    private void showConfirmationSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.confirmation_bottom_sheet); // Updated layout reference
        bottomSheetDialog.findViewById(R.id.button_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences();
                bottomSheetDialog.dismiss();
                Toast.makeText(MainActivity.this, "Preferences saved", Toast.LENGTH_SHORT).show();
            }
        });
        bottomSheetDialog.show();
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("sound", soundSwitch.isChecked());
        editor.putBoolean("vibration", vibrationSwitch.isChecked());
        editor.putBoolean("led", ledSwitch.isChecked());
        editor.putBoolean("banner", bannerSwitch.isChecked());
        editor.putBoolean("content", contentSwitch.isChecked());
        editor.putBoolean("lock_screen", lockScreenSwitch.isChecked());
        editor.apply();
    }
}
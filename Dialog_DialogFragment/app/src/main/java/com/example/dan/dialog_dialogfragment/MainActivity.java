package com.example.dan.dialog_dialogfragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dan.dialog_dialogfragment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements CustomDialog.OnInputListener{
    ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    public String mInput;

    @Override
    public void sendInput(String input){
        Log.d(TAG, "sendInput: got the input: "+input);

//        binding.mainContent.tvDialogResult.setText(input);
        mInput = input;
        setInputToTextView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        Intent in = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(in);
        binding.mainContent.btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog dialog = new CustomDialog();
                dialog.show(getFragmentManager(),"CustomDialog");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    private void setInputToTextView(){
        binding.mainContent.tvDialogResult.setText(mInput);
    }

}
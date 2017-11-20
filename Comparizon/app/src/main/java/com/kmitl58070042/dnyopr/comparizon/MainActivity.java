package com.kmitl58070042.dnyopr.comparizon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kmitl58070042.dnyopr.comparizon.fragment.AddItemFragment;
import com.kmitl58070042.dnyopr.comparizon.fragment.ItemLeftFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.item_l_fragment, new ItemLeftFragment())
                    .commit();
        }
    }

    @Override
    public void onClick(View view) {
        if(R.id.btn_add == view.getId()){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_item_fragment, AddItemFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }
    }
}

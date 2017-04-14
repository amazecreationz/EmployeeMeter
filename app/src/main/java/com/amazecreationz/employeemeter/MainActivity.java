package com.amazecreationz.employeemeter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.amazecreationz.employeemeter.models.Employee;
import com.amazecreationz.employeemeter.tabs.CalendarFragment;
import com.amazecreationz.employeemeter.tabs.OverviewFragment;
import com.amazecreationz.employeemeter.tabs.ReportFragment;
import com.amazecreationz.employeemeter.tabs.TabsPagerAdapter;
import com.amazecreationz.employeemeter.wrappers.AndroidDBWrapper;
import com.amazecreationz.employeemeter.wrappers.EmployeeArrayAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    AndroidDBWrapper androidDBWrapper;

    private void setTabs() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        tabsPagerAdapter.addFragment(new OverviewFragment(), getString(R.string.tab_overview));
        tabsPagerAdapter.addFragment(new CalendarFragment(), getString(R.string.tab_calendar));
        tabsPagerAdapter.addFragment(new ReportFragment(), getString(R.string.tab_report));
        viewPager.setAdapter(tabsPagerAdapter);
    }

    private void setEmployeeSelector() {
        List<Employee> employeesList = androidDBWrapper.getAllEmployees();
        Spinner spinner = (Spinner) findViewById(R.id.employeelist);
        if(employeesList.size() > 0) {
            EmployeeArrayAdapter employeeArrayAdapter = new EmployeeArrayAdapter(this, employeesList);
            spinner.setAdapter(employeeArrayAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Employee employee = (Employee) parent.getItemAtPosition(position);
                    Log.d(employee.id, employee.name);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else {
            spinner.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        androidDBWrapper = new AndroidDBWrapper(this);
        androidDBWrapper.initialise();
        androidDBWrapper.removeAllEmployees();
        androidDBWrapper.addEmployee(new Employee("Podi", "Manager", 10));
        androidDBWrapper.addEmployee(new Employee("Anand Mohan", "Manager", 10));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTabs();
        setEmployeeSelector();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
}

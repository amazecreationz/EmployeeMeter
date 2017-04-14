package com.amazecreationz.employeemeter.wrappers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.amazecreationz.employeemeter.constants.SQLConstants;
import com.amazecreationz.employeemeter.models.Employee;
import com.amazecreationz.employeemeter.services.AndroidDBService;
import com.amazecreationz.employeemeter.services.StringService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 14/4/17 3:16 PM.
 */

public class AndroidDBWrapper implements SQLConstants {
    private SQLiteDatabase sqLiteDatabase;

    public AndroidDBWrapper(Context context) {
        this.sqLiteDatabase = new AndroidDBService(context).getWritableDatabase();
    }

    public void initialise() {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS " + EMPLOYEE_TABLE + " (" +
                        EMPLOYEE_ID + " TEXT PRIMARY KEY," +
                        EMPLOYEE_NAME + " TEXT," +
                        EMPLOYEE_DESIGNATION + " TEXT," +
                        SALARY_PER_DAY + " INTEGER)";
        Log.d("moghan", sqlQuery);
        sqLiteDatabase.execSQL(sqlQuery);
        sqlQuery = "CREATE TABLE IF NOT EXISTS " + EXCLUDE_TABLE + " (" +
                EMPLOYEE_ID + " TEXT PRIMARY KEY," +
                DATE+ " INTEGER," +
                BONUS + " INTEGER DEFAULT 0)";
        Log.d("moghan", sqlQuery);
        sqLiteDatabase.execSQL(sqlQuery);
        sqlQuery = "CREATE TABLE IF NOT EXISTS " + ABSENT_TABLE + " (" +
                EMPLOYEE_ID + " TEXT PRIMARY KEY," +
                DATE+ " INTEGER," +
                SALARY_REDN + " INTEGER DEFAULT 0)";
        Log.d("moghan", sqlQuery);
        sqLiteDatabase.execSQL(sqlQuery);
        sqlQuery = "CREATE TABLE IF NOT EXISTS " + PAY_TABLE + " (" +
                EMPLOYEE_ID + " TEXT PRIMARY KEY," +
                DATE+ " INTEGER," +
                AMOUNT + " INTEGER DEFAULT 0)";
        Log.d("moghan", sqlQuery);
        sqLiteDatabase.execSQL(sqlQuery);
    }

    public void addEmployee(Employee employee) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMPLOYEE_NAME, employee.name);
        contentValues.put(EMPLOYEE_ID, employee.id);
        contentValues.put(EMPLOYEE_DESIGNATION, employee.designation);
        contentValues.put(SALARY_PER_DAY, employee.salaryPerDay);
        long newRowId = sqLiteDatabase.insert(EMPLOYEE_TABLE, null, contentValues);
        Log.d("moghan", Long.toString(newRowId));
    }

    public List<Employee> getEmployeesByName(String name) {
        String selectionClause = EMPLOYEE_NAME + " = ?";
        Cursor cursor = sqLiteDatabase.query(EMPLOYEE_TABLE, null, selectionClause, new String[]{StringService.getSentenceCase(name)}, null, null, null);
        List<Employee> employeesList = new ArrayList<>();
        if(cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(EMPLOYEE_ID));
            String designation = cursor.getString(cursor.getColumnIndexOrThrow(EMPLOYEE_DESIGNATION));
            int salaryPerDay = cursor.getInt(cursor.getColumnIndexOrThrow(SALARY_PER_DAY));
            Employee employee = new Employee(name, id, designation, salaryPerDay);
            employeesList.add(employee);
        }
        cursor.close();
        return employeesList;
    }

    public Employee getEmployeeByID(String id) {
        String selectionClause = EMPLOYEE_ID + " = ?";
        Cursor cursor = sqLiteDatabase.query(EMPLOYEE_TABLE, null, selectionClause, new String[]{id}, null, null, null);
        Employee employee = null;
        if(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(EMPLOYEE_NAME));
            String designation = cursor.getString(cursor.getColumnIndexOrThrow(EMPLOYEE_DESIGNATION));
            int salaryPerDay = cursor.getInt(cursor.getColumnIndexOrThrow(SALARY_PER_DAY));
            employee = new Employee(name, id, designation, salaryPerDay);
        }
        cursor.close();
        return employee;
    }

    public List<Employee> getAllEmployees() {
        Cursor cursor = sqLiteDatabase.query(EMPLOYEE_TABLE, null, null, null, null, null, null);
        List<Employee> employeesList = new ArrayList<>();
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(EMPLOYEE_NAME));
            String id = cursor.getString(cursor.getColumnIndexOrThrow(EMPLOYEE_ID));
            String designation = cursor.getString(cursor.getColumnIndexOrThrow(EMPLOYEE_DESIGNATION));
            int salaryPerDay = cursor.getInt(cursor.getColumnIndexOrThrow(SALARY_PER_DAY));
            Employee employee = new Employee(name, id, designation, salaryPerDay);
            employeesList.add(employee);
            Log.d(id, name);

        }
        cursor.close();
        return employeesList;
    }

    public void removeEmployeeByID(String id) {
        String selectionClause = EMPLOYEE_ID + " = ?";
        sqLiteDatabase.delete(EMPLOYEE_TABLE, selectionClause, new String[]{id});
    }

    public void removeAllEmployees() {
        List<Employee> employeesList = getAllEmployees();
        for(Employee employee: employeesList) {
            removeEmployeeByID(employee.id);
        }
    }
}

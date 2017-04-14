package com.amazecreationz.employeemeter.wrappers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.amazecreationz.employeemeter.R;
import com.amazecreationz.employeemeter.models.Employee;

import java.util.List;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 14/4/17 5:59 PM.
 */

public class EmployeeArrayAdapter extends ArrayAdapter {

    private static class ViewHolder {
        TextView name;
        TextView designation;
    }

        public EmployeeArrayAdapter(Context context, List<Employee> employeesList) {
        super(context, R.layout.spinner_employee, employeesList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Employee employee = (Employee) getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.spinner_employee, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.spinner_employee_name);
            viewHolder.designation = (TextView) convertView.findViewById(R.id.spinner_employee_designation);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(employee.name);
        viewHolder.designation.setText(employee.designation);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Employee employee = (Employee) getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.spinner_employee_dropdown, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.spinner_employee_name);
            viewHolder.designation = (TextView) convertView.findViewById(R.id.spinner_employee_designation);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(employee.name);
        viewHolder.designation.setText(employee.designation);
        return convertView;
    }
}

package com.task.cdse.sqlite

import android.provider.BaseColumns

class DBContract {
    /* Inner class that defines the table contents */
    class DepartmentEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "department"
            const val COLUMN_ID = "id"
            const val COLUMN_DEPT_NAME = "dept_name"
            const val COLUMN_DEPT_NO = "dept_no"
        }
    }

    class DepartmentManagerEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "department_manager"
            const val COLUMN_ID = "id"
            const val COLUMN_EMP_NO = "emp_no"
            const val COLUMN_DEPT_NO = "dept_no"
            const val COLUMN_FROM_DATE = "from_date"
            const val COLUMN_TO_DATE = "to_date"
        }
    }

    class EmployeeEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "employee"
            const val COLUMN_EMP_NO = "emp_no"
            const val COLUMN_FIRST_NAME = "first_name"
            const val COLUMN_LAST_NAME = "last_name"
            const val COLUMN_GENDER = "gender"
            const val COLUMN_HIRE_DATE = "hire_date"
            const val COLUMN_BIRTH_DATE = "birth_date"
        }
    }

    class EmployeeManagementEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "employee_management"
            const val COLUMN_ID = "id"
            const val COLUMN_EMP_NO = "emp_no"
            const val COLUMN_DEPT_NO = "dept_no"
            const val COLUMN_FROM_DATE = "from_date"
            const val COLUMN_TO_DATE = "to_date"
        }
    }

    class SalariesEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "salaries"
            const val COLUMN_ID = "id"
            const val COLUMN_EMP_NO = "emp_no"
            const val COLUMN_SALARY = "salary"
            const val COLUMN_FROM_DATE = "from_date"
            const val COLUMN_TO_DATE = "to_date"
        }
    }

    class TitlesEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "titles"
            const val COLUMN_ID = "id"
            const val COLUMN_EMP_NO = "emp_no"
            const val COLUMN_TITLE = "title"
            const val COLUMN_FROM_DATE = "from_date"
            const val COLUMN_TO_DATE = "to_date"
        }
    }
}
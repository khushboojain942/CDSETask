package com.task.cdse.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.task.cdse.model.DepartmentItemModel
import com.task.cdse.model.DepartmentManagerItemModel
import com.task.cdse.model.EmployeeDepartmentItemModel
import com.task.cdse.model.EmployeeItemModel
import com.task.cdse.model.SalariesItemModel
import com.task.cdse.model.TitlesItemModel


@SuppressLint("Range")
class UsersDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_DEPARTMENT)
        db.execSQL(SQL_CREATE_DEPARTMENT_MANAGER)
        db.execSQL(SQL_CREATE_EMPLOYEE)
        db.execSQL(SQL_CREATE_EMPLOYEE_MANAGEMENT)
        db.execSQL(SQL_CREATE_SALARIES)
        db.execSQL(SQL_CREATE_TITLES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_DEPARTMENT)
        db.execSQL(SQL_DELETE_DEPARTMENT_MANAGER)
        db.execSQL(SQL_DELETE_EMPLOYEE)
        db.execSQL(SQL_DELETE_EMPLOYEE_MANAGEMENT)
        db.execSQL(SQL_DELETE_TITLES)
        db.execSQL(SQL_DELETE_SALARIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertDepartment(user: List<DepartmentItemModel>) {
        // Gets the data repository in write mode
        val db = writableDatabase
        db.beginTransaction()
        try {
            for (department in user) {
                val values = ContentValues()
                values.put(DBContract.DepartmentEntry.COLUMN_DEPT_NO, department.deptNo)
                values.put(DBContract.DepartmentEntry.COLUMN_DEPT_NAME, department.deptName)

                // Insert the new row, returning the primary key value of the new row
                db.insert(DBContract.DepartmentEntry.TABLE_NAME, null, values)
            }
            db.setTransactionSuccessful()
        }
        finally {
            db.endTransaction()
        }
        db.close()
    }

    @Throws(SQLiteConstraintException::class)
    fun insertDepartmentManager(user: List<DepartmentManagerItemModel>) {
        // Gets the data repository in write mode
        val db = writableDatabase
        db.beginTransaction()
        try {
            for (department in user) {
                val values = ContentValues()
                values.put(DBContract.DepartmentManagerEntry.COLUMN_DEPT_NO, department.deptNo)
                values.put(DBContract.DepartmentManagerEntry.COLUMN_EMP_NO, department.empNo)
                values.put(DBContract.DepartmentManagerEntry.COLUMN_FROM_DATE, department.fromDate)
                values.put(DBContract.DepartmentManagerEntry.COLUMN_TO_DATE, department.toDate)

                // Insert the new row, returning the primary key value of the new row
                db.insert(DBContract.DepartmentManagerEntry.TABLE_NAME, null, values)
            }
            db.setTransactionSuccessful()
        }
        finally {
            db.endTransaction()
        }
        db.close()

    }

    @Throws(SQLiteConstraintException::class)
    fun insertEmployee(user: List<EmployeeItemModel>) {
        // Gets the data repository in write mode
        val db = writableDatabase
        db.beginTransaction()
        try {
            for (department in user) {
                val values = ContentValues()
                values.put(DBContract.EmployeeEntry.COLUMN_EMP_NO, department.empNo)
                values.put(DBContract.EmployeeEntry.COLUMN_BIRTH_DATE, department.birthDate)
                values.put(DBContract.EmployeeEntry.COLUMN_GENDER, department.gender)
                values.put(DBContract.EmployeeEntry.COLUMN_HIRE_DATE, department.hireDate)
                values.put(DBContract.EmployeeEntry.COLUMN_FIRST_NAME, department.firstName)
                values.put(DBContract.EmployeeEntry.COLUMN_LAST_NAME, department.lastName)

                // Insert the new row, returning the primary key value of the new row
                db.insert(DBContract.EmployeeEntry.TABLE_NAME, null, values)
            }
            db.setTransactionSuccessful()
        }
        finally {
            db.endTransaction()
        }
        db.close()
    }

    @Throws(SQLiteConstraintException::class)
    fun insertEmployeeDepartment(user: List<EmployeeDepartmentItemModel>) {
        // Gets the data repository in write mode
        val db = writableDatabase
        db.beginTransaction()
        try {
            for (department in user) {
                val values = ContentValues()
                values.put(DBContract.EmployeeManagementEntry.COLUMN_EMP_NO, department.deptNo)
                values.put(DBContract.EmployeeManagementEntry.COLUMN_FROM_DATE, department.fromDate)
                values.put(DBContract.EmployeeManagementEntry.COLUMN_DEPT_NO, department.empNo)
                values.put(DBContract.EmployeeManagementEntry.COLUMN_TO_DATE, department.toDate)

                // Insert the new row, returning the primary key value of the new row
                db.insert(DBContract.EmployeeManagementEntry.TABLE_NAME, null, values)
            }
            db.setTransactionSuccessful()
        }
        finally {
            db.endTransaction()
        }
        db.close()
        // Create a new map of values, where column names are the keys

    }

    @Throws(SQLiteConstraintException::class)
    fun insertSalaries(user: List<SalariesItemModel>) {
        // Gets the data repository in write mode
        val db = writableDatabase
        db.beginTransaction()
        try {
            for (department in user) {
                val values = ContentValues()
                values.put(DBContract.SalariesEntry.COLUMN_EMP_NO, department.empNo)
                values.put(DBContract.SalariesEntry.COLUMN_SALARY, department.salary)
                values.put(DBContract.SalariesEntry.COLUMN_FROM_DATE, department.fromDate)
                values.put(DBContract.SalariesEntry.COLUMN_TO_DATE, department.toDate)

                // Insert the new row, returning the primary key value of the new row
                db.insert(DBContract.SalariesEntry.TABLE_NAME, null, values)
            }
            db.setTransactionSuccessful()
        }
        finally {
            db.endTransaction()
        }
        db.close()
    }

    @Throws(SQLiteConstraintException::class)
    fun insertTitle(user: List<TitlesItemModel>): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        for (department in user) {
            val values = ContentValues()
            values.put(DBContract.TitlesEntry.COLUMN_EMP_NO, department.empNo)
            values.put(DBContract.TitlesEntry.COLUMN_FROM_DATE, department.fromDate)
            values.put(DBContract.TitlesEntry.COLUMN_TO_DATE, department.toDate)
            values.put(DBContract.TitlesEntry.COLUMN_TITLE, department.title)

            // Insert the new row, returning the primary key value of the new row
            db.insert(DBContract.TitlesEntry.TABLE_NAME, null, values)
        }

        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteAllTables() {
        val db = writableDatabase
        db.delete(DBContract.DepartmentEntry.TABLE_NAME, null, null)
        db.delete(DBContract.DepartmentManagerEntry.TABLE_NAME, null, null)
        db.delete(DBContract.EmployeeEntry.TABLE_NAME, null, null)
        db.delete(DBContract.EmployeeManagementEntry.TABLE_NAME, null, null)
        db.delete(DBContract.SalariesEntry.TABLE_NAME, null, null)
        db.delete(DBContract.TitlesEntry.TABLE_NAME, null, null)
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteUser(userid: String): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = DBContract.DepartmentEntry.COLUMN_DEPT_NO + " LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(userid)
        // Issue SQL statement.
        db.delete(DBContract.DepartmentEntry.TABLE_NAME, selection, selectionArgs)

        return true
    }

    fun readDepartment(name: String): ArrayList<DepartmentItemModel> {
        val users = ArrayList<DepartmentItemModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(
                "select * from " + DBContract.DepartmentEntry.TABLE_NAME
                        + " WHERE " + DBContract.DepartmentEntry.COLUMN_DEPT_NAME + "='" + name + "'",
                null
            )
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_DEPARTMENT)
            return ArrayList()
        }

        if (cursor?.moveToFirst() == true) {
            while (!cursor.isAfterLast) {
                val deptName =
                    cursor.getString(cursor.getColumnIndex(DBContract.DepartmentEntry.COLUMN_DEPT_NAME))
                val deptNo =
                    cursor.getString(cursor.getColumnIndex(DBContract.DepartmentEntry.COLUMN_DEPT_NO))

                users.add(DepartmentItemModel(deptName, deptNo))
                cursor.moveToNext()
            }
        }
        return users
    }

    fun readEmployee(name: String): ArrayList<EmployeeItemModel> {
        val users = ArrayList<EmployeeItemModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(
                "select * from " + DBContract.EmployeeEntry.TABLE_NAME
                        + " WHERE " + DBContract.EmployeeEntry.COLUMN_FIRST_NAME + "='" + name + "'"
                        + " OR " + DBContract.EmployeeEntry.COLUMN_LAST_NAME + "='" + name + "'",
                null
            )
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_DEPARTMENT)
            return ArrayList()
        }

        if (cursor?.moveToFirst() == true) {
            while (!cursor.isAfterLast) {
                users.add(
                    EmployeeItemModel(
                        cursor.getString(cursor.getColumnIndex(DBContract.EmployeeEntry.COLUMN_EMP_NO)),
                        cursor.getString(cursor.getColumnIndex(DBContract.EmployeeEntry.COLUMN_FIRST_NAME)),
                        cursor.getString(cursor.getColumnIndex(DBContract.EmployeeEntry.COLUMN_LAST_NAME)),
                        cursor.getString(cursor.getColumnIndex(DBContract.EmployeeEntry.COLUMN_BIRTH_DATE)),
                        cursor.getString(cursor.getColumnIndex(DBContract.EmployeeEntry.COLUMN_GENDER)),
                        cursor.getString(cursor.getColumnIndex(DBContract.EmployeeEntry.COLUMN_HIRE_DATE))
                    )
                )
                cursor.moveToNext()
            }
        }
        return users
    }

    fun readEmployeeByIds(empNo: String): EmployeeItemModel {
        var users: EmployeeItemModel = EmployeeItemModel()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(
                "select * from " + DBContract.EmployeeEntry.TABLE_NAME
                        + " WHERE " + DBContract.EmployeeEntry.COLUMN_EMP_NO + "='" + empNo + "'",
                null
            )
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_DEPARTMENT)
            return EmployeeItemModel()
        }

        if (cursor?.moveToFirst() == true) {
            do {
                users = EmployeeItemModel(
                    cursor.getString(cursor.getColumnIndex(DBContract.EmployeeEntry.COLUMN_EMP_NO)),
                    cursor.getString(cursor.getColumnIndex(DBContract.EmployeeEntry.COLUMN_FIRST_NAME)),
                    cursor.getString(cursor.getColumnIndex(DBContract.EmployeeEntry.COLUMN_LAST_NAME)),
                    cursor.getString(cursor.getColumnIndex(DBContract.EmployeeEntry.COLUMN_BIRTH_DATE)),
                    cursor.getString(cursor.getColumnIndex(DBContract.EmployeeEntry.COLUMN_GENDER)),
                    cursor.getString(cursor.getColumnIndex(DBContract.EmployeeEntry.COLUMN_HIRE_DATE))
                )

            } while (cursor.moveToNext())

        }
        return users
    }

    fun readEmployeeManager(deptNo: String): ArrayList<EmployeeDepartmentItemModel> {
        val users = ArrayList<EmployeeDepartmentItemModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(
                "select * from " + DBContract.EmployeeManagementEntry.TABLE_NAME
                        + " WHERE " + DBContract.EmployeeManagementEntry.COLUMN_DEPT_NO + "='" + deptNo + "'",
                null
            )
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_DEPARTMENT)
            return ArrayList()
        }

        if (cursor?.moveToFirst() == true) {
            while (!cursor.isAfterLast) {
                users.add(
                    EmployeeDepartmentItemModel(
                        cursor.getString(cursor.getColumnIndex(DBContract.EmployeeManagementEntry.COLUMN_DEPT_NO)),
                        cursor.getString(cursor.getColumnIndex(DBContract.EmployeeManagementEntry.COLUMN_EMP_NO)),
                        cursor.getString(cursor.getColumnIndex(DBContract.EmployeeManagementEntry.COLUMN_FROM_DATE)),
                        cursor.getString(cursor.getColumnIndex(DBContract.EmployeeManagementEntry.COLUMN_TO_DATE))
                    )
                )
                cursor.moveToNext()
            }
        }
        return users
    }

    fun readSalaries(empNo: String): ArrayList<SalariesItemModel> {
        val users = ArrayList<SalariesItemModel>()
        val db = writableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(
                "select * from " + DBContract.SalariesEntry.TABLE_NAME
                        + " WHERE " + DBContract.SalariesEntry.COLUMN_EMP_NO + "='" + empNo + "'",
                null
            )
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_DEPARTMENT)
            return ArrayList()
        }

        if (cursor?.moveToFirst() == true) {
            while (!cursor.isAfterLast) {
                users.add(
                    SalariesItemModel(
                        cursor.getString(cursor.getColumnIndex(DBContract.SalariesEntry.COLUMN_EMP_NO)),
                        cursor.getString(cursor.getColumnIndex(DBContract.SalariesEntry.COLUMN_SALARY)),
                        cursor.getString(cursor.getColumnIndex(DBContract.SalariesEntry.COLUMN_FROM_DATE)),
                        cursor.getString(cursor.getColumnIndex(DBContract.SalariesEntry.COLUMN_TO_DATE))
                    )
                )
                cursor.moveToNext()
            }
        }
        return users
    }

    fun readTitle(empNo: String): TitlesItemModel {
        var users = TitlesItemModel()
        val db = writableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(
                "select * from " + DBContract.TitlesEntry.TABLE_NAME
                        + " WHERE " + DBContract.TitlesEntry.COLUMN_EMP_NO + "='" + empNo + "'",
                null
            )
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_DEPARTMENT)
            return TitlesItemModel()
        }

        if (cursor?.moveToFirst() == true) {
            do {
                users = TitlesItemModel(
                    cursor.getString(cursor.getColumnIndex(DBContract.TitlesEntry.COLUMN_EMP_NO)),
                    cursor.getString(cursor.getColumnIndex(DBContract.TitlesEntry.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(DBContract.TitlesEntry.COLUMN_FROM_DATE)),
                    cursor.getString(cursor.getColumnIndex(DBContract.TitlesEntry.COLUMN_TO_DATE))
                )

            } while (cursor.moveToNext())

        }
        return users
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "CDSCDatabase.db"

        private const val SQL_CREATE_DEPARTMENT =
            "CREATE TABLE IF NOT EXISTS " + DBContract.DepartmentEntry.TABLE_NAME + " (" +
                    DBContract.SalariesEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.DepartmentEntry.COLUMN_DEPT_NO + " TEXT," +
                    DBContract.DepartmentEntry.COLUMN_DEPT_NAME + " TEXT)"

        private const val SQL_CREATE_DEPARTMENT_MANAGER = ("CREATE TABLE IF NOT EXISTS "
                + DBContract.DepartmentManagerEntry.TABLE_NAME +
                " (" +  DBContract.DepartmentManagerEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBContract.DepartmentManagerEntry.COLUMN_EMP_NO + " TEXT," +
                DBContract.DepartmentManagerEntry.COLUMN_FROM_DATE + " TEXT, " +
                DBContract.DepartmentManagerEntry.COLUMN_TO_DATE + " TEXT, " +
                DBContract.DepartmentManagerEntry.COLUMN_DEPT_NO + " TEXT, " +
                " FOREIGN KEY (" + DBContract.DepartmentManagerEntry.COLUMN_DEPT_NO + ") REFERENCES "
                + DBContract.DepartmentEntry.TABLE_NAME + "("
                + DBContract.DepartmentEntry.COLUMN_DEPT_NO + "))");

        private const val SQL_CREATE_EMPLOYEE_MANAGEMENT = ("CREATE TABLE IF NOT EXISTS "
                + DBContract.EmployeeManagementEntry.TABLE_NAME +
                " (" +DBContract.EmployeeManagementEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBContract.EmployeeManagementEntry.COLUMN_EMP_NO + " TEXT," +
                DBContract.EmployeeManagementEntry.COLUMN_FROM_DATE + " TEXT, " +
                DBContract.EmployeeManagementEntry.COLUMN_TO_DATE + " TEXT, " +
                DBContract.EmployeeManagementEntry.COLUMN_DEPT_NO + " TEXT, " +
                " FOREIGN KEY (" + DBContract.EmployeeManagementEntry.COLUMN_DEPT_NO + ") REFERENCES "
                + DBContract.DepartmentEntry.TABLE_NAME + "("
                + DBContract.DepartmentEntry.COLUMN_DEPT_NO + "))");

        private
        const val SQL_CREATE_EMPLOYEE =
            "CREATE TABLE IF NOT EXISTS " + DBContract.EmployeeEntry.TABLE_NAME + " (" +
                     DBContract.EmployeeEntry.COLUMN_EMP_NO + " TEXT PRIMARY KEY," +
                    DBContract.EmployeeEntry.COLUMN_FIRST_NAME + " TEXT," +
                    DBContract.EmployeeEntry.COLUMN_LAST_NAME + " TEXT," +
                    DBContract.EmployeeEntry.COLUMN_GENDER + " TEXT," +
                    DBContract.EmployeeEntry.COLUMN_BIRTH_DATE + " TEXT," +
                    DBContract.EmployeeEntry.COLUMN_HIRE_DATE + " TEXT)"

        private
        const val SQL_CREATE_SALARIES =
            "CREATE TABLE IF NOT EXISTS " + DBContract.SalariesEntry.TABLE_NAME + " (" +
                    DBContract.SalariesEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.SalariesEntry.COLUMN_EMP_NO + " TEXT," +
                    DBContract.SalariesEntry.COLUMN_SALARY + " TEXT," +
                    DBContract.SalariesEntry.COLUMN_FROM_DATE + " TEXT," +
                    DBContract.SalariesEntry.COLUMN_TO_DATE + " TEXT, " +
                    " FOREIGN KEY (" + DBContract.SalariesEntry.COLUMN_EMP_NO +
                    ") REFERENCES " + DBContract.EmployeeEntry.TABLE_NAME + "(" +
                    DBContract.EmployeeEntry.COLUMN_EMP_NO + "))"

        private
        const val SQL_CREATE_TITLES =
            "CREATE TABLE IF NOT EXISTS " + DBContract.TitlesEntry.TABLE_NAME + " (" +
                    DBContract.TitlesEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.TitlesEntry.COLUMN_EMP_NO + " TEXT," +
                    DBContract.TitlesEntry.COLUMN_TITLE + " TEXT," +
                    DBContract.TitlesEntry.COLUMN_FROM_DATE + " TEXT," +
                    DBContract.TitlesEntry.COLUMN_TO_DATE + " TEXT, " +
                    " FOREIGN KEY (" + DBContract.TitlesEntry.COLUMN_EMP_NO +
                    ") REFERENCES " + DBContract.EmployeeEntry.TABLE_NAME + "(" +
                    DBContract.EmployeeEntry.COLUMN_EMP_NO + "))"

        private
        const val SQL_DELETE_DEPARTMENT =
            "DROP TABLE IF EXISTS " + DBContract.DepartmentEntry.TABLE_NAME

        private
        const val SQL_DELETE_DEPARTMENT_MANAGER =
            "DROP TABLE IF EXISTS " + DBContract.DepartmentManagerEntry.TABLE_NAME

        private
        const val SQL_DELETE_EMPLOYEE =
            "DROP TABLE IF EXISTS " + DBContract.EmployeeEntry.TABLE_NAME

        private
        const val SQL_DELETE_EMPLOYEE_MANAGEMENT =
            "DROP TABLE IF EXISTS " + DBContract.EmployeeManagementEntry.TABLE_NAME

        private
        const val SQL_DELETE_SALARIES =
            "DROP TABLE IF EXISTS " + DBContract.SalariesEntry.TABLE_NAME

        private
        const val SQL_DELETE_TITLES =
            "DROP TABLE IF EXISTS " + DBContract.TitlesEntry.TABLE_NAME
    }
}
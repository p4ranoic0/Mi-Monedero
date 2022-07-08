package com.hgarcia.controlgastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hgarcia.controlgastos.Fragment.ListasFragment;

public class Datos extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME ="monedero.db";

    public Datos(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    public Datos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE movimientos(" +
                "idmovimiento INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fecha DATETIME DEFAULT CURRENT_TIMESTAMP,"+
                "descripcion TEXT," +
                "monto float," +
                "movimiento int)");
    }

    public long registrarMovimiento(Datos datos,String descripcion, float monto, int movimiento){
        SQLiteDatabase sqLiteDatabase = datos.getWritableDatabase();
        //sqLiteDatabase.execSQL("INSERT INTO....");
        ContentValues contentValues = new ContentValues();
        contentValues.put("descripcion",descripcion);
        contentValues.put("monto",monto);
        contentValues.put("movimiento",movimiento);

        long nuevoId = sqLiteDatabase.insert("movimientos",null,contentValues);

        return nuevoId;
    }

    public Cursor mostrarTodo(Datos datos){
        SQLiteDatabase sqLiteDatabase = datos.getReadableDatabase();
        String consultaSQL = "SELECT * FROM movimientos";
        return sqLiteDatabase.rawQuery(consultaSQL,null);
    }


    public Cursor mostrarIngresos(Datos datos){
        SQLiteDatabase sqLiteDatabase = datos.getReadableDatabase();
        String consultaSQL = "SELECT * FROM movimientos WHERE movimiento= 1";
        return sqLiteDatabase.rawQuery(consultaSQL,null);
    }
    public Cursor mostrarGasto(Datos datos){
        SQLiteDatabase sqLiteDatabase = datos.getReadableDatabase();
        String consultaSQL = "SELECT * FROM movimientos WHERE movimiento= -1";
        return sqLiteDatabase.rawQuery(consultaSQL,null);
    }

    public Cursor consultarDatos(Datos datos, String id){
        SQLiteDatabase sqLiteDatabase = datos.getReadableDatabase();
        String consultaSQL = "SELECT * FROM movimientos WHERE idmovimiento="+ id;
        String parametros[] = null;
        return sqLiteDatabase.rawQuery(consultaSQL,parametros);

        //long nombre = sqLiteDatabase.insert("movimientos",null,contentValues);
        //return nombre,precio,movimiento;
    }

    public long modificarDatos(Datos datos,String id,String descripcion, float monto, int movimiento){

        String []arg = new String[]{id};
        SQLiteDatabase sqLiteDatabase = datos.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("descripcion",descripcion);
        contentValues.put("monto",monto);
        contentValues.put("movimiento",movimiento);

        int idactual = sqLiteDatabase.update("movimientos", contentValues, "idmovimiento=?", arg);

        sqLiteDatabase.close();
        return idactual;
    }

    public long eliminarDatos(Datos datos,String id){
        String []arg = new String[]{id};
        SQLiteDatabase sqLiteDatabase = datos.getWritableDatabase();
        int ideliminado = sqLiteDatabase.delete("movimientos", "idmovimiento=?", arg);
        sqLiteDatabase.close();
        return ideliminado;
    }

    public Integer mostrarSaldoTotal(Datos datos){
        SQLiteDatabase sqLiteDatabase = datos.getReadableDatabase();
        String consultaSQL = "SELECT sum((monto)*(movimiento)) as Saldo_Total FROM movimientos";
        Cursor cursor= sqLiteDatabase.rawQuery(consultaSQL,null);
        cursor.moveToFirst();
        final Integer  saldo = cursor.getInt(0);
        sqLiteDatabase.close();
        return saldo;
    }





    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



}
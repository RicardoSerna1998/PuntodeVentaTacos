package com.example.ricardosernam.puntodeventa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ricardosernam.puntodeventa.provider.ContractParaProductos;

/**
 * Clase envoltura para el gestor de Bases de datos
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase database) {
        productos(database);
    }

    public static void productos(SQLiteDatabase database) {
        String cmd0 = "CREATE TABLE " + ContractParaProductos.CARRITO + " (" +
                ContractParaProductos.Columnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContractParaProductos.Columnas.DESCRIPCION + " TEXT, " +
                ContractParaProductos.Columnas.UBICACION + " DOUBLE, " +
                ContractParaProductos.Columnas.DISPONIBLE+ " DOUBLE, " +
                ContractParaProductos.Columnas.VENDEDOR + " TEXT, " +
                ContractParaProductos.Columnas.ID_REMOTA + " TEXT UNIQUE," +
                ContractParaProductos.Columnas.ESTADO + " INTEGER NOT NULL DEFAULT "+ ContractParaProductos.ESTADO_OK+"," +
                ContractParaProductos.Columnas.PENDIENTE_INSERCION + " INTEGER NOT NULL DEFAULT 0)";
        database.execSQL(cmd0);
        //database.execSQL("INSERT INTO estados (ip) values ('0')");

        String cmd = "CREATE TABLE " + ContractParaProductos.PRODUCTO + " (" +
                ContractParaProductos.Columnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContractParaProductos.Columnas.NOMBRE + " TEXT, " +
                ContractParaProductos.Columnas.PRECIO + " DOUBLE, " +
                ContractParaProductos.Columnas.PORCION + " DOUBLE, " +
                ContractParaProductos.Columnas.GUISADO + " VARCHAR(45), " +
                ContractParaProductos.Columnas.DISPONIBLE+ " DOUBLE, " +
                ContractParaProductos.Columnas.TIPO_PRODUCTO+ " VARCHAR(45), " +
                ContractParaProductos.Columnas.ID_REMOTA + " TEXT UNIQUE," +
                ContractParaProductos.Columnas.ESTADO + " INTEGER NOT NULL DEFAULT "+ ContractParaProductos.ESTADO_OK+"," +
                ContractParaProductos.Columnas.PENDIENTE_INSERCION + " INTEGER NOT NULL DEFAULT 0)";
        database.execSQL(cmd);
        String cmd2 = "CREATE TABLE " + ContractParaProductos.INVENTARIO + " (" +
                ContractParaProductos.Columnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContractParaProductos.Columnas.ID_CARRITO + " TEXT, " +
                ContractParaProductos.Columnas.FECHA + " INT, " +
                ContractParaProductos.Columnas.DISPONIBLE+ " INT, " +
                ContractParaProductos.Columnas.ID_REMOTA + " TEXT UNIQUE," +
                ContractParaProductos.Columnas.ESTADO + " INTEGER NOT NULL DEFAULT "+ ContractParaProductos.ESTADO_OK+"," +
                ContractParaProductos.Columnas.PENDIENTE_INSERCION + " INTEGER NOT NULL DEFAULT 0)";
        database.execSQL(cmd2);
        String cmd3 = "CREATE TABLE " + ContractParaProductos.INVENTARIO_DETALLE + " (" +
                ContractParaProductos.Columnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContractParaProductos.Columnas.ID_PRODUCTO + " TEXT, " +
                ContractParaProductos.Columnas.INVENTARIO_INICIAL + " DOUBLE, " +
                ContractParaProductos.Columnas.INVENTARIO_FINAL + " DOUBLE, " +
                ContractParaProductos.Columnas.ID_REMOTA + " TEXT," +
                ContractParaProductos.Columnas.ESTADO + " INTEGER NOT NULL DEFAULT "+ ContractParaProductos.ESTADO_OK+"," +
                ContractParaProductos.Columnas.PENDIENTE_INSERCION + " INTEGER NOT NULL DEFAULT 0)";
        database.execSQL(cmd3);

        String cmd4 = "CREATE TABLE " + ContractParaProductos.VENTA + " (" +
                ContractParaProductos.Columnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContractParaProductos.Columnas.ID_CARRITO + " INTEGER, " +
                ContractParaProductos.Columnas.ID_INVENTARIO + " INTEGER, " +
                ContractParaProductos.Columnas.FECHA + " TEXT, " +
                ContractParaProductos.Columnas.UBICACION + " DOUBLE, " +
                ContractParaProductos.Columnas.VENDEDOR + " TEXT, " +
                ContractParaProductos.Columnas.ID_REMOTA + " TEXT," +
                ContractParaProductos.Columnas.ESTADO + " INTEGER NOT NULL DEFAULT "+ ContractParaProductos.ESTADO_OK+"," +
                ContractParaProductos.Columnas.PENDIENTE_INSERCION + " INTEGER NOT NULL DEFAULT 0)";
        database.execSQL(cmd4);

        String cmd5 = "CREATE TABLE " + ContractParaProductos.VENTA_DETALLE + " (" +
                ContractParaProductos.Columnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContractParaProductos.Columnas.CANTIDAD + " INT, " +
                ContractParaProductos.Columnas.PRECIO + " DOUBLE, " +
                ContractParaProductos.Columnas.ID_PRODUCTO + " TEXT, " +
                ContractParaProductos.Columnas.ID_REMOTA + " TEXT," +
                ContractParaProductos.Columnas.ESTADO + " INTEGER NOT NULL DEFAULT "+ ContractParaProductos.ESTADO_OK+"," +
                ContractParaProductos.Columnas.PENDIENTE_INSERCION + " INTEGER NOT NULL DEFAULT 0)";
        database.execSQL(cmd5);
        String cmd6 = "CREATE TABLE IF NOT EXISTS " + ContractParaProductos.ESTADOS + " (" +
                ContractParaProductos.Columnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContractParaProductos.Columnas.IMPORTADO + " INTEGER NOT NULL DEFAULT 1, " +
                ContractParaProductos.Columnas.IP + " TEXT)";

        database.execSQL(cmd6);
        database.execSQL("INSERT INTO estados (ip) values ('0')");
        database.execSQL("INSERT INTO estados (importado) values (1)");

    }

    //@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            limpiar(db);
            onCreate(db);
    }

    public static void limpiar(SQLiteDatabase db) {
        db.execSQL("drop table if exists " + ContractParaProductos.VENTA_DETALLE);
        db.execSQL("drop table if exists " + ContractParaProductos.VENTA);
        db.execSQL("drop table if exists " + ContractParaProductos.INVENTARIO_DETALLE);
        db.execSQL("drop table if exists " + ContractParaProductos.INVENTARIO);
        db.execSQL("drop table if exists " + ContractParaProductos.PRODUCTO);
        db.execSQL("drop table if exists " + ContractParaProductos.CARRITO);
        productos(db);
    }

}

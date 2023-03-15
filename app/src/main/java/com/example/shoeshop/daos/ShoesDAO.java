package com.example.shoeshop.daos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoeshop.db.MyDatabase;
import com.example.shoeshop.model.ShoesDTO;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoesDAO implements Serializable {
    private MyDatabase conn = null;
    private SQLiteDatabase db = null;

    public ShoesDAO(Context context) {
        conn = new MyDatabase(context);
    }

    public ArrayList<ShoesDTO> getFruits() throws Exception {
        ArrayList<ShoesDTO> result = new ArrayList<>();
        int id, quantity;
        String name, description, image;
        double price;
        ShoesDTO dto = null;

        try {
            db = conn.getReadableDatabase();
            Cursor cs = db.rawQuery("SELECT Id, Name, Description, Price, Quantity, Image " +
                    "FROM Shoes", null);

            cs.moveToFirst();
            while (!cs.isAfterLast()) {
                id = cs.getInt(0);
                name = cs.getString(1);
                description = cs.getString(2);
                price = cs.getDouble(3);
                quantity = cs.getInt(4);
                image = cs.getString(5);
                dto = new ShoesDTO(id, name, description, price, quantity, image);
                result.add(dto);
                cs.moveToNext();
            }
        } finally {
            if (db != null) {
                db.close();
            }
        }

        return result;
    }
}

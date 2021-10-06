package com.example.plantdiary.db;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Converters {
    /*
    @TypeConverter
    public static Calendar toCalendar(Long l) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(l);
        return c;
    }

    @TypeConverter
    public static Long fromCalendar(Calendar c){
        return c == null ? null : c.getTime().getTime();
    }
*/
    @TypeConverter
    public static Calendar calendarFromTimestamp(String value) {
        if(value == null) {
            return null;
        }
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(Long.parseLong(value)*1000);
        return cal;
    }

    @TypeConverter
    public static String dateToTimestamp(Calendar cal) {
        if(cal == null) {
            return null;
        }
        return "" + cal.getTimeInMillis()/1000;
    }
    @TypeConverter
    public static String bitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        if(temp==null)
        {
            return null;
        }
        else
            return temp;
    }
  /*
    @TypeConverter
    public static byte
*/
    @TypeConverter
    public static Bitmap stringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            if (bitmap == null) {
                return null;
            } else {
                return bitmap;
            }

        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}

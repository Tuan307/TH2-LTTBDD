package com.example.th2de2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.th2de2.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tuanpham
 * @since 4/20/2023
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    private static final String dbName = "myDB";

    public MySqliteHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE dbs1(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "author TEXT," +
                "scope TEXT," +
                "subject TEXT," +
                "rating INTEGER)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public long addBook(Book book) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", book.getName());
        contentValues.put("author", book.getAuthor());
        contentValues.put("scope", book.getScope());
        contentValues.put("subject", book.getSubject());
        contentValues.put("rating", book.getRating());
        long result = sqLiteDatabase.insert("dbs1", null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public List<Book> getAllBook() {
        List<Book> bookList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM dbs1", null);
//        Cursor cursor = sqLiteDatabase.query("dbs", null, null,
//                null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String author = cursor.getString(2);
            String album = cursor.getString(3);
            String type = cursor.getString(4);
            float favourite = cursor.getFloat(5);
            Book book = new Book(id, name, author, album, type, favourite);
            bookList.add(book);
        }
        return bookList;
    }

//    public long updateBook(Book book) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", book.getName());
//        contentValues.put("author", book.getAuthor());
//        contentValues.put("album", book.getAlbum());
//        contentValues.put("type", book.getType());
//        contentValues.put("favourite", book.getFavourite());
//        long result = sqLiteDatabase.update("dbs",
//                contentValues, "_id=?",
//                new String[]{book.getId() + ""});
//        sqLiteDatabase.close();
//        return result;
//    }
//
//    public long deleteBook(int bookId) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        long result = sqLiteDatabase.delete("dbs", "_id=?",
//                new String[]{bookId + ""});
//        sqLiteDatabase.close();
//        return result;
//    }
//
//    public List<Book> findBooksByPrice(String startPrice, String endPrice) {
//        List<Book> listBook = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.query("books", null, "price BETWEEN ? AND ?"
//                , new String[]{startPrice, endPrice}, null, null, null);
//        while (cursor != null && cursor.moveToNext()) {
//            int id = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String author = cursor.getString(2);
//            String publishDate = cursor.getString(3);
//            String publisher = cursor.getString(4);
//            String price = cursor.getFloat(5) + "";
//            Book book = new Book(id, name, author, publishDate, publisher, price);
//            listBook.add(book);
//        }
//        return listBook;
//    }
//
//    public List<Book> getStatistic() {
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        List<Book> listBook = new ArrayList<>();
//        Cursor cursor = sqLiteDatabase.query("books", new String[]{"_id", "name", "author", "publishDate", "publisher", "MAX(price) AS price"},
//                null, null, "publisher", null, "price DESC");
//        while (cursor != null && cursor.moveToNext()) {
//            int id = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String author = cursor.getString(2);
//            String publishDate = cursor.getString(3);
//            String publisher = cursor.getString(4);
//            String price = cursor.getFloat(5) + "";
//            Book book = new Book(id, name, author, publishDate, publisher, price);
//            listBook.add(book);
//        }
//        return listBook;
//    }
}

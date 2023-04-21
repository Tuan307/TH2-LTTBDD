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


    /*
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM dbs1 WHERE name LIKE '%' || ? || '%'", null);
//        Cursor cursor = sqLiteDatabase.query("dbs", null, null,
//                null, null, null, null);
        String[] selectionArgs = { name};
        SQLiteDatabase st=getReadableDatabase();

        Cursor rs = st.rawQuery(query, selectionArgs);
     */
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

    public Cursor getBooksWithMaxPriceByAuthor() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = { "author", "MAX(price) AS max_price" };
        String groupBy = "author";
        Cursor cursor = db.query("books", columns, null, null, groupBy, null, null);
        return cursor;
        // String title = rs.getString(rs.getColumnIndexOrThrow("title"));
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

/*
    public List<Item> getItems(){
        List<Item> list=new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();
        String order = "date DESC";

        Cursor rs=st.query("items",null, null, null, null, null, order);
        while(rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String c = rs.getString(2);
            String p = rs.getString(3);
            String date = rs.getString(4);

            list.add(new Item(id, title, c, p, date));
        }
        return list;
    }

    public long insertItem(Item t){
        ContentValues values=new ContentValues();
        values.put("title",t.getTitle());
        values.put("category",t.getCategory());
        values.put("price",t.getPrice());
        values.put("date",t.getDate());
        SQLiteDatabase st=getWritableDatabase();
        return st.insert("items",null,values);
    }

    public List<Item> getFromDateToDateAndName(String name){
        List<Item> list=new ArrayList<>();
        String query = "SELECT * FROM items WHERE title LIKE '%' || ? || '%'";
        String[] selectionArgs = { name};
        SQLiteDatabase st=getReadableDatabase();

        Cursor rs = st.rawQuery(query, selectionArgs);

        while(rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String c = rs.getString(2);
            String p = rs.getString(3);
            String d = rs.getString(4);
            list.add(new Item(id, title, c, p, d));
        }
        return list;
    }
    public List<Item> getByDate(String date){
        List<Item> list=new ArrayList<>();
        String whereClause = "date like ?";
        String[] whereArgs = {date};
        SQLiteDatabase st=getReadableDatabase();

        Cursor rs = st.query("items",null, whereClause, whereArgs, null, null, null);

        while(rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String c = rs.getString(2);
            String p = rs.getString(3);

            list.add(new Item(id, title, c, p, date));
        }
        return list;
    }

    public int updateItem(Item t){
        ContentValues values=new ContentValues();
        values.put("title",t.getTitle());
        values.put("category",t.getCategory());
        values.put("price",t.getPrice());
        values.put("date",t.getDate());
        String where="id=?";
        String[] agrs={Integer.toString(t.getId())};
        SQLiteDatabase st=getWritableDatabase();
        return st.update("items",values,where,agrs);
    }

    public int deleteItem(int id){
        String where="id=?";
        String[] agrs={Integer.toString(id)};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete("items",where,agrs);
    }

    public List<Item> searchItemBykey(String key){
        List<Item> list=new ArrayList<>();
        String sql="select t.id,t.name,t.price,t.date,c.id,c.name " +
                "from categories c inner join items t " +
                "on (c.id=t.cid) where t.name like ? or c.name like ?";
        String[] agrs={"%"+key+"%","%"+key+"%"};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.rawQuery(sql,agrs);
        while(rs!=null && rs.moveToNext()){
            Category c=new Category(rs.getInt(4),rs.getString(5));
            list.add(new Item(rs.getInt(0),rs.getString(1),rs.getDouble(2),
                    rs.getString(3),c));
        }
        rs.close();
        return list;
    }
    public List<Item> getItemByfromPricetoPrice(double from,double to){
        List<Item> list=new ArrayList<>();
        String where="price between ? and ?";
        String[] agrs={Double.toString(from),Double.toString(to)};
        String orderby="price desc";
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("items",null,where,agrs,null,
                null,orderby);
        while(rs!=null && rs.moveToNext()){
            list.add( new Item(rs.getInt(0),rs.getString(1),rs.getDouble(3),
                    rs.getString(4),new Category(rs.getInt(2),"")));

        }
        return list;
    }
    public List<Item> searchByKey(String key){
        List<Item> list=new ArrayList<>();
        String where="name like ? or date like ?";
        String[] agrs={"%"+key+"%","%"+key+"%"};
        String orderby="date desc";
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("items",null,where,agrs,null,
                null,orderby);
        while(rs!=null && rs.moveToNext()){
            list.add( new Item(rs.getInt(0),rs.getString(1),rs.getDouble(3),
                    rs.getString(4),new Category(rs.getInt(2),"")));

        }
        return list;
    }
 */

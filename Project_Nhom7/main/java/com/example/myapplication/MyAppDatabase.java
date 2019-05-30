package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class MyAppDatabase extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";


    // Phiên bản
    private static final int DATABASE_VERSION = 18;

    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "Comic_App";


    // Tên bảng: Comic.
    private static final String TABLE_COMIC = "Comic";

    private static final String COLUMN_COMIC_ID = "Comic_Id";
    private static final String COLUMN_COMIC_NAME = "Comic_Name";
    private static final String COLUMN_COMIC_THUMBNAIL = "Comic_Thumbnail";
    private static final String COLUMN_COMIC_SUMMARY = "Comic_Summary";

    // Tên bảng: Chap
    private static final String TABLE_CHAP = "Chap";

    private static final String COLUMN_CHAP_ID = "Chap_Id";
    private static final String COLUMN_CHAP_COMICID = " Chap_ComicId";
    private static final String COLUMN_CHAP_NAME = "Chap_Name";
    private static final String COLUMN_CHAP_IMAGELINK = "Chap_ImagesLink";

    //Tên bảng: Subscribe
    private static final String TABLE_SUBSCRIBE= "Subscribe";

    private static final String COLUMN_SUBSCRIBE_ID = "Subscribe_Id";
    private static final String COLUMN_SUBSCRIBE_COMICID = "Subscribe_ComicId";

    //Tên bảng: History
    private static final String TABLE_HISTORY= "History";

    private static final String COLUMN_HISTORY_COMICID = "History_ComicId";
    private static final String COLUMN_HISTORY_CHAPID = "History_ChapId";
    private static final String COLUMN_HISTORY_POSITIONIMG = "History_PositionImg";

    //Tên bảng: History
    private static final String TABLE_CATEGORY= "Category";

    private static final String COLUMN_CATEGORY_ID = "Category_Id";
    private static final String COLUMN_CATEGORY_NAME = "Category_Name";
    private static final String COLUMN_CATEGORY_IMAGE= "Category_Image";

    //Tên bảng: CategoryComic
    private static final String TABLE_CATEGORYCOMIC= "CategoryComic";

    private static final String COLUMN_CATEGORYCOMIC_CATEGORYID = "Category_CategoryId";
    private static final String COLUMN_CATEGORYCOMIC_COMICID = "Category_ComicId";


    public MyAppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Script to create table.
        String comicTable = "CREATE TABLE " + TABLE_COMIC + "("
                + COLUMN_COMIC_ID + " INTEGER PRIMARY KEY," + COLUMN_COMIC_NAME + " TEXT,"
                + COLUMN_COMIC_THUMBNAIL + " INTEGER,"
                + COLUMN_COMIC_SUMMARY + " TEXT" + ")";

        String chapTable = "CREATE TABLE " + TABLE_CHAP + "("
                + COLUMN_CHAP_ID + " INTEGER PRIMARY KEY ,"
                + COLUMN_CHAP_COMICID + " INTEGER,"
                + COLUMN_CHAP_IMAGELINK + " TEXT,"
                + COLUMN_CHAP_NAME + " TEXT"
                 + ")";

        String subscribeTable = "CREATE TABLE " + TABLE_SUBSCRIBE + "("
                + COLUMN_SUBSCRIBE_ID + " INTEGER PRIMARY KEY ,"
                + COLUMN_SUBSCRIBE_COMICID + " INTEGER "
                + ")";

        String historyTable = "CREATE TABLE " + TABLE_HISTORY + "("
                + COLUMN_HISTORY_COMICID + " INTEGER  PRIMARY KEY,"
                + COLUMN_HISTORY_CHAPID + " INTEGER ,"
                + COLUMN_HISTORY_POSITIONIMG + " INTEGER"
                + ")";

        String categoryTable = "CREATE TABLE " + TABLE_CATEGORY + "("
                + COLUMN_CATEGORY_ID+ " INTEGER  PRIMARY KEY,"
                + COLUMN_CATEGORY_NAME + " TEXT ,"
                + COLUMN_CATEGORY_IMAGE + " INTEGER"
                + ")";

        String categorycomicTable = "CREATE TABLE " + TABLE_CATEGORYCOMIC + "("
                + COLUMN_CATEGORYCOMIC_COMICID+ " INTEGER ,"
                + COLUMN_CATEGORYCOMIC_CATEGORYID + " INTEGER "
                + ")";

        // Execute script.
        db.execSQL(comicTable);
        db.execSQL(chapTable);
        db.execSQL(subscribeTable);
        db.execSQL(historyTable);
        db.execSQL(categoryTable);
        db.execSQL(categorycomicTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMIC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBSCRIBE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORYCOMIC);
        // Recreate
        onCreate(db);
    }

    public void addComic(Comic comic) {
        Log.i(TAG, "MyDatabaseHelper.addComic ... " + comic.getTenTruyen());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_COMIC_NAME, comic.getTenTruyen());
        values.put(COLUMN_COMIC_THUMBNAIL, comic.getBiaTruyen());
        values.put(COLUMN_COMIC_SUMMARY, comic.getTomTat());


        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_COMIC, null, values);


        // Đóng kết nối database.
        db.close();
    }

    public Comic getComic(int id) {
        Log.i(TAG, "MyDatabaseHelper.getComic ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_COMIC, new String[]{COLUMN_COMIC_ID,
                        COLUMN_COMIC_NAME, COLUMN_COMIC_THUMBNAIL, COLUMN_COMIC_SUMMARY}, COLUMN_COMIC_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Comic comic = new Comic(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3));

        return comic;
    }

    public ArrayList<Comic> getComicsByName(String name) {
        Log.i(TAG, "MyDatabaseHelper.getAllComics ... ");

        ArrayList<Comic> comicList = new ArrayList<Comic>();
        // Select All Query


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_COMIC, new String[]{COLUMN_COMIC_ID,
                        COLUMN_COMIC_NAME, COLUMN_COMIC_THUMBNAIL, COLUMN_COMIC_SUMMARY}, COLUMN_COMIC_NAME + " LIKE ?",
                new String[] { "%" + name + "%" }, null, null, null, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Comic comic = new Comic();
                comic.setId(Integer.parseInt(cursor.getString(0)));
                comic.setTenTruyen(cursor.getString(1));
                comic.setBiaTruyen(Integer.parseInt(cursor.getString(2)));
                comic.setTomTat(cursor.getString(3));
                Log.i(TAG, "MyDatabaseHelper.getAllComics ... " + comic.getTenTruyen());
                // Thêm vào danh sách.
                comicList.add(comic);
            } while (cursor.moveToNext());
        }

        // return Comic list
        return comicList;
    }

    public ArrayList<Comic> getAllComics() {
        Log.i(TAG, "MyDatabaseHelper.getAllComics ... ");

        ArrayList<Comic> comicList = new ArrayList<Comic>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_COMIC;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Comic comic = new Comic();
                comic.setId(Integer.parseInt(cursor.getString(0)));
                comic.setTenTruyen(cursor.getString(1));
                comic.setBiaTruyen(Integer.parseInt(cursor.getString(2)));
                comic.setTomTat(cursor.getString(3));
                // Thêm vào danh sách.
                comicList.add(comic);
            } while (cursor.moveToNext());
        }

        // return Comic list
        return comicList;
    }

    public void createDefaultComic() {
        if (getComicsCount() == 0) {
            Comic comic1 = new Comic("Naruto Shippuden", R.drawable.thumbail_naruto, "Bối cảnh Naruto xảy ra vào mười hai năm trước khi câu chuyện chính thức bắt đầu, một con hồ ly chín đuôi đã tấn công Konohagakure. Nó là một con quái vật có sức mạnh khủng khiếp, chỉ một cái vẫy từ một trong chín cái đuôi của nó có thể tạo ra những cơn sóng thần và san bằng nhiều ngọn núi. Nó gây ra sự hỗn loạn và giết chết rất nhiều người cho đến khi người đứng đầu làng Lá – Hokage đệ tứ – đã đánh bại nó bằng cách đổi lấy mạng sống của mình để phong ấn nó vào trong người một đứa trẻ mới sinh. Đứa trẻ đó tên là Uzumaki Naruto. Bộ truyện kể về cuộc hành trình đầy gian khổ với vô vàn khó khăn, thử thách của Naruto từ khi còn là một cậu bé tới khi trở thành một trong những nhẫn giả vĩ đại nhất. Không chỉ mô tả về một thế giới nhẫn giả huyền bí, Naruto còn mang trong nó nhiều ý nghĩa nhân sinh sâu sắc về tình bạn, tình đồng đội, tình yêu, ước mơ và hi vọng.");
            Comic comic2 = new Comic("Conan", R.drawable.thumbail_conan, "Mở đầu câu truyện, cậu học sinh trung học 16 tuổi Shinichi Kudo bị biến thành cậu bé Conan Edogawa. Shinichi trong phần đầu của Thám tử lừng danh Conan được miêu tả là một thám tử học đường. Trong một lần đi chơi công viên \"Miền Nhiệt đới\" với cô bạn từ thuở nhỏ (cũng là bạn gái) Ran Mori , cậu bị dính vào vụ án một hành khách trên Chuyến tàu tốc hành trong công viên, Kishida , bị giết trong một vụ án cắt đầu rùng rợn. Cậu đã làm sáng tỏ vụ án và trên đường về nhà, chứng kiến một vụ làm ăn mờ ám của những người đàn ông mặc toàn đồ đen. Kudo bị phát hiện, bị đánh ngất sau đó những người đàn ông áo đen đã cho cậu uống một thứ thuốc độc chưa qua thử nghiệm là Apotoxin-4869 (APTX4869) với mục đích thủ tiêu cậu. Tuy nhiên chất độc đã không giết chết Kudo. Khi tỉnh lại, cậu bàng hoàng nhận thấy thân thể mình đã bị teo nhỏ trong hình dạng của một cậu học sinh tiểu học....");
            Comic comic3 = new Comic("Thất Hình Đại Tội", R.drawable.thumbail_daitoi, "“Thất đại ác nhân”, một nhóm chiến binh có tham vọng lật đổ vương quốc Britannia, được cho là đã bị tiêu diệt bởi các “hiệp sĩ thánh chiến” mặc dù vẫn còn 1 số người cho rằng họ vẫn còn sống. 10 năm sau, Các hiệp sĩ thánh chiến đã làm 1 cuộc đảo chính và khống chế đức vua, họ trở thành người cai trị độc tài mới của vương quốc. Elizabeth, con gái duy nhất của nhà vua, đã lên đường tìm “thất đại ác nhân” để nhờ họ tái chiếm lại vương quốc. Công chúa có thành công trong việc tìm kiếm “thất đại ác nhân”, các “hiệp sĩ thánh chiến” sẽ làm gì để ngăn chăn cô? xem các chap truyện sau sẽ rõ.");
            Comic comic4 = new Comic("Cu Shin", R.drawable.thumbail_cushin, "Crayon Shinchan xoay quanh những câu chuyện thú vị của một cậu nhóc 5 tuổi tinh quái Nohara Shinnosuke với bố Hiroshi và mẹ Misae cùng những người bạn vô cùng dễ thương ở trường mẫu giáo. Sự \"hiểu đời\" rất vô tư của nhóc Shin đã gây ra những tình huống dở khóc dở cười cho bao người lớn. Tuy nhiên đằng sau tiếng cười đó là những bài học nhẹ nhàng về cách nuôi dạy con trẻ khiến chúng ta phải suy ngẫm. ");
            Comic comic5 = new Comic("Code Gress", R.drawable.thumbail_codegress, "Đế chế Brittania xâm chiếm Nhật Bản sử dụng những tên nửa người nửa máy làm vũ khí, gọi là Knightmare Frames. Lelouch được C.C trao sức mạnh Geass và đứng lên giúp người dân Nhật Bản chống lại Brittania. Dựa trên một bộ Anime vô cùng nổi tiếng");
            Comic comic6 = new Comic("Dragon Ball", R.drawable.thumbail_dragon_ball, "Dragon Ball – Câu chuyện cuộc sống về Goku và những người bạn. Mọi người xem đến đây nên cũng đã hiểu phải không… Lại xuất hiện 1 rắc rối mới… Rắc rối đó là gì ?.. Không còn quan trọng nữa vì chúng ta vẫn còn ngọc rồng mà. ");
            Comic comic7 = new Comic("One Piece", R.drawable.thumbail_one_piece, "One Piece là câu truyện kể về Luffy và các thuyền viên của mình. Khi còn nhỏ, Luffy ước mơ trở thành Vua Hải Tặc. Cuộc sống của cậu bé thay đổi khi cậu vô tình có được sức mạnh có thể co dãn như cao su, nhưng đổi lại, cậu không bao giờ có thể bơi được nữa");
            Comic comic8 = new Comic("One Puch Man", R.drawable.thumbail_one_puch_man, "Một Manga thể loại siêu anh hùng với đặc trưng phồng tôm đấm phát chết luôn... và mang đậm tính chất troll của tác giả. ");
            Comic comic9 = new Comic("Akame ga Kill", R.drawable.thumbail_akame, "Tatsumi là một chiến binh nhỏ tuổi (chắc thế), vì muốn cứu ngôi làng của mình mà cậu cùng 2 người bạn đã dứt áo ra đi lên thủ đô, ");
            Comic comic10 = new Comic("Bleach", R.drawable.thumbail_bleach, "Nhân vật chính của Bleach là Ichigo Kurosaki có khả năng nhìn thấy những hồn ma. Cuộc sống của cậu thay đổi khi cậu gặp Rukia Kuchiki, một Thần Chết và là thành viên của Âm Giới. Khi chiến đấu với một yêu quái chuyên đi săn những người có năng lực tâm linh,");
            Comic comic11 = new Comic("Anothor", R.drawable.thumbail_anothor, "26 năm trước, tại lớp 3-3 của 1 trường trung học nọ, có 1 nữ sinh tên là Misaki. Cô ấy rất nổi tiếng trong trường vì giỏi học hành lẫn thể thao, lại là một cô gái rất tốt");
            Comic comic12 = new Comic("Doraemon", R.drawable.thumbail_doremon, "Nối tiếp truyện Đôrêmon thêm (The Doraemons), truyện xoay quanh về cuộc phiêu lưu của Nobita và nhóm Doraemons sau khi 7 người bạn thân tốt nghiệp Trường học Robot và bắt đầu cuộc sống riêng mình.");
            this.addComic(comic1);
            this.addComic(comic2);
            this.addComic(comic3);
            this.addComic(comic4);
            this.addComic(comic5);
            this.addComic(comic6);
            this.addComic(comic7);
            this.addComic(comic8);
            this.addComic(comic9);
            this.addComic(comic10);
            this.addComic(comic11);
            this.addComic(comic12);
        }

    }

    public int getComicsCount() {
        Log.i(TAG, "MyDatabaseHelper.getComicCount ... ");

        String countQuery = "SELECT  * FROM " + TABLE_COMIC;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public void deleteChuong(Chuong note) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CHAP, COLUMN_CHAP_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

    public void deleteComic(Comic note) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COMIC, COLUMN_COMIC_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }


    public void addChuong(Chuong chap) {
        Log.i(TAG, "MyDatabaseHelper.addChuong ... " + chap.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        //Arraylist<int> to String with ,
        String imagesLink = "";
        for (int i = 0; i < chap.getNoiDungHinhAnh().size(); i++) {
            imagesLink += Integer.toString(chap.getNoiDungHinhAnh().get(i)) + ",";
        }
        imagesLink = imagesLink.substring(0, imagesLink.length() - 1);

        //setup data
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHAP_COMICID, chap.getComicId());
        values.put(COLUMN_CHAP_NAME, chap.getTenChuong());
        values.put(COLUMN_CHAP_IMAGELINK, imagesLink);

        // add data
        db.insert(TABLE_CHAP, null, values);

        // Đóng kết nối database.
        db.close();
    }

    private ArrayList<Integer> convertStringToArray(String string) {
        //tranform string to ArrayList<Integer>
        ArrayList<String> imagesLinkRaw = new ArrayList<>(Arrays.asList(string.split(",")));
        ArrayList<Integer> imagesLink = new ArrayList<>();
        for (String str : imagesLinkRaw) {
            imagesLink.add(Integer.parseInt(str));
        }
        return imagesLink;
    }

    public Chuong getChuong(int id) {
        Log.i(TAG, "MyDatabaseHelper.getChuong ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CHAP, new String[]{COLUMN_CHAP_ID,
                        COLUMN_CHAP_COMICID, COLUMN_CHAP_NAME, COLUMN_CHAP_IMAGELINK}, COLUMN_CHAP_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ArrayList<Integer> imagesLink = convertStringToArray(cursor.getString(3));

        Chuong chuong = new Chuong(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2), imagesLink);

        return chuong;
    }

    public ArrayList<Chuong> getChuongByComicId(int comicId) {
        Log.i(TAG, "MyDatabaseHelper.getChuong ... " + comicId);

        ArrayList<Chuong> chapList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_CHAP + " WHERE " + COLUMN_CHAP_COMICID + "= ?";
        Cursor cursor = db.rawQuery(selectQuery,
                new String[]{String.valueOf(comicId)});

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Chuong chuong = new Chuong();
                chuong.setId(Integer.parseInt(cursor.getString(0)));
                chuong.setComicId(Integer.parseInt(cursor.getString(1)));
                chuong.setNoiDungHinhAnh(convertStringToArray(cursor.getString(2)));
                chuong.setTenChuong(cursor.getString(3));
                Log.i(TAG, "MyDatabaseHelper.getImgList ... " + chuong.getNoiDungHinhAnh());
                Log.i(TAG, "MyDatabaseHelper.Cursor ... " + cursor.getString(1));
                // Thêm vào danh sách.
                chapList.add(chuong);
            } while (cursor.moveToNext());
        }

        return chapList;
    }

    public ArrayList<Chuong> getAllChuong() {
        Log.i(TAG, "MyDatabaseHelper.getAllChuong ... ");

        ArrayList<Chuong> chapList = new ArrayList<Chuong>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CHAP;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Chuong chuong = new Chuong();
                chuong.setId(Integer.parseInt(cursor.getString(0)));
                chuong.setComicId(Integer.parseInt(cursor.getString(1)));
                chuong.setNoiDungHinhAnh(convertStringToArray(cursor.getString(2)));
                chuong.setTenChuong(cursor.getString(3));
                // Thêm vào danh sách.
                chapList.add(chuong);
            } while (cursor.moveToNext());
        }

        // return Comic list
        return chapList;
    }

    public void createDefaultChuong() {
        if (getChuongCount() == 0) {
            //Naruto
            ArrayList<Integer> arrImg = new ArrayList<>();
            arrImg.add(R.drawable.naruto_1_0);
            arrImg.add(R.drawable.naruto_1_1);
            arrImg.add(R.drawable.naruto_1_2);
            arrImg.add(R.drawable.naruto_1_3);
            arrImg.add(R.drawable.naruto_1_4);
            arrImg.add(R.drawable.naruto_1_5);
            arrImg.add(R.drawable.naruto_1_6);
            arrImg.add(R.drawable.naruto_1_7);
            arrImg.add(R.drawable.naruto_1_20);
            arrImg.add(R.drawable.naruto_1_21);
            Chuong chuong1 = new Chuong(1, "Chương 1", arrImg);

            arrImg = new ArrayList<>();
            arrImg.add(R.drawable.naruto_2_8);
            arrImg.add(R.drawable.naruto_2_9);
            arrImg.add(R.drawable.naruto_2_10);
            arrImg.add(R.drawable.naruto_2_11);
            arrImg.add(R.drawable.naruto_2_12);
            arrImg.add(R.drawable.naruto_2_13);
            arrImg.add(R.drawable.naruto_2_14);
            arrImg.add(R.drawable.naruto_2_15);
            arrImg.add(R.drawable.naruto_2_16);
            arrImg.add(R.drawable.naruto_2_17);
            Chuong chuong2 = new Chuong(1, "Chương 2", arrImg);

            arrImg = new ArrayList<>();
            arrImg.add(R.drawable.naruto_3_4);
            arrImg.add(R.drawable.naruto_3_5);
            arrImg.add(R.drawable.naruto_3_6);
            arrImg.add(R.drawable.naruto_3_7);
            arrImg.add(R.drawable.naruto_3_8);
            arrImg.add(R.drawable.naruto_3_9);
            arrImg.add(R.drawable.naruto_3_10);
            arrImg.add(R.drawable.naruto_3_11);
            arrImg.add(R.drawable.naruto_3_12);
            arrImg.add(R.drawable.naruto_3_12);
            Chuong chuong3 = new Chuong(1, "Chương 3", arrImg);

            arrImg = new ArrayList<>();
            arrImg.add(R.drawable.naruto_4_004);
            arrImg.add(R.drawable.naruto_4_005);
            arrImg.add(R.drawable.naruto_4_006);
            arrImg.add(R.drawable.naruto_4_007);
            arrImg.add(R.drawable.naruto_4_008);
            arrImg.add(R.drawable.naruto_4_009);
            arrImg.add(R.drawable.naruto_4_010);
            arrImg.add(R.drawable.naruto_4_011);
            arrImg.add(R.drawable.naruto_4_012);
            arrImg.add(R.drawable.naruto_4_013);
            arrImg.add(R.drawable.naruto_4_014);
            arrImg.add(R.drawable.naruto_4_015);
            arrImg.add(R.drawable.naruto_4_016);
            arrImg.add(R.drawable.naruto_4_017);
            arrImg.add(R.drawable.naruto_4_018);
            arrImg.add(R.drawable.naruto_4_019);
            Chuong chuong4 = new Chuong(1, "Chương 4", arrImg);

            this.addChuong(chuong1);
            this.addChuong(chuong2);
            this.addChuong(chuong3);
            this.addChuong(chuong4);

            //Connan
            Chuong chuong;
            arrImg = new ArrayList<>();
            arrImg.add(R.drawable.conan_1_09);
            arrImg.add(R.drawable.conan_1_10);
            arrImg.add(R.drawable.conan_1_11);
            arrImg.add(R.drawable.conan_1_12);
            arrImg.add(R.drawable.conan_1_13);
            arrImg.add(R.drawable.conan_1_14);
            arrImg.add(R.drawable.conan_1_15);
            chuong = new Chuong(2,"Chương 1", arrImg);
            this.addChuong(chuong);

            arrImg = new ArrayList<>();
            arrImg.add(R.drawable.conan_2_02);
            arrImg.add(R.drawable.conan_2_03);
            arrImg.add(R.drawable.conan_2_04);
            arrImg.add(R.drawable.conan_2_05);
            arrImg.add(R.drawable.conan_2_06);
            arrImg.add(R.drawable.conan_2_07);
            arrImg.add(R.drawable.conan_2_08);
            arrImg.add(R.drawable.conan_2_16);
            chuong = new Chuong(2,"Chương 2", arrImg);
            this.addChuong(chuong);

            arrImg = new ArrayList<>();
            arrImg.add(R.drawable.conan_3_09);
            arrImg.add(R.drawable.conan_3_10);
            arrImg.add(R.drawable.conan_3_11);
            arrImg.add(R.drawable.conan_3_12);
            arrImg.add(R.drawable.conan_3_13);
            arrImg.add(R.drawable.conan_3_14);
            arrImg.add(R.drawable.conan_3_15);
            arrImg.add(R.drawable.conan_3_16);
            chuong = new Chuong(2,"Chương 3", arrImg);
            this.addChuong(chuong);

            arrImg = new ArrayList<>();
            arrImg.add(R.drawable.conan_4_1);
            arrImg.add(R.drawable.conan_4_2);
            arrImg.add(R.drawable.conan_4_3);
            arrImg.add(R.drawable.conan_4_4);
            arrImg.add(R.drawable.conan_4_5);
            arrImg.add(R.drawable.conan_4_6);
            arrImg.add(R.drawable.conan_4_7);
            arrImg.add(R.drawable.conan_4_8);
            arrImg.add(R.drawable.conan_4_9);
            arrImg.add(R.drawable.conan_4_10);
            chuong = new Chuong(2,"Chương 4", arrImg);
            this.addChuong(chuong);

            //that hinh dai toi
            arrImg = new ArrayList<>();
            arrImg.add(R.drawable.thathinhdaitoi_1_0);
            arrImg.add(R.drawable.thathinhdaitoi_1_1);
            arrImg.add(R.drawable.thathinhdaitoi_1_2);
            arrImg.add(R.drawable.thathinhdaitoi_1_3);
            arrImg.add(R.drawable.thathinhdaitoi_1_4);
            arrImg.add(R.drawable.thathinhdaitoi_1_5);
            arrImg.add(R.drawable.thathinhdaitoi_1_6);
            arrImg.add(R.drawable.thathinhdaitoi_1_7);
            chuong = new Chuong(3,"Chương 1", arrImg);
            this.addChuong(chuong);

            arrImg = new ArrayList<>();
            arrImg.add(R.drawable.thathinhdaitoi_2_8);
            arrImg.add(R.drawable.thathinhdaitoi_2_9);
            arrImg.add(R.drawable.thathinhdaitoi_2_10);
            arrImg.add(R.drawable.thathinhdaitoi_2_11);
            arrImg.add(R.drawable.thathinhdaitoi_2_12);
            arrImg.add(R.drawable.thathinhdaitoi_2_13);
            arrImg.add(R.drawable.thathinhdaitoi_2_14);
            arrImg.add(R.drawable.thathinhdaitoi_2_15);
            chuong = new Chuong(3,"Chương 2", arrImg);
            this.addChuong(chuong);

            arrImg = new ArrayList<>();
            arrImg.add(R.drawable.thathinhdaitoi_3_2);
            arrImg.add(R.drawable.thathinhdaitoi_3_5);
            arrImg.add(R.drawable.thathinhdaitoi_3_7);
            arrImg.add(R.drawable.thathinhdaitoi_3_8);
            arrImg.add(R.drawable.thathinhdaitoi_3_16);
            arrImg.add(R.drawable.thathinhdaitoi_3_17);
            arrImg.add(R.drawable.thathinhdaitoi_3_18);
            arrImg.add(R.drawable.thathinhdaitoi_3_19);
            chuong = new Chuong(3,"Chương 3", arrImg);
            this.addChuong(chuong);

            arrImg = new ArrayList<>();
            arrImg.add(R.drawable.thathinhdaitoi_4_1);
            arrImg.add(R.drawable.thathinhdaitoi_4_2);
            arrImg.add(R.drawable.thathinhdaitoi_4_3);
            arrImg.add(R.drawable.thathinhdaitoi_4_4);
            arrImg.add(R.drawable.thathinhdaitoi_4_5);
            arrImg.add(R.drawable.thathinhdaitoi_4_6);
            arrImg.add(R.drawable.thathinhdaitoi_4_7);
            arrImg.add(R.drawable.thathinhdaitoi_4_8);
            chuong = new Chuong(3,"Chương 4", arrImg);
            this.addChuong(chuong);
        }


    }

    public int getChuongCount() {
        Log.i(TAG, "MyDatabaseHelper.getchuongCount ... ");

        String countQuery = "SELECT  * FROM " + TABLE_CHAP;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public void addSubscribe(int comicId){
        Log.i(TAG, "MyAppDatabase.addSubscribe ... " + comicId);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBSCRIBE_COMICID, comicId);


        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_SUBSCRIBE, null, values);


        // Đóng kết nối database.
        db.close();
    }

    public int getSubscribe(int comicId){
        Log.i(TAG, "MyAppDatabase.getSubscribe ... " + comicId);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SUBSCRIBE, new String[]{COLUMN_SUBSCRIBE_COMICID}, COLUMN_SUBSCRIBE_COMICID + "=?",
                new String[]{String.valueOf(comicId)}, null, null, null, null);

        if(!cursor.moveToFirst())
            return -1;

        return cursor.getInt(0);
    }

    public void deleteSubscribe(int comicId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUBSCRIBE, COLUMN_SUBSCRIBE_COMICID + " = ?",
                new String[]{String.valueOf(comicId)});
        db.close();
    }

    public ArrayList<Integer> getAllSubscribe(){
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SUBSCRIBE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<Integer> arrayList = new ArrayList<>();
        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                arrayList.add(cursor.getInt(1));
            } while (cursor.moveToNext());
        }

        return arrayList;
    }

    public void deleteAllSubscribe(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUBSCRIBE, null, null);
        db.close();
    }




    public History getHistory(int comicId){


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HISTORY, new String[]{COLUMN_HISTORY_COMICID, COLUMN_HISTORY_CHAPID, COLUMN_HISTORY_POSITIONIMG}, COLUMN_HISTORY_COMICID + "=?",
                new String[]{String.valueOf(comicId)}, null, null, null, null);

        if(!cursor.moveToFirst())
            return null;
        History history = new History();
        history.setComicId(cursor.getInt(0));
        history.setChapId(cursor.getInt(1));
        history.setPositionImg(cursor.getInt(2));
        Log.i(TAG, "MyAppDatabase.getHistory ... ComicId: " + history.getComicId() + " ChapId: " + history.getChapId() + "PositionImg" + history.getPositionImg());
        return history;
    }

    public void addHistory(History history){
        Log.i(TAG, "MyAppDatabase.addHistory ... comidid:" + history.getComicId() + " chapid:" + history.getChapId() + "positionImg" + history.getPositionImg());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HISTORY_COMICID, history.getComicId());
        values.put(COLUMN_HISTORY_CHAPID, history.getChapId());
        values.put(COLUMN_HISTORY_POSITIONIMG, history.getPositionImg());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_HISTORY, null, values);


        // Đóng kết nối database.
        db.close();
    }

    public void updateHistory(History history){
        Log.i(TAG, "MyAppDatabase.updateHistory ... comidid:" +  history.getComicId() + " chapid:" + history.getChapId() + "positionImg" + history.getPositionImg());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HISTORY_CHAPID, history.getChapId());
        values.put(COLUMN_HISTORY_POSITIONIMG, history.getPositionImg());

        // Trèn một dòng dữ liệu vào bảng.
        db.update(TABLE_HISTORY,  values, COLUMN_HISTORY_COMICID + "=" + history.getComicId(), null);


        // Đóng kết nối database.
        db.close();
    }

    public ArrayList<History> getAllHistory(){
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HISTORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<History> arrayList = new ArrayList<>();
        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                History history = new History();
                history.setComicId(cursor.getInt(0));
                history.setChapId(cursor.getInt(1));
                history.setPositionImg((cursor.getInt(2)));
                arrayList.add(history);
            } while (cursor.moveToNext());
        }

        return arrayList;
    }

    public void deleteAllHistory(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HISTORY, null, null);
        db.close();
    }

    public void addCategory(Category category){
        Log.i(TAG, "MyAppDatabase.addCategory ..." + category.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_NAME, category.getTenTheLoai());
        values.put(COLUMN_CATEGORY_IMAGE, category.getImage());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_CATEGORY, null, values);


        // Đóng kết nối database.
        db.close();
    }

    public Category getCategory(int categoryId){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CATEGORY, new String[]{COLUMN_CATEGORY_ID, COLUMN_CATEGORY_NAME, COLUMN_CATEGORY_IMAGE}, COLUMN_CATEGORY_ID + "=?",
                new String[]{String.valueOf(categoryId)}, null, null, null, null);

        if(!cursor.moveToFirst())
            return null;

        Category category = new Category();
        category.setId(cursor.getInt(0));
        category.setTenTheLoai(cursor.getString(1));
        category.setImage((cursor.getInt(2)));

        return category;
    }

    public ArrayList<Category> getAllCategory(){
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<Category> arrayList = new ArrayList<>();
        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(cursor.getInt(0));
                category.setTenTheLoai(cursor.getString(1));
                category.setImage((cursor.getInt(2)));
                arrayList.add(category);
            } while (cursor.moveToNext());
        }

        return arrayList;
    }

    public void deleteAllCategory(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORY, null, null);
        db.close();
    }

    public void createDefaultCategory(){
        if(getCategoryCount() == 0){
            Category category = new Category("Adventure", R.drawable.adventure_icon);
            addCategory(category);
            category = new Category("Magic", R.drawable.magic_icon);
            addCategory(category);
            category = new Category("Horror", R.drawable.horror_icon);
            addCategory(category);
            category = new Category("Fantasy", R.drawable.fantasy_icon);
            addCategory(category);
            category = new Category("Comic", R.drawable.comic_icon);
            addCategory(category);
            category = new Category("Harem", R.drawable.harem_icon);
            addCategory(category);
            category = new Category("Drama", R.drawable.drama_icon);
            addCategory(category);
            category = new Category("Ecchi", R.drawable.ecchi_icon);
            addCategory(category);
            category = new Category("Mecha", R.drawable.mecha_icon);
            addCategory(category);
            category = new Category("Seinen", R.drawable.seinen_icon);
            addCategory(category);
            category = new Category("School", R.drawable.school_icon);
            addCategory(category);
            category = new Category("Action", R.drawable.action_icon);
            addCategory(category);
        }
    }

    public int getCategoryCount() {
        Log.i(TAG, "MyAppDatebase.getComicCount ... ");

        String countQuery = "SELECT  * FROM " + TABLE_CATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public void addCategoryComic(int categoryId, int comicId){
        Log.i(TAG, "MyAppDatabase.addCategoryComic ... CategoryId: " + categoryId + " ComicId: " + comicId);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORYCOMIC_COMICID, comicId);
        values.put(COLUMN_CATEGORYCOMIC_CATEGORYID, categoryId);

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_CATEGORYCOMIC, null, values);


        // Đóng kết nối database.
        db.close();
    }

    public ArrayList<Integer> getCategoryIdListByComicId(int comicId){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CATEGORYCOMIC, new String[]{COLUMN_CATEGORYCOMIC_CATEGORYID}, COLUMN_CATEGORYCOMIC_COMICID + "=?",
                new String[]{String.valueOf(comicId)}, null, null, null, null);

        ArrayList<Integer> arrayList = new ArrayList<>();
        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                arrayList.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }

        return arrayList;
    }

    public ArrayList<Integer> getComicIdListByCategoryId(int categoryId){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CATEGORYCOMIC, new String[]{COLUMN_CATEGORYCOMIC_COMICID}, COLUMN_CATEGORYCOMIC_CATEGORYID + "=?",
                new String[]{String.valueOf(categoryId)}, null, null, null, null);

        ArrayList<Integer> arrayList = new ArrayList<>();
        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                arrayList.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }

        return arrayList;
    }

    public int getCategoryComicCount() {
        Log.i(TAG, "MyAppDatebase.getCategoryComicCount ... ");

        String countQuery = "SELECT  * FROM " + TABLE_CATEGORYCOMIC;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public void createDefaultCategoryComic(){
        if(getCategoryComicCount() == 0){
            addCategoryComic(1,1);
            addCategoryComic(1,2);
            addCategoryComic(1,3);
            addCategoryComic(2,2);
            addCategoryComic(2,3);
            addCategoryComic(3,4);
            addCategoryComic(5,5);
            addCategoryComic(4,1);
            addCategoryComic(6,6);
            addCategoryComic(7,7);
            addCategoryComic(8,8);
            addCategoryComic(9,11);
            addCategoryComic(10,10);
            addCategoryComic(11,9);
            addCategoryComic(12,10);
        }
    }

    public void deleteAllCategoryComic(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORYCOMIC, null, null);
        db.close();
    }

}

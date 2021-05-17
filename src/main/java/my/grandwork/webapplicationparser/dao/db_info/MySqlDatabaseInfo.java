package my.grandwork.webapplicationparser.dao.db_info;

public class MySqlDatabaseInfo {

    // Путь к хранению драйвера для загрузки
    public static final String DriverClass = "com.mysql.cj.jdbc.Driver";

    // UserConnection - доступен только select
    public static final String Name1 = "user_access";
    public static final String Password1 = "user#mysql693#wrong_door";
    public static final String ConnectionUrlToDb1 = "jdbc:mysql://localhost:3306/universitiesdata?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";

    // EditorConnection к данным - доступен select и insert
    public static final String Name2 = "editor_access";
    public static final String Password2 = "mysql#editor#two_blocks_down#access";
    public static final String ConnectionUrlToDb2 = "jdbc:mysql://localhost:3306/universitiesdata?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";

    // EditorConnection к журналу - доступен select и insert
    public static final String Name3 = "editor_access";
    public static final String Password3 = "mysql#editor#two_blocks_down#access";
    public static final String ConnectionUrlToDb3 = "jdbc:mysql://localhost:3306/parserlog_journal?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
}

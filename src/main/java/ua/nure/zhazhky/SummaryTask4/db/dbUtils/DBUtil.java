package ua.nure.zhazhky.SummaryTask4.db.dbUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class DBUtil {

    private DBUtil() {}
    public static void fillPreparedStatement(PreparedStatement preparedStatement, Object... args) throws SQLException {
        int counter = 1;
        for (Object arg : args) {
            preparedStatement.setObject(counter++, arg);
        }
    }
}

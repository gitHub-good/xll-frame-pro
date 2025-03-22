package com.xll.frame.starter.data.core.enums;

import com.xll.frame.starter.data.core.function.ISqlFunction;

import java.io.Serializable;

/**
 * 功能描述: <br>
 * <p>
 *  <数据库类型枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:30
 * @version 1.0.0
 */
public enum DatabaseType implements ISqlFunction {

    /**
     * MySQL
     */
    MYSQL("MySQL") {
        @Override
        public String findInSet(Serializable value, String set) {
            return "find_in_set('%s', %s) <> 0".formatted(value, set);
        }
    }
    ,;

    private final String database;

    DatabaseType(String database) {
        this.database = database;
    }

    /**
     * 获取数据库类型
     *
     * @param database 数据库
     */
    public static DatabaseType get(String database) {
        for (DatabaseType databaseType : DatabaseType.values()) {
            if (databaseType.database.equalsIgnoreCase(database)) {
                return databaseType;
            }
        }
        return null;
    }

    public String getDatabase() {
        return database;
    }
}

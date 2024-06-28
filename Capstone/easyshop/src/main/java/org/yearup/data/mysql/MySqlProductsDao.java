package org.yearup.data.mysql;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MySqlProductsDao extends MySqlDaoBase {

    public MySqlProductsDao(DataSource dataSource) {
        super(dataSource);
    }
}
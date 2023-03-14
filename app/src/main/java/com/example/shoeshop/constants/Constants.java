package com.example.shoeshop.constants;

public final class Constants {
    public static final String SHARED_PREFERENCE_NAME = "com.example.shoeshop";

    public static final String DB_NAME = "management.db";

    public static final String CREATE_USER_QUERY = "CREATE TABLE \"user\" (\n" +
            "\t\"username\"\tTEXT NOT NULL,\n" +
            "\t\"password\"\tTEXT,\n" +
            "\t\"role\"\tTEXT,\n" +
            "\t\"email\"\tTEXT,\n" +
            "\tPRIMARY KEY(\"username\")\n" +
            ")";

    public static final String CREATE_SHOES_QUERY = "CREATE TABLE IF NOT EXISTS \"shoes\" (\n" +
            "\t\"id\"\tINTEGER NOT NULL,\n" +
            "\t\"name\"\tTEXT,\n" +
            "\t\"description\"\tINTEGER,\n" +
            "\t\"price\"\tNUMERIC,\n" +
            "\t\"quantity\"\tINTEGER,\n" +
            "\t\"image\"\tTEXT,\n" +
            "\tPRIMARY KEY(\"id\")\n" +
            ")";

    public static final String CREATE_CART_QUERY = "CREATE TABLE \"cart\" (\n" +
            "\t\"fruit_id\"\tINTEGER NOT NULL,\n" +
            "\t\"price\"\tNUMERIC NOT NULL,\n" +
            "\t\"quantity\"\tINTEGER NOT NULL,\n" +
            "\t\"fruit_name\"\tTEXT NOT NULL,\n" +
            "\t\"image\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"fruit_id\")\n" +
            ")";

    public static final String CREATE_ORDER_QUERY = "CREATE TABLE \"order\" (\n" +
            "\t\"id\"\tINTEGER PRIMARY KEY NOT NULL,\n" +
            "\t\"user_id\"\tTEXT NOT NULL,\n" +
            "\t\"created_time\"\tdate NOT NULL\n" +
            //"\tPRIMARY KEY(\"id\")\n" +
            ")";

    public static final String CREATE_ORDERDETAIL_QUERY = "CREATE TABLE \"order_detail\" (\n" +
            "\t\"id\"\tINTEGER PRIMARY KEY NOT NULL,\n" +
            "\t\"order_id\"\tINTEGER NOT NULL,\n" +
            "\t\"fruit_id\"\tINTEGER NOT NULL,\n" +
            "\t\"quantity\"\tINTEGER NOT NULL\n" +
            //"\tPRIMARY KEY(\"id\")\n" +
            ")";

    public static final String DROP_USER_QUERY = "DROP TABLE IF EXISTS User";
    public static final String DROP_SHOES_QUERY = "DROP TABLE IF EXISTS Shoes";
    public static final String DROP_CART_QUERY = "DROP TABLE IF EXISTS Cart";
    public static final String DROP_ORDER_QUERY = "DROP TABLE IF EXISTS Order";
    public static final String DROP_ORDERDETAIL_QUERY = "DROP TABLE IF EXISTS order_detail";

    public static final String SEED_SHOES_QUERY = "INSERT INTO \"shoes\" (\"id\",\"name\",\"description\",\"price\",\"quantity\",\"image\") VALUES " +
            "(1,'Nike','Giay nike air force 1',3400000,100,'https://cdn.vortexs.io/api/images/0531025E-4638-4620-AFD3-1DF6E9E87491/375/w/giay-nike-air-force-1-07-lv8-photon-dust-and-sail-grey-do9801-100.jpeg')," +
            "(2,'Adidas','Giay adidas ultra boost',3500000,30,'https://cdn.vortexs.io/api/images/59FDBCDB-0EBF-409D-81B5-4740B7441F1C/375/w/giay-adidas-ultra-boost-21-7-0-core-black-fy0378.jpeg')," +
            "(3,'MLB','Giay MLB chunky liner mid basic new york yankees',5000000,40,'https://cdn.vortexs.io/api/images/a967e96c-23fc-4003-85a4-9de0d848356f/375/w/giay-mlb-chunky-liner-mid-basic-new-york-yankees-3asxlmb3n-50bks.jpeg')," +
            "(4,'New Balance','Giày New Balance Pro Court Beige Navy',1750000,40,'https://cdn.vortexs.io/api/images/1b7bbd77-b3e7-4cf9-90b7-247743c7ef58/1920/w/giay-new-balance-pro-court-beige-navy-proctccf.jpeg')," +
            "(5,'Nike','Giày Nike Air Jordan 1 Mid Is Arriving In Essential “Black/White”',3750000,100,'https://cdn.vortexs.io/api/images/d0d06b25-876d-4174-9eda-05af36429ffc/375/w/giay-nike-air-jordan-1-mid-is-arriving-in-essential-black-white-dv0991-001.jpeg')," +
            "(6,'Adidas','Giày Adidas SuperStar Cloud White / Black [FV2813] - [FV3739]',1550000,100,'https://cdn.vortexs.io/api/images/9EC662E1-ED4A-4C5C-BAC1-7BEEFA6D9DA3/375/w/giay-adidas-superstar-cloud-white-black-fv2813-fv3739.jpeg')";

    public static final String SEED_USER_QUERY = "INSERT INTO \"user\" (\"username\",\"password\",\"role\",\"email\") VALUES ('admin','1','Admin','admin@gmail.com');\n";
}

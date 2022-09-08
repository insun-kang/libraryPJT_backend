package com.project.library.bookDB;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * CSV파일을 읽어서 파싱하여 DB(Mysql)에 저장하는 프로그램 샘플
 *  - apache commons-csv를 이용해서 CSV파일을 파싱(참고: https://mvnrepository.com/artifact/org.apache.commons/commons-csv/1.9.0 )
 *
 * @author
 */
@Slf4j
public class CSVReader {

    private static String targetFilePath = ".booklist.csv";

    public static void main(String[] args) {

        //한글 깨지 방지를 위해서 characterEncoding=UTF-8 처리
        final String jdbcURL = "jdbc:mysql://localhost:3306/library?serverTimezone=UTC&characterEncoding=UTF-8";
        final String username = "insun";
        final String password = "dlstjs06!";

        final int batchSize = 2_000; //bulk insert시 커밋 갯수

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            String sql = "insert  into `book`(`book_id`,`author`, `content`,`point`, `title`, `now_stock_quantity`,`rating`,`stock_quantity`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            int columnSize = 9; //CSV 데이터 필드 컬럼 갯수

            List<CSVRecord> records = getCsvRecords();
            for (int row = 0; row < records.size(); row++) {

                CSVRecord data = records.get(row);
                for (int fieldIndex = 0; fieldIndex < columnSize; fieldIndex++) {
                    statement.setString(fieldIndex + 1, data.get(fieldIndex));
                }

                statement.addBatch();
                if (row % batchSize == 0) {
                    statement.executeBatch();
                    System.out.println(String.format("statement.executeBatch ing row ==> %s", row));
                    connection.commit(); //DB서버 부하분산을 원하는 대용량 처리시 중간중간 커밋

                    sleep(1); //부하 분산
                }

            }

            //남아있는 데이터 처리
            System.out.println("나머지 데이터도 executeBatch ");
            statement.executeBatch();
            connection.commit();

            connection.close();

        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private static void sleep(long millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static List<CSVRecord> getCsvRecords() throws IOException {

        File targetFile = new File(targetFilePath);

        int sampleDataRow = 0; //샘플 데이터 row번호
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(targetFile))) {

            CSVParser parser = CSVFormat.EXCEL.withFirstRecordAsHeader().withQuote('"').parse(bufferedReader); //엑셀타입 & 쌍따옴표 escape처리
            List<CSVRecord> records = parser.getRecords();

            log.debug("\nCSV 헤더\n\t{}\n데이터 샘플\n\t{}\n", parser.getHeaderMap(), records.get(sampleDataRow));
            log.info("\n\t헤더 필드 갯수 :{}\n\t데이터 갯수 :{}\n\t{}번째 row의 데이터 필드 갯수:{}\n\n", parser.getHeaderMap().size(), records.size(), sampleDataRow,
                    records.get(sampleDataRow).size());

            return records;
        }
    }

}

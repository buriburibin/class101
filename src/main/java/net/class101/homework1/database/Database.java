package net.class101.homework1.database;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Database implements ApplicationRunner{

	@Autowired
	DataSource datasource;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try(Connection connection = datasource.getConnection()){
            System.out.println(connection);
            String URL = connection.getMetaData().getURL();
            System.out.println(URL);
            String User = connection.getMetaData().getUserName();
            System.out.println(User);

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE PRODUCT(ID INTEGER NOT NULL, TYPE VARCHAR(255), NAME VARCHAR(255), PRICE INTEGER, QUANTITY INTEGER, PRIMARY KEY (ID) )";
            statement.executeUpdate(sql);  
			
			statement.execute("INSERT INTO PRODUCT VALUES(16374, 'KLASS', '����Ʈ������ �� 100���� �����, ����� ����� ���� ����� ���', 151950, 99999      )");
			statement.execute("INSERT INTO PRODUCT VALUES(26825, 'KLASS', '�ر�, Ư���ϰ� �Ƹ��ٿ� ������ �ݷ��Ǳ�', 114500, 99999                         )");
			statement.execute("INSERT INTO PRODUCT VALUES(65625, 'KLASS', '�ϻ� ������ ������ �Ҿ�־��, �������� �Բ��ϴ� �����е� �����', 174500, 99999      )");
			statement.execute("INSERT INTO PRODUCT VALUES(91008, 'KIT', '�۰� ���� �׷��� - �δ���� �����ϴ� ��äȭ �̴� ŰƮ', 28000, 10                    )");
			statement.execute("INSERT INTO PRODUCT VALUES(9236, 'KIT', '�Ϸ��� ���۰� ��, ����� �������� �����ϴ� õ�� ��', 9900, 22                       )");
			statement.execute("INSERT INTO PRODUCT VALUES(55527, 'KLASS', '�ڹٴ÷� ������ ������! �������� ������ �۾���', 299500, 99999                    )");
			statement.execute("INSERT INTO PRODUCT VALUES(2344, 'KLASS', '�ϻ� ��Ʃ�� ������ ������ �״��. ��ȭ���� ���̷α� ���۹�', 184500, 99999            )");
			statement.execute("INSERT INTO PRODUCT VALUES(60538, 'KIT', '���ۿ� ���� �δ��� ����. ���� ���� �����е� Ư����', 135800, 7                       )");
			statement.execute("INSERT INTO PRODUCT VALUES(78156, 'KIT', '�ϻ��� �����ϰ� ä��� ��ĳ�Ŀ� �����', 45000, 16                                )");
			statement.execute("INSERT INTO PRODUCT VALUES(53144, 'KLASS', '���� �����, �޸� ���� ������. �ɵ��Ʈ�� ����, �׸���', 249500, 99999             )");
			statement.execute("INSERT INTO PRODUCT VALUES(78311, 'KLASS', '�簢�簢 �ձ۾��� ����, ��ũ����Ʈ�� �ѱ� ����ü ��۾�', 209500, 99999              )");
			statement.execute("INSERT INTO PRODUCT VALUES(97166, 'KIT', '�̷��� ���� ��äȭ ŰƮ,���? Ŭ����101�� �����ȭ���� ��ȹ�� 3���� ��äȭ ŰƮ', 96900, 5 )");
			statement.execute("INSERT INTO PRODUCT VALUES(83791, 'KLASS', '�������� �������� ���ϴ� �е��� ���� �Ƹ���/�̺��� �Թ�', 178500, 99999              )");
			statement.execute("INSERT INTO PRODUCT VALUES(58395, 'KIT', '���� �������� ä��� 2020�� Ķ������ ����', 18620, 31                             )");
			statement.execute("INSERT INTO PRODUCT VALUES(39712, 'KIT', '������ ������ �ܿ��� ������ ������, �÷��׸��� ������', 17600, 8                      )");
			statement.execute("INSERT INTO PRODUCT VALUES(96588, 'KLASS','���� �Թ��ڸ� ���� ���� ���� ���� ��Դ� ������ ����Ʈ��', 234500, 99999           )");
			statement.execute("INSERT INTO PRODUCT VALUES(42031, 'KIT', '������ ������ ����� ���� ��� ��Ű��', 209000, 2                                 )");
			statement.execute("INSERT INTO PRODUCT VALUES(45947, 'KLASS', '�Ϸ���Ʈ������ ������ �ŷ����� �� �׸���', 249500, 99999                        )");
			statement.execute("INSERT INTO PRODUCT VALUES(74218, 'KLASS', '������ ���汸�� ������! �׸������� Ÿ������ ���� �����', 191600, 99999            )");
            statement.execute("INSERT INTO PRODUCT VALUES(28448, 'KLASS', '��ŵ� �� �� �ִ�! ���׶� �ǹ��ڰ� �˷��ִ� ��Ǳ׷����� ��� ��', 52200, 99999        )");
        }

    }
}

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.sql.DataSource;

public class Test {
    public static void main2(String[] args) throws IOException {
        WrapperModel wrapperModel = new WrapperModel();
        wrapperModel.setPackageName("net.sf.jwrappers.jdbc");
        wrapperModel.setDefaultExceptionType(SQLException.class, "if a database access error occurs");
        wrapperModel.addInterface(DataSource.class);
        wrapperModel.addInterface(Connection.class);
        wrapperModel.addInterface(DatabaseMetaData.class).addRelation(Connection.class, "connection");
        wrapperModel.addInterface(Statement.class).addRelation(Connection.class, "connection");
        wrapperModel.addInterface(PreparedStatement.class);
        wrapperModel.addInterface(CallableStatement.class);
        wrapperModel.addInterface(ResultSet.class).addRelation(Statement.class, "statement");
        wrapperModel.generate(new File("out"));
    }
    
    public static void main(String[] args) throws IOException {
        WrapperModel wrapperModel = new WrapperModel();
        wrapperModel.setPackageName("net.sf.jwrappers.jms");
        wrapperModel.setDefaultExceptionType(JMSException.class, "if an error occurs");
        wrapperModel.addInterface(ConnectionFactory.class);
        wrapperModel.addInterface(QueueConnectionFactory.class);
        wrapperModel.addInterface(TopicConnectionFactory.class);
        wrapperModel.addInterface(javax.jms.Connection.class);
        wrapperModel.addInterface(QueueConnection.class);
        wrapperModel.addInterface(TopicConnection.class);
        wrapperModel.addInterface(Session.class);
        wrapperModel.addInterface(QueueSession.class);
        wrapperModel.addInterface(TopicSession.class);
        wrapperModel.addInterface(MessageConsumer.class);
        wrapperModel.addInterface(QueueReceiver.class);
        wrapperModel.addInterface(TopicSubscriber.class);
        wrapperModel.generate(new File("out"));
    }
}

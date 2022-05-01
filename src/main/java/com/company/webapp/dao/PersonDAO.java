package com.company.webapp.dao;

import com.company.webapp.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index(){
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setFirstName(resultSet.getString("firstname"));
                person.setLastName(resultSet.getString("lastname"));
                person.setPhone(resultSet.getString("phone"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person show(int id){
        Person person = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setFirstName(resultSet.getString("firstname"));
            person.setLastName(resultSet.getString("lastname"));
            person.setPhone(resultSet.getString("phone"));
            person.setEmail(resultSet.getString("email"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void save(Person person){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES (1, ?, ?, ?, ?)");

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getPhone());
            preparedStatement.setString(4, person.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update( int id, Person updatedPerson){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Person SET firstname=?," +
                    "lastname=?, phone=?, email=? WHERE id=?");

            preparedStatement.setString(1, updatedPerson.getFirstName());
            preparedStatement.setString(2, updatedPerson.getLastName());
            preparedStatement.setString(3, updatedPerson.getPhone());
            preparedStatement.setString(4, updatedPerson.getEmail());
            preparedStatement.setInt(5,id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

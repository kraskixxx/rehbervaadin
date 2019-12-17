package com.uniyaz;

import com.uniyaz.domain.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDbTransaction {

    public void savePerson(Person person){
        String sqlQueryString = "insert into rehber_islemleri(name, surname, telno) values (?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = Connector.makeConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sqlQueryString)) {

            preparedStatement.setString(1, person.getAd());
            preparedStatement.setString(2, person.getSoyad());
            preparedStatement.setString(3, person.getTelNo());

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " satır eklendi.");

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Person getPersonById(int id){
        Person person = null;

        String selectQuery = "select * from rehber_islemleri where id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = Connector.makeConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int idIc = resultSet.getInt("id");
                String adi = resultSet.getString("name");
                String soyadi = resultSet.getString("surname");
                String telNo = resultSet.getString("telno");

                person = new Person(idIc,adi, soyadi,telNo);

                System.out.printf("%d \t\t  \t\t%s \t\t%s  \n", id, adi, soyadi);

            }

            return person;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public List<Person> getAllPerson(){

        List<Person> personList = new ArrayList<>();

        String sqlQuery = "select * from rehber_islemleri";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = Connector.makeConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String adi = resultSet.getString("name");
                String soyadi = resultSet.getString("surname");
                String telNo = resultSet.getString("telno");

                Person person = new Person(id,adi, soyadi,telNo);
                personList.add(person);

                System.out.printf("%d \t\t  \t\t%s \t\t%s  \n", id, adi, soyadi);

            }

            return personList;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void updatePerson(Person person){

        String sqlQuery = "update rehber_islemleri set name = ?, surname = ?, telno = ? where id = ?";

        try (Connection conn = Connector.makeConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {

            preparedStatement.setString(1,person.getAd());
            preparedStatement.setString(2,person.getSoyad());
            preparedStatement.setString(3,person.getTelNo());
            preparedStatement.setInt(4,person.getId());

            preparedStatement.executeUpdate();

            System.out.println(person.getAd() +" ile güncellendi....");


        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    //id ile silme eklenebilir.
    public void deletePerson(int id){
        String sqlQuery = "delete from rehber_islemleri where id = ?";

        try (Connection conn = Connector.makeConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {


            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();

            System.out.println(id +" basari ile silindi....");


        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}

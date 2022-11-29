package com.example.demo;

import com.example.demo.enums.Bed;
import com.example.demo.model.persons.Administrator;
import com.example.demo.model.persons.Customer;
import com.example.demo.model.persons.Employee;
import com.example.demo.model.reservations.Reservation;
import com.example.demo.model.rooms.DoubleRoom;
import com.example.demo.model.rooms.FamilyRoom;
import com.example.demo.model.rooms.SingleRoom;
import com.example.demo.model.rooms.Suite;
import com.example.demo.repositories.persons.AdministratorCRUDRepository;
import com.example.demo.repositories.persons.CustomerCRUDRepository;
import com.example.demo.repositories.persons.EmployeeCRUDRepository;
import com.example.demo.repositories.reservations.ReservationCRUDRepository;
import com.example.demo.repositories.rooms.DoubleRoomCRUDRepository;
import com.example.demo.repositories.rooms.FamilyRoomCRUDRepository;
import com.example.demo.repositories.rooms.SingleRoomCRUDRepository;
import com.example.demo.repositories.rooms.SuiteCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;


@SpringBootApplication
public class TestApplication implements CommandLineRunner {
    @Autowired
    CustomerCRUDRepository customerCRUDRepository;
    @Autowired
    AdministratorCRUDRepository administratorCRUDRepository;
    @Autowired
    ReservationCRUDRepository reservationCRUDRepository;
    @Autowired
    EmployeeCRUDRepository employeeCRUDRepository;
    @Autowired
    SingleRoomCRUDRepository singleRoomCRUDRepository;
    @Autowired
    DoubleRoomCRUDRepository doubleRoomCRUDRepository;
    @Autowired
    FamilyRoomCRUDRepository familyRoomCRUDRepository;
    @Autowired
    SuiteCRUDRepository suiteCRUDRepository;




    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
            Reservation reservation = new Reservation();



            try {
                singleRoomCRUDRepository.save(new SingleRoom(true, Bed.KING,100,true, 101,1));
                singleRoomCRUDRepository.save(new SingleRoom(true, Bed.WATER,110,true, 102,1));
                singleRoomCRUDRepository.save(new SingleRoom(true, Bed.SINGLE,90,true, 103,1));
                singleRoomCRUDRepository.save(new SingleRoom(true, Bed.SINGLE,90,true, 104,1));
                singleRoomCRUDRepository.save(new SingleRoom(true, Bed.SINGLE,90,true, 105,1));
                doubleRoomCRUDRepository.save(new DoubleRoom(true, Bed.DOUBLE,120,true, 201,2));
                doubleRoomCRUDRepository.save(new DoubleRoom(true, Bed.KING,135,true, 202,2));
                doubleRoomCRUDRepository.save(new DoubleRoom(true, Bed.WATER,150,true, 203,2));
                doubleRoomCRUDRepository.save(new DoubleRoom(true, Bed.KING,135,true, 204,2));
                doubleRoomCRUDRepository.save(new DoubleRoom(true, Bed.QUEEN,130,true, 205,2));
                familyRoomCRUDRepository.save(new FamilyRoom(true, Bed.KING,220,true, 301,3));
                familyRoomCRUDRepository.save(new FamilyRoom(true, Bed.KING,220,true, 302,3));
                familyRoomCRUDRepository.save(new FamilyRoom(true, Bed.KING,220,true, 303,3));
                familyRoomCRUDRepository.save(new FamilyRoom(true, Bed.KING,220,true, 304,3));
                familyRoomCRUDRepository.save(new FamilyRoom(true, Bed.KING,200,true, 305,3));
                suiteCRUDRepository.save(new Suite(true, Bed.KING,500,true, 401,2));
                suiteCRUDRepository.save(new Suite(true, Bed.HEART,550,true, 402,2));
                suiteCRUDRepository.save(new Suite(true, Bed.WATER,600,true, 403,2));
                suiteCRUDRepository.save(new Suite(true, Bed.QUEEN,500,true, 404,2));
                suiteCRUDRepository.save(new Suite(true, Bed.KING,500,true, 405,2));
                employeeCRUDRepository.save(new Employee("Katharina","Mlcovksy", "Wattgasse 89/19/11", "+43 660 277 8978","kathy@outlool.com", "Rezeptionistin", 2700.0, true, false));
                employeeCRUDRepository.save(new Employee("Julio","Gonzales", "Favoritenstraße 45/18", "+43 660 987 9877","julio@outlook.com", "Rezeptionist", 2700.0, false, false));
                employeeCRUDRepository.save(new Employee("Sasuke","Uchiha", "Konohastraße 1", "+43 660 876 6546","sharingan@outlook.com", "Shicherheitschef", 2200.0, false, false));
                employeeCRUDRepository.save(new Employee("Souma","Youji", "Teigoplatz 2", "+43 660 106 4546","shokugeki@outlook.com", "Koch", 2200.0, false, false));
                administratorCRUDRepository.save(new Administrator("Walter","Bossman", "niemandstraße 123","+43 660 765 2341","walter@hotmail.com", "Manager",3500.0, true,true));
            } catch (Exception e) {
                System.err.println("Fehler beim einfügen des Datensatzes: " + e.getMessage());
            }



        }


    }



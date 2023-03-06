package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public class ProgramAPITest {
        public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 1.1=-=-=-=-=-=-=-=-=-=-=");
            Optional<User> userCreate = ProgramAPI.createUser(getTestUser());
            userCreate.ifPresent(System.out::println);
            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 1.1=-=-=-=-=-=-=-=-=-=-=\n");

            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 1.2=-=-=-=-=-=-=-=-=-=-=");
            Optional<User> userUpdate = ProgramAPI.getUserById(9);
            if (userUpdate.isPresent()) {
                User userToUpdate = userUpdate.get();
                userToUpdate.setName("!UPDATED! Borodavkin Dmytro !UPDATED!");
                Optional<User> updatedUser = ProgramAPI.updateUser(userToUpdate);
                updatedUser.ifPresent(System.out::println);
            } else {
                System.out.println("Такого користувача не існує");
            }
            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 1.2=-=-=-=-=-=-=-=-=-=-=\n");


            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 1.3=-=-=-=-=-=-=-=-=-=-=");
            System.out.println(ProgramAPI.deleteUser(5));
            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 1.3=-=-=-=-=-=-=-=-=-=-=\n");

            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 1.4=-=-=-=-=-=-=-=-=-=-=");
            System.out.println(ProgramAPI.getAllUsers());
            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 1.4=-=-=-=-=-=-=-=-=-=-=\n");

            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 1.5=-=-=-=-=-=-=-=-=-=-=");
            Optional<User> userById = ProgramAPI.getUserById(4);
            userById.ifPresentOrElse(System.out::println, () -> System.out.println("Такого користувача не існує"));
            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 1.5=-=-=-=-=-=-=-=-=-=-=\n");

            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 1.6=-=-=-=-=-=-=-=-=-=-=");
            System.out.println(ProgramAPI.getUserByUsername("Karianne"));
            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 1.6=-=-=-=-=-=-=-=-=-=-=\n");

            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 2=-=-=-=-=-=-=-=-=-=-=");
            System.out.println(ProgramAPI.getCommentsOfUsersLastPost(7));
            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 2=-=-=-=-=-=-=-=-=-=-=\n");

            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 3=-=-=-=-=-=-=-=-=-=-=");
            System.out.println(ProgramAPI.getUncompletedTasks(6));
            System.out.println("=-=-=-=-=-=-=-=-=-=-=Task 3=-=-=-=-=-=-=-=-=-=-=\n");
        }

        private static User getTestUser() {
            User user = new User();
            user.setName("Test");
            user.setUsername("Test");
            user.setEmail("Test");
            user.setPhone("test");
            user.setWebsite("Test");
            Address address = new Address();
            address.setStreet("Test");
            address.setCity("test");
            address.setSuite("test");
            address.setZipcode("test");
            Geo geo = new Geo();
            geo.setLat(123.123);
            geo.setLng(123.123);
            address.setGeo(geo);
            user.setAddress(address);
            Company company = new Company();
            company.setBs("test");
            company.setName("test");
            company.setCatchPhrase("test");
            user.setCompany(company);
            return user;
        }
    }
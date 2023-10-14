//package ru.itis.servletsauth.repositories;
//
//import ru.itis.servletsauth.dto.User;
//
//import java.io.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class UserRepositoryCSV implements CrudRepository {
//    private final String pathToCsv = "C:\\11-203ORIS\\ServletsAuth\\ServletsAuth\\src\\main\\java\\ru\\itis\\servletsauth\\store\\data.csv";
//
//    @Override
//    public void save(User user) {
//        try {
//            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(pathToCsv, true));
//            csvWriter.append(user.getName() + user.getPassword());
//            csvWriter.newLine();
//            csvWriter.flush();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    public void delete() {
//
//    }
//
//    @Override
//    public List<String> read() {
//        try {
//            BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
//            return csvReader.lines().collect(Collectors.toList());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public boolean readAndCheckCSV(String uniqueLine) {
//        try {
//            BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
//            boolean flag = false;
//            String line;
//            while ((line = csvReader.readLine()) != null) {
//                if (uniqueLine.equals(line)) {
//                    flag = true;
//                }
//            }
//            return flag;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

package com.parameta.challenge.utils;

import com.parameta.challenge.domain.Employee;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

import static com.parameta.challenge.utils.Constants.ADULT_AGE;

@Slf4j
public class EmployeeUtils {


    public static boolean areNullValues(final Employee employee) {
        return Stream.of(employee.getId(), employee.getName(), employee.getJobTitle(),
                        employee.getSalary(), employee.getHiringDate())
                .anyMatch(Objects::isNull);
    }

    //TODO: HANDLE EXCEPTION FOR DATE 1900-13-31
    public static boolean isAValidDate(final LocalDate inputDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        log.info("Date to validate {}", inputDate);
        try {
            LocalDate.parse(inputDate.toString(), formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isAnAdult(final LocalDate inputDate) {
        Period age = Period.between(inputDate, LocalDate.now());
        log.info("employee is {} years old", age.getYears());
        return age.getYears() >= ADULT_AGE;
    }

    public static String getEmployeeDate(final LocalDate inputDate) {
        Period age = Period.between(inputDate, LocalDate.now());
        return String.format("%02d years - %02d months - %d days",
                age.getYears(), age.getMonths(), age.getDays());
    }

//    public static LocalDate covertToLocalDate(final LocalDate inputDate) {
//        return inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//    }
}

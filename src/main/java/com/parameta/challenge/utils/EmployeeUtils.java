package com.parameta.challenge.utils;

import static com.parameta.challenge.utils.Constants.ADULT_AGE;

import com.parameta.challenge.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Utility class for operations related to employees.
 */
@Slf4j
public class EmployeeUtils {

    /**
     * Checks if any of the fields of an Employee object is null.
     *
     * @param employee The Employee object to check.
     * @return true if any field is null, false otherwise.
     */
    public static boolean areNullOrEmptyValues(final Employee employee) {
        return Stream.of(employee.getId(), employee.getName(), employee.getJobTitle(),
                        employee.getSalary(), employee.getHiringDate())
                .anyMatch(value -> value == null|| (value instanceof String && ((String) value).isEmpty()));
    }

    /**
     * Checks if a given date is valid.
     *
     * @param inputDate The date to validate.
     * @return true if the date is valid, false otherwise.
     */
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

    /**
     * Checks if an employee is an adult based on their date of birth.
     *
     * @param inputDate The employee's date of birth.
     * @return true if the employee is an adult, false otherwise.
     */
    public static boolean isAnAdult(final LocalDate inputDate) {
        Period age = Period.between(inputDate, LocalDate.now());
        log.info("employee is {} years old", age.getYears());
        return age.getYears() >= ADULT_AGE;
    }

    /**
     * Gets the age of an employee in string format.
     *
     * @param inputDate The employee's date of birth.
     * @return The age of the employee in string format.
     */
    public static String getEmployeeDate(final LocalDate inputDate) {
        Period age = Period.between(inputDate, LocalDate.now());
        return String.format("%02d years - %02d months - %d days",
                age.getYears(), age.getMonths(), age.getDays());
    }
}

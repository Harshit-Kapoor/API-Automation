package com.tests;

import com.pojo.*;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class BuilderDesignPatternTest {

    @Test
    public void builderTest()  {

        FavouriteFoods favouriteFoods = null;
        List<Marks> listOfMarks = null;

        // firstly we send params in constructor to maintain immutability - dont want to include setter method, only want getter method

        // 1. if the number of parameters are increased, the readability decreases
        // 2. if we dont want to send any specific parameter, i need to again create different constructor


//        Employee employee = new Employee(47, "Agent", " ",
//                "bart.baker@gmail.com", null, Arrays.asList("player", "singer"), null);

        // BUILDER DESIGN PATTER  //  1. EXTERNAL BUILDER

        Teacher teacher = new TeacherBuilder().builder()
                .setId(11).setEmail("simmy@gmail.com").setFirstName("sim").setLastName("go").build();


        Teacher teacher1 = new TeacherBuilder().builder().setId(11).setFirstName("sim").setLastName("go").build();

        System.out.println(teacher);
        System.out.println(teacher1);

        // BUILDER DESIGN PATTER  //  2. STATIC INNER CLASS

//        Teacher t1 = Teacher.TeacherBuilder1.builder().setId(11).setEmail("simmy@gmail.com").setFirstName("sim").setLastName("go").build();

//        System.out.println(t1);

//        Using builder lombok

        //Teacher teacher2 = Teacher.builder().firstName("harry").lastName("stew").email("harry@gmail.com").id(98).build();
        //System.out.println(teacher2);

    }

}

package com.cdg.recruit.services;

import com.cdg.recruit.models.dtos.JDoodleResponse;
import lombok.extern.java.Log;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Log
public class JdoodleServiceTests {

    private final JDoodleServices jDoodleService ;

    @Autowired
    public JdoodleServiceTests(JDoodleServices jDoodleService) {
        this.jDoodleService = jDoodleService;
    }

    @Test
    public void testCodeSubmission1() {
       String code = "print(\"Yarbi ysde9\")";
       String language = "python3" ;

       JDoodleResponse jdoodleResponse = jDoodleService.processSubmission(code , language) ;
       log.info(jdoodleResponse.toString());

        Assertions.assertThat(jdoodleResponse).isNotNull() ;
        Assertions.assertThat(jdoodleResponse.getStatusCode()).isEqualTo("200") ;
        Assertions.assertThat(jdoodleResponse.getOutput()).contains("Yarbi") ;

    }

    @Test
    public void testCodeSubmission2() {

        String code = "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "public class Class {\n" +
                "\n" +
                "    public static int add (int a , int b) {\n" +
                "        return  a + b ;\n" +
                "    }\n" +
                "\n" +
                "    public static void main(String [] args ) {\n" +
                "        int r1 = add(1 , 2) ;\n" +
                "        int r2 = add(55 , 66) ;\n" +
                "        List<Integer> result = new ArrayList<>() ; \n" +
                "        result.add(r1) ; \n" +
                "        result.add(r2) ;\n" +
                "\n" +
                "        System.out.println(result.toString());\n" +
                "    }\n" +
                "}\n" ;

        String language = "java" ;

        JDoodleResponse jDoodleResponse = jDoodleService.processSubmission(code , language) ;
        System.out.println(jDoodleResponse.toString());
        Assertions.assertThat(true).isTrue() ;

    }
}

package gr.marou.samplemoviedatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan("gr.marou.samplemoviedatabase.mapper")
public class SampleMovieDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleMovieDatabaseApplication.class, args);
    }

}

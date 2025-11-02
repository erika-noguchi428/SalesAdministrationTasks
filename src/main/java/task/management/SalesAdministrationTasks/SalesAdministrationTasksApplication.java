package task.management.SalesAdministrationTasks;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "タスク管理システム"))
@SpringBootApplication
public class SalesAdministrationTasksApplication {

  public static void main(String[] args) {
    SpringApplication.run(SalesAdministrationTasksApplication.class, args);
  }
}

package task.management.SalesAdministrationTasks.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "タスク")
@Getter
@Setter
public class Tasks {

  private Integer taskId;

  @NotBlank
  private String taskName;

  @NotBlank
  private String status;

  @NotBlank
  private String salesmanName;

  @NotBlank
  private String clerkName;

  private LocalDate deadline;

  @NotBlank
  private String comment;
  private boolean isDeleted;
}

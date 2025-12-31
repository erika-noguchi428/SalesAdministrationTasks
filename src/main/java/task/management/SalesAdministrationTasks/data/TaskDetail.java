package task.management.SalesAdministrationTasks.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "タスク詳細")
@Getter
@Setter
public class TaskDetail {

  private Integer taskDetailId;
  private Integer taskId;
  private String operation;
  private String companyName;
  private String requirements;
}

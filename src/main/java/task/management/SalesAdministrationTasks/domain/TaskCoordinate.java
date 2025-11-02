package task.management.SalesAdministrationTasks.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import task.management.SalesAdministrationTasks.data.TaskDetail;
import task.management.SalesAdministrationTasks.data.Tasks;

@Schema(description = "タスクコーディネート")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCoordinate {

  @Valid
  private Tasks tasks;

  @Valid
  private List<TaskRequestGroup> taskRequestGroups;

}

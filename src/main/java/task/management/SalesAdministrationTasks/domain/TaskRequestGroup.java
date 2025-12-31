package task.management.SalesAdministrationTasks.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import task.management.SalesAdministrationTasks.data.ARequest;
import task.management.SalesAdministrationTasks.data.BRequest;
import task.management.SalesAdministrationTasks.data.TaskDetail;

@Schema(description = "依頼事項まとめ")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestGroup {

  @Valid
  private TaskDetail taskDetail;

  @Valid
  private List<ARequest> aRequests;

  @Valid
  private List<BRequest> bRequests;

}

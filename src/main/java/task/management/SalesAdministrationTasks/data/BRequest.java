package task.management.SalesAdministrationTasks.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "B部署への依頼事項")
@Getter
@Setter
public class BRequest {

  private Integer bRequestId;

  @NotBlank
  private String item;

  private String requestStatus = "Pending";

  private Integer taskDetailId;

}

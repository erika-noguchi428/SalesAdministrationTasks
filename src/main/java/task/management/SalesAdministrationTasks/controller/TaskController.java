package task.management.SalesAdministrationTasks.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import task.management.SalesAdministrationTasks.domain.TaskCoordinate;
import task.management.SalesAdministrationTasks.exception.TestException;
import task.management.SalesAdministrationTasks.service.TaskService;

/**
 * タスクの検索や登録、更新などを行うREST APIとして受け付けるControllerです。
 */

@Validated
@RestController
public class TaskController {

  private TaskService service;


  @Autowired
  public TaskController(TaskService service) {
    this.service = service;
  }

  /**
   * タスクコーディネート一覧検索です。 全件検索を行うので、条件指定が行わないものになります。
   *
   * @return　タスクコーディネート一覧 (全件)
   */

  @Operation(summary = "一覧検索", description = "タスクの一覧を検索します。")
  @GetMapping("/tasksList")
  public List<TaskCoordinate> getTaskList() {
    return service.searchTaskList();
  }

  /**
   * タスクコーディネートの検索です。 IDに紐づく任意のタスク情報を取得します。
   *
   * @param taskId 　タスクID
   * @return　タスクコーディネート
   */

  @GetMapping("/task/{taskId}")
  public TaskCoordinate getTasks(@PathVariable Integer taskId) {
    return service.searchTask(taskId);
  }

  /**
   * タスクコーディネートの登録を行います。
   *
   * @param taskCoordinate 　タスクコーディネート
   * @return
   */
  @Operation(summary = "タスク登録", description = "タスクを登録します。",
      responses = {@ApiResponse(responseCode = "400", content = @Content())})
  @PostMapping("/registerTask")
  public ResponseEntity<TaskCoordinate> registerTask(
      @RequestBody @Valid TaskCoordinate taskCoordinate) {
    TaskCoordinate responseTaskCoordinate = service.registerTask(taskCoordinate);
    return ResponseEntity.ok(responseTaskCoordinate);
  }

  /**
   * タスクコーディネートの更新を行います。キャンセルフラグの更新もここで行います。（論理削除）
   *
   * @return　実行結果
   */

  @PutMapping("/updateTask")
  public ResponseEntity<String> updateTasks(@RequestBody TaskCoordinate taskCoordinate) {
    service.updateTasks(taskCoordinate);
    return ResponseEntity.ok("更新処理が成功しました。");
  }

  @ExceptionHandler(TestException.class)
  public ResponseEntity<String> handleTestException(TestException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }
}
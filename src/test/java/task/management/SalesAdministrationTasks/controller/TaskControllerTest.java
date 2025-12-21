package task.management.SalesAdministrationTasks.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.MediaType;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.scheduling.config.Task;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import task.management.SalesAdministrationTasks.data.Tasks;
import task.management.SalesAdministrationTasks.domain.TaskCoordinate;
import task.management.SalesAdministrationTasks.service.TaskService;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  private TaskService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void タスク検索の一覧検索が実行できて空のリストが返ってくること() throws Exception {
    when(service.searchTaskList()).thenReturn(List.of());

    mockMvc.perform(get("/tasksList"))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));

    verify(service, times(1)).searchTaskList();
  }

  @Test
  void タスク詳細の検索が実行できて空で返ってくること() throws Exception {
    Integer taskId = 999;
    mockMvc.perform(get("/task/{taskId}", taskId))
        .andExpect(status().isOk());

    verify(service, times(1)).searchTask(taskId);
  }

  @Test
  void タスク詳細の登録が実行できて空で返ってくること() throws Exception {

    mockMvc.perform(post("/registerTask")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
            {
                 "tasks": {
                     "taskId": 21,
                     "taskName": "顧客Aへの見積作成",
                     "status": "Pending",
                     "salesmanName": "田中太郎",
                     "clerkName": "佐藤花子",
                     "deadline": "2025-10-25",
                     "comment": "初回訪問後の見積対応",
                     "deleted": false
                 },
                 "taskRequestGroups": [
                     {
                         "taskDetail": {
                             "taskDetailId": 9,
                             "taskId": 21,
                             "operation": "見積書作成",
                             "companyName": "株式会社サンプル",
                             "requirements": "Department_A"
                         },
                         "arequests": [
                             {
                                 "item": "見積書のドラフト作成",
                                 "requestStatus": "Pending",
                                 "taskDetailId": 9,
                                 "arequestId": 1
                             }
                         ],
                         "brequests": [
                             {
                                 "item": "社内承認フローの開始",
                                 "requestStatus": "Pending",
                                 "taskDetailId": 9,
                                 "brequestId": 1
                             }
                         ]
                     }
                 ]
             }
            """
    ))
    .andExpect(status().isOk());

verify(service, times(1)).registerTask(any());
  }

  @Test
  void タスク詳細の更新が実行できて空で返ってくること() throws Exception {
    mockMvc.perform(put("/updateTask")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
            {
                 "tasks": {
                     "taskId": 21,
                     "taskName": "顧客Aへの見積作成",
                     "status": "Pending",
                     "salesmanName": "田中太郎",
                     "clerkName": "佐藤花子",
                     "deadline": "2025-10-25",
                     "comment": "初回訪問後の見積対応",
                     "deleted": false
                 },
                 "taskRequestGroups": [
                     {
                         "taskDetail": {
                             "taskDetailId": 9,
                             "taskId": 21,
                             "operation": "見積書作成",
                             "companyName": "株式会社サンプル",
                             "requirements": "Department_A"
                         },
                         "arequests": [
                             {
                                 "item": "見積書のドラフト作成",
                                 "requestStatus": "Pending",
                                 "taskDetailId": 9,
                                 "arequestId": 1
                             }
                         ],
                         "brequests": [
                             {
                                 "item": "社内承認フローの開始",
                                 "requestStatus": "Pending",
                                 "taskDetailId": 9,
                                 "brequestId": 1
                             }
                         ]
                     }
                 ]
             }
            """
    ))
        .andExpect(status().isOk());

    verify(service, times(1)).updateTasks(any());
  }

  @Test
  void タスク詳細のタスクで適切な値を入力したときに入力チェックに異常が発生しないこと() {
    Tasks tasks = new Tasks();
    tasks.setTaskId(3);
    tasks.setTaskName("見積作成");
    tasks.setStatus("Pending");
    tasks.setSalesmanName("里山 聡美");
    tasks.setClerkName("江山 虎次郎");
    tasks.setComment("5/11アポイント時の見積対応");

    Set<ConstraintViolation<Tasks>> violations = validator.validate(tasks);

    assertEquals(0, violations.size());

  }

  @Test
  void タスク詳細のタスクでIDに数字以外を用いた時に入力チェックに掛かること() {
    Tasks tasks = new Tasks();
    tasks.setTaskId(20000);
    tasks.setTaskName("請求書作成");
    tasks.setStatus("Pending");
    tasks.setSalesmanName("田中 和美");
    tasks.setClerkName("佐藤 次郎");
    tasks.setComment("1月の請求書作成");

    Set<ConstraintViolation<Tasks>> violations = validator.validate(tasks);

    assertEquals(1, violations.size());
  }




}
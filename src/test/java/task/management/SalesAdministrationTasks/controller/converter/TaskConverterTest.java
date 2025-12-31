package task.management.SalesAdministrationTasks.controller.converter;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.management.SalesAdministrationTasks.data.ARequest;
import task.management.SalesAdministrationTasks.data.BRequest;
import task.management.SalesAdministrationTasks.data.TaskDetail;
import task.management.SalesAdministrationTasks.data.Tasks;
import task.management.SalesAdministrationTasks.domain.TaskCoordinate;
import task.management.SalesAdministrationTasks.domain.TaskRequestGroup;

class TaskConverterTest {

  private TaskConverter sut;

  @BeforeEach
  void before() {
    sut = new TaskConverter();
  }

  @Test
  void タスク情報のリストとタスク詳細情報のリストを渡してタスクコーディネートのリストが作成できること() {
    Tasks tasks = createTasks();
    TaskDetail taskDetail = createTaskDetails();

    ARequest aRequest = new ARequest();
    aRequest.setARequestId(8);
    aRequest.setTaskDetailId(3);
    aRequest.setRequestStatus("Completed");
    aRequest.setItem("原価計算の確認");

    BRequest bRequest = new BRequest();
    bRequest.setBRequestId(8);
    bRequest.setTaskDetailId(3);
    bRequest.setRequestStatus("Pending");
    bRequest.setItem("契約書の管理台帳記入");

    List<Tasks> tasksList = List.of(tasks);
    TaskRequestGroup group = new TaskRequestGroup();
    group.setTaskDetail(taskDetail);

    List<TaskRequestGroup> groups = List.of(group);

    List<TaskCoordinate> actual = sut.convertTaskCoordinates(tasksList, groups);

    assertThat(actual.get(0).getTasks()).isEqualTo(tasks);
    assertThat(actual.get(0).getTaskRequestGroups()).isEqualTo(groups);

  }

  @Test
  void タスク情報のリストとタスク詳細情報のリストを渡したときに紐づかないタスク詳細情報は除外されること() {
    Tasks tasks = createTasks();

    TaskDetail taskDetail = new TaskDetail();
    taskDetail.setTaskId(6);
    taskDetail.setTaskDetailId(7);
    taskDetail.setOperation("請求書作成");
    taskDetail.setCompanyName("会社C");
    taskDetail.setRequirements("Department_A");

    ARequest aRequest = new ARequest();
    aRequest.setARequestId(8);
    aRequest.setTaskDetailId(3);
    aRequest.setRequestStatus("Completed");
    aRequest.setItem("原価計算の確認");

    BRequest bRequest = new BRequest();
    bRequest.setBRequestId(8);
    bRequest.setTaskDetailId(3);
    bRequest.setRequestStatus("Pending");
    bRequest.setItem("契約書の管理台帳記入");

    List<Tasks> tasksList = List.of(tasks);
    TaskRequestGroup group = new TaskRequestGroup();
    group.setTaskDetail(taskDetail);

    List<TaskRequestGroup> groups = List.of(group);

    List<TaskCoordinate> actual = sut.convertTaskCoordinates(tasksList, groups);

    assertThat(actual.get(0).getTasks()).isEqualTo(tasks);
    assertThat(actual.get(0).getTaskRequestGroups()).isEmpty();

  }

  private static TaskDetail createTaskDetails() {
    TaskDetail taskDetail = new TaskDetail();
    taskDetail.setTaskId(3);
    taskDetail.setTaskDetailId(7);
    taskDetail.setOperation("請求書作成");
    taskDetail.setCompanyName("会社C");
    taskDetail.setRequirements("Department_A");
    return taskDetail;
  }

  private static Tasks createTasks() {
    Tasks tasks = new Tasks();
    tasks.setTaskId(3);
    tasks.setTaskName("見積作成");
    tasks.setStatus("Pending");
    tasks.setSalesmanName("里山 聡美");
    tasks.setClerkName("江山 虎次郎");
    tasks.setComment("5/11アポイント時の見積対応");
    return tasks;
  }

}
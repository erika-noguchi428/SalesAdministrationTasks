package task.management.SalesAdministrationTasks.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import task.management.SalesAdministrationTasks.data.ARequest;
import task.management.SalesAdministrationTasks.data.BRequest;
import task.management.SalesAdministrationTasks.data.TaskDetail;
import task.management.SalesAdministrationTasks.data.Tasks;
import task.management.SalesAdministrationTasks.domain.TaskCoordinate;
import task.management.SalesAdministrationTasks.domain.TaskRequestGroup;

/**
 * タスクコーディネートをタスクやタスク詳細、もしくはその逆の変換を行うコンバーターです。
 */
@Component
public class TaskConverter {

  /**
   * タスクに紐づくタスク詳細情報をマッピングする。 タスク詳細情報はタスクに対して単一。
   *
   * @param tasks             　タスク一覧
   * @param taskRequestGroups 　タスク詳細 &#064;return　タスク
   */
  public List<TaskCoordinate> convertTaskCoordinates(
      List<Tasks> tasks,
      List<TaskRequestGroup> taskRequestGroups) {
    List<TaskCoordinate> taskCoordinates = new ArrayList<>();
    tasks.forEach(task -> {
      TaskCoordinate taskCoordinate = new TaskCoordinate();
      taskCoordinate.setTasks(task);

      List<TaskRequestGroup> convertTaskRequestGroupList = taskRequestGroups.stream()
          .filter(group ->
              Objects.equals(task.getTaskId(), group.getTaskDetail().getTaskId()))
          .collect(Collectors.toList());
      taskCoordinate.setTaskRequestGroups(convertTaskRequestGroupList);
      taskCoordinates.add(taskCoordinate);
    });
    return taskCoordinates;
  }

  public List<TaskRequestGroup> convertTaskDetailsA(
      List<TaskDetail> taskDetails,
      List<ARequest> aRequests,
      List<BRequest> bRequests
  ) {

    List<TaskRequestGroup> taskRequestGroups = new ArrayList<>();

    taskDetails.forEach(taskDetail -> {
      TaskRequestGroup taskRequestGroup = new TaskRequestGroup();
      taskRequestGroup.setTaskDetail(taskDetail);

      // TaskDetailId で ARequest を紐づける
      List<ARequest> matchedARequests = aRequests.stream()
          .filter(req -> Objects.equals(taskDetail.getTaskDetailId(), req.getTaskDetailId()))
          .collect(Collectors.toList());

      // TaskDetailId で BRequest を紐づける
      List<BRequest> matchedBRequests = bRequests.stream()
          .filter(req -> Objects.equals(taskDetail.getTaskDetailId(), req.getTaskDetailId()))
          .collect(Collectors.toList());

      taskRequestGroup.setARequests(matchedARequests);
      taskRequestGroup.setBRequests(
          matchedBRequests);

      taskRequestGroups.add(taskRequestGroup);
    });

    return taskRequestGroups;
  }
}



package task.management.SalesAdministrationTasks.service;


import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.BooleanUtils.forEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.management.SalesAdministrationTasks.controller.converter.TaskConverter;
import task.management.SalesAdministrationTasks.data.ARequest;
import task.management.SalesAdministrationTasks.data.BRequest;
import task.management.SalesAdministrationTasks.data.TaskDetail;
import task.management.SalesAdministrationTasks.data.Tasks;

import task.management.SalesAdministrationTasks.domain.TaskCoordinate;
import task.management.SalesAdministrationTasks.domain.TaskRequestGroup;
import task.management.SalesAdministrationTasks.repository.TaskRepository;

/**
 * タスク情報を取り扱うserviceです。 タスクの検索や登録・ 更新処理  を行います。
 */
@Service
public class TaskService {

  private TaskRepository repository;
  private TaskConverter converter;

  @Autowired
  public TaskService(TaskRepository repository, TaskConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * タスクコーディネートの一覧検索です。 全件検索を行うので、条件指定が行わないものになります。 &#064;return　タスクコーディネート一覧(全件)
   */

  public List<TaskCoordinate> searchTaskList() {
    List<Tasks> tasksList = repository.search();
    List<TaskDetail> taskDetailList = repository.searchTaskDetailList();
    List<ARequest> aRequests = repository.searchARequest();
    List<BRequest> bRequests = repository.searchBRequest();

    List<TaskRequestGroup> taskRequestGroups = converter.convertTaskDetailsA(taskDetailList,
        aRequests, bRequests);
    List<TaskCoordinate> coordinates = converter.convertTaskCoordinates(tasksList,
        taskRequestGroups);

    if (coordinates.isEmpty()) {
      throw new IllegalStateException("TaskCoordinate が見つかりません");
    }

    return coordinates;
  }

  /**
   * タスク検索です。 IDに紐づくタスク情報を取得したあと、そのタスクに紐づくタスクの詳細情報を取得して設定します。
   *
   * @param taskId 　タスクID
   * @return タスク
   */
  public TaskCoordinate searchTask(Integer taskId) {
    Tasks task = repository.searchTasks(taskId);
    TaskDetail taskDetail = repository.searchTaskDetail(task.getTaskId());

    Integer taskDetailId = taskDetail.getTaskDetailId();
    ARequest aRequest = repository.searchARequestList(taskDetailId);
    BRequest bRequest = repository.searchBRequestList(taskDetailId);

    List<TaskDetail> taskDetails = Collections.singletonList(taskDetail);
    List<ARequest> aRequests = Collections.singletonList(aRequest);
    List<BRequest> bRequests = Collections.singletonList(bRequest);
    List<Tasks> tasks = Collections.singletonList(task);

    List<TaskRequestGroup> taskRequestGroups = converter.convertTaskDetailsA(taskDetails, aRequests,
        bRequests);
    List<TaskCoordinate> coordinates = converter.convertTaskCoordinates(tasks, taskRequestGroups);
    if (!coordinates.isEmpty()) {
      return coordinates.get(0);
    } else {
      return null;
    }
  }

  /**
   * タスクコーディネートの登録を行います。 タスクとタスク詳細情報を個別に登録し、タスク詳細にはタスク情報を紐づける値と更新日を指定します。
   *
   * @param taskCoordinate 　タスクコーディネート
   * @return　登録情報を付与したタスクコーディネート
   */

  @Transactional
  public TaskCoordinate registerTask(TaskCoordinate taskCoordinate) {
    Tasks tasks = taskCoordinate.getTasks();
    repository.registerTasks(tasks);
    Integer taskId = tasks.getTaskId();

    taskCoordinate.getTaskRequestGroups().forEach(group -> {
      TaskDetail taskDetail = group.getTaskDetail();
      taskDetail.setTaskId(tasks.getTaskId());
      repository.registerTaskDetail(taskDetail);
      Integer taskDetailId = taskDetail.getTaskDetailId();

      // ARequest 登録
      if (group.getARequests() != null) {
        group.getARequests().forEach(aRequest -> {
          aRequest.setTaskDetailId(taskDetail.getTaskDetailId());
          repository.registerARequest(aRequest);
        });
      }

      // BRequest 登録
      if (group.getBRequests() != null) {
        group.getBRequests().forEach(bRequest -> {
          bRequest.setTaskDetailId(taskDetail.getTaskDetailId());
          repository.registerBRequest(bRequest);
        });
      }
    });

    return taskCoordinate;
  }

  /**
   * タスクコーディネートの更新を行います。キャンセルフラグの更新もここで行います。（論理削除） タスク情報とタスク詳細情報もそれぞれ更新します。
   *
   * @param taskCoordinate 　タスクコーディネート
   */

  @Transactional
  public void updateTasks(TaskCoordinate taskCoordinate) {
    repository.updateTasks(taskCoordinate.getTasks());

    for (TaskRequestGroup taskRequestGroup : taskCoordinate.getTaskRequestGroups()) {
      TaskDetail taskDetail = taskRequestGroup.getTaskDetail();
      repository.updateTaskDetails(taskDetail);

      for (ARequest aRequest : taskRequestGroup.getARequests()) {
        repository.updateARequest(aRequest);
      }

      for (BRequest bRequest : taskRequestGroup.getBRequests()) {
        repository.updateBRequest(bRequest);
      }

    }
  }

}

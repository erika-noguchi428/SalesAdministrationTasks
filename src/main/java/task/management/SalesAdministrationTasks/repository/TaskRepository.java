package task.management.SalesAdministrationTasks.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import task.management.SalesAdministrationTasks.data.ARequest;
import task.management.SalesAdministrationTasks.data.BRequest;
import task.management.SalesAdministrationTasks.data.TaskDetail;
import task.management.SalesAdministrationTasks.data.Tasks;
import task.management.SalesAdministrationTasks.domain.TaskRequestGroup;

/**
 * タスクテーブルとタスク詳細テーブルと紐づくRepositoryです。
 */

@Mapper
public interface TaskRepository {

  /**
   * タスクの全件検索を行います。
   *
   * @return　タスク一覧 (全件)
   */

  List<Tasks> search();

  /**
   * タスクの検索を行います。
   *
   * @param taskId 　タスクID
   * @return タスク
   */

  Tasks searchTasks(Integer taskId);

  /**
   * タスクの詳細情報の全件検索を行います。
   *
   * @return　タスクの詳細情報(全件)
   */

  List<TaskDetail> searchTaskDetailList();

  /**
   * タスクIDに紐づくタスク詳細を検索します。
   *
   * @param taskId 　タスクID
   * @return タスクIDに紐づくタスク詳細情報
   */

  TaskDetail searchTaskDetail(Integer taskId);

  /**
   * A部署依頼事項の全件検索を行います。
   */

  List<ARequest> searchARequest();

  /**
   * タスク詳細のIDに紐づくA部署依頼事項の全件検索を行います。
   */

  ARequest searchARequestList(Integer taskDetailId);


  /**
   * B部署依頼事項の全件検索を行います。
   */

  List<BRequest> searchBRequest();

  /**
   * タスク詳細のIDに紐づくB部署依頼事項の全件検索を行います。
   */

  BRequest searchBRequestList(Integer taskDetailId);


  /**
   * タスクを登録します。タスクIDに関しては自動採番を行う。
   *
   * @param task 　タスク
   */
  void registerTasks(Tasks task);

  /**
   * タスク詳細情報を登録します。タスクIDに関しては自動採番を行う。
   *
   * @param taskDetail 　タスク詳細情報
   */
  void registerTaskDetail(TaskDetail taskDetail);

  /**
   * A部署依頼事項の登録を行います。
   */
  void registerARequest(ARequest aRequest);

  /**
   * B部署依頼事項の登録を行います。
   */
  void registerBRequest(BRequest bRequest);

  /**
   * タスク情報を更新します。
   *
   * @param task 　タスク
   */
  //@Update("UPDATE tasks SET operation = #{operation}, status = #{status}, assigned_to = #{assignedTo},"
  //   + " created_at = #{createdAt}, deadline = #{deadline}, description = #{description}, is_deleted = #{isDeleted}"
  //   + " WHERE task_id = #{taskId}")
  void updateTasks(Tasks task);

  /**
   * タスク詳細情報を更新します。
   *
   * @param taskDetail 　受講生詳細情報
   */
  // @Update("UPDATE task_details SET task_id = #{taskId}, task_name = #{taskName}, "
  //    + "company_name = #{companyName}, required_info = #{requiredInfo}, updated_at = #{updatedAt} "
  //   + "WHERE task_detail_id = #{taskDetailId} ")
  void updateTaskDetails(TaskDetail taskDetail);


/**
 * A部署依頼事項の更新を行います。
 */
void updateARequest(ARequest aRequest);

  /**
   * B部署依頼事項の更新を行います。
   */
  void updateBRequest(BRequest bRequest);


}
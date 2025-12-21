package task.management.SalesAdministrationTasks.repository;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import task.management.SalesAdministrationTasks.data.ARequest;
import task.management.SalesAdministrationTasks.data.BRequest;
import task.management.SalesAdministrationTasks.data.TaskDetail;
import task.management.SalesAdministrationTasks.data.Tasks;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskRepositoryTest {

  @Autowired
  private TaskRepository sut;


  @Test
  void タスクの全件検索が行えること() {
    List<Tasks> actual = sut.search();
    assertThat(actual.size()).isEqualTo(2);

  }

  @Test
  void タスク詳細の全件検索が行えること() {
    List<TaskDetail> actual = sut.searchTaskDetailList();
    assertThat(actual.size()).isEqualTo(2);

  }

  @Test
  void A部署依頼事項の全件検索が行えること() {
    List<ARequest> actual = sut.searchARequest();
    assertThat(actual.size()).isEqualTo(2);

  }

  @Test
  void B事項の全件検索が行えること() {
    List<BRequest> actual = sut.searchBRequest();
    assertThat(actual.size()).isEqualTo(2);

  }

  @Test
  void タスクのID検索が行えること() {
    Tasks actual = sut.searchTasks(24);

    assertThat(actual).isNotNull();
    assertThat(actual.getTaskId()).isEqualTo(24);
  }

  @Test
  void タスク情報のID検索が行えること() {
    TaskDetail actual = sut.searchTaskDetail(24);

    assertThat(actual).isNotNull();
    assertThat(actual.getTaskId()).isEqualTo(24);
  }

  @Test
  void A部署依頼事項のタスク詳細ID検索が行えること() {
    ARequest actual = sut.searchARequestList(24);

    assertThat(actual).isNotNull();
    assertThat(actual.getTaskDetailId()).isEqualTo(24);
  }

  @Test
  void B部署依頼事項のタスク詳細ID検索が行えること() {
    BRequest actual = sut.searchBRequestList(24);

    assertThat(actual).isNotNull();
    assertThat(actual.getTaskDetailId()).isEqualTo(24);
  }

  @Test
  void タスクの登録が行えること() {
    Tasks tasks = new Tasks();
    tasks.setTaskName("見積作成");
    tasks.setStatus("Pending");
    tasks.setSalesmanName("里山 聡美");
    tasks.setClerkName("江山 虎次郎");
    tasks.setComment("5/11アポイント時の見積対応");

    sut.registerTasks(tasks);

    List<Tasks> actual = sut.search();

    assertThat(actual.size()).isEqualTo(2);

  }

  @Test
  void タスク詳細の登録が行えること() {
    Tasks tasks = new Tasks();
    //tasks.setTaskId(3);
    tasks.setTaskName("見積作成");
    tasks.setStatus("Pending");
    tasks.setSalesmanName("里山 聡美");
    tasks.setClerkName("江山 虎次郎");
    tasks.setComment("5/11アポイント時の見積対応");

    sut.registerTasks(tasks);

    List<Tasks> actual = sut.search();

    assertThat(actual.size()).isEqualTo(2);

  }

  @Test
  void A部署依頼事項の登録が行えること() {
    ARequest aRequest = new ARequest();
    aRequest.setItem("検査実施可否のお知らせ");
    aRequest.setRequestStatus("Completed");
    aRequest.setTaskDetailId(25);

    sut.registerARequest(aRequest);

    List<ARequest> actual = sut.searchARequest();

    assertThat(actual.size()).isEqualTo(3);

  }

  @Test
  void B部署依頼事項の登録が行えること() {
    BRequest bRequest = new BRequest();
    bRequest.setItem("覚書の書類確認");
    bRequest.setRequestStatus("In Progress");
    bRequest.setTaskDetailId(26);

    sut.registerBRequest(bRequest);

    List<BRequest> actual = sut.searchBRequest();

    assertThat(actual.size()).isEqualTo(3);

  }

  @Test
  void タスク情報の更新が行えること() {

    Tasks task = sut.searchTasks(24);
    assertEquals("顧客Oへの見積作成", task.getTaskName());

    task.setTaskName("更新したタスク");
    sut.updateTasks(task);

    Tasks updated = sut.searchTasks(24);
    assertEquals("更新したタスク", updated.getTaskName());
    assertNotEquals("顧客Oへの見積作成", updated.getTaskName());
  }

  @Test
  void タスク情報詳細の更新が行えること() {

    TaskDetail taskDetail = sut.searchTaskDetail(24);
    assertEquals("株式会社サンプル", taskDetail.getCompanyName());

    taskDetail.setCompanyName("株式会社マスター");
    sut.updateTaskDetails(taskDetail);

    TaskDetail updateddetails = sut.searchTaskDetail(24);
    assertEquals("株式会社マスター", updateddetails.getCompanyName());
    assertNotEquals("株式会社サンプル", updateddetails.getCompanyName());
  }

  @Test
  void A部署依頼事項の更新が行えること() {

    ARequest aRequest = sut.searchARequestList(24);
    assertEquals("見積書のドラフト作成", aRequest.getItem());

    aRequest.setItem("30%値引きの方法確認");
    sut.updateARequest(aRequest);

    ARequest updatedARequest = sut.searchARequestList(24);
    assertEquals("30%値引きの方法確認", updatedARequest.getItem());
    assertNotEquals("見積書のドラフト作成", updatedARequest.getItem());
  }

  @Test
  void B部署依頼事項の更新が行えること() {

    BRequest bRequest = sut.searchBRequestList(24);
    assertEquals("Completed", bRequest.getRequestStatus());

    bRequest.setRequestStatus("In Progress");
    sut.updateBRequest(bRequest);

    BRequest updatedBRequest = sut.searchBRequestList(24);
    assertEquals("In Progress", updatedBRequest.getRequestStatus());
    assertNotEquals("Completed", updatedBRequest.getRequestStatus());
  }
}
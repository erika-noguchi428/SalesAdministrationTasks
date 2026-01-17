# SalesAdministrationTasks
営業事務タスク管理アプリケーション
    
## 概要
営業事務と営業担当、かつ営業事務同士での依頼事項管理システム

## 目的
営業事務のタスクを管理するアプリケーションです。営業担当者から依頼を受け、社内の様々な部署とのやりとりする際に管理できるように使用します。<br>
タスクが発生した際に、タスクを紐づけて管理できるようにIDで紐づけて管理します。<br>

## 作成の背景
今まで依頼された業務はメールのみの管理となり、依頼をこなすために発生した依頼も別々で管理していました。<br>
そこで、WEB上で同じ業務から派生する業務は紐づけて管理できるようにすることで、全体の期日を把握しながら取り組むことができます。<br>
また、その他の連絡事項としても速やかに確認することができると考えられます。<br>

## 機能
### 機能一覧
- Taskの検索、登録、更新、削除<br>
- TaskDetailの検索、登録、更新、削除<br>
- Arequest (A部署への依頼事項)の登録、更新、削除<br>
- Brequest (B部署への依頼事項)の登録、更新、削除<br>

### 仕様書

| 機能 | HTTP Method | Endpoint | 説明 |
|------|-------------|----------|------|
| Task一覧取得 | GET | [/api/tasksList](http://localhost:8080/tasksList) | Taskと紐づくTaskDetail・ Arequest・ Brequestを全件取得 |
| Task Id検索 | GET | [/api/task/taskID](http://localhost:8080/task/21) | Task Id（このEndpointの場合は21）に紐づくTaskDetail・ Arequest・ Brequestを登録 |
| Task登録 | POST | [/api/registerTask](http://localhost:8080/registerTask) | 新規 Task・TaskDetail・ Arequest・ Brequestを登録 |
| Task更新 | PUT | [/api/updateTask](http://localhost:8080/updateTask) | Task・TaskDetail・ Arequest・ Brequestを更新 |
| Task更新 (削除) | PUT | [/api/updateTask](http://localhost:8080/updateTask) | Task・TaskDetail・ Arequest・ Brequestを更新 (削除) |

### ER図

<img width="907" height="1021" alt="E-R図 drawio" src="https://github.com/user-attachments/assets/cbd0a411-4771-447d-a0cd-a4eb6b3da658" />

## 動作確認
- Taskの全件検索<br>
https://github.com/user-attachments/assets/111bbc22-6f4d-4d24-b016-16bbf2faa947<br>
![プレゼンテーション1](https://github.com/user-attachments/assets/edf9b53b-d24b-4f79-91d6-279c551505df)

- Taskの条件検索<br>
https://github.com/user-attachments/assets/2c2e6bb6-ae29-4aec-8b17-6e65337a0fd6<br>
![プレゼンテーション2](https://github.com/user-attachments/assets/cc4a5cb7-60b5-475c-b87a-e01b04e02c1f)

- Taskの登録機能<br>
https://github.com/user-attachments/assets/8eb84060-0d75-4cd1-9915-15da9c00c1dc<br>
![プレゼンテーション3](https://github.com/user-attachments/assets/a88c1c49-8389-47fb-a637-45e50e5aafd2)

- Taskの更新機能<br>
https://github.com/user-attachments/assets/483e7bc1-a676-4097-aaa4-e39bd877885b<br>
![プレゼンテーション4正しい](https://github.com/user-attachments/assets/1cc5f722-5057-4e98-bf44-25c74d412a02)

- Taskの論理削除機能<br>
https://github.com/user-attachments/assets/69b3b2a4-1863-4732-b304-ac025e20d4f1<br>
![プレゼンテーション5](https://github.com/user-attachments/assets/5eabe9fe-f4c6-4821-a693-c725de7ce381)

## 使用機能
java　21.0.8<br>
SpringBoot 3.5.6<br>
MySQL<br>
GitHub<br>
POSTMAN<br>

## ポートフォリオ作成にあたり 
### - 苦労した点 機能ごとに必要なテスト内容を整理し、設計すること
各機能（Task、TaskDetail、Arequest、Brequest）に対して、
「どのケースをテストすべきか」「どこまで検証すれば十分か」を判断することに苦労しました。
特に、
- 正常系・異常系の切り分け
- 更新系のテストで何をもって “成功” とするか
- H2 を用いたテスト用データベースの構築と初期データ投入（schema.sql / data.sql）の調整

最終的には、機能の仕様 → 想定される入力 → 期待される結果
という流れを理解し、テストケースを一つずつ言語化することで、再現性の高いテストを作成できるようになりました。

### - ハマった点 ブランチ運用を誤り、プルリクエストを出し直す必要があったこと
GitHub でプルリクエストを作成する際、誤ってブランチを切らずに main ブランチに直接コミットしてしまい、そのまま PR を作成してしまうミスがありました。
この結果、プルリクエストを一度取り下げ、作業内容を別ブランチに切り直して再度 PR を作成する必要があり、Git の正しい運用手順を理解するまでに時間がかかりました。
この経験を通じて、
- 作業前に必ずブランチを切る習慣
- PR の粒度を意識した作業管理
- 誤ったコミットを戻す際の Git コマンドの理解（reset / revert / cherry-pick など）

といった、実務でも重要となる Git 運用の基礎を身につけることができました。

### - 今後の展開
ログイン機能の実装<br>
タスク詳細、A依頼事項、B依頼事項ごとに検索でき、その結果としてほかの紐づくテーブルも表示されることです。



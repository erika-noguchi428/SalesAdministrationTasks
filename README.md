# SalesAdministrationTasks
営業事務タスク管理アプリケーション

# 概要
営業事務と営業担当、かつ営業事務同士での依頼事項管理システム

# 目的
営業事務のタスクを管理するアプリケーションです。営業担当者から依頼を受け、社内の様々な部署とのやりとりする際に管理できるように使用します。<br>
タスクが発生した際に、タスクを紐づけて管理できるようにIDで紐づけて管理します。<br>

# 作成の背景
今まで依頼された業務はメールのみの管理となり、依頼をこなすために発生した依頼も別々で管理していました。<br>
そこで、WEB上で同じ業務から派生する業務は紐づけて管理できるようにすることで、全体の期日を把握しながら取り組むことができます。<br>
また、その他の連絡事項としても速やかに確認することができると考えられます。<br>

# 機能一覧
Taskの検索、登録、更新、削除<br>
TaskDetailの検索、登録、更新、削除<br>
Arequest (A部署への依頼事項)の登録、更新、削除<br>
Brequest (B部署への依頼事項)の登録、更新、削除<br>

## 動作確認
Taskの全件検索<br>
https://github.com/user-attachments/assets/111bbc22-6f4d-4d24-b016-16bbf2faa947<br>
Taskの条件検索<br>
https://github.com/user-attachments/assets/2c2e6bb6-ae29-4aec-8b17-6e65337a0fd6<br>
Taskの登録機能<br>
https://github.com/user-attachments/assets/8eb84060-0d75-4cd1-9915-15da9c00c1dc<br>
Taskの更新機能<br>
https://github.com/user-attachments/assets/483e7bc1-a676-4097-aaa4-e39bd877885b<br>
Taskの論理削除機能<br>
https://github.com/user-attachments/assets/69b3b2a4-1863-4732-b304-ac025e20d4f1<br>

# 使用機能
java　21.0.8<br>
SpringBoot 3.5.6<br>
MySQL<br>
GitHub<br>
POSTMAN<br>

# 今後の展開
ログイン機能の実装<br>
タスク詳細、A依頼事項、B依頼事項ごとに検索でき、その結果としてほかの紐づくテーブルも表示されることです。

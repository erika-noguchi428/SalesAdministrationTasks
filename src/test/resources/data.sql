
INSERT INTO tasks (task_id, task_name, status, salesman_name, clerk_name, created_at,
                   deadline, comment, is_deleted)
VALUES (24, '顧客Oへの見積作成', 'Pending', '佐藤太郎', '山田花子','2025-10-19', '2025-10-25', '初回訪問後の見積対応', false),
       (25, '顧客1への見積作成', 'Pending', '佐藤太郎', '山田花子','2025-10-19', '2025-10-25', '初回訪問後の見積対応', false);

INSERT INTO task_details (task_detail_id, task_id, operation, company_name, requirements)
VALUES (13, 24, '見積書作成', '株式会社サンプル', 'Department_A'),
       (14, 25, '見積書作成', '株式会社サンプル', 'Department_A');

INSERT INTO a_request (a_request_id, item, request_status, task_detail_id)
VALUES (7, '見積書のドラフト作成', 'Completed', 24),
       (8, '原価計算の確認', 'In Progress', 25);

INSERT INTO b_request (b_request_id, item, request_status, task_detail_id)
VALUES (7, '検査価格のお問い合わせ', 'Completed', 24),
       (8, '原価の確認', 'In Progress', 25);
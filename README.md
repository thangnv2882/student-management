Quy trình thực hiện bài toán:
B1: Tạo 1 project Spring boot trong intelliJ IDEA
B2: Tải thư viện tại: pegurnee/db4o (github.com)
B3: Thêm thư viện vào project
		![image](https://github.com/thangnv2882/student-management/assets/84519272/b8d3fdc2-d38a-43c7-b843-96c6ede78d8e)

B4: Trong file pom.xml, khai báo thêm dependency với systemPath là đường dẫn đến thư viện
    ![image](https://github.com/thangnv2882/student-management/assets/84519272/ff2e07ea-6f31-4faa-b067-41b57567efca)

B5: Xây dựng chức năng cho hệ thống
B6: Kiểm thử hệ thống
	Cơ sở dữ liệu, hệ thống gồm các bảng sau:
  - Bảng NguoiDung (ma_nguoi_dung, ten, mat_khau, email, quyen)
  - Bảng LopHoc (ma_lop_hoc, ten, ma_giao_vien, so_tin_chi)
  - Bảng NguoiDungLopHoc (ma_nguoi_dung, ma_lop_hoc, diem)
  - Bảng ThongBao (ma_thong_bao, tieu_de, noi_dung)
  - Bảng NguoiDungThongBao (ma_nguoi_dung, ma_thong_bao)

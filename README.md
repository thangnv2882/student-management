# <h1 align="center">Quy trình thực hiện bài toán</h1> <br />
B1: Tạo 1 project Spring boot trong intelliJ IDEA <br />
B2: Tải thư viện tại: pegurnee/db4o (github.com) <br />
B3: Thêm thư viện vào project <br />
		![image](https://github.com/thangnv2882/student-management/assets/84519272/b8d3fdc2-d38a-43c7-b843-96c6ede78d8e) <br />

B4: Trong file pom.xml, khai báo thêm dependency với systemPath là đường dẫn đến thư viện <br />
    ![image](https://github.com/thangnv2882/student-management/assets/84519272/ff2e07ea-6f31-4faa-b067-41b57567efca) <br />

B5: Xây dựng chức năng cho hệ thống <br />
B6: Kiểm thử hệ thống <br />
	Cơ sở dữ liệu, hệ thống gồm các bảng sau: <br />
  - Bảng NguoiDung (ma_nguoi_dung, ten, mat_khau, email, quyen) <br />
  - Bảng LopHoc (ma_lop_hoc, ten, ma_giao_vien, so_tin_chi) <br />
  - Bảng NguoiDungLopHoc (ma_nguoi_dung, ma_lop_hoc, diem) <br />
  - Bảng ThongBao (ma_thong_bao, tieu_de, noi_dung) <br />
  - Bảng NguoiDungThongBao (ma_nguoi_dung, ma_thong_bao) <br />

--create database CMSs
CREATE TABLE Users (
    UserID CHAR(5) PRIMARY KEY,
	Username NVARCHAR(50) ,
    Password NVARCHAR(50) ,
    FullName NVARCHAR(255),
    Email VARCHAR(50),
    Ngaysinh Date,
    Diachi NVARCHAR(255),
    Gioitinh NVARCHAR(50),
	SĐT VARCHAR(15)
);

CREATE TABLE Quyen (
    RoleID CHAR(5) PRIMARY KEY,
    RoleName NVARCHAR(50)
);

CREATE TABLE Quyen_Users (
    UserID CHAR(5),
    RoleID CHAR(5) ,
    PRIMARY KEY (UserID, RoleID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (RoleID) REFERENCES Quyen(RoleID)
);
CREATE TABLE NhanVien (
    NhanVienID CHAR(5) PRIMARY KEY,
    UserID CHAR(5) ,
    Chucvu NVARCHAR(225),
    PhongBan NVARCHAR(225), 
    FOREIGN KEY (UserID) REFERENCES Users(UserID) 
);
CREATE TABLE GiaoDich (
    Giaodich_ID CHAR(5)  PRIMARY KEY,
    TenGD NVARCHAR(255),
    Sotien MONEY,
);
CREATE TABLE HopDong (
    HD_ID CHAR(5)  PRIMARY KEY,
    TenHD NVARCHAR(255),
    Loaihopdong NVARCHAR(225),
	Sanpham NVARCHAR(255),
    Ngaybatdau Date,
    Ngayketthuc Date,
	NhanVienID CHAR(5) ,
	FOREIGN KEY (NhanVienID) REFERENCES NhanVien(NhanVienID) 
);

CREATE TABLE NhanVien_HopDong_Users (
    NhanVienID CHAR(5),
    HD_ID CHAR(5),
    UserID CHAR(5),
    PRIMARY KEY (NhanVienID, HD_ID, UserID),
    FOREIGN KEY (NhanVienID) REFERENCES NhanVien(NhanVienID),
    FOREIGN KEY (HD_ID) REFERENCES HopDong(HD_ID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE NhanVien_GiaoDich_Users (
    NhanVienID CHAR(5),
    Giaodich_ID CHAR(5),
    UserID CHAR(5),
    PRIMARY KEY (NhanVienID, Giaodich_ID, UserID),
    FOREIGN KEY (NhanVienID) REFERENCES NhanVien(NhanVienID),
    FOREIGN KEY (Giaodich_ID) REFERENCES GiaoDich(Giaodich_ID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE ChienDich (
    Chiendich_ID CHAR(5)  PRIMARY KEY,
    Tenchiendich NVARCHAR(255),
	Motachitiet NVARCHAR(255),
	Loaichiendich NVARCHAR(255),
	Muctieu NVARCHAR(255),
	Chiphi Money,
    Ngaybatdau DATE,
    Ngayketthuc DATE,
	NhanVienID CHAR(5) ,
	FOREIGN KEY (NhanVienID) REFERENCES NhanVien(NhanVienID),
);
CREATE TABLE TuongTac(
	TuongTac_ID CHAR(5) NOT NULL PRIMARY KEY,
	TinhHuong NVARCHAR(255),
	Phanhoi NVARCHAR(255),
	Lichlamviec Time,
	UserID CHAR(5),
	NhanVienID CHAR(5) ,
	FOREIGN KEY (UserID) REFERENCES Users(UserID),
	FOREIGN KEY (NhanVienID) REFERENCES NhanVien(NhanVienID),

); 
INSERT INTO Users (UserID, Username, Password, FullName, Email, Ngaysinh, Diachi, Gioitinh, SĐT)
VALUES
    ('U0001', 'nguyenvanA', '123', N'Nguyễn Văn A', 'nguyenvana@example.com', '1990-05-25', N'123 Đường ABC, Quận 1, TP.HCM', 'Nam', '0123456789'),
    ('U0002', 'lethib', '123', N'Lê Thị B', 'lethib@example.com', '1985-12-10', N'456 Đường XYZ, Quận 2, TP.HCM', N'Nữ', '0987654321'),
    ('U0003', 'trungn', '123', N'Trung Nguyễn', 'trungn@example.com', '1998-08-15', N'789 Đường QWERTY, Quận 3, TP.HCM', 'Nam', '0365987412'),
	('U0004', 'phuongh', '123', N'Phương Hoàng', 'phuongh@example.com', '1992-03-18', N'101 Đường ABC, Quận 4, TP.HCM', N'Nữ', '0123456789'),
    ('U0005', 'trand', '123', N'Trần Dũng', 'trand@example.com', '1995-06-30', N'202 Đường XYZ, Quận 5, TP.HCM', 'Nam', '0987654321'),
    ('U0006', 'hoangv', '123', N'Văn Hoàng', 'hoangv@example.com', '1989-09-12', N'303 Đường QWERTY, Quận 6, TP.HCM', 'Nam', '0365987412'),
    ('U0007', 'thut', '123', N'Thư Trần', 'thut@example.com', '1993-12-25', N'404 Đường DEF, Quận 7, TP.HCM', N'Nữ', '0123456789'),
    ('U0008', 'namd', '123', N'Duy Nam', 'namd@example.com', '1997-04-03', N'505 Đường GHI, Quận 8, TP.HCM', 'Nam', '0987654321');
INSERT INTO Quyen (RoleID, RoleName)
VALUES
    ('R0001', 'ADMIN'),
    ('R0002', 'EMPLOYEE'),
    ('R0003', 'CUSTOMER');
INSERT INTO Quyen_Users (UserID, RoleID)
VALUES
    ('U0001', 'R0001'),
    ('U0002', 'R0002'),
    ('U0003', 'R0002'),
	('U0004', 'R0002'),
    ('U0005', 'R0003'),
    ('U0006', 'R0003'),
	('U0007', 'R0003'),
	('U0008', 'R0003');

INSERT INTO GiaoDich (Giaodich_ID, TenGD, Sotien)
VALUES
    ('GD001', N'Mua hàng', 5000000),
    ('GD002', N'Bán hàng', 7000000),
    ('GD003', N'Thanh toán lương', 20000000);

INSERT INTO NhanVien (NhanVienID, UserID, Chucvu, PhongBan)
VALUES
    ('NV001', 'U0002', N'Quản lý', N'Bộ phận Kế toán'),
    ('NV002', 'U0003', N'Biên tập viên', N'Bộ phận Nội dung'),
	('NV003', 'U0004', N'Nhân viên kinh doanh', N'Bộ phận kinh doanh');    


INSERT INTO HopDong (HD_ID, TenHD, Loaihopdong, Sanpham, Ngaybatdau, Ngayketthuc, NhanVienID)
VALUES
    ('HD001', N'Hợp đồng mua bán', N'Vĩnh Viễn', N'Sản phẩm A', '2024-01-15', NULL, 'NV001'),
    ('HD002', N'Hợp đồng cung cấp dịch vụ', N'Vĩnh Viễn', N'Dịch vụ B', '2024-03-20', NULL, 'NV002'),
    ('HD003', N'Hợp đồng hỗ trợ kỹ thuật', N'Có Thời Hạn', N'Sản phẩm C', '2024-02-10', '2024-08-10', 'NV003');

INSERT INTO NhanVien_HopDong_Users (NhanVienID, HD_ID, UserID)
VALUES
    ('NV001', 'HD001', 'U0005'),
    ('NV002', 'HD002', 'U0006'),
    ('NV002', 'HD003', 'U0007');

INSERT INTO NhanVien_GiaoDich_Users (NhanVienID, Giaodich_ID, UserID)
VALUES
    ('NV001', 'GD001', 'U0005'),
    ('NV002', 'GD002', 'U0006'),
    ('NV002', 'GD003', 'U0007');

INSERT INTO ChienDich (Chiendich_ID, Tenchiendich, Motachitiet, Loaichiendich, Muctieu, Chiphi, Ngaybatdau, Ngayketthuc, NhanVienID)
VALUES
    ('CD001', N'Chiến dịch quảng cáo', N'Chiến dịch quảng cáo sản phẩm A', N'Quảng cáo', N'Tăng doanh số bán hàng', 10000000, '2024-04-01', '2024-06-30', 'NV001'),
    ('CD002', N'Chiến dịch PR', N'Chiến dịch PR dịch vụ B', N'PR', N'Nâng cao uy tín thương hiệu', 8000000, '2024-05-10', '2024-07-31', 'NV002'),
    ('CD003', N'Chiến dịch khuyến mãi', N'Chiến dịch khuyến mãi sản phẩm C', N'Khuyến mãi', N'Kích thích mua hàng', 12000000, '2024-03-15', '2024-05-15', 'NV003');
INSERT INTO TuongTac (TuongTac_ID, TinhHuong, Phanhoi, Lichlamviec, UserID, NhanVienID)
VALUES
    ('TT001', N'Người dùng hỏi về sản phẩm A', N'Nhân viên trả lời tình huống A', '09:00:00', 'U0005', 'NV001'),
    ('TT002', N'Khách hàng phản hồi về dịch vụ B', N'Nhân viên cảm ơn về phản hồi B', '10:30:00', 'U0006', 'NV002'),
    ('TT003', N'Người dùng yêu cầu tư vấn sản phẩm C', N'Nhân viên tư vấn sản phẩm C', '14:00:00', 'U0007', 'NV003');

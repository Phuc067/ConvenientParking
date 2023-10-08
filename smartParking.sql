USE [master]
GO
/****** Object:  Database [SmartParking]    Script Date: 10/7/2023 10:50:01 AM ******/
CREATE DATABASE [SmartParking]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SmartParking', FILENAME = N'D:\Applications\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\SmartParking.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SmartParking_log', FILENAME = N'D:\Applications\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\SmartParking_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SmartParking] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SmartParking].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SmartParking] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SmartParking] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SmartParking] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SmartParking] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SmartParking] SET ARITHABORT OFF 
GO
ALTER DATABASE [SmartParking] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SmartParking] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SmartParking] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SmartParking] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SmartParking] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SmartParking] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SmartParking] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SmartParking] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SmartParking] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SmartParking] SET  ENABLE_BROKER 
GO
ALTER DATABASE [SmartParking] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SmartParking] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SmartParking] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SmartParking] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SmartParking] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SmartParking] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SmartParking] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SmartParking] SET RECOVERY FULL 
GO
ALTER DATABASE [SmartParking] SET  MULTI_USER 
GO
ALTER DATABASE [SmartParking] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SmartParking] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SmartParking] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SmartParking] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SmartParking] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SmartParking] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'SmartParking', N'ON'
GO
ALTER DATABASE [SmartParking] SET QUERY_STORE = OFF
GO
USE [SmartParking]
GO
/****** Object:  User [1]    Script Date: 10/7/2023 10:50:01 AM ******/
CREATE USER [1] FOR LOGIN [phuc] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  DatabaseRole [EMPLOYEE]    Script Date: 10/7/2023 10:50:01 AM ******/
CREATE ROLE [EMPLOYEE]
GO
/****** Object:  DatabaseRole [ADMIN]    Script Date: 10/7/2023 10:50:01 AM ******/
CREATE ROLE [ADMIN]
GO
ALTER ROLE [ADMIN] ADD MEMBER [1]
GO
ALTER ROLE [db_owner] ADD MEMBER [1]
GO
ALTER ROLE [db_owner] ADD MEMBER [ADMIN]
GO
USE [SmartParking]
GO
/****** Object:  Sequence [dbo].[hibernate_sequence]    Script Date: 10/7/2023 10:50:01 AM ******/
CREATE SEQUENCE [dbo].[hibernate_sequence] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE 
GO
/****** Object:  Table [dbo].[employees]    Script Date: 10/7/2023 10:50:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[employees](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[firstname] [varchar](50) NOT NULL,
	[lastname] [varchar](50) NOT NULL,
	[gender] [bit] NOT NULL,
	[phone] [varchar](10) NULL,
	[avatar] [text] NULL,
	[status] [bit] NOT NULL,
	[parkingLotId] [int] NOT NULL,
	[loginId] [int] NULL,
 CONSTRAINT [PK_employees] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[endUsers]    Script Date: 10/7/2023 10:50:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[endUsers](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[firstName] [nvarchar](50) NOT NULL,
	[lastName] [nvarchar](50) NOT NULL,
	[gender] [bit] NOT NULL,
	[phone] [varchar](10) NULL,
	[momoId] [varchar](100) NULL,
	[loginId] [int] NOT NULL,
 CONSTRAINT [PK__endUsers__3213E83F06912DA1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[logins]    Script Date: 10/7/2023 10:50:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[logins](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[password] [varchar](100) NOT NULL,
	[email] [varchar](100) NULL,
	[status] [bit] NOT NULL,
	[verificationCode] [varchar](7) NULL,
	[roleId] [tinyint] NOT NULL,
 CONSTRAINT [PK_logins] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[merchants]    Script Date: 10/7/2023 10:50:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[merchants](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[represent] [varchar](50) NOT NULL,
	[phone] [varchar](50) NULL,
	[loginId] [int] NULL,
 CONSTRAINT [PK_merchants] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[parkingLotImages]    Script Date: 10/7/2023 10:50:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[parkingLotImages](
	[id] [varchar](100) NOT NULL,
	[parkingLotId] [int] NOT NULL,
	[url] [text] NOT NULL,
 CONSTRAINT [PK_parkingLotImages] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[parkingLots]    Script Date: 10/7/2023 10:50:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[parkingLots](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[parkingLotName] [varchar](50) NULL,
	[numberSlot] [int] NOT NULL,
	[numberSlotRemaining] [int] NOT NULL,
	[status] [tinyint] NOT NULL,
	[merchantId] [int] NOT NULL,
	[lat] [float] NOT NULL,
	[lng] [float] NOT NULL,
	[timeOpen] [varchar](10) NOT NULL,
	[timeClose] [varchar](10) NOT NULL,
	[city] [varchar](50) NULL,
	[district] [varchar](50) NULL,
	[ward] [varchar](50) NULL,
	[street] [varchar](50) NULL,
	[number] [varchar](50) NULL,
 CONSTRAINT [PK_parkingLot] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[priceTickets]    Script Date: 10/7/2023 10:50:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[priceTickets](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[parkingLotId] [int] NOT NULL,
	[vehicleTypeId] [tinyint] NOT NULL,
	[periodTime] [int] NOT NULL,
	[price] [int] NOT NULL,
 CONSTRAINT [PK_priceTickets] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[roles]    Script Date: 10/7/2023 10:50:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[roles](
	[id] [tinyint] NOT NULL,
	[name] [varchar](10) NOT NULL,
 CONSTRAINT [PK__roles__3213E83F0AC8DC5A] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tickets]    Script Date: 10/7/2023 10:50:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tickets](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[checkInTime] [datetime] NOT NULL,
	[checkOutTime] [datetime] NULL,
	[liciensePlate] [varchar](15) NOT NULL,
	[vehicleTypeId] [tinyint] NOT NULL,
	[endUserId] [int] NOT NULL,
	[parkingLotId] [int] NOT NULL,
 CONSTRAINT [PK_tickets] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[transactions]    Script Date: 10/7/2023 10:50:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[transactions](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[Type] [varchar](50) NOT NULL,
	[ticketId] [bigint] NOT NULL,
	[TDate] [datetime] NOT NULL,
	[TLog] [text] NULL,
 CONSTRAINT [PK_transactions] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[vehicleTypes]    Script Date: 10/7/2023 10:50:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[vehicleTypes](
	[id] [tinyint] IDENTITY(1,1) NOT NULL,
	[typeName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_vehicleTypes] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[employees] ON 

INSERT [dbo].[employees] ([id], [firstname], [lastname], [gender], [phone], [avatar], [status], [parkingLotId], [loginId]) VALUES (2, N'Phuc', N'Le', 1, NULL, N'', 1, 1, NULL)
INSERT [dbo].[employees] ([id], [firstname], [lastname], [gender], [phone], [avatar], [status], [parkingLotId], [loginId]) VALUES (5, N'Phuc', N'Le', 1, N'0982687241', N'', 1, 1, NULL)
INSERT [dbo].[employees] ([id], [firstname], [lastname], [gender], [phone], [avatar], [status], [parkingLotId], [loginId]) VALUES (6, N'Truong', N'Nguyen', 1, NULL, NULL, 1, 1, NULL)
INSERT [dbo].[employees] ([id], [firstname], [lastname], [gender], [phone], [avatar], [status], [parkingLotId], [loginId]) VALUES (7, N'Truong', N'Nguyen', 1, N'0912479394', NULL, 1, 1, NULL)
INSERT [dbo].[employees] ([id], [firstname], [lastname], [gender], [phone], [avatar], [status], [parkingLotId], [loginId]) VALUES (10, N'Truong', N'Nguyen', 1, N'0912479394', N'', 1, 1, NULL)
INSERT [dbo].[employees] ([id], [firstname], [lastname], [gender], [phone], [avatar], [status], [parkingLotId], [loginId]) VALUES (14, N'Truong', N'Nguyen', 1, N'0912479394', N'', 1, 1, NULL)
INSERT [dbo].[employees] ([id], [firstname], [lastname], [gender], [phone], [avatar], [status], [parkingLotId], [loginId]) VALUES (16, N'Phuc', N'Le', 1, N'0912489394', N'', 1, 1, NULL)
SET IDENTITY_INSERT [dbo].[employees] OFF
GO
SET IDENTITY_INSERT [dbo].[endUsers] ON 

INSERT [dbo].[endUsers] ([id], [firstName], [lastName], [gender], [phone], [momoId], [loginId]) VALUES (1, N'Phuc', N'Le', 1, N'0928846843', NULL, 3)
SET IDENTITY_INSERT [dbo].[endUsers] OFF
GO
SET IDENTITY_INSERT [dbo].[logins] ON 

INSERT [dbo].[logins] ([id], [username], [password], [email], [status], [verificationCode], [roleId]) VALUES (1, N'phuc', N'$2a$10$lJbSh0H4gp5HXIiC25ipFOkdap618TvKTh4c5ribVvtZHGyTtm/UK', NULL, 1, NULL, 1)
INSERT [dbo].[logins] ([id], [username], [password], [email], [status], [verificationCode], [roleId]) VALUES (2, N'truong', N'$2a$10$lJbSh0H4gp5HXIiC25ipFOkdap618TvKTh4c5ribVvtZHGyTtm/UK', NULL, 1, NULL, 2)
INSERT [dbo].[logins] ([id], [username], [password], [email], [status], [verificationCode], [roleId]) VALUES (3, N'phucle', N'$2a$10$lJbSh0H4gp5HXIiC25ipFOkdap618TvKTh4c5ribVvtZHGyTtm/UK', NULL, 1, NULL, 3)
INSERT [dbo].[logins] ([id], [username], [password], [email], [status], [verificationCode], [roleId]) VALUES (19, N'Phucle1234', N'$2a$10$/Qjgog0P2S4BINaeBPLdrepzz6OTeu0WwH5MLwif5j/VgNe6y0qxO', N'phucdt04@gmail.com', 1, NULL, 3)
SET IDENTITY_INSERT [dbo].[logins] OFF
GO
SET IDENTITY_INSERT [dbo].[merchants] ON 

INSERT [dbo].[merchants] ([id], [name], [represent], [phone], [loginId]) VALUES (1, N'The Parking Hub & Depot', N'Le Van Phuc', N'0982964242', 1)
SET IDENTITY_INSERT [dbo].[merchants] OFF
GO
SET IDENTITY_INSERT [dbo].[parkingLots] ON 

INSERT [dbo].[parkingLots] ([id], [parkingLotName], [numberSlot], [numberSlotRemaining], [status], [merchantId], [lat], [lng], [timeOpen], [timeClose], [city], [district], [ward], [street], [number]) VALUES (1, N'Le Van Viêt', 10, 10, 1, 1, 190, 200, N'6', N'23', N'HCM', N'Thu Duc', N'Hiep Phu', N'Le Van Viet', N'19')
INSERT [dbo].[parkingLots] ([id], [parkingLotName], [numberSlot], [numberSlotRemaining], [status], [merchantId], [lat], [lng], [timeOpen], [timeClose], [city], [district], [ward], [street], [number]) VALUES (11, N'Man Thien', 100, 100, 1, 1, 1000, 2000, N'6', N'23', N'HCM', N'Thu Duc', NULL, N'Man Thien', N'98')
INSERT [dbo].[parkingLots] ([id], [parkingLotName], [numberSlot], [numberSlotRemaining], [status], [merchantId], [lat], [lng], [timeOpen], [timeClose], [city], [district], [ward], [street], [number]) VALUES (13, N'Man Thien', 100, 100, 1, 1, 1000, 2000, N'6', N'23', N'HCM', N'Thu Duc', N'Hiep phu', N'Man Thien', N'98')
INSERT [dbo].[parkingLots] ([id], [parkingLotName], [numberSlot], [numberSlotRemaining], [status], [merchantId], [lat], [lng], [timeOpen], [timeClose], [city], [district], [ward], [street], [number]) VALUES (14, N'Man Thien', 100, 100, 1, 1, 1000, 2000, N'6', N'23', N'HCM', N'Thu Duc', NULL, N'Man Thien', N'98')
SET IDENTITY_INSERT [dbo].[parkingLots] OFF
GO
INSERT [dbo].[roles] ([id], [name]) VALUES (1, N'MERCHANT')
INSERT [dbo].[roles] ([id], [name]) VALUES (2, N'EMPLOYEE')
INSERT [dbo].[roles] ([id], [name]) VALUES (3, N'USER')
GO
/****** Object:  Index [UQ_LOGIN]    Script Date: 10/7/2023 10:50:01 AM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UQ_LOGIN] ON [dbo].[employees]
(
	[loginId] ASC
)
WHERE ([loginId] IS NOT NULL)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ_LOGIN]    Script Date: 10/7/2023 10:50:01 AM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UQ_LOGIN] ON [dbo].[endUsers]
(
	[loginId] ASC
)
WHERE ([loginId] IS NOT NULL)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ_EMAIL]    Script Date: 10/7/2023 10:50:01 AM ******/
ALTER TABLE [dbo].[logins] ADD  CONSTRAINT [UQ_EMAIL] UNIQUE NONCLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ_USERNAME]    Script Date: 10/7/2023 10:50:01 AM ******/
ALTER TABLE [dbo].[logins] ADD  CONSTRAINT [UQ_USERNAME] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ_LOGIN]    Script Date: 10/7/2023 10:50:01 AM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UQ_LOGIN] ON [dbo].[merchants]
(
	[loginId] ASC
)
WHERE ([loginId] IS NOT NULL)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ_Transaction]    Script Date: 10/7/2023 10:50:01 AM ******/
ALTER TABLE [dbo].[transactions] ADD  CONSTRAINT [UQ_Transaction] UNIQUE NONCLUSTERED 
(
	[ticketId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[employees]  WITH CHECK ADD  CONSTRAINT [FK_employees_logins] FOREIGN KEY([loginId])
REFERENCES [dbo].[logins] ([id])
GO
ALTER TABLE [dbo].[employees] CHECK CONSTRAINT [FK_employees_logins]
GO
ALTER TABLE [dbo].[employees]  WITH CHECK ADD  CONSTRAINT [FK_employees_parkingLot] FOREIGN KEY([parkingLotId])
REFERENCES [dbo].[parkingLots] ([id])
GO
ALTER TABLE [dbo].[employees] CHECK CONSTRAINT [FK_employees_parkingLot]
GO
ALTER TABLE [dbo].[endUsers]  WITH CHECK ADD  CONSTRAINT [FK_endUsers_logins] FOREIGN KEY([loginId])
REFERENCES [dbo].[logins] ([id])
GO
ALTER TABLE [dbo].[endUsers] CHECK CONSTRAINT [FK_endUsers_logins]
GO
ALTER TABLE [dbo].[logins]  WITH CHECK ADD  CONSTRAINT [FK_logins_roles] FOREIGN KEY([roleId])
REFERENCES [dbo].[roles] ([id])
GO
ALTER TABLE [dbo].[logins] CHECK CONSTRAINT [FK_logins_roles]
GO
ALTER TABLE [dbo].[merchants]  WITH CHECK ADD  CONSTRAINT [FK_merchants_logins] FOREIGN KEY([loginId])
REFERENCES [dbo].[logins] ([id])
GO
ALTER TABLE [dbo].[merchants] CHECK CONSTRAINT [FK_merchants_logins]
GO
ALTER TABLE [dbo].[parkingLotImages]  WITH CHECK ADD  CONSTRAINT [FK_parkingLotImages_parkingLot] FOREIGN KEY([parkingLotId])
REFERENCES [dbo].[parkingLots] ([id])
GO
ALTER TABLE [dbo].[parkingLotImages] CHECK CONSTRAINT [FK_parkingLotImages_parkingLot]
GO
ALTER TABLE [dbo].[parkingLots]  WITH CHECK ADD  CONSTRAINT [FKe43r35ma1500ff5ne6hljm6lv] FOREIGN KEY([merchantId])
REFERENCES [dbo].[merchants] ([id])
GO
ALTER TABLE [dbo].[parkingLots] CHECK CONSTRAINT [FKe43r35ma1500ff5ne6hljm6lv]
GO
ALTER TABLE [dbo].[priceTickets]  WITH CHECK ADD  CONSTRAINT [FK_priceTickets_parkingLot] FOREIGN KEY([parkingLotId])
REFERENCES [dbo].[parkingLots] ([id])
GO
ALTER TABLE [dbo].[priceTickets] CHECK CONSTRAINT [FK_priceTickets_parkingLot]
GO
ALTER TABLE [dbo].[priceTickets]  WITH CHECK ADD  CONSTRAINT [FK_priceTickets_vehicleTypes] FOREIGN KEY([vehicleTypeId])
REFERENCES [dbo].[vehicleTypes] ([id])
GO
ALTER TABLE [dbo].[priceTickets] CHECK CONSTRAINT [FK_priceTickets_vehicleTypes]
GO
ALTER TABLE [dbo].[tickets]  WITH CHECK ADD  CONSTRAINT [FK_tickets_endUsers] FOREIGN KEY([endUserId])
REFERENCES [dbo].[endUsers] ([id])
GO
ALTER TABLE [dbo].[tickets] CHECK CONSTRAINT [FK_tickets_endUsers]
GO
ALTER TABLE [dbo].[tickets]  WITH CHECK ADD  CONSTRAINT [FK_tickets_parkingLot] FOREIGN KEY([parkingLotId])
REFERENCES [dbo].[parkingLots] ([id])
GO
ALTER TABLE [dbo].[tickets] CHECK CONSTRAINT [FK_tickets_parkingLot]
GO
ALTER TABLE [dbo].[tickets]  WITH CHECK ADD  CONSTRAINT [FK_tickets_vehicleTypes] FOREIGN KEY([vehicleTypeId])
REFERENCES [dbo].[vehicleTypes] ([id])
GO
ALTER TABLE [dbo].[tickets] CHECK CONSTRAINT [FK_tickets_vehicleTypes]
GO
ALTER TABLE [dbo].[transactions]  WITH CHECK ADD  CONSTRAINT [FK_transactions_tickets] FOREIGN KEY([ticketId])
REFERENCES [dbo].[tickets] ([id])
GO
ALTER TABLE [dbo].[transactions] CHECK CONSTRAINT [FK_transactions_tickets]
GO
USE [master]
GO
ALTER DATABASE [SmartParking] SET  READ_WRITE 
GO

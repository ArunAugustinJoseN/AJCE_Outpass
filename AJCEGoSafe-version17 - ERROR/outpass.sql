SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE TABLE `login` (
  `login_id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `login` (`login_id`, `username`, `password`) VALUES
(1, 'admin@gmail.com', '1111');

CREATE TABLE `table_branch` (
  `branch_id` int(11) NOT NULL,
  `stream_id` int(11) NOT NULL,
  `branch_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `table_branch` (`branch_id`, `stream_id`, `branch_name`) VALUES
(1, 1, 'Mechanical Engineering'),
(2, 1, 'Civil Engineering'),
(3, 1, 'Chemical Engineering'),
(4, 1, 'Metallurgy'),
(5, 2, 'Civil Engineering'),
(6, 2, 'Mechanical Engineering'),
(7, 3, 'Integrated MCA'),
(8, 3, 'Regular MCA'),
(9, 3, 'LE MCA');

CREATE TABLE `table_login` (
  `login_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `admission_no` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL DEFAULT '1111',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `table_login` (`login_id`, `role_id`, `admission_no`, `password`, `created_at`) VALUES
(1, 1, 'admin', '1234', '2018-01-19 14:53:04'),
(2, 2, 'security', '1111', '2018-01-19 14:53:04'),
(7, 3, '9049', '1234', '2018-01-22 11:33:12'),
(8, 3, '9050', '1111', '2018-02-02 10:26:26'),
(9, 3, '9051', '1111', '2018-02-04 07:16:56'),
(10, 3, '9036', '1111', '2018-03-12 11:35:22');

CREATE TABLE `table_outpass` (
  `outpass_id` int(11) NOT NULL,
  `admission_no` int(11) NOT NULL,
  `purpose` varchar(100) NOT NULL,
  `date_of_leaving` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_of_return` varchar(30) NOT NULL,
  `time_of_leaving` varchar(20) NOT NULL DEFAULT '00:00',
  `time_of_return` varchar(20) NOT NULL DEFAULT '00:00',
  `status` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `table_outpass` (`outpass_id`, `admission_no`, `purpose`, `date_of_leaving`, `date_of_return`, `time_of_leaving`, `time_of_return`, `status`) VALUES
(4, 9050, 'hiii', '2018-02-08 15:50:24', '08-02-2018', '00:00', '00:00', 2),
(5, 9049, 'shopping', '2018-02-09 10:57:54', '10-02-2018', '00:00', '00:00', 2),
(6, 9049, 'going home', '2018-02-06 18:30:00', '07-02-2018', '00:00', '00:00', 1),
(7, 9049, 'Shopping', '2018-03-10 16:24:05', '10-03-2018', '00:00', '00:00', 2),
(8, 9050, 'Going to home', '2018-03-11 04:21:56', '13-03-2018', '00:00', '00:00', 1),
(9, 9051, 'going to hospital', '2018-03-11 04:39:18', '12-03-2018', '00:00', '00:00', 0),
(10, 9049, 'going to home', '2018-03-12 08:52:01', '13-03-2018', '00:00', '00:00', 1);

CREATE TABLE `table_role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `table_role` (`role_id`, `role_name`) VALUES
(1, 'admin'),
(2, 'security'),
(3, 'student');

CREATE TABLE `table_stream` (
  `stream_id` int(11) NOT NULL,
  `stream_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `table_stream` (`stream_id`, `stream_name`) VALUES
(1, 'BTech'),
(2, 'MTech'),
(3, 'MCA');

CREATE TABLE `table_studentinfo` (
  `student_id` int(11) NOT NULL,
  `login_id` int(11) NOT NULL,
  `stream_id` int(11) NOT NULL,
  `branch_id` int(11) NOT NULL,
  `admission_no` int(11) NOT NULL,
  `fname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `address` varchar(200) NOT NULL,
  `dob` date NOT NULL,
  `gender` varchar(10) NOT NULL,
  `mobile_no` varchar(20) NOT NULL,
  `parent_mobile` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `batch` varchar(10) NOT NULL,
  `photo` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `table_studentinfo` (`student_id`, `login_id`, `stream_id`, `branch_id`, `admission_no`, `fname`, `lname`, `address`, `dob`, `gender`, `mobile_no`, `parent_mobile`, `email`, `batch`, `photo`) VALUES
(1, 7, 3, 9, 9049, 'Arun', 'Jose', 'Nellissery(H),\r\nVemom P.O,\r\nMananthavady', '1995-08-26', 'male', '8281758023', '8547272923', 'arunaugustinjose@gmail.com', '2016-2018', 'photo/1516620792arun.jpg'),
(2, 8, 1, 2, 9050, 'Swathi', 'Krishna', 'Anupam (H),\r\nKallodi P.O,\r\nKallodi', '1995-10-27', 'female', '9645420875', '9547284569', 'swathi@gmail.com', '2016-2019', 'photo/1517567186team-2.jpg'),
(3, 9, 2, 6, 9051, 'Abijith', 'Jose', 'Njanjilath (H),\r\nVemom P.O,\r\nMananthavady', '1995-04-09', 'male', '9526300234', '9744013627', 'abijithjose@rocketmail.com', '2017-2019', 'photo/1517728616IMG_20160210_092643.jpg'),
(4, 10, 3, 9, 9036, 'Albin', 'Tom', 'Mulangassery (H),\r\nSrekandapuram,\r\nKannur', '1995-08-08', 'male', '9446407273', '9746173332', 'albintom@mca.ajce.in', '2016-2018', 'photo/15208545222017-03-17-11-36-07-115.jpg');


ALTER TABLE `login`
  ADD PRIMARY KEY (`login_id`);

ALTER TABLE `table_branch`
  ADD PRIMARY KEY (`branch_id`);

ALTER TABLE `table_login`
  ADD PRIMARY KEY (`login_id`);

ALTER TABLE `table_outpass`
  ADD PRIMARY KEY (`outpass_id`);

ALTER TABLE `table_role`
  ADD PRIMARY KEY (`role_id`);

ALTER TABLE `table_stream`
  ADD PRIMARY KEY (`stream_id`);

ALTER TABLE `table_studentinfo`
  ADD PRIMARY KEY (`student_id`);


ALTER TABLE `login`
  MODIFY `login_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

ALTER TABLE `table_branch`
  MODIFY `branch_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

ALTER TABLE `table_login`
  MODIFY `login_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

ALTER TABLE `table_outpass`
  MODIFY `outpass_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

ALTER TABLE `table_stream`
  MODIFY `stream_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

ALTER TABLE `table_studentinfo`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

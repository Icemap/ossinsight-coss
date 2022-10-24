DROP TABLE `mv_coss_dev_day`;

CREATE TABLE IF NOT EXISTS `mv_coss_dev_day` (
	-- base
  `github_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `event_day` date NOT NULL,
	
	-- event number
  `event_num` int(11) DEFAULT NULL,
  `star_num` int(11) DEFAULT NULL,
  `pr_num` int(11) DEFAULT NULL,
  `issue_num` int(11) DEFAULT NULL,
	
	-- developer number
  `dev_num` int(11) DEFAULT NULL,
  `star_dev_num` int(11) DEFAULT NULL,
  `pr_dev_num` int(11) DEFAULT NULL,
  `issue_dev_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`github_name`,`event_day`) /*T![clustered_index] NONCLUSTERED */
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


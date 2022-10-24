// Copyright 2022 PingCAP, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.pingcap.ossinsightcoss.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * COSSDevDailyRepository
 *
 * @author Icemap
 * @date 2022/10/24
 */
@Repository
public interface COSSDevDailyRepository extends JpaRepository<COSSDevDailyBean, Long> {
    @Modifying
    @Transactional
    @Query(value = """
    INSERT INTO mv_coss_dev_day (
        github_name, event_day, 
        event_num, star_num, 
        pr_num, issue_num, 
        dev_num, star_dev_num, 
        pr_dev_num, issue_dev_num
    )
    SELECT
        ci.github_name AS raw_github_name,
        ge.event_day AS raw_event_day,
    
        COUNT(*) raw_event_num,
        COUNT(CASE WHEN ge.type = "WatchEvent" THEN 1 ELSE NULL END) AS raw_star_num,
        COUNT(CASE WHEN ge.type = "PullRequestEvent" THEN 1 ELSE NULL END) AS raw_pr_num,
        COUNT(CASE WHEN ge.type = "IssuesEvent" THEN 1 ELSE NULL END) AS raw_issue_num,
    
        COUNT(DISTINCT ge.actor_id) raw_dev_num,
        COUNT(DISTINCT CASE WHEN ge.type = "WatchEvent" THEN ge.actor_id ELSE NULL END) AS raw_star_dev_num,
        COUNT(DISTINCT CASE WHEN ge.type = "PullRequestEvent" THEN ge.actor_id ELSE NULL END) AS raw_pr_dev_num,
        COUNT(DISTINCT CASE WHEN ge.type = "IssuesEvent" THEN ge.actor_id ELSE NULL END) AS raw_issue_dev_num
    FROM github_events ge
    INNER JOIN coss_invest ci ON ci.github_name = ge.repo_name
    AND ci.company IS NOT NULL
    AND ci.github_name IS NOT NULL
    AND ci.has_github = TRUE
    AND ci.has_repo = TRUE
    AND ci.github_name = :repo_name
    AND ge.event_day != CURRENT_DATE()
    GROUP BY ci.github_name, ge.event_day
    ON DUPLICATE KEY UPDATE 
        event_num = raw_event_num, star_num = raw_star_num, 
        pr_num = raw_pr_num, issue_num = raw_issue_num, 
        dev_num = raw_dev_num, star_dev_num = raw_star_dev_num, 
        pr_dev_num = raw_pr_dev_num, issue_dev_num = raw_issue_dev_num
    """, nativeQuery = true)
    Integer transferCOSSDevDailyBeanByRepoName(@Param("repo_name") String repoName);
}

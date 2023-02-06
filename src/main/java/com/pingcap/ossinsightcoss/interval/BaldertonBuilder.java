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

package com.pingcap.ossinsightcoss.interval;

import com.pingcap.ossinsightcoss.dao.BaldertonMonthlyRepository;
import com.pingcap.ossinsightcoss.dao.BaldertonTrackedBean;
import com.pingcap.ossinsightcoss.dao.BaldertonTrackedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * BaldertonBuilder
 *
 * @author Icemap
 * @date 2023/2/6
 */
@Service
public class BaldertonBuilder {
    @Autowired
    BaldertonMonthlyRepository baldertonMonthlyRepository;
    @Autowired
    BaldertonTrackedRepository baldertonTrackedRepository;

    Stack<BaldertonTrackedBean> refreshStack = new Stack<>();

    @Scheduled(fixedDelay=30, timeUnit= TimeUnit.SECONDS)
    public void buildAndRefreshMonthlyOfRepo() {
        if (refreshStack.isEmpty()) {
            refreshStack.addAll(baldertonTrackedRepository.findAll());
            return;
        }

        baldertonMonthlyRepository.transferBaldertonMonthlyBeanByRepoName(
                refreshStack.pop().getRepoName()
        );
    }
}

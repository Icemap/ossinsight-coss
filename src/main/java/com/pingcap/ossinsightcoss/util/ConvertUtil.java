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

package com.pingcap.ossinsightcoss.util;

import com.pingcap.ossinsightcoss.dao.COSSInvestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * ConvertUtil
 *
 * @author Icemap
 * @date 2022/10/20
 */
@Component
public class ConvertUtil {
    @Autowired
    FileUtil fileUtil;

    private static final int DATA_LENGTH = 12;
    SimpleDateFormat normalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public COSSInvestBean convertFromCSV (String csvLine) throws Exception {
        // "id","company","repository","stage","round_size","year","month","lead_investor","has_repo","has_github","github_name","date"
        // "1","Acryl Data","https://github.com/datahub-project/datahub","Seed","9.00","2021","6","8VC","1","1","datahub-project/datahub","2021-06-01 00:00:00"
        COSSInvestBean result = new COSSInvestBean();

        csvLine = csvLine.substring(1, csvLine.length() - 1);
        String[] params = csvLine.split("\",\"");

        if (params.length != DATA_LENGTH) {
            throw new Exception("data error");
        }

        result.setId(Integer.parseInt(params[0]));
        result.setCompany(params[1]);
        result.setRepository(params[2]);
        result.setStage(COSSInvestBean.Stage.valueOf(params[3]));
        result.setRoundSize(new BigDecimal(params[4]));
        result.setYear(Year.parse(params[5]));
        result.setMonth(Integer.parseInt(params[6]));
        result.setLeadInvestor(params[7]);
        result.setHasRepo(Integer.parseInt(params[8]) == 1);
        result.setHasGithub(Integer.parseInt(params[9]) == 1);
        result.setGithubName(params[10]);
        result.setDate(normalFormat.parse(params[11]));

        return result;
    }

    public List<COSSInvestBean> readCOSSInvestBean () throws Exception {
        List<String> cossCSVList = fileUtil.readCOSSInvest();
        cossCSVList.remove(0);
        List<COSSInvestBean> beanList = new ArrayList<>();

        for (String line: cossCSVList) {
            beanList.add(convertFromCSV(line));
        }

        return beanList;
    }
}

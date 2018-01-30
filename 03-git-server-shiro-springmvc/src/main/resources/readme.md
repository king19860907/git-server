# 东方希望成本模块接口文档

## 1.公共部分
### 1.1.返回参数说明
```JSON
{
    "msg": "success",
    "result": {Object},
    "flag": 1
}
```
| 字段名称       | 说明           | 类型   |
|:-------------:|:-------------:| :-----:|
|msg| 返回说明| String |
|result| 返回的具体数据 | Object |
|flag|状态:1.成功,0.失败,-1,未登录|Integer|


## 2.登录登出
### 2.1.查询分支机构
#### 输入
**地址:** http://{ip}:{port}/masterData/branches.htm

**提交方式:** GET

**参数:** 无

#### 输出
```JSON
{
    "msg": "success",
    "flag": 1,
    "result": [
        {
            "code": -2,             //分支代码
            "name": "Main",         //分支名称
            "description": null     //分支描述
        },
        {
            "code": 1,
            "name": "Branch2",
            "description": null
        }
    ]
}
```


### 2.2.登录

#### 输入
**地址:** http://{ip}:{port}/login.htm

**提交方式:** POST **参数形式:** params

**参数:**

| 字段名称       | 说明           | 类型   |
|:-------------:|:-------------:| :-----:|
| username      | 用户名         | String |
| passoword     | 密码           | String |

#### 输出
```JSON
{
    "msg": "success",
    "result": "success",
    "flag": 1
}
```

### 2.3.登出
#### 输入
**地址:** http://{ip}:{port}/logout.htm

**提交方式:** POST

**参数:** 无

#### 输出
```JSON
{
    "msg": "success",
    "result": "success",
    "flag": 1
}
```

## 3.数据采集
### 3.1 查询期间数据
#### 输入
**地址:** http://{ip}:{port}/masterData/periods.htm?params={jsonString}

**提交方式:** POST **参数形式:** JSON

**参数:**
```JSON
{
  "branch": 1,    //分支机构代码 非空
  "years": [      //年份列表
    "2018",
    "2017"
  ]
}
```

#### 输出
```JSON
{
  "msg": "success",
  "flag": 1,
  "result": [
    {
      "docEntry": 1,       //主键
      "code": "2017-01",   //期间代码
      "name": "2017-01",   //期间名称
      "postingDateFrom": "2017-01-01",  //过账开始日期
      "postingDateTo": "2017-01-01",    //过账结束日期
      "dueDateFrom": "2017-01-01",      //到期开始日期
      "dueDateTo": "2017-01-01",        //到期结束日期
      "taxDateFrom": "2017-01-01",      //单据开始日期
      "taxDateTo": "2017-01-01",        //单据结束日期
      "periodStatus": "N"               //期间状态 N:已解锁,S:除销售外均已解锁,C:结算期间,Y:已锁定
    }
  ]
}
```

### 3.2 查询数据采集情况
#### 输入
**地址:** http://{ip}:{port}/data/info.htm?params={jsonString}

**提交方式:** GET **参数形式:** JSON

**参数:**
```JSON
{
    "branch": 1,               //分支机构代码     非空
    "source": "efs",          //数据来源:efs,nc  非空
    "periodCode": "2017-01"   //核算时间         非空
}
```

#### 输出
```JSON
{
    "msg": "success",
    "flag": 1,
    "result": {
        "branch": {
            "code": 1,          //分支机构代码
            "name": "branch1",  //分支结构名称
            "description": null //分支机构描述
        },
        "period": {             //期间数据,参考3.1查询期间数据
           "docEntry": 1,
           "code": "2017-01",
           "name": "2017-01",
           "postingDateFrom": "2017-01-01",
           "postingDateTo": "2017-01-01",
           "dueDateFrom": "2017-01-01",
           "dueDateTo": "2017-01-01",
           "taxDateFrom": "2017-01-01",
           "taxDateTo": "2017-01-01",
           "periodStatus": "N"
       },
        "source": "efs",         //数据来源
        "execStatus": "C",       //执行状态 O - 未执行 / P - 处理中 / C - 已执行
        "execStartTime": "2017-02-01 9:30", //执行开始时间
        "execEndTime": "2017-02-01 9:31",   //执行结束时间
        "durationMills": 60000,   //处理时间
        "message": null  //执行消息
    }
}
```

### 3.3 执行数据采集
#### 输入
**地址:** http://{ip}:{port}/data/collect.htm

**提交方式:** POST **参数形式:** JSON

**参数:**
```JSON
{
    "branch": 1,              //分支机构代码     非空
    "cover": false,           //是否覆盖  默认false
    "source": "efs",          //数据来源  默认efs
    "periodCode": "2017-01"   //核算期间  非空
}
```

#### 输出
```JSON
{
    "msg": "success",
    "result": "success",
    "flag": 1
}
```

## 4.工费计提
### 4.1 查看计提信息
#### 输入
**地址:** http://{ip}:{port}/cal/info.htm

**提交方式:** GET **参数形式:** params

**参数:**
| 字段名称       | 说明           | 类型   |
|:-------------:|:-------------:| :-----:|
|branch| 分支机构代码 非空| Number |
|periodCode| 核算期间 非空| String |
|refresh|是否刷新数据 默认为不刷新（如果true则表示数据库重新生成初始数据返回|boolean|

#### 输出
```JSON
{
  "msg": "success",
  "flag": 1,
  "result": {
    "branch": {                 //分支机构
      "code": 1,
      "name": "branch1",
      "description": null
    },
    "period": {                 //期间信息
      "docEntry": 1,
      "code": "2017-01",
      "name": "2017-01",
      "postingDateFrom": "2017-01-01",
      "postingDateTo": "2017-01-01",
      "dueDateFrom": "2017-01-01",
      "dueDateTo": "2017-01-01",
      "taxDateFrom": "2017-01-01",
      "taxDateTo": "2017-01-01",
      "periodStatus": "N"
    },
    "unitPrice": 10,          //抚养单价
    "status": "O",            //审核状态 O - 未审核/C - 已审核
    "items": [                //计提明细
      {
        "lineNum": 1,        //行号
        "hogPen": "内丘猪场",  //猪场
        "houseHold": "养户1",  //养户
        "batch": "1001",      //饲养批次
        "beginQty": 1,        //期初存栏
        "endQty": 1,          //期末存栏
        "sumQty": 1,          //批次日存栏汇总
        "category": "105",    //费用类别
        "description": "直接人工",  //费用说明
        "price": 10           //计提金额
      }
    ]
  }
}
```
### 4.2 保存计提数据
#### 输入
**地址:** http://{ip}:{port}/cal/save.htm

**提交方式:** POST **参数形式:** JSON

**参数:**
```JSON
{
    "branch": 1,               //分支机构代码     非空
    "periodCode": "2017-01",   //核算期间 非空
    "items": [{
  		"amount": 31.5,         //计提金额
  		"lineNum": 1            //行号
  	}, {
  		"amount": 41.5,
  		"lineNum": 2
  	}]
}
```
#### 输出
```JSON
{
    "msg": "success",
    "flag": 1,
    "result": "success"
}
```
### 4.3 审核
#### 输入
**地址:** http://{ip}:{port}/cal/check.htm

**提交方式:** POST **参数形式:** JSON

**参数:**
```JSON
{
    "branch": 1,               //分支机构代码     非空
    "periodCode": "2017-01",   //核算期间 非空
    "status": "O"              //审核状态 O - 未审核/C - 已审核 非空
}
```
#### 输出
```JSON
{
    "msg": "success",
    "flag": 1,
    "result": "success"
}
```
## 5.成本核算
### 5.1 获取源数据检查信息
#### 输入
**地址:** http://{ip}:{port}/cost/sourceCheck.htm

**提交方式:** GET **参数形式:** params

**参数:**
| 字段名称       | 说明           | 类型   |
|:-------------:|:-------------:| :-----:|
|branch| 分支机构代码 非空| Number |
|periodCode| 核算期间 非空| String |

#### 输出
```JSON
{
    "msg": "success",
    "flag": 1,
    "result": {
        "startDate":"2018-01-01 10:00:00",  //开始时间
        "endDate":"2018-01-01 10:01:00",    //结束时间
        "durationMills":60000, //已执行时间
        "branch": {        //分支机构信息
            "code": 3,
            "name": "东方希望内丘畜牧有限公司",
            "description": null
        },
        "period": {       //期间信息
            "docEntry": 1,
            "code": "2018-01",
            "name": "2018-01",
            "postingDateFrom": "2018-01-01",
            "postingDateTo": "2018-01-31",
            "dueDateFrom": "2018-01-01",
            "dueDateTo": "2018-12-31",
            "taxDateFrom": "2018-01-01",
            "taxDateTo": "2018-12-31",
            "periodStatus": "N"
        },
        "items": [     //检查列表
            {
                "checkId": "A0010",     //检查项编号
                "checkDesc": "A0010",   //检查项说明
                "checkStatus": "P",     //检查状态	N-未检查；P-未通过；Y-已通过
                "confirmed": "N",       //是否确认  Y:确认 N：未确认
                "messages": [           //错误信息
                    {
                        "id": "1",
                        "desc": "CheckPoint_01_Msg"
                    },
                    {
                        "id": "2",
                        "desc": "CheckPoint_02_Msg"
                    }
                ]
            }
        ]
    }
}
```
### 5.2 执行源数据检查
#### 输入
**地址:** http://{ip}:{port}/cost/sourceCheck.htm

**提交方式:** POST **参数形式:** JSON

**参数:**
```JSON
{
    "branch": 1,               //分支机构代码     非空
    "periodCode": "2017-01",   //核算期间 非空
}
```
#### 输出
```JSON
{
    "msg": "success",
    "flag": 1,
    "result": {
        "startDate":"2018-01-01 10:00:00",  //开始时间
        "endDate":"2018-01-01 10:01:00",    //结束时间
        "durationMills":60000, //已执行时间
        "branch": {        //分支机构信息
            "code": 3,
            "name": "东方希望内丘畜牧有限公司",
            "description": null
        },
        "period": {       //期间信息
            "docEntry": 1,
            "code": "2018-01",
            "name": "2018-01",
            "postingDateFrom": "2018-01-01",
            "postingDateTo": "2018-01-31",
            "dueDateFrom": "2018-01-01",
            "dueDateTo": "2018-12-31",
            "taxDateFrom": "2018-01-01",
            "taxDateTo": "2018-12-31",
            "periodStatus": "N"
        },
        "items": [     //检查列表
            {
                "checkId": "A0010",     //检查项编号
                "checkDesc": "A0010",   //检查项说明
                "checkStatus": "P",     //检查状态	N-未检查；P-未通过；Y-已通过
                "confirmed": "N",       //是否确认  Y:确认 N：未确认
                "messages": [           //错误信息
                    {
                        "id": "1",
                        "desc": "CheckPoint_01_Msg"
                    },
                    {
                        "id": "2",
                        "desc": "CheckPoint_02_Msg"
                    }
                ]
            }
        ]
    }
}
```
### 5.3 获取成本核算信息
#### 输入
**地址:** http://{ip}:{port}/cost/costAccounting.htm

**提交方式:** GET **参数形式:** params

**参数:**
| 字段名称       | 说明           | 类型   |
|:-------------:|:-------------:| :-----:|
|branch| 分支机构代码 非空| Number |
|periodCode| 核算期间 非空| String |

#### 输出
```JSON
{
  "msg": "success",
  "flag": 1,
  "result": [
    {
      "step": {         //核算阶段
        "code": "G",    //阶段代码
        "name": "种公猪" //阶段名称
      },
      "startTime": "2017-01-01 19:30",  //开始时间
      "endTime": "2017-01-01 19:31",    //结束时间
      "durationMillSeconds": 60000,     //耗时（毫秒）
      "errMsg": null,  //错误信息
      "status": "N" //处理状态：N-未核算；P-执行中；Y-已核算
    },
    {
      "step": {
        "code": "M",
        "name": "种母猪"
      },
      "startTime": "2017-01-01 19:30",
      "endTime": "2017-01-01 19:31",
      "durationMillSeconds": 60000,
      "errMsg": null,
      "status": "O"
    }
  ]
}
```
### 5.4 执行成本核算
#### 输入
**地址:** http://{ip}:{port}/cost/costAccounting.htm

**提交方式:** POST **参数形式:** JSON

**参数:**
```JSON
{
  "branch": 1,              //分支机构代码     非空
  "steps": [                //成本阶段 可空默认执行所有阶段
      {
          "code": "G",
          "cover": "N"
      },
      {
          "code": "M",
          "cover": "Y"
      }
  ],
  "periodCode": "2017-01-01"  //核算期间  非空
}
```
#### 输出
```JSON
{
    "msg": "success",
    "flag": 1,
    "result": "success"
}
```

### 5.5 获取成本检查信息
#### 输入
**地址:** http://{ip}:{port}/cost/costCheck.htm

**提交方式:** GET **参数形式:** params

**参数:**
| 字段名称       | 说明           | 类型   |
|:-------------:|:-------------:| :-----:|
|branch| 分支机构代码 非空| Number |
|periodCode| 核算期间 非空| String |

#### 输出
```JSON
{
    "msg": "success",
    "flag": 1,
    "result": {
        "startDate":"2018-01-01 10:00:00",  //开始时间
        "endDate":"2018-01-01 10:01:00",    //结束时间
        "durationMills":60000, //已执行时间
        "branch": {        //分支机构信息
            "code": 3,
            "name": "东方希望内丘畜牧有限公司",
            "description": null
        },
        "period": {       //期间信息
            "docEntry": 1,
            "code": "2018-01",
            "name": "2018-01",
            "postingDateFrom": "2018-01-01",
            "postingDateTo": "2018-01-31",
            "dueDateFrom": "2018-01-01",
            "dueDateTo": "2018-12-31",
            "taxDateFrom": "2018-01-01",
            "taxDateTo": "2018-12-31",
            "periodStatus": "N"
        },
        "items": [     //检查列表
            {
                "checkId": "A0010",     //检查项编号
                "checkDesc": "A0010",   //检查项说明
                "checkStatus": "P",     //检查状态	N-未检查；P-未通过；Y-已通过
                "confirmed": "N",       //是否确认  Y:确认 N：未确认
                "messages": [           //错误信息
                    {
                        "id": "1",
                        "desc": "CheckPoint_01_Msg"
                    },
                    {
                        "id": "2",
                        "desc": "CheckPoint_02_Msg"
                    }
                ]
            }
        ]
    }
}
```
### 5.6 执行成本检查
#### 输入
**地址:** http://{ip}:{port}/cost/costCheck.htm

**提交方式:** POST **参数形式:** JSON

**参数:**
```JSON
{
    "branch": 1,               //分支机构代码     非空
    "periodCode": "2017-01",   //核算期间 非空
}
```
#### 输出
```JSON
{
    "msg": "success",
    "flag": 1,
    "result": {
        "startDate":"2018-01-01 10:00:00",  //开始时间
        "endDate":"2018-01-01 10:01:00",    //结束时间
        "durationMills":60000, //已执行时间
        "branch": {        //分支机构信息
            "code": 3,
            "name": "东方希望内丘畜牧有限公司",
            "description": null
        },
        "period": {       //期间信息
            "docEntry": 1,
            "code": "2018-01",
            "name": "2018-01",
            "postingDateFrom": "2018-01-01",
            "postingDateTo": "2018-01-31",
            "dueDateFrom": "2018-01-01",
            "dueDateTo": "2018-12-31",
            "taxDateFrom": "2018-01-01",
            "taxDateTo": "2018-12-31",
            "periodStatus": "N"
        },
        "items": [     //检查列表
            {
                "checkId": "A0010",     //检查项编号
                "checkDesc": "A0010",   //检查项说明
                "checkStatus": "P",     //检查状态	N-未检查；P-未通过；Y-已通过
                "confirmed": "N",       //是否确认  Y:确认 N：未确认
                "messages": [           //错误信息
                    {
                        "id": "1",
                        "desc": "CheckPoint_01_Msg"
                    },
                    {
                        "id": "2",
                        "desc": "CheckPoint_02_Msg"
                    }
                ]
            }
        ]
    }
}
```
### 5.7 获取凭证生成信息
#### 输入
**地址:** http://{ip}:{port}/cost/genDoc.htm

**提交方式:** GET **参数形式:** params

**参数:**
| 字段名称       | 说明           | 类型   |
|:-------------:|:-------------:| :-----:|
|branch| 分支机构代码 非空| Number |
|periodCode| 核算期间 非空| String |

#### 输出
```JSON
{
    "msg": "success",
    "flag": 1,
    "result": {
        "startDate": null,        //开始时间
        "endDate": null,          //结束时间
        "durationMills": null,    //已执行时间
        "items": [
            {
                "category": {         //业务类别
                    "code": "1001",   //类别代码
                    "name": "1001"    //类别名称
                },
                "createTime": null,   //创建时间
                "status": "N",        //状态：N：未生成 Y：已生成 S:已同步 E:同步错误
                "debit": 0,           //借方
                "credit": 0,          //贷方
                "syncMsg": null       //同步消息
            }
        ]
    }
}
```
### 5.8 执行凭证生成
#### 输入
**地址:** http://{ip}:{port}/cost/genDoc.htm

**提交方式:** POST **参数形式:** JSON

**参数:**
```JSON
{
  "codes": [                  //需要生成凭证的业务类别代码集合 非空
    "1001",
    "1002"
  ],
  "branch": 3,                //分支机构代码     非空
  "periodCode": "2018-01"     //核算期间  非空
}
```
#### 输出
```JSON
{
    "msg": "success",
    "flag": 1,
    "result": "success"
}
```

### 5.9 同步凭证
#### 输入
**地址:** http://{ip}:{port}/cost/genDoc/sync.htm

**提交方式:** POST **参数形式:** JSON

**参数:**
```JSON
{
  "codes": [                  //需要同步的凭证业务类别代码集合 非空
    "1001",
    "1002"
  ],
  "branch": 3,                //分支机构代码     非空
  "periodCode": "2018-01"     //核算期间  非空
}
```
#### 输出
```JSON
{
    "msg": "success",
    "flag": 1,
    "result": "success"
}
```
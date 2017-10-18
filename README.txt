1.项目介绍
cariello-planet 是springMVC+sqlite 框架结合原型项目

2.接口描述

(1)微信授权登录
访问地址：http://122.112.210.142/planet/wechat/login
访问参数:  code (String)
返回结果：成功 {"result":1,"token":"fjwej3jerj82348439","ifSave":true}
              失败 {"result":0,"errMsg":"错误错误"}

(2)菜品识别
访问地址：http://122.112.210.142/planet/food/detect
访问参数:  foodImage (File)
返回结果：成功 {"result":1,"data":{"calorie":"447","name":"回锅肉","probability":"0.950897"}}
              失败 {"result":0,"errMsg":"错误错误"}

(3)保存用户信息
访问地址：http://122.112.210.142/planet/user/save
访问参数:  wechat_login_token (String)
                openId (String)
                nickName (String)
                gender (String)
                city (String)
                province (String)
                country (String)
                avatarUrl (String)
返回结果：成功 {"result":1}
              失败 {"result":0,"errMsg":"错误错误"}

(4)喂养
访问地址：http://122.112.210.142/planet/feed/do
访问参数:  wechat_login_token (String) name (String) calorie (String) probability (String)
返回结果：成功 {"result":1}
              失败 {"result":0,"errMsg":"错误错误"}

(4)喂养记录查询
访问地址：http://122.112.210.142/planet/feed/queryListByTimeRange
访问参数: wechat_login_token (String)  foodName (String) startTime (String yyyy-MM-dd HH:mm:ss) endTime(String yyyy-MM-dd HH:mm:ss)
返回结果：成功 {"result":1,"data":[{"calorie":"933","feedTime":"2017-10-18 11:09:21","foodName":"水果沙拉0","openId":"408605"}]}
              失败 {"result":0,"errMsg":"错误错误"}
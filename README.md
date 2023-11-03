# 一、工具概述

![fofa_search](https://socialify.git.ci/thebatmanfuture/fofa_search/image?description=1&font=Rokkitt&forks=1&issues=1&language=1&logo=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F75312962%3Fv%3D4&name=1&owner=1&pattern=Diagonal%20Stripes&stargazers=1&theme=Dark)

`Fofa_Search` 是基于Java8在 `fofa_viewer v1.1.12` 上进行魔改二开产生的Fofa便捷搜索工具，界面如下：

![](images/1.png)

**当前工具版本号：V1.0.1**

工具下载地址：[https://github.com/thebatmanfuture/fofa_search/releases](https://github.com/thebatmanfuture/fofa_search/releases)

**关于学习交流以及问题的解决**

![微信群](./WeChat.jpg)

如果微信群已满200人，请加微信号 `chunshangchunshu_` 邀请入群即可

# 二、TODO

相比于 `fofa_viewer` ，本工具新增以下十余种功能：

* [x] 1、能够打开项目：读取指定TXT文件，用每一行作为Fofa查询语法从而实现批量查询
* [x] 2、能够保存项目：打开标签页的查询语法都保存成一个TXT文件
* [x] 3、能够导出全部内容：打开所有全部的标签页，全部导出到一个TXT文件，并自动内容去重
* [x] 4、AWVS格式导出：用AWVS漏洞扫描需要导入 `csv` 格式文件，新增实现AWVS的导入实现
* [x] 5、URL存活探测：导出大量URL之后必须进行存活判断，实现导入文件后自动判断存活
* [x] 6、组合语法功能：导入基础语法和组合语法，配合其他选项，能更自由地生成新的内容
* [x] 7、提取检测率：统计初始语法和组合语法之间的占比
* [x] 8、AWVS格式转换：将导出的TXT转换成AWVS需要导入的 `csv` 格式文件
* [x] 9、组合语法切分：通过组合语法功能得到很多语法，自动进行切分处理操作
* [x] 10、长度限制超过数据保留：如果超过能导出的限制，则会将超过的语法结果追加写入到一个文件
* [x] 11、URL数据去重：自动对URL数据进行去重处理

**师傅们有什么想新加的功能，可以提交issue**

**如果师傅们觉得不错，欢迎给我点个Star哈哈~**


# 三、创新点细节

## 1、打开项目

这里指打开一个TXT文件，读取其中每一行作为fofa查询语法，由此可以实现批量查询

![](images/2.png)

## 2、保存项目

将下面打开的标签页的查询语法都保存成一个TXT文件

![](images/3.png)

## 3、导出全部

`fofa_viewer` 只能导出当前一个标签页的数据，还是以CSV格式，每次获取hosts数据都要打开，很麻烦

所以本工具实现了打开所有标签页都全部导出到一个TXT文件中，并自动内容去重

![](images/4.png)

## 4、AWVS格式导出

很多人用AWVS漏洞扫描，需要导入 `.csv` 格式的文件，由此本工具增加AWVS格式导出功能，实现导入

![image.png](images/5.png)

内容是400条URL一个文件（因为AWVS不能一次导入大于500条URL）

![image.png](images/6.png)

## 5、URL存活探测

导出大量URL之后必须进行存活判断，实现导入文件后自动判断存活

![image.png](images/7.png)

存活则生成新的TXT文件，以展示当前百分比判断项目的进度

## 6、组合语法功能

![image.png](images/8.png)

该功能是导入基础语法和组合语法，配合上其他选项，生成新的内容，例如：

```
基础语法：1.txt
    host="test123.cn"
    host="nefu.cn"
组合语法：2.txt
    body="register"
    body="login"
生成3.txt
host="test123.cn" && body="register"
host="test123.cn" && body="login"
host="nefu.cn" && body="register"
host="nefu.cn" && body="login"
```

![image.png](images/9.png)

## 7、提取检测率

这里首先输入初始语法，输入之后会进行查询，得到size的大小（即该语法实际有多少数据）

再导入组合语法生成内容，即利用组合语法功能生成的TXT大全，导入之后会检测TXT文件的行数

两者进行百分比计算，得出覆盖率，如图：

![image.png](images/10.png)

## 8、AWVS格式转换

这里如果之前通过"导出全部"功能得到了是txt功能，但是你又想将这些TXT再转换成AWVS需要导入的 `.csv` 格式文件

![](images/11.png)

## 9、组合语法切分

如果通过组合语法功能得到了很多语法，大约3000多行，但是你一次性导入在 `fofa_search` 中可能程序会崩溃，或者会出现其他情况

这时候本工具就实现导入组合语法自动切分，会100行，100行的自动切分

![](images/12.png)

如下

![image.png](images/13.png)

如果你想合并其中的某几部分，只需要将这些文件复制到一个文件夹下，在cmd执行

```powershell
copy *.txt hhhhhhhhhhhh.txt
```

## 10、长度限制超过数据保留

在 `query` 方法中

![image.png](images/14.png)

如下，`maxSize > client.max` 就是实际存在的数据和我当前会员最多能导出的数量（10000条）

即如果超过能导出的限制，则会将超过的语法追加写入到一个文件

![image.png](images/15.png)

如下

![image.png](images/16.png)

当我们第一次通过组合语法进行查询之后，肯定在新的组合语法里面也有超过的语句，那么我们将这些超过最大限制的语句作为第二次组合语法的基础语法，再次生成新的语法，进行查询

由此，直到没有超过1万条的内容，然后我们可以进行之前的步骤进行覆盖率检测，看看成功率是多少，来判断以后是否增加组合语法内容

## 11、URL去重

![image.png](images/17.png)


# 三、其他tips

## 1）必须将jar设置成以UTF8格式启动

![image.png](images/18.png)

只有这样，你导入时中文才不会像如下乱码，所以运行时直接点击start.bat即可

![image.png](images/19.png)


## 2）新版会员API限制

现在新版的fofa会员不仅没有永久的，而且即使是api也是一个月固定不能超过50万，这显然是不利于我们的组合语法功能，所以推荐使用原来有永久会员的号

![image.png](images/20.png)

如下

![image.png](images/21.png)


## 3）一次性不要导入太多语法

最好是一次导入100条语法，直接导入了几千条后点击“导出全部”会卡住


# 四、感谢各位师傅的支持

## 致谢

Fofa_Search 已加入 [FOFA 共创者计划](https://fofa.info/development)，感谢 FOFA 提供的账号支持。

![fofa-logo](https://user-images.githubusercontent.com/40891670/209631625-f73811b0-a26a-4a42-8158-e5061464481d.png)

## Stargazers

[![Stargazers repo roster for @thebatmanfuture/fofa_search](https://reporoster.com/stars/thebatmanfuture/fofa_search)](https://github.com/thebatmanfuture/fofa_search/stargazers)


## Forkers

[![Forkers repo roster for @thebatmanfuture/fofa_search](https://reporoster.com/forks/thebatmanfuture/fofa_search)](https://github.com/thebatmanfuture/fofa_search/network/members)


## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=thebatmanfuture/fofa_search&type=Date)](https://star-history.com/#thebatmanfuture/fofa_search&Date)


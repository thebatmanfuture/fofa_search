# fofa_search
 基于java8在fofa_viewer v1.1.12上二开

![](images/1.png)

相比于fofa_viewer增加了如下功能：

1）打开项目

这里指打开一个txt文件，读取其中每一行作为fofa查询语法，由此可以实现批量查询

![](images/2.png)

2）保存项目

将下面打开的标签页的查询语法都保存成一个txt文件

![](images/3.png)

3）导出全部

fofa_viewer只能导出当前一个标签页的数据，还是以CSV格式，每次获取hosts数据都要打开，很麻烦，所有我这里的功能是打开所有全部的标签页都全部导出到一个txt文件，内容去重

![](images/4.png)

4）AWVS格式导出

很多人用AWVS漏洞扫描，需要导入xxx.csv文件，由此，我增加此功能，实现导入

![image.png](images/5.png)

内容是400一分，因为AWVS不能一次导入大于500条URL

![image.png](images/6.png)

.

5）URL存活

导出大量URL之后必须进行存活判断，点击导入文件判断存活，

![image.png](images/7.png)

存活则生成新的txt文件，以展示当前百分比判断项目的进度

6）组合语法

![image.png](images/8.png)

该功能是导入基础语法，和组合语法，配合上其他选项，生成新的内容，例如

~~~
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
~~~

![image.png](images/9.png)

7）提取检测率

这里首先输入初始语法，输入之后会进行查询，得到size的大小（即该语法实际有多少数据），再导入组合语法生成内容，即利用组合语法功能生成的txt大全，导入之后会检测txt文件的行数，两者进行百分比计算，得出覆盖率，如图

![image.png](images/10.png)

8）AWVS格式转换

这里如果之前通过"导出全部"功能得到了是txt功能，但是你又想将这些txt再转换成AWVS需要导入的csv

![](images/11.png)

9）组合语法切分

如果你通过组合语法功能得到了很多语法，大约3000多行，但是你一次性导入在fofa_search中可能程序会崩溃，或者其他情况之类的，这时候你导入组合语法切分后，会100行，100行的切分

![](images/12.png)

如下

![image.png](images/13.png)

如果你想合并其中的某几部分，只需要将这些文件复制到一个文件夹下，在cmd执行

~~~powershell
copy *.txt hhhhhhhhhhhh.txt
~~~



10）长度限制超过数据保留

在query方法中

![image.png](images/14.png)

如下，maxSize > client.max就是实际存在的数据和我当前会员最多能导出的数量（10000条）

即如果超过能导出的限制，则会将超过的语法追加写入到一个文件

![image.png](images/15.png)

如下

![image.png](images/16.png)

当我们第一次通过组合语法进行查询之后，肯定在新的组合语法里面也有超过的语句，那么我们将这些超过最大限制的语句作为第二次组合语法的基础语法，再次生成新的语法，进行查询

由此，直到没有超过1万条的内容，然后我们可以进行之前的步骤进行覆盖率检测，看看成功率是多少，来判断以后是否增加组合语法内容

11）组合语法切分



12）URL去重

![image.png](images/17.png)



#### 其他tips

1）必须将jar设置成以UTF8格式启动

![image.png](images/18.png)

只有这样，你导入时中文才不会像如下乱码，所以运行时直接点击start.bat即可

![image.png](images/19.png)



2）新版会员api限制

现在新版的fofa会员不仅没有永久的，而且即使是api也是一个月固定不能超过50万，这显然是不利于我们的组合语法功能，所以推荐使用原来有永久会员的号

![image.png](images/20.png)

如下

![image.png](images/21.png)

.

3）最好是100条语法一次导入，我这里导入了几千条现在点击"导出全部"已经卡住了



有什么想新加的功能可以提交issue



#### 1.0.2更新

修复了数据导出时格式转换的错误


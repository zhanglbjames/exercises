索引

## 索引的作用-一个例子
> 索引对查询的速度有着至关重要的影响，理解索引也是进行数据库性能调优的起点。
考虑如下情况，假设数据库中一个表有10^6条记录，DBMS的页面大小为4K，并存储100条记录。
如果没有索引，查询将对整个表进行扫描，最坏的情况下，如果所有数据页都不在内存，
需要读取10^4个页面，如果这10^4个页面在磁盘上随机分布，需要进行10^4次I/O，
假设磁盘每次I/O时间为10ms(忽略数据传输时间)，则总共需要100s(但实际上要好很多很多)。
如果对之建立B-Tree索引，则只需要进行log100(10^6)=3次页面读取，最坏情况下耗时30ms。
这就是索引带来的效果，很多时候，当你的应用程序进行SQL查询速度很慢时，应该想想是否可以建索引。

索引的优点：
1. 减少了服务器需要扫描的数据量
2. 避免排序和临时表
3. 将随机IO变为顺序IO

## 聚集索引和非聚集索引的区别及优缺点

1. 聚集索引一个表只能有一个，而非聚集索引一个表可以存在多个
2. 聚集索引存储记录是物理上连续存在，而非聚集索引是逻辑上的连续，物理存储并不连续
3. 聚集索引:物理存储按照索引排序；聚集索引是一种索引组织形式，索引的键值逻辑顺序决定了表数据行的物理存储顺序
　 非聚集索引:物理存储不按照索引排序；非聚集索引则就是普通索引了，仅仅只是对数据列创建相应的索引，不影响整个表的物理存储顺序.
4. 索引是通过二叉树的数据结构来描述的，我们可以这么理解聚簇索引：索引的叶节点就是数据节点。而非聚簇索引的叶节点仍然是索引节点，只不过有一个指针指向对应的数据块。

优势与缺点:
> 聚集索引插入数据时速度要慢（时间花费在“物理存储的排序”上，也就是首先要找到位置然后插入）,查询数据比非聚集数据的速度快


## InnoDB只能还有一个聚集索引
> 
- 如果表中含有主键,则InnoDB组织数据就是通过这个聚集索引
- 如果没有主键，则Mysql会选择第一个（按照声明的顺序）不允许null的unique的普通索引作为聚集索引
- 如果没有索引，或者索引都不是非null的唯一索引，则使用InnoDB引擎内置的ROWID作为聚集索引

## InnoDB的聚集索引可以包含多列
比如在创建表的时候指定包含多列的主键，在存储的时候按照聚集索引包含的列的前后顺序来分组排序存放

## InnoDB可以有多个非聚集索引
非聚集索引通过引用主键索引（聚集索引）来访问或者定位数据
> 其他索引(普通索引)中不会保存行的物理位置,而是保存主键的值,所以通过"二级索引"进行查找是先找到主键,
再找到行,要进行二次索引查找

## InnoDB中聚集索引和主键的关系
在InnoDB表中，其聚集索引相当于整张表，而整张表也是聚集索引。主键必然是聚集索引，而聚集索引则未必是主键。



## select选择字段是否用到索引

#### 1- 单列索引
- 当在where子句中在单列索引字段**在数值运算的一侧或者函数**，那么就不会使用单列索引
> select id from people where id + 1 = 5; # 不使用索引
select id from people where id = 5+1; # 使用索引
select ... from people where to_days(current_date) - to_days(date_col) <= 10; # 不使用索引

- 对于创建 blob text varchar 类型字段的索引，必须指定长度，因为Mysql不允许这些列的完整长度
`create index index_name on table_name(varchat_columns(10))`

确定前缀索引的长度：为了尽量使得单列前缀索引更有选择性，需要进行列的选择性测试

选择性 = 不重复的行数 / 总行数
计算方式如下：
```
select count(left(index_column,3))/count(*) as pre_index_3,
    count(left(index_column,4))/count(*) as pre_index_4
    from database_name.table_name;
````
如果发现pre_index_4 > pre_index_3
那么就创建这个索引
`alter table table_name add index/key index_name(index_column(4));`

注意：
- 以下不能使用前缀索引：
**order by、group by、覆盖索引**

#### 2- 复合索引

考虑如下索引
```
      CREATE TABLE `people` (
        `last_name` varchar(50)  NOT NULL,
        `first_name` varchar(50)  NOT NULL,
        `age` int(8) NOT NULL ,
        `gender` enum('m','f') NOT NULL,
        KEY `last_name` (`last_name`,`first_name`,`age`)
      ) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
```

###### 使用索引的情况

1. 全值匹配
和索引中所有列进行匹配
`select gender from people where last_name = 'Tom' and first_name = 'cat' and age=10;`
使用了符合索引的全部列
即where子句对索引列全值匹配

2. 匹配最左前缀列
`select gender from people where last_name = 'Tom';`
使用了复合索引的第一列

3. 匹配列的最左前缀
`select gender from people where last_name like 'Tom%';`
使用了索引的第一列
注意like子句开头不能包含通配符(%,_)，而且必须是常量字符串，而不能是其他字段名，否则使用不到索引


4. 匹配范围（where子句中只有第一个符合最左前缀列的范围匹配可以使用到索引）
要求：1. 范围列是最左前缀列；只能匹配第一个范围列
`select gender from people where last_name between 'Alice' and 'Davis';`
使用了符合索引的第一列
`select gender from people where first_name between 'Alice' and 'Davis';`
不满足要求，所以不会使用索引
表示范围的可以使用到索引：
<,<=,=,>,>=
between and
in(num1,num2)
注意 != , <> 这些不等于不能使用索引，而是应该使用 column > num or column < num 来等价替换，来使用索引


5. where子句先是精确匹配再是范围匹配
`select gender from people where last_name = 'James' and first_name between 'Alice' and 'Davis';`
使用索引的第一列（全匹配）和第二列（范围匹配）

6. 创建索引时指定列的排序模式，排序时使用相同的排序顺序或则完全相反的排序顺序
创建索引：`CREATE INDEX idx_example ON table1 (col1 ASC, col2 DESC, col3 ASC);`
1-相同顺序排序：`Select col1, col2, col3 from table1 order by col1 ASC, col2 DESC, col3 ASC;`
2-完全逆序排序`Select col1, col2, col3 from table1 order by col1 DESC, col2 ASC, col3 DESC; `
3-不完全顺序排序 `Select col1, col2, col3 from table1 order by col1 ASC, col2 ASC, col3 ASC`
排序使用到的索引只有1，2；3没有使用到索引。



###### 不使用索引列的情况
1. 不是索引的最左前缀列
2. 不是单列索引的列的最左前缀，主要限制为 like子句的开头包含通配符前缀
3. 跳过中间列，只能使用中间列前面的那些索引列
4. 一个范围匹配（包括 like子句）的后面无法再使用索引
5. where 子句中使用了索引，则其后面的order by group by子句没有办法再使用索引
6. 索引的列在运算符的一侧，即紧邻的是 +，- / *这些运算符，而不是紧邻 = ，<,>,>=,<= 这些符号；
或者对索引列使用函数；这些都将不能使用索引。

###### 最左前缀和对and的查询优化


###### 可以使用到索引的子句

###### 多个子句使用索引的情况
每一个select查询只能使用一个索引

###### where中是用or连接的查询条件

###### where中在索引列之间包含普通列

#### 3- 覆盖索引
如果一个索引包含（覆盖）所有需要查询的字段的值，我们就称为覆盖索引
即 select 子句选择的字段都出现在定义多列索引的字段中。
因为索引中包含查询的所有字段，所以就不需要根据索引回数据行获取其他非索引列的数据，
而是直接将索引中的数据直接返回。


创建索引
`alter table table_name add index index_name(column_1,column_2,column_3);`

查询时使用覆盖索引
`select column_2 from table_name where column_2 = 8;`
注意这里没有使用最左前缀列，而是通过索引覆盖来使用索引

###### 覆盖索引的限制


#### 4- 不同类型索引的选择问题
> 如果一个列被多个不同类型索引包含，那么如何选择索引的问题
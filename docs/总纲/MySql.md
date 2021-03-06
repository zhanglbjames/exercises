# Mysql

## 0-索引 
> [索引原理与优化](./Mysql_index.md)

 0.1- MyISAM/InnoDB索引原理（聚集索引、非聚集索引）
> 索引是在存储引擎层而不是服务器层实现的，所以没有统一的标准，大多数索引都是通过BTREE(B+树)的数据结构来实现的，之所以是BTREE是因为
Mysql使用这个关键字来描述索引的类型

- MyISAM：索引是非聚集索引，即索引和数据单独存放，物理不连续，使用的数据结构式B-Tree，使用前缀压缩技术使得索引更小，索引通过数据的物理位置引用被索引的行
- InnoDB：有且只有一个聚集索引，按照原数据格式进行存储，根据主键来引用被索引的行。由此看见主键的重要性（之注意只要求主键唯一，但是主键可以包含多列）

主键和索引，聚集索引以及非聚集索引，唯一索引，不唯一索引的区别
> 见 http://blog.csdn.net/ochangwen/article/details/53997366

#### 0.2-索引的创建方式

- 通过**key()关键字**在创建数据表的时候指定索引（也叫键）
```
CREATE TABLE `people` (
  `last_name` varchar(50)  NOT NULL,
  `first_name` varchar(50)  NOT NULL,
  `dob` date NOT NULL,
  `gender` enum('m','f') NOT NULL,
  KEY `last_name` (`last_name`,`first_name`,`dob`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
```

- 通过 **create index** 来创建索引

```
create index index_name on table_name(column_list);
如
create index last_first_index on people(last_name(5),first_name(7));

```

- 通过 **alter table ... add index**来创建索引
`alter table people create index d_g_index(dob,gender);`


#### 0.3-索引的删除

- 首先查看索引的信息
`show index from people;`

- 通过**drop index ... on**来删除索引
`drop index index_name on table_name`

- 通过 **alter table ... drop index**来删除索引
`alter table people drop index index_name;`

- 删除主键类型的是索引
`alter table people drop primary key;`


#### 0.4- 索引的使用及分析
> http://www.cnblogs.com/hustcat/archive/2009/10/28/1591648.html
http://www.jianshu.com/p/43c7442d0c63


## 1-join的各种方式

#### 左连接右表不唯一
当A left join B时，对于联结条件来说，一个A的联结字段对应B的多条记录，则会发生1：N的情况
解决方法是让B表的联结字段唯一。

#### inner join 
> 包含两种方式，一种是隐式的，一种是显示的
- select A.id,B.name from A,B where A.id = B.id;
这句其实是先内联接两个表生成中间表，然后从中间表中过滤出where子句中限制的记录。
- select A.id,B.name from A inner join B on A.id = B.id;
返回where查询条件或者on联结条件的记录，即返回两个表满足where或者on条件的记录
换句话说就是条件里两个表之间要对应，不对应的就过滤，这也是内联接和外联结的区别

#### left join（left outer join）、right join（right outer join）、full join（full outer join）
> 这三种是外联结，outer关键字可以省略，和内联接的区别是外联接不仅返回符合条件的记录还返回没有匹配的记录，具体如下
- left join：以左表为基准，当联结条件中右表没有与左表匹配的记录则此时右表的对应字段显示为null (1:0)；
如果有一条匹配的记录则 1:1；如果有多于1条这会出现 1:n，即结果集大于左表原来的记录数
- right join和left join具有相同的效果，只是左右不一样
- full join：相当于先进行一次left join，然后在进行一次right join，然后对这两次的结果集合并，然后去重。换句话说包含三个部分
即符合联结条件的记录，以左表为基准与右表不配的记录，以右表为基准与左表不匹配的记录

注意Mysql不支持全连接，Oracle和DB2支持

#### cross join
> 有两种一种是隐式的一种是显示的
- select A.id, B.name from A,B where A.id = 1;
A表中的每一条满足where子句的记录都对应B表的全部记录
- select A.id ,B.name from A cross join B on A.id = 1;
效果和1中的一样

#### where子句
where字句是对联结之后的结果集进行过滤，即放在on联结条件子句之后

## 2-group by的注意事项
> http://blog.csdn.net/xxpyeippx/article/details/8059910

#### 分组中的select字段的约束
当使用分组时，select的字段只能是分组字段列或者聚集函数列

#### 分组前的where子句和分组后的having子句的区别
- where 子句的作用是在对查询结果进行分组前，将不符合where条件的行去掉，即在分组之前过滤数据，条件中不能包含聚组函数，使用where条件显示特定的行。
- having 子句的作用是筛选满足条件的组，即在分组之后过滤数据，条件中经常包含聚组函数，使用having 条件显示特定的组，也可以使用多个分组标准进行分组。
having 子句被限制子已经在SELECT语句中定义的列和聚合表达式上。通常，你需要通过在HAVING子句中重复聚合函数表达式来引用聚合值，就如你在SELECT语句中做的那样。
例如：SELECT A COUNT(B) FROM TABLE GROUP BY A HAVING COUNT(B)>2

http://www.cnblogs.com/xizhongshui/p/5864581.html

## 3-union联合的注意事项
> union用于联合两个select语句的结果集，并去除表这两个结果集中任何重复的数据记录（即A与B中重复的也删除，而不仅仅是删除A中重复，然后删除B中重复，然后联合，相当于先合并在一个临时表中，对这个临时表进行去重，即去重是在合并之后进行的）;
但是可以使用union all来允许联合两个结果集重复的数据记录

1. 两个select子句的列的数量必须相同。
2. 两个select子句的列的名称可以不相同，但是逻辑上对应的列的顺序要一致，使用的函数表达式和聚集函数也要一样，即按照列的顺序进行联合的。
3. 对应列的数据类型可以不一致，但必须是DBMS允许的隐含的类型转换类型
4. select子句中可以包含任何过滤条件 distinct、where等
5. 对于order by子句，因为两个select子句不允许使用不同的排序策略，所以多个select子句进行联合一般包含一条order by子句，而且是在最后位置
如果在select子句上使用order by子句也会被优化忽略，因为和最外层的order by子句重叠；如果想让内排序其作用则可以配合使用limit，从而使内排序对子select结果集产生影响。
6. union可以实现行变列的转换 http://blog.csdn.net/rainyspring4540/article/details/50230259

## 4-触发器
> 1. 一种特殊的存储过程，由事件触发（insert,delete,update）。
分为事前触发和事后触发。而语句级触发可以在语句执行前或者后执行，行级触发发生在触发器所影响的每一行触发一次。
2. 和存储过程区别：触发器隐时调用，不能接受参数输入。

http://www.cnblogs.com/duodushu/p/5446384.html

#### 触发器语法
```
# 指定触发器的名称
create trigger trigger_name 
# 指定触发器执行的时机（有两个值 before、after）
trigger_time          
# 指定触发器对指定数据表上的监测的事件
triggle_event on table_name 
for each row
# 指定将要执行的触发器实体内容，可以是用begin end包含的sql语句
triggle_statement;          
```


有三个语句级事件：insert、update、delete，以及两个行级事件：load data、replace
其中load data语句用于将一个文件装入到一个数据表中，相当于一系列inset操作
对于replace语句和insert很像
> 只是在表中有 primary key 或 unique 索引时，如果插入的数据和原来 primary key 或 unique 索引一致时，
会先删除原来的数据，然后增加一条新数据，也就是说，一条 REPLACE 语句有时候等价于一条。

#### 触发器分类
```
INSERT 型触发器：插入某一行时激活触发器，可能通过 INSERT、LOAD DATA、REPLACE 语句触发；
UPDATE 型触发器：更改某一行时激活触发器，可能通过 UPDATE 语句触发；
DELETE 型触发器：删除某一行时激活触发器，可能通过 DELETE、REPLACE 语句触发。

再结合触发时机则总共可以分为6中触发器
即：BEFORE INSERT、BEFORE UPDATE、BEFORE DELETE、AFTER INSERT、AFTER UPDATE、AFTER DELETE。
```

begin ... end
> 语法为：
begin
[statement_list]
end

> statement_list 代表一个或多个语句的列表，列表内的每条语句都必须用分号（;）来结尾。
而在MySQL中，分号是语句结束的标识符，遇到分号表示该段语句已经结束，MySQL可以开始执行了。因此，解释器遇到statement_list 中的分号后就开始执行，然后会报出错误，因为没有找到和 BEGIN 匹配的 END。

delemiter 定义定界符
> 这时就会用到 delemiter 命令（delemiter 是定界符，分隔符的意思），它是一条命令，不需要语句结束标识，语法为：
delemiter new_delemiter
new_delemiter 可以设为1个或多个长度的符号，默认的是分号（;），我们可以把它修改为其他符号，如$：
delemiter $
在这之后的语句，以分号结束，解释器不会有什么反应，只有遇到了$，才认为是语句结束。注意，使用完之后，我们还应该记得把它给修改回来。

#### 完整实例
```
delemiter $
create trigger tri_stuInsert after insert
on student for each row
begin
declare c int;
set c = (select stuCount from class where classID=new.classID);
update class set stuCount = c + 1 where classID = new.classID;
end$
delemiter ;
```

###### declare定义begig-end中的临时变量
> `DECLARE var_name[,...] type [DEFAULT value]`
对变量赋值采用 set 语句，语法为：
`set var_name = expr [,var_name = expr] ...`

###### new与old详解
> MySQL 中定义了 NEW 和 OLD，用来表示触发器的所在表中，触发了触发器的那一行数据。
具体地：
在 INSERT 型触发器中，NEW 用来表示将要（BEFORE）或已经（AFTER）插入的新数据；
在 UPDATE 型触发器中，OLD 用来表示将要或已经被修改的原数据，NEW 用来表示将要或已经修改为的新数据；
在 DELETE 型触发器中，OLD 用来表示将要或已经被删除的原数据；
使用方法： NEW.columnName （columnName 为相应数据表某一列名）
另外，OLD 是只读的，而 NEW 则可以在触发器中使用 SET 赋值，这样不会再次触发触发器，造成循环调用（如每插入一个学生前，都在其学号前加“2013”）。


###### 查看触发器，和查看数据表一样
> 
`SHOW TRIGGERS [FROM schema_name];`

删除触发器，一个数据表对应对个触发器，即触发器是和数据表绑定的
> 
`DROP TRIGGER [IF EXISTS] [schema_name.]trigger_name`


###### 触发器的执行顺序
> 我们建立的数据库一般都是 InnoDB 数据库，其上建立的表是事务性表，也就是事务安全的。这时，若SQL语句或触发器执行失败，MySQL 会回滚事务，有：
1. 如果 BEFORE 触发器执行失败，SQL 无法正确执行。
2. SQL 执行失败时，AFTER 型触发器不会触发。
3. AFTER 类型的触发器执行失败，SQL 会回滚


## 5-存储过程

#### 特点
> 
1. 一组为了完成特定功能的SQL 语句集，存储在数据库中，经过第一次编译后再次调用不需要再次编译，用户通过指定存储过程的名字并给出参数（如果该存储过程带有参数）来执行它。
2. 优点：增加SQL语言的功能，灵活性和安全性。执行速度快，减少网络传输。缺点在于可移植性差。
3. 和函数区别：存储过程是独立的部分，而函数作为查询语句的一部分，嵌入在SQL中，执行速度更快。
4. 游标：用于定位结果集的行，一种能够从包含多条数据记录的结果集中每次提取一条记录的机制。

#### 1-创建存储过程
> 
```
create procedure sp_name()
begin
[statement_list]
end
```

#### 2-调用存储过程
> `call sp_name()`

#### 3-删除存储过程
> `drop procedure sp_name;`
注意不能在一个存储过程中删除另一个存储过程。

#### 4-查看存储过程
> 
`show procedure status;`
`show create procedure sp_name;`

#### 5-存储过程实例
>语法以及内置函数介绍以及使用：http://www.cnblogs.com/cxxjohnson/p/5965194.html

###### 5.1-最简单的一个存储过程
```
/********************* 创建表 *****************************/
delimiter //

DROP TABLE if exists test //
CREATE TABLE test(
  id int(11) NULL
) //

/********************** 最简单的一个存储过程 **********************/
drop procedure if exists sp//
CREATE PROCEDURE sp() //
 
call sp()//

```

###### 5.2-带输入参数的存储过程
```
/********************* 带输入参数的存储过程  *******************/

drop procedure if exists sp1 //

create procedure sp1(in p int)
comment 'insert into a int value'
begin
  /* 定义一个整形变量 */
  declare v1 int;
  
  /* 将输入参数的值赋给变量 */
  set v1 = p;
  
  /* 执行插入操作 */
  insert into test(id) values(v1);
end
//

/* 调用这个存储过程  */
call sp1(1)//

/* 去数据库查看调用之后的结果 */
select * from test//
```

###### 5.3-带输出参数的存储过程
```
 /****************** 带输出参数的存储过程 ************************/

drop procedure if exists sp2 //
create procedure sp2(out p int)
/*这里的DETERMINISTIC子句表示输入和输出的值都是确定的,不会再改变.我一同事说目前mysql并没有实现该功能,因此加不加都是NOT DETERMINISTIC的*/
DETERMINISTIC
begin
  select max(id) into p from test;
end
//

/* 调用该存储过程，注意：输出参数必须是一个带@符号的变量 */
call sp2(@pv)//

/* 查询刚刚在存储过程中使用到的变量 */
select @pv//                                                    
```

###### 5.4-带输入和输出参数的存储过程 
```
/******************** 带输入和输出参数的存储过程 ***********************/

drop procedure if exists sp3 //
create procedure sp3(in p1 int , out p2 int)
begin

  if p1 = 1 then
    /* 用@符号加变量名的方式定义一个变量，与declare类似 */
    set @v = 10;
  else
    set @v = 20;
  end if;
  
  /* 语句体内可以执行多条sql，但必须以分号分隔 */
  insert into test(id) values(@v);
  select max(id) into p2 from test;
  
end
//

/* 调用该存储过程，注意：输入参数是一个值，而输出参数则必须是一个带@符号的变量 */
call sp3(1,@ret)//

select @ret//
```

###### 5.5-既做输入又做输出参数的存储过程
```
/***************** 既做输入又做输出参数的存储过程 **************************/

drop procedure if exists sp4 //
create procedure sp4(inout p4 int)
begin
   if p4 = 4 then
      set @pg = 400;
   else
      set @pg = 500;
   end if; 
   
   select @pg;
   
end//

call sp4(@pp)//

/* 这里需要先设置一个已赋值的变量，然后再作为参数传入 */
set @pp = 4//
call sp4(@pp)//
select @pp //
```


###### 5.6-带游标的存储过程
> http://blog.csdn.net/rdarda/article/details/7881648/



```
DELIMITER $$  
  
DROP PROCEDURE IF EXISTS `test`.`CursorProc` $$  
CREATE PROCEDURE `test`.`CursorProc` ()  
BEGIN  
 DECLARE  no_more_products, quantity_in_stock INT DEFAULT 0;  
 DECLARE  prd_code VARCHAR(255);  
 /*第一步: 定义游标 Delcare a cursor,首先这里对游标进行定义*/ 
 DECLARE  cur_product CURSOR FOR   SELECT code FROM products;   
 /*然后定义异常处理 "not found" occur,just continue,这个是个条件处理,针对NOT FOUND的条件*/  
 DECLARE  CONTINUE HANDLER FOR NOT FOUND  SET  no_more_products = 1; 
 /* for  loggging information 创建个临时表格来保持*/  
 CREATE TEMPORARY TABLE infologs (  
 Id int(11) NOT NULL AUTO_INCREMENT,  
 Msg varchar(255) NOT NULL,  
 PRIMARY KEY (Id)  
 );  
  
 /*第二步: 打开游标 Open the cursor */  
 OPEN  cur_product; 
 /*第三步: now you can Fetch the row 把第一行数据写入变量中,游标也随之指向了记录的第一行*/  
 FETCH  cur_product INTO prd_code; 
  
 REPEAT  
  
 SELECT  quantity INTO quantity_in_stock  
 FROM  products  
 WHERE  code = prd_code;  
   
 IF  quantity_in_stock < 100 THEN  
 INSERT  INTO infologs(msg)  
 VALUES  (prd_code);  
 END  IF;  
 FETCH  cur_product INTO prd_code;  
  
 UNTIL  no_more_products = 1  
 END REPEAT;  
 
 /*最后: cursor need be closed 用完后记得用CLOSE把资源释放掉*/  
 CLOSE  cur_product;  
 SELECT *  FROM infologs;  
 DROP TABLE  infologs;  
END $$  
  
DELIMITER ;  
```

## 6-创建分区表
> http://blog.csdn.net/jhq0113/article/details/44593511


#### 系统一共分为5种：
> 
- Range（范围）–这种模式允许将数据划分不同范围。例如可以将一个表通过年份划分成若干个分区。
- Hash（哈希）–这中模式允许通过对表的一个或多个列的Hash Key进行计算，最后通过这个Hash码不同数值对应的数据区域进行分区。例如可以建立一个对表主键进行分区的表。
- Key（键值）-上面Hash模式的一种延伸，这里的Hash Key是MySQL系统产生的。
- List（预定义列表）–这种模式允许系统通过预定义的列表的值来对数据进行分割。
- Composite（复合模式） –以上模式的组合使用　

#### 分区表的特性
> 
1. 而分区是将数据分段划分在多个位置存放，可以是同一块磁盘也可以跨越多个磁盘。分区后，表面上还是一张表，但数据散列到多个位置了。app读写的时候操作的还是大表名字，db自动去组织分区的数据。
2. 虽然能提高mysql的性高，但表的分区并不是分的越多越好，当表的分区太多时找分区又是一个性能的瓶颈了，建议在200个分区以内。

#### 6.1-Range
1-创建表时指定分区
```
CREATE TABLE IF NOT EXISTS `user` (  
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',  
   `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',  
   `sex` int(1) NOT NULL DEFAULT '0' COMMENT '0为男，1为女',  
   PRIMARY KEY (`id`)  
 ) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1  
 PARTITION BY RANGE (id) (  
     PARTITION p0 VALUES LESS THAN (3),  
     PARTITION p1 VALUES LESS THAN (6),  
     PARTITION p2 VALUES LESS THAN (9),  
     PARTITION p3 VALUES LESS THAN (12),  
     PARTITION p4 VALUES LESS THAN MAXVALUE  
 );  
```

2-对现有表进行分区
> 自动按照规则将表中的数据分配到相应的分区总中

```
alter table user partition by RANGE(id)  
 (PARTITION p1 VALUES less than (1),  
 PARTITION p2 VALUES less than (5),  
 PARTITION p3 VALUES less than MAXVALUE);  
```

3-删除分区
> 注意删除一个分区将会把这个分区的所有数据都删除（即对应的是物理分区而不是逻辑分区）

`alter table user drop partition p4;`

新增分区
`alter table user add partition(partition p4 values less than MAXVALUE);`


#### 6.2-List
> 创建list分区时，如果有主键的话，分区时主键必须在其中，不然就会报错。如果表中没有主键，分区就没有什么限制。

```
//这种方式失败  
CREATE TABLE IF NOT EXISTS `list_part` (  
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',  
   `province_id` int(2) NOT NULL DEFAULT 0 COMMENT '省',  
   `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',  
   `sex` int(1) NOT NULL DEFAULT '0' COMMENT '0为男，1为女',  
   PRIMARY KEY (`id`)  
 ) ENGINE=INNODB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1  
 PARTITION BY LIST (province_id) (  
     PARTITION p0 VALUES IN (1,2,3,4,5,6,7,8),  
     PARTITION p1 VALUES IN (9,10,11,12,16,21),  
     PARTITION p2 VALUES IN (13,14,15,19),  
     PARTITION p3 VALUES IN (17,18,20,22,23,24)  
 );  
ERROR 1503 (HY000): A PRIMARY KEY must include all columns in the table's partitioning function 
 
//这种方式成功 
CREATE TABLE IF NOT EXISTS `list_part` ( 
   `id` int(11) NOT NULL  COMMENT '用户ID', 
   `province_id` int(2) NOT NULL DEFAULT 0 COMMENT '省', 
   `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称', 
   `sex` int(1) NOT NULL DEFAULT '0' COMMENT '0为男，1为女'  
 ) ENGINE=INNODB  DEFAULT CHARSET=utf8  
 PARTITION BY LIST (province_id) (  
     PARTITION p0 VALUES IN (1,2,3,4,5,6,7,8),  
     PARTITION p1 VALUES IN (9,10,11,12,16,21),  
     PARTITION p2 VALUES IN (13,14,15,19),  
     PARTITION p3 VALUES IN (17,18,20,22,23,24)  
 );  
```
新增分区
`alter table list_part add partition(partition p4 values in (25,26,28));`

#### 6.3-Hash分区
```
CREATE TABLE IF NOT EXISTS `hash_part` (  
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论ID',  
   `comment` varchar(1000) NOT NULL DEFAULT '' COMMENT '评论',  
   `ip` varchar(25) NOT NULL DEFAULT '' COMMENT '来源IP',  
   PRIMARY KEY (`id`)  
 ) ENGINE=INNODB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1  
 PARTITION BY HASH(id)  
 PARTITIONS 3;  # 需要指定分区的数量
```
hash分区不能添加新分区，只能重新指定分区大小，此时数据也会重新分配
`alter table key_part add partition partitions 4;`

#### 6.4-Key分区

```
CREATE TABLE IF NOT EXISTS `key_part` (  
   `news_id` int(11) NOT NULL  COMMENT '新闻ID',  
   `content` varchar(1000) NOT NULL DEFAULT '' COMMENT '新闻内容',  
   `u_id` varchar(25) NOT NULL DEFAULT '' COMMENT '来源IP',  
   `create_time` DATE NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '时间'  
 ) ENGINE=INNODB  DEFAULT CHARSET=utf8  
 PARTITION BY LINEAR HASH(YEAR(create_time))  
 PARTITIONS 3;  
```

#### 6.5-子分区
> 子分区是分区表中每个分区的再次分割，子分区既可以使用HASH希分区，也可以使用KEY分区。这 也被称为复合分区（composite partitioning）。
- 如果一个分区中创建了子分区，其他分区也要有子分区
- 如果创建了了分区，每个分区中的子分区数必有相同
- 同一分区内的子分区，名字不相同，不同分区内的子分区名子可以相同（5.1.50不适用）

```
CREATE TABLE `msgss` (  
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表主键',  
  `sender` int(10) unsigned NOT NULL COMMENT '发送者ID',  
  `reciver` int(10) unsigned NOT NULL COMMENT '接收者ID',  
  `msg_type` tinyint(3) unsigned NOT NULL COMMENT '消息类型',  
  `msg` varchar(225) NOT NULL COMMENT '消息内容',  
  `atime` int(10) unsigned NOT NULL COMMENT '发送时间',  
  `sub_id` tinyint(3) unsigned NOT NULL COMMENT '部门ID',  
  PRIMARY KEY (`id`,`atime`,`sub_id`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8  
/*********分区信息**************/  
PARTITION BY RANGE (atime) SUBPARTITION BY HASH (sub_id)   
(  
        PARTITION t0 VALUES LESS THAN(1451577600)  
        (  
            SUBPARTITION s0,  
            SUBPARTITION s1,  
            SUBPARTITION s2,  
            SUBPARTITION s3,  
            SUBPARTITION s4,  
            SUBPARTITION s5  
        ),  
        PARTITION t1 VALUES LESS THAN(1483200000)  
        (  
            SUBPARTITION s6,  
            SUBPARTITION s7,  
            SUBPARTITION s8,  
            SUBPARTITION s9,  
            SUBPARTITION s10,  
            SUBPARTITION s11  
        ),  
        PARTITION t2 VALUES LESS THAN MAXVALUE  
        (  
            SUBPARTITION s12,  
            SUBPARTITION s13,  
            SUBPARTITION s14,  
            SUBPARTITION s15,  
            SUBPARTITION s16,  
            SUBPARTITION s17  
        )  
);  
```

#### 6.6-指定分区的存储位置
> 通过在分区内指定 `data directory`和`index directory`来指定数据和索引存放的位置
1. MyISAM引擎：因为MyISAM创建的索引是非聚集索引，数据和索引单独存放，所以数据和索引都需要指定存放位置
2. InnoDB引擎：因为InnoDB是聚集索引，所以只需通过 `data directory`来指定数据存放的位置。


以MyISAM为例
```
engine='MyISAM'
PARTITION BY RANGE(YEAR(purchased)) SUBPARTITION BY HASH(TO_DAYS(purchased))
(
 	PARTITION p0 VALUES LESS THAN (1990)
	(
 		SUBPARTITION s0a 
		DATA DIRECTORY = '/home/bzuo/d1' 
		INDEX DIRECTORY = '/home/bzuo/d1',
 		SUBPARTITION s0b 
 		DATA DIRECTORY = '/home/bzuo/d2' 
 		INDEX DIRECTORY = '/home/bzuo/d2'
	)
 );
```

#### 6.7-合并分区
> 通过reorganize into 关键字来实现合并分区

下面的SQL，将p201001 - p201009 合并为3个分区p2010Q1 - p2010Q3

```
ALTER TABLE sale_data
REORGANIZE PARTITION p201001,p201002,p201003,
p201004,p201005,p201006,
p201007,p201008,p201009 INTO
(
PARTITION p2010Q1 VALUES LESS THAN (201004),
PARTITION p2010Q2 VALUES LESS THAN (201007),
PARTITION p2010Q3 VALUES LESS THAN (201010)
);
```

## 7-创建视图
> 优点：重用SQL，简化数据库查询，提高数据库的**安全性和逻辑独立性**。

1. 从数据库中的基本表中选取出来的逻辑窗口，虚表，本身并不存在。将表与表之间的复杂操作和搜索条件对用户不可见，用户只需要对视图进行查询即可。但是不能提高查询效率。
减少数据库和应用程序的耦合性，即应用程序只对应视图逻辑字段，对于视图之下的真实数据表的结构改变（如增加一列等），应用程序是无感的
2. 同时由于底层数据表可能包含一些敏感的数据，但是又不想对某些程序开放这些敏感的数据，则可以在创建视图的时候忽略这些敏感字段，这样用户程序是
查询不到敏感数据的，对不同权限的查询程序创建不同的视图，可以做到权限管理

```
use test; # 创建视图和创建普通表是一样，需要指定数据库，当然也可以不指定则使用当前数据库
create view view_A_B(id,name,sex,age,department) 
as select A.id,A.name,A.sex,A.age,B.department 
from test.A A, test.B B
where A.id = B.id;
```

需要注意的是视图虽然是虚表，但是其逻辑地位和表是一样的，既不允许视图和表的重名，存在于指定数据库当中

#### 查看视图/表结构
```
describe view_name/table_name;

# 等价于 show columns from view_name/table_name，是其简写形式
```

#### 显示创建视图/表的SQL语句

```
show create view/table view_name/table_name;

```

#### 修改视图结构

```
alter view view_name(id,name)
as select id,name 
from test.A;
```

#### 更新视图中的数据
> 视图是虚拟表，本身不包含数据，而是视图会将数据更新映射到底层的数据表；
视图的数据行和底层的数据表行之间必须具有一一对应的关系时，视图才是可更改的，否则是不可更改的，具体如下：
视图中存在聚合函数，distinct、group by、union、having 、join等。
所以视图的限制是很多的，在很多情况下，视图只是用来查询数据的虚拟表，而不是要通过视图来更新数据，如果堆不可更改视图进行更新操作
很可能造成数据更新失败

#### 删除视图

```
drop view if exists view_name;
```

## 8-创建主键和外键
> MySQL创建关联表可以理解为是两个表之间有个外键关系，但这两个表必须满足三个条件
1. 两个表必须是InnoDB数据引擎
2. 使用在外键关系的域必须为索引型(Index)
3. 使用在外键关系的域必须与数据类型相似


#### 1、建立s_user表
```
create table s_user(
       u_id int auto_increment primary key,
       u_name varchar(15),
       u_pwd varchar(15),
       u_truename varchar(20),
        u_role varchar(6),
       u_email varchar(30)
)
```

#### 2、插入几条数据
```
insert into s_user values
       (1,"wangc","aaaaaa","wangchao","buyer","wang@163.com"),
      (2,"huangfp","bbbbbb","huangfp","seller","huang@126.com"),
      (3,"zhang3","cccccc","zhangsan","buyer","zhang@163.com"),
      (4,"li4","dddddd","lisi","seller","li@1256.com")

```

#### 3、建立s_orderform表
```
create table s_orderform(
         o_id int auto_increment primary key,
         o_buyer_id int,
         o_seller_id int,
         o_totalprices double,
         o_state varchar(50),
         o_information varchar(200),
         foreign key(o_buyer_id) references s_user(u_id),      #外链到s_user表的u_id字段
         foreign key(o_seller_id) references s_user(u_id)      #外链到s_user表的u_id字段
)
```

外键起到约束和保证数据完整性的作用
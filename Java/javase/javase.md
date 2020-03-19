### Lambda

#### Lambda表达式介绍

​		Lambda表达式是Java8中最重要的新功能之一，使用Lambda表达式可以替代只有一个抽象函数的接口实现，告别匿名内部类，代码看起来更简洁移动。Lambda表达式同时还提升了对集合、框架的迭代、便利、过滤数据的操作。

#### Lambda表达式的应用场景

​		任何有**<font color="red">函数式接口</font>**的地方

##### 函数式接口

​		只有<font color="red">**<strong>一个</strong>**</font>抽象方法（Object类中的方法除外）的接口是函数式接口

​		@FunctionalInterface

##### 常用的函数式接口

- Supplier			代表一个输出
- Consumer         代表一个输入
- Function           代表一个输入，一个输出（一般输入和输出是不同类型的）
- UnaryOperator  代表一个输入，一个输出（输入和输出是相同类型的）
- BiFunction       代表两个输入，一个输出（一般输入和输出是不同类型的）
- BinaryOperator   代表两个输入，一个输出（输入和输出是相同类型的）

<strong>在Java中定义了一系列的函数式接口，用于接收后面的逻辑，但对输入和输出有要求</strong>

#### 方法的引用

​		方法的引用是用来直接访问类或实例的已经存在的方法或者构造方法。方法引用提供了一种引用而不执行方法的方式，如果抽象方法的实现恰好可以使用调用另外一个方法来实现，就<strong><font color="red">有可能</font></strong>可以使用方法引用

##### 方法引用的分类

|     类型     |        语法        |         对应的Lambda表达式         |
| :----------: | :----------------: | :--------------------------------: |
| 静态方法引用 | 类名::staticMethod |  (args)->类名.staticMethod(args)   |
| 实例方法引用 |  inst::instMethod  |   (args)->inst.instMethod(args)    |
| 对象方法引用 |  类名::instMethod  | (inst.args)->类名.instMethod(args) |
| 构造方法引用 |     类名::new      |       (args)->new 类名(args)       |

​	System.out  -  标准输出流

###### 静态方法引用

​		如果函数式接口的实现恰好可以通过调用一个静态方法实现，那么就可以使用静态方法引用

###### 实例方法引用

​		如果函数式接口的实现恰好可以通过调用一个实例的实例方法来实现，那么就可以使用实例方法引用

###### 对象方法的引用

​		抽象方法的第一个参数类型刚好是实例方法的类型，抽象方法剩余的参数恰好可以当作实例方法的参数。如果函数式接口的实现能由上面说的实例方法调用来实现的话，那么就可以使用对象方法引用

###### 构造方法引用

​		如果函数式接口的实现恰好可以通过调用一个类的构造方法实现，呢么就可以使用构造方法引用



---

### Stream API

#### 		Stream

- A sequence of elements supporting sequential and parallel aggregate operations
- Stream是一组用来处理数组、集合的API
- Java 8 之所以费这么大功夫引入函数式编程，原因有二：
  - 代码简洁。函数式编程写出的代码简洁且意图明显，使用stream接口让你从此告别for循环。
  - 多核友好，Java函数式编程使得编写并行程序从未如此简单，你需要的全部就是调用一些parallel()方法。

#### Stream特性

- 不是数据接口，没有存储结构
- 不支持索引访问
- 延迟计算
- 支持并行
- 很容易生成数组或集合（List，Set）
- 支持过滤，查找，转换，汇总

#### Stream运行机制

- Stream分为源source，中间操作，终止操作
- 流的源可以是一个数组、一个集合、一个生成器方法，一个I/O通道等等
- 一个流可以有零个或者多个中间操作，每个中间操作都会返回一个新的流，供下一个操作使用。一个流只会有一个终止操作
- Stream只有遇到终止操作，它的源才开始执行遍历操作

#### Stream的创建方式

- 通过数组的方式生成：Stream.of(new Array());
- 通过集合的方式来生成：list.stream();
- 通过Stream .generate()
- 使用iterator
- 其他API创建方式     IntStream          str.chars()

##### 中间操作

如果调用方法之后返回的结果是Stream对象，就意味着是一个中间操作

#### Stream常用API
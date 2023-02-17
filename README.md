# iMusic 音乐系统

## Attention

+ 本项目已经部署在了[此链接](https://yanyc.cn/mymusic) ,您可以快速查看网站demo，与您在本地部署的效果一模一样，但受限于服务器带宽限制，第一次访问可能需要较长时间。

## To Start

+ 请确保您的设备安装了`JSE`环境，JDK版本高于`8.0`。  
+ 你可以使用以下的语句查看java版本 `java --version`
+ 请确保您安装了`Tomcat`容器

+ 文件的目录包括
  + `src`: 本项目的源码
  + `target`: 通过编译后形成的文件，`java`源码在这都会被编译为`.class`文件
  + `doc`: `java`类的定义文档
  
## To compile

+ 程序已经提前编辑成`.class`文件，存放在`target`文件夹中。  
+ 本项目基于Maven， 若要单独完成编译，请参考maven教程，`https://www.runoob.com/maven/maven-tutorial.html` 。

## To Run

+ 若要在本地部署本项目，请将`target`文件夹中的`mymusic-web-1.0-RELEASE.war`移动至`Tomcat`安装目录的`webapp`文件夹中，并正常启动Tomcat，访问[此链接](http://localhost:8080/mymusic-web-1.0-RELEASE/)
+ 程序已经提前打好了war包，包含了所有依赖，您只需运行war包即可。
+ 因为本项目使用了开源CDN，在访问网页时请务必保证网络连接，如遇页面错位，刷新一次即可。
+ 第一次访问项目的时间可能较长，因为受限于CDN带宽的因素，运行一次后恢复正常。

## To Know More

+ 本项目提前制作了`Javadoc`, 您可以在`doc`文件夹下，寻找`index.html`，查看本项目的相关类的定义和解释。

## 初始用户

+ 强烈建议您注册一个账号进行体验。
+ 管理员：

  ```
  账号：yycyycyyc
  密码：12345678
  ```
  
+ 普通用户
  ``` 
  账号：userabc
  密码：12345678
  ```
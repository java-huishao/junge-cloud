FROM openjdk:8-jdk-alpine

VOLUME /tmp

ENV LANG en_US.UTF-8

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN echo 'Asia/Shanghai' >/etc/timezone

ADD ./target/lehe-gateway.jar app.jar
# Image创建容器时的初始化内存，最大内存，及启动时使用的profile. -c为清除以前启动的数据
EXPOSE 8443
#-Xms768m：设置JVM初始堆内存为768m。此值可以设置与-Xmx相同，以避免每次垃圾回收完成后JVM重新分配内存。
#-Xmx768m：设置JVM最大堆内存为768m。
#-Xss128k：设置每个线程的栈大小。JDK5.0以后每个线程栈大小为1M，之前每个线程栈大小为256K。应当根据应用的线程所需内存大小进行调整。在相同物理内存下，减小这个值能生成更多的线程。但是操作系统对一个进程内的线程数还是有限制的，不能无限生成，经验值在3000~5000左右。需要注意的是：当这个值被设置的较大（例如>2MB）时将会在很大程度上降低系统的性能。
#-Xmn2g：设置年轻代大小为2G。在整个堆内存大小确定的情况下，增大年轻代将会减小年老代，反之亦然。此值关系到JVM垃圾回收，对系统性能影响较大，官方推荐配置为整个堆大小的3/8。
#-XX:NewSize=1024m：设置年轻代初始值为1024M。
#-XX:MaxNewSize=1024m：设置年轻代最大值为1024M。
#-XX:PermSize=256m：设置持久代初始值为256M。
#-XX:MaxPermSize=256m：设置持久代最大值为256M。
#-XX:NewRatio=4：设置年轻代（包括1个Eden和2个Survivor区）与年老代的比值。表示年轻代比年老代为1:4。
#-XX:SurvivorRatio=4：设置年轻代中Eden区与Survivor区的比值。表示2个Survivor区（JVM堆内存年轻代中默认有2个大小相等的Survivor区）与1个Eden区的比值为2:4，即1个Survivor区占整个年轻代大小的1/6。
#-XX:MaxTenuringThreshold=7：表示一个对象如果在Survivor区（救助空间）移动了7次还没有被垃圾回收就进入年老代。如果设置为0的话，则年轻代对象不经过Survivor区，直接进入年老代，对于需要大量常驻内存的应用，这样做可以提高效率。如果将此值设置为一个较大值，则年轻代对象会在Survivor区进行多次复制，这样可以增加对象在年轻代存活时间，增加对象在年轻代被垃圾回收的概率，减少Full GC的频率，这样做可以在某种程度上提高服务稳定性。
ENTRYPOINT ["java","-server","-jar","-Dfile.encoding=UTF-8","-Xdebug -Xrunjdwp:transport=dt_socket,address=8543,server=y,suspend=y","-Djava.security.egd=file:/dev/./urandom","/app.jar","-c"]
#!/bin/bash
cd `dirname $0`

#��ǰ·��
BIN_DIR=`pwd`

#����һ��·��
cd ..
DEPLOY_DIR=`pwd`
echo $DEPLOY_DIR

#�����ļ�·��
CONF_DIR=$DEPLOY_DIR/
#��־���·��
LOGS_DIR=$DEPLOY_DIR/logs

# ���JDK��������û��д��ȫ��Ҫ������¼���
# JAVA_HOME=/opt/java/jdk1.6.0_45
# PATH=$JAVA_HOME/bin:$PATH
# export JAVA_HOME
# export PATH

#��dubbo.propertiesȡ��Ӧ�������˿ںţ��˿���
SERVER_NAME=`sed '/dubbo.application.name/!d;s/.*=//' dubbo.properties | tr -d '\r'`
SERVER_PROTOCOL_NAME=`sed '/dubbo.protocol.name/!d;s/.*=//' dubbo.properties | tr -d '\r'`
SERVER_PROTOCOL_PORT=`sed '/dubbo.protocol.port/!d;s/.*=//' dubbo.properties | tr -d '\r'`

#Ӧ����Ϊ�յĻ���ȡ��ǰϵͳ��
if [ -z "$SERVER_NAME" ]; then
    echo "SERVER_NAME is empty"
    SERVER_NAME=`hostname`
fi

#���������ļ�·��ȥ���ҵ�ǰ�Ƿ�����dubboӦ����������
APP_PID=`ps -ef -ww | grep "java" | grep " -DappName=$SERVER_NAME " | awk '{print $2}'`
echo "SERVER_NAME: $SERVER_NAME"
echo "SERVER_PROTOCOL_NAME: $SERVER_PROTOCOL_NAME"
echo "SERVER_PROTOCOL_PORT: $SERVER_PROTOCOL_PORT"
echo "APP_PID: $APP_PID" 

#APP_PID��Ϊ�գ�˵��Ӧ����������ֱ���˳�
if [ -n "$APP_PID" ]; then
    echo "ERROR: The $SERVER_NAME already started!"
    echo "PID: $APP_PID"
    exit 1
fi

#���˿��Ƿ�ռ��
if [ -n "$SERVER_PROTOCOL_PORT" ]; then
    SERVER_PORT_COUNT=`netstat -tln | grep $SERVER_PROTOCOL_PORT | wc -l`
    if [ $SERVER_PORT_COUNT -gt 0 ]; then
        echo "ERROR: The $SERVER_NAME port $SERVER_PROTOCOL_PORT already used!"
        exit 1
    fi
fi


#���logsĿ¼�����ڣ��ʹ���һ��
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi

echo "LOGS_DIR :$LOGS_DIR"

#����̨��־����ռ�λ��
STDOUT_FILE=$LOGS_DIR/stdout.log

#����jar��Ŀ¼
LIB_DIR=$DEPLOY_DIR/lib

#�������jar�ļ����ƣ�ƴ����lib��·��Ȼ�����
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

#-DappNameָ��Ӧ����
JAVA_OPTS="-DappName=$SERVER_NAME -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Ddubbo.shutdown.hook=true"

#����ģʽ
JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi
JAVA_JMX_OPTS=""
if [ "$1" = "jmx" ]; then
    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi


#���Ƚ�java�汾����Ϣ�������׼�����Ȼ����ҡ�64-bit����Ϣ��Ŀ�ľ����ж�jdk�汾�Ƿ�Ϊ64λ

JAVA_MEM_OPTS=""
BITS=`java -version 2>&1 | grep -i 64-bit`

#JVM���������������������Ӧ�����е���
JAVA_MEM_SIZE_OPTS="-Xmx768m -Xms378m -Xmn256m -XX:PermSize=64m -XX:MaxPermSize=256M -Xss512k"

#����32λ��64λ���ò�ͬ������java�������ղ���������Ӧ�����е���
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -server $JAVA_MEM_SIZE_OPTS -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
else
    JAVA_MEM_OPTS=" -server $JAVA_MEM_SIZE_OPTS -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi

echo -e "Starting the $SERVER_NAME ...\c"
echo "����������java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS $JAVA_PROPERTIES_OPTS -classpath $CONF_DIR:$LIB_JARS com.alibaba.dubbo.container.Main"

#ͨ��java������������ͬʱ������Ϊ��̨����ִ�С�
nohup java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -classpath $CONF_DIR:$LIB_JARS com.alibaba.dubbo.container.Main > $STDOUT_FILE 2>&1 &

#˯��һ���ټ��Ӧ���Ƿ���������������ע�ʹ򿪵Ļ����Ͱ����������һ��ע�͵�
#sleep 1
#APP_PID=`ps -f | grep java | grep "$CONF_DIR" |awk '{print $2}'`

#if [ -z "$APP_PID" ]; then
    #echo "START APP FAIL!"
    #echo "STDOUT: $STDOUT_FILE"
    #exit 1
#fi

#echo "START  SUCCESSED APP_PID: $APP_PID"
#echo "STDOUT: $STDOUT_FILE"


#grep -c ��ֹ�����Ľ�������ת�����ƥ��Ľ������������������OK�ĸ�����
COUNT=0
while [ $COUNT -lt 1 ]; do    
    echo -e ".\c"
    sleep 1
    echo "$SERVER_PROTOCOL_PORT"
    if [ -n "$SERVER_PROTOCOL_PORT" ]; then
    echo "$SERVER_PROTOCOL_NAME"
        if [ "$SERVER_PROTOCOL_NAME" == "dubbo" ]; then
            echo -e `echo status | nc -i 1 127.0.0.1 $SERVER_PROTOCOL_PORT` 
            COUNT=`echo status | nc -i 1 127.0.0.1 $SERVER_PROTOCOL_PORT | grep -c OK`
        else
            COUNT=`netstat -an | grep $SERVER_PROTOCOL_PORT | wc -l`
        fi
    else
        COUNT=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l`
    fi
    if [ $COUNT -gt 0 ]; then
        break
    fi
done
echo "OK!"
APP_PID=`ps -ef -ww | grep "java" | grep " -DappName=$SERVER_NAME " | awk '{print $2}'`
echo "START  SUCCESSED APP_PID: $APP_PID"
echo "STDOUT: $STDOUT_FILE"

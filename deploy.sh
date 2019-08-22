#!/bin/sh

# export JAVA_HOME=/home/bcz/programs/jdk1.8.0_144/
# export JRE_HOME=/home/bcz/programs/jdk1.8.0_144/jre/
# export PATH=$JAVA_HOME/bin:$PATH

SERVICE_NAME=movie-manage
PID_PATH_NAME=/tmp/movie-manage.pid
HOME=/home/app
PATH_TO_JAR=${HOME}/movie-manage-1.0.jar
PORT=8080
TARGET_JAR_NAME=${HOME}/web-*.jar

function check_port_use() {
    PID=`/usr/sbin/lsof -i :${PORT} | grep -v "PID" | awk '{print $2}'`
    if [ "${PID}" != "" ];
    then
       echo "1"
    else
       echo "0"
    fi
}

function wait_service_shutdown() {
    echo 'waiting service shutdown...'
    while [ $(check_port_use) -eq 1 ]
    do
        sleep 1
    done
}

function wait_service_running() {
    echo 'waiting service running...'
    while [ $(check_port_use) -eq 0 ]
    do
        sleep 1
    done
}

function check_jar_exists() {
    PWD=$(pwd)
    cd ${HOME}
    if [ ! -f ${PATH_TO_JAR} ]; then
        echo 'not found deploy file web.jar'
        exit -1
    fi
    cd ${PWD}
}

function check_target_file_exists() {
    PWD=$(pwd)
    cd ${HOME}
    COUNT=$(ls | grep web- | wc -l)
    if [ ${COUNT} -eq 0 ]; then
        echo 'not found file like web-*.jar'
        exit 1
    fi
    cd ${PWD}
}

function replace_origin_file_with_new_file() {
    PWD=$(pwd)
    cd ${HOME}
    TARGET_JAR_NAME=${HOME}/$(ls | grep web- | head -1)
    echo "move ${TARGET_JAR_NAME} to web.jar"
    if [ -f ${PATH_TO_JAR} ]; then
        rm ${PATH_TO_JAR}
    fi
    mv ${TARGET_JAR_NAME} ${PATH_TO_JAR}
    cd ${PWD}
}

function start_service() {
    if [ ! -f ${PID_PATH_NAME} ]; then
        CUR=$(pwd)
        # cd ${HOME}
        nohup java -jar ${PATH_TO_JAR} >/dev/null 2>&1 &
                    echo $! > ${PID_PATH_NAME}
        wait_service_running
        echo "${SERVICE_NAME} started ..."
        cd ${CUR}
    else
        echo "${SERVICE_NAME} is already running ..."
    fi
}

function stop_service() {
    if [ -f ${PID_PATH_NAME} ]; then
        PID=$(cat ${PID_PATH_NAME});
        echo "${SERVICE_NAME} stoping ..."
        kill ${PID};
        echo "${SERVICE_NAME} stopped ..."
        rm ${PID_PATH_NAME}
        wait_service_shutdown
    else
        echo "{$SERVICE_NAME} is not running ..."
    fi
}

case $1 in
    start)
        echo "Starting ${SERVICE_NAME} ..."
        check_jar_exists
        start_service
    ;;
    stop)
        stop_service
    ;;
    restart)
        stop_service
        start_service
    ;;
    deploy)
        check_target_file_exists
        stop_service
        replace_origin_file_with_new_file
        start_service
    ;;
esac